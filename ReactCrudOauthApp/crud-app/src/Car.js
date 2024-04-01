import CarForm from "./CarForm";
import {SERVER_URL} from "./Constant"
import {useEffect, useState} from 'react'
import DeleteComponent from "./DeleteComponent";
import EditComponent from "./EditComponent";


function Car()
{
    
    const[cars,setCars]=useState()

    const getCars=async()=>{
        const response=await fetch(
            SERVER_URL +'/car',
            {method:'GET',redirect:'follow',credentials:'include'}
        ).then((response)=>response);

        console.log(response)
        if(response.redirected)
            {
                document.location=response.url;
            }
            const data=await response.json()
       setCars(data)
    }

    useEffect(()=>{
        getCars()
    },[])

    function handleClick()
    {
        getCars();
    }
    return(
        <>
        <table>
  <tr>
    <th>Brand</th>
    <th>Model</th> 
    <th>Year</th>
  </tr>
  {cars && cars.map((car)=>(

 
  <tr key={car.id}>
    <td>{car.brand}</td>
    <td>{car.model}</td> 
    <td>{car.year}</td>
    <td><EditComponent handleClick={handleClick} car={car}/></td>
    <td><DeleteComponent handleClick={handleClick} id={car.id}/></td>
  </tr>
   ))}
 
</table>
<br/>
<CarForm handleClick={handleClick}/>
</>
    )
}
export default Car;