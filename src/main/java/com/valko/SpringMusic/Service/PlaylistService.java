package com.valko.SpringMusic.Service;

import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Entity.Song;
import com.valko.SpringMusic.Entity.User;
import com.valko.SpringMusic.Exception.DuplicateException;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;


    public List<Playlist> findAll(){
        return (List<Playlist>) playlistRepository.findAll();
    }

    public Playlist findOnePlaylist(long id) throws ResourceNotFoundException {
        return playlistRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
    }

    public Set<Song> findSongsForPlaylist(long id) throws ResourceNotFoundException {
        return playlistRepository.findById(id).orElseThrow(()->new ResourceNotFoundException()).getSongs();
    }

    public Playlist save(Playlist playlist, long owner_id) throws DuplicateException {
        User user =userService.findUserByID(owner_id);
        playlist.setOwner(user);
        if(this.isExist(playlist)){
            throw new DuplicateException("Playlist exist at this user");
        }
        playlistRepository.save(playlist);
        return playlist;
    }

    public Playlist addSongToPlaylist(long playlist_id, long song_id) throws ResourceNotFoundException {
        Song song = songService.findOneSongById(song_id);
        Playlist playlist = playlistRepository.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException());
        playlist.addSongToPlaylist(song);
        return playlistRepository.save(playlist);
    }

    public Playlist deleteSongFromPlaylist(long playlist_id, long song_id) throws ResourceNotFoundException {
        Song song = songService.findOneSongById(song_id);
        Playlist playlist = playlistRepository.findById(playlist_id).orElseThrow(()->new ResourceNotFoundException());
        playlist.removeSong(song);
        return  playlistRepository.save(playlist);
    }

    public long  deletePlaylist( long id){
        playlistRepository.deleteById(id);
        return id;
    }

   public boolean isExist(Playlist newPlaylist){
        List<Playlist> playlists = (List<Playlist>)playlistRepository.findAll();
        for(Playlist playlist : playlists){
            if(playlist.getName().equals(newPlaylist.getName()) &&
                    playlist.getOwner().getId()==newPlaylist.getOwner().getId()){
                return true;
            }
        }
        return false;
    }
}
