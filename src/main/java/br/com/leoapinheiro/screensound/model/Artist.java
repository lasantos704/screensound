package br.com.leoapinheiro.screensound.model;

import br.com.leoapinheiro.screensound.service.GeminiQuery;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String artistName;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String about;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Track> tracks = new ArrayList<>();

    public Artist() {}

    public Artist(String artistName, String genre) {
        this.artistName = artistName;
        this.genre = Genre.fromString(genre);
//        TODO: Implements todo in method not in constructor
        this.about = GeminiQuery.getArtistInfo(artistName).trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        tracks.forEach(t -> t.setArtist(this));
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "artistName='" + artistName + '\'' +
                ", genre=" + genre +
                ", tracks=" + tracks;
    }
}
