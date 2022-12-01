package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Entity.Song;
import com.valko.SpringMusic.Exception.DuplicateException;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Service.PlaylistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Tag(name="Плэйлисты", description = "Контроллер работаетающий с плэйлистами")
@RestController
@RequestMapping(value = "/playlists")
public class PlaylistRestController {

    @Autowired
    private PlaylistService playlistService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Playlist> getPlaylists(){
        return playlistService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist getOnePlaylist(@PathVariable long id) throws ResourceNotFoundException {
        return  playlistService.findOnePlaylist(id);
    }

    @GetMapping(value = "/{id}/songs")
    @ResponseStatus(HttpStatus.OK)
    Set<Song> getSongsFromPlaylist(@PathVariable long id) throws ResourceNotFoundException {
        return playlistService.findSongsForPlaylist(id);
    }

    @PostMapping(value = "/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist savePlaylistWithOwner(@RequestBody Playlist playlist,@PathVariable long owner_id) throws DuplicateException {
        return playlistService.save(playlist,owner_id);
    }

    @PostMapping(value = "/{playlist_id}/songs/{song_id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist addSongToPlaylist(@PathVariable long playlist_id, @PathVariable long song_id) throws ResourceNotFoundException {
        return playlistService.addSongToPlaylist(playlist_id,song_id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    long  deletePlaylistById(@PathVariable long id){
        return playlistService.deletePlaylist(id);
    }


    @DeleteMapping(value ="/{playlist_id}/songs/{song_id}" )
    @ResponseStatus(HttpStatus.OK)
    Playlist deleteSongFromPlaylist(@PathVariable long playlist_id, @PathVariable long song_id) throws ResourceNotFoundException {
        return  playlistService.deleteSongFromPlaylist(playlist_id,song_id);
    }
}
