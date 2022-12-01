package com.valko.SpringMusic.Service;

import com.valko.SpringMusic.Entity.Playlist;
import com.valko.SpringMusic.Entity.Song;
import com.valko.SpringMusic.Exception.DuplicateException;
import com.valko.SpringMusic.Exception.ResourceNotFoundException;
import com.valko.SpringMusic.Repository.PlaylistRepository;
import com.valko.SpringMusic.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PlaylistRepository playlistRepository;


    public List<Song> findAll(){
        return (List<Song>) songRepository.findAll();
    }

    public List<Song> findByIncludesStr(String str){
        List<Song> songs = (List<Song>) songRepository.findAll();
        List<Song> result = new ArrayList<>();
        for(Song song:songs){
            if(song.getName().toLowerCase().contains(str.toLowerCase())){
                result.add(song);
            }
        }
        return result;
    }

    public Song findOneSongById(long id) throws ResourceNotFoundException {
        return songRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
    }

    public Song saveSong(Song song) throws DuplicateException {
        if(this.isExist(song)){
            throw new DuplicateException("Song exist");
        }
        songRepository.save(song);
        return song;
    }

    public long deleteSongByID(long id){
        Song song = songRepository.findById(id).get();
        List<Playlist> playlists = (List<Playlist>)playlistRepository.findAll();
        for(Playlist playlist: playlists){
            playlist.removeSong(song);
        }
        songRepository.delete(song);
        return id;
    }

    public boolean isExist(Song newSong){
        List<Song> songs = (List<Song>)songRepository.findAll();
        for(Song song: songs)
        {
            if(song.getName().equals(newSong.getName()) && song.getAuthor().equals(newSong.getAuthor())){
                return true;
            }
        }
        return false;
    }
}
