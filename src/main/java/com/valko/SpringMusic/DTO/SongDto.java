package com.valko.SpringMusic.DTO;
import com.valko.SpringMusic.Entity.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private Long id;
    private String name;
    private String author;
    private Genre genre;
    private String album;
    private int rating;
    private String source;
}
