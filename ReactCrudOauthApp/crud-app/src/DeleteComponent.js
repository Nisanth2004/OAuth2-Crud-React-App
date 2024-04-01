import {SERVER_URL} from "./Constant"
const DeleteComponent = ({id,handleClick}) => {
    function deleteCar()
    {
        const requestOptions={
            method:"DELETE",
            headers:{'Content-Type':'application/json'},
            credentials:'include'
            
          }
         let idObject={id}
          fetch(
            SERVER_URL+'/car/'+idObject.id,
            requestOptions
          ).then(async response=>{
            if(!response.ok)
            {
                console.log("Error Happened");
            }
    
            handleClick();
          })
        }

    
  return (
    <div>
      <button onClick={deleteCar}>Delete</button>
    </div>
  )
  }
export default DeleteComponent
