import { SERVER_URL } from "./Constant";

function CarForm({handleClick})
{
    function handleSubmit(event)
    {
        event.preventDefault();
        
       const formData=new FormData(event.target);
       const formJson=Object.fromEntries(formData.entries());
      const requestOptions={
        method:"POST",
        headers:{'Content-Type':'application/json'},
        body:JSON.stringify(formJson),
        credentials:'include'
      }

      fetch(
        SERVER_URL+'/car',
        requestOptions
      ).then(async response=>{
        if(!response.ok)
        {
            console.log("Error Happened");
        }

        handleClick();
      })
    }
    return(
        <div>
          <form  onSubmit={handleSubmit}> 
            <input name="brand" placeholder="Enter Car Brand"/>
            <input name="model" placeholder="Enter Car Model"/>
            <input name="year" placeholder="Enter Car Year"/>
            <button type="submit">Add Car</button>

          </form>
        </div>
    )
}
export default CarForm;