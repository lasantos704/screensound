package br.com.leoapinheiro.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackName;
    @ManyToOne
    private Artist artist;

    public Track() {
    }

    public Track(String trackName) {
        this.trackName = trackName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "trackName=" + trackName + ", artist=" + artist.getArtistName();
    }
}
