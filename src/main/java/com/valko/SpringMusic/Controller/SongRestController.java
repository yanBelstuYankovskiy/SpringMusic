package com.valko.SpringMusic.Controller;
import com.valko.SpringMusic.Entity.Song;
import com.valko.SpringMusic.Exception.DuplicateException;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Repository.PlaylistRepository;
import com.valko.SpringMusic.Service.SongService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * junit
 */

@Tag(name="Песни", description = "Контроллер работаетающий с треками")
@RestController
@RequestMapping(value = "/songs")
public class SongRestController{

    @Autowired
    SongService songService;

    @Autowired
    PlaylistRepository playlistRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Song> getSongs(){
        return songService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Song getOneSong(@PathVariable long id) throws ResourceNotFoundException {
        return songService.findOneSongById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    Song saveSong(@RequestBody Song song) throws DuplicateException {
        return songService.saveSong(song);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Long deleteSongById(@PathVariable long id){
        return songService.deleteSongByID(id);
    }

}
