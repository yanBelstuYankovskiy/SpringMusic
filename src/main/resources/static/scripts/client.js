const audio = document.querySelector("audio");

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains("song_button")){
        console.log("add source");
        audio.setAttribute("src", `/listen/${e.target.getAttribute("src")}`);
    }
});