package com.example.OauthCrudExample.config;

import com.example.OauthCrudExample.entity.RegistrationSource;
import com.example.OauthCrudExample.entity.UserEntity;
import com.example.OauthCrudExample.entity.UserRole;
import com.example.OauthCrudExample.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    private final UserService userService;
    @Value("${frontend.url}")
    private  String frontendUrl;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {
        OAuth2AuthenticationToken oAuth2AuthenticationToken=(OAuth2AuthenticationToken) authentication;

        if("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId()))
        {
            // if user is from github what can we do  (get attributes emial ,name)
           DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
          Map<String,Object> attributes= principal.getAttributes();
            String email = Objects.toString(attributes.get("email"), "");
            String name = Objects.toString(attributes.get("name"), "");

            userService.findByEmail(email)
                    .ifPresentOrElse(user->{
                              DefaultOAuth2User newUser=new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRole().name())),
                                       attributes,"id");
                              Authentication securityAuth=new OAuth2AuthenticationToken(newUser,List.of(new SimpleGrantedAuthority(user.getRole().name())),
                                      oAuth2AuthenticationToken.getAuthorizedClientRegistrationId() );
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    },()->{
// if user is not present it had to be registered and then check authorize
                        UserEntity userEntity=new UserEntity();
                        userEntity.setRole(UserRole.ROLE_USER);
                        userEntity.setEmail(email);
                        userEntity.setName(name);
                        userEntity.setSource(RegistrationSource.GITHUB);
                        userService.save(userEntity);

                        DefaultOAuth2User newUser=new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                                attributes,"id");
                        Authentication securityAuth=new OAuth2AuthenticationToken(newUser,List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId() );
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    });
        }
        // redirect to our  custom after login
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontendUrl);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
