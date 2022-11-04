package com.valko.SpringMusic.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@EqualsAndHashCode(exclude = "playlists")
@ToString(exclude = "playlists")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Size(min = 4, max = 32, message = "{valid.name.notNull}")
    @NotNull(message = "{valid.name.notNull}")
    private String name;

    @Column
    @Size(min = 4, max = 20,message = "{valid.login.size}")
    @NotNull(message = "{valid.login.notNull}")
    private String login;

    @Column
    @NotNull(message = "{valid.password.notNull}")
    private String password;

    @Column
    @NotNull(message = "{valid.isAdmin.notNull}")
    private boolean isAdmin;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", targetEntity = Playlist.class)
    private Set<Playlist> playlists = new HashSet<Playlist>();

    public User(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void addPlaylistToUser(Playlist playlist){
        playlists.add(playlist);
    }
    public void deletePlaylistFromUser(Playlist playlist){
        playlists.remove(playlist);
        playlist.removeOwner();
    }
}
