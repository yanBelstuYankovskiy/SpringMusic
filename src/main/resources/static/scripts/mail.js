const button = document.querySelector('#mail_message');

button.addEventListener('click',(e)=>{
    const token = localStorage.getItem("token");
    fetch("http://localhost:8083/mail",{
        headers:{
            'Authorization': `Bearer ${token}`
        }
    }).catch(err=>console.log(err));
})