package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Service.PlaylistService;
import com.valko.SpringMusic.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class MainAppController {

    private String rootPath = "E:\\GIT\\Music\\";
    private String pathSuffix = ".mp3";

    @Autowired
    SongService songService;
    @Autowired
    PlaylistService playlistService;

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model){
        return "registration";
    }

    @GetMapping(value = "/client")
    public String Client(Model model){
        model.addAttribute("songs", songService.findAll());
        return "client";
    }

    @GetMapping(value = "/client/songs/{str}")
    public String Client(@PathVariable String str, Model model){
        model.addAttribute("songs", songService.findByIncludesStr(str));
        return "songs";
    }

    @GetMapping(value = "/client/create")
    public String CreatePlaylist(Model model){
        model.addAttribute("songs", songService.findAll());
        return "create_playlist";
    }

    @GetMapping(value = "/client/playlists")
    public String ClientPlaylists(Model model){
        model.addAttribute("playlists", playlistService.findAll());
        return "clientPlaylists";
    }

    @GetMapping(value = "/client/playlist/{id}")
    public String ClientPlaylistById(@PathVariable Long id, Model model) throws ResourceNotFoundException {
        Playlist playlist =  playlistService.findOnePlaylist(id);
        model.addAttribute("playlist_name",playlist.getName());
        model.addAttribute("songs",playlist.getSongs());
        return "playlist";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("songs", songService.findAll());
        return "admin";
    }


    @GetMapping(
            value = "/listen/{id}",
            produces = "audio/mp3"
    )
    @ResponseBody
    public FileSystemResource getSong(@PathVariable long id) throws ResourceNotFoundException {
        String src  = songService.findOneSongById(id).getSource();
        return new FileSystemResource(src);
    }
}
