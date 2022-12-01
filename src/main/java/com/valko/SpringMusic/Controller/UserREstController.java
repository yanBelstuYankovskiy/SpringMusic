package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.DTO.UserResponse;
import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Entity.User;
import com.valko.SpringMusic.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Tag(name="Пользователи", description = "Контроллер работаетающий с пользователями")
@RestController
@RequestMapping(value = "/users")
public class UserREstController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    User getOneUser(@PathVariable long id){
        return userService.findUserByID(id);
    }
    

    @GetMapping(value = "/{user_id}/playlists")
    @ResponseStatus(HttpStatus.OK)
    Set<Playlist> getUsersPlaylists(@PathVariable long user_id){
        return userService.getUsersPlaylists(user_id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    User saveUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @PostMapping(value = "/{user_id}/playlists/{playlist_id}")
    @ResponseStatus(HttpStatus.OK)
    Set<Playlist> addPlaylistToUser(@PathVariable long user_id, @PathVariable long playlist_id){
        return userService.addPlaylistToUser(user_id, playlist_id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    long deleteUserById(@PathVariable long id){
        return userService.deleteUser(id);
    }


    @DeleteMapping(value = "/{user_id}/playlists/{playlist_id}")
    @ResponseStatus(HttpStatus.OK)
    Set<Playlist> deletePlaylist(@PathVariable long user_id, @PathVariable long playlist_id){
        return userService.deletePlaylist(user_id,playlist_id);
    }
}
