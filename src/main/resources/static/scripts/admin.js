const rootPath = "E:/GIT/Music/";
const form = document.querySelector("form");

document.addEventListener("click",(e)=>{
    const token = localStorage.getItem("token");
   const target = e.target;
    if(target.classList.contains("save_btn")){
        e.preventDefault();

        const name = document.querySelector("#songName").value;
        const author = document.querySelector("#songAuthor").value;
        const album = document.querySelector("#songAlbum").value;
        const genre = document.querySelector("#songGenre").value;
        const source = rootPath +  document.querySelector("#songSource").files[0].name;
        const body = {
            name,author,album,genre,source,
            rating: 0
        };
        fetch("http://localhost:8083/songs",{
            method: "POST",
            body: JSON.stringify(body),
            headers: {
                "Content-Type":"application/json",
                'Authorization': `Bearer ${token}`,
            }
        }).then(response=>response.json())
            .then(data=>{
                console.log("Data: ", data);
                document.location.reload();
            })
            .catch(err=>{
                console.log(err);
            })
   } else if(target.classList.contains("song_button")){
        e.preventDefault();
        console.log("song_button");
        const id = e.target.getAttribute("src");
        fetch(`http://localhost:8083/songs/${id}`,{
            method: "DELETE",
            headers:{
                'Authorization': `Bearer ${token}`,
            }
        }).then(request=>request.json()).then(data=>{
            console.log(data);
            document.location.reload();
        }).catch(err=>console.log(err));
    }
});