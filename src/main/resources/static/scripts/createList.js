const playlist = {
    name: "",
    songs:[]
}
const songs = [];
let ownerId = 2;

document.addEventListener('click',(e)=>{
    const token = localStorage.getItem("token");
    const object = e.target;
    const info_message = document.querySelector('.info_message');
    if(object.classList.contains("song_button")){
        e.preventDefault();
        const songId = object.getAttribute("src");
        if(!object.classList.contains("active")){
            songs.push(songId);
            object.classList.add("active");
        }else{
            const index = songs.indexOf(songId);
            songs.splice(index,1);
            object.classList.remove("active");
        }

    }else if(object.classList.contains("save_btn")){
        e.preventDefault();
        const name = document.querySelector("#playlistName").value;
        playlist.name = name;
        ownerId = localStorage.getItem("user_id");
        //console.log(playlist);
        fetch(`http://localhost:8083/playlists/${ownerId}`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(playlist)
        })
            .then(request=>{
                return request.text();
            })
            .then(data=>{
                const result = JSON.parse(data);
                addSongs(result['id']);
                document.location.reload();
            })
            .catch(err=>{
                info_message.innerHTML = `<p>${err}</p>`;
                console.log(err);
            });
    }
})

function addSongs(playlistId){
    const token = localStorage.getItem("token");
    for(let song of songs){
        fetch(`http://localhost:8083/playlists/${playlistId}/songs/${song}`,
            {
                method:"POST",
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }).catch(err=>console.log(err));
    }
}