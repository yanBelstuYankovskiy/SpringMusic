const searchBtn = document.querySelector('#search_button');
const searchInput = document.querySelector('#search_input');


searchInput.addEventListener('input',(e)=>{
    if(searchInput.value){
        console.log(searchInput.value);
        searchBtn.setAttribute("href",`/client/songs/${searchInput.value}`);
    }
})