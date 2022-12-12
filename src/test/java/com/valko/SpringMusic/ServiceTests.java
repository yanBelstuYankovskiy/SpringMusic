package com.valko.SpringMusic;

import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Entity.Song;
import com.valko.SpringMusic.Entity.User;
import com.valko.SpringMusic.Exception.DuplicateException;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Service.PlaylistService;
import com.valko.SpringMusic.Service.SongService;
import com.valko.SpringMusic.Service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @Autowired
    PlaylistService playlistService;
    @Autowired
    SongService songService;
    @Autowired
    UserService userService;

    @Test
    public void isPlaylistExists(){
        Playlist playlist = new Playlist();
        User user = new User();
        user.setId(1432235);
        playlist.setName("PL1");
        playlist.setOwner(user);
        Assert.assertFalse(playlistService.isExist(playlist));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getNotExistsSong() throws ResourceNotFoundException {
        Song song = songService.findOneSongById(1123);
    }

    @Test(expected = DuplicateException.class)
    public void saveDuplicateSong() throws ResourceNotFoundException, DuplicateException {
        Song song = songService.findOneSongById(12);
        songService.saveSong(song);
    }

    @Test
    public void getUserByLoginAndPassword(){
        Assert.assertNotNull(userService.findByLoginAndPassword("HateGnom","12345678"));
    }

}
