package br.com.leoapinheiro.screensound.repository;

import br.com.leoapinheiro.screensound.model.Artist;
import br.com.leoapinheiro.screensound.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByArtistNameContainingIgnoreCase(String name);

    @Query("SELECT t FROM Artist a JOIN a.tracks t ORDER BY t.artist")
    List<Track> listAllTracks();

    @Query("SELECT t FROM Artist a JOIN a.tracks t WHERE a.artistName ILIKE %:artistName%")
    List<Track> searchTrackByArtist(String artistName);

    @Query("SELECT a.about FROM Artist a WHERE a.artistName ILIKE %:artistName%")
    String getAboutByArtist(String artistName);
}
