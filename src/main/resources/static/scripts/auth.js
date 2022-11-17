

function signIN(){
    const infoMessage = document.querySelector(".info_message");
    const login = document.querySelector('#login').value;
    const password = document.querySelector('#password').value;
    const data = {login, password};

    fetch('/login',{
        method: "POST",
        body: JSON.stringify(data),
        headers:{
            "Content-Type":"application/json"
        }
    }).then(response=>response.text())
        .then(data=>{
            const result = JSON.parse(data);
            console.log(result);
            localStorage.setItem('token',result['token']);
            fetch('/getUser',{
                method:"POST",
                headers:{
                    'Authorization': `Bearer ${result['token']}`
                }
            }).then(response=>response.text()).then(data=>{
                const resData = JSON.parse(data);
                localStorage.setItem("user_id", resData['id']);
                window.location.replace(window.location.origin);
            })
        }).catch(err=>{
        console.log(err);
        infoMessage.innerHTML = "Sign In error: " + err;
    })
}

function registration(){
    const infoMessage = document.querySelector(".info_message");
    const name = document.querySelector('#name').value;
    const login = document.querySelector('#login').value;
    const password = document.querySelector("#password").value;
    const reppas = document.querySelector("#reppass").value;

    if(password === reppas){

        const requestData = {name, login, password};
        fetch("/register",{
            method: "POST",
            body: JSON.stringify(requestData),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response=>response.text()).then(data=>{
            if(data == 'OK'){
                infoMessage.innerHTML = "Registration has been finished successfully";
                window.location.replace(window.location.origin);
            }
        }).catch(err=>{console.log("SignUp error: ", err);  infoMessage.innerHTML = "Sign Up error: " + err;});
    }else{
        infoMessage.innerHTML = "Sign In error";
        console.log("SignUp error ");
    }
}