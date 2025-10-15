package br.com.leoapinheiro.screensound.main;

import br.com.leoapinheiro.screensound.model.Artist;
import br.com.leoapinheiro.screensound.model.Track;
import br.com.leoapinheiro.screensound.repository.ArtistRepository;
import br.com.leoapinheiro.screensound.service.GeminiQuery;

import java.util.*;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ArtistRepository repository;
    private List<Artist> artists = new ArrayList<>();

    public Main(ArtistRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Register Artist
                    2 - Register Track
                    3 - List Tracks
                    4 - Search track by Artist
                    5 - Search info about an artist
                    
                    0 - Exit                          
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerTrack();
                    break;
                case 3:
                    listTracks();
                    break;
                case 4:
                    searchTrackByArtist();
                    break;
                case 5:
                    searchInfoAboutAnArtist();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void registerArtist() {
        var registerNewArtist = "Y";
        while (registerNewArtist.equalsIgnoreCase("Y")) {
            System.out.println("Write the artist name:");
            var name = scanner.nextLine();
            System.out.println("Write the artist genre:");
            var genre = scanner.nextLine();
            Artist artist = new Artist(name, genre);

            repository.save(artist);
            System.out.println("Do you want to add another artist? (Y/N)");
            registerNewArtist = scanner.nextLine();
        }
    }

    private void listRegisterArtists() {
        artists = repository.findAll();
        artists.stream()
                .sorted(Comparator.comparing(Artist::getGenre))
                .forEach(System.out::println);
    }

    private void registerTrack() {
        listRegisterArtists();
        System.out.println("Write the artist name to add a music:");
        var name = scanner.nextLine();
        var artist = repository.findByArtistNameContainingIgnoreCase(name);

        if (artist.isPresent()) {
            var artistFound = artist.get();
            System.out.println("Write the music name:");
            var musicName = scanner.nextLine();
            Track track = new Track(musicName);
            track.setArtist(artistFound);
            artistFound.getTracks().add(track);
            repository.save(artistFound);
            System.out.println("Music added successfully.");
        } else {
            System.out.println("Artist not found.");
        }
    }

    private void listTracks() {
        var tracks = repository.listAllTracks();
        tracks.stream()
                .forEach(System.out::println);
    }

    private void searchTrackByArtist() {
        listRegisterArtists();
        System.out.println("Write the artist name to search for their music:");
        var name = scanner.nextLine();
        var tracks = repository.searchTrackByArtist(name);
        tracks.forEach(System.out::println);
    }

    private void searchInfoAboutAnArtist() {
        listRegisterArtists();
        System.out.println("Write the artist name to search for info:");
        var name = scanner.nextLine();
        var artist = repository.findByArtistNameContainingIgnoreCase(name);

        if (artist.isPresent()) {
            var artistFound = artist.get();
            var about = GeminiQuery.getArtistInfo(name);
            System.out.println("About the artist: \n" + about);
            artistFound.setAbout(about.trim().substring(0, 240) + "...");
            repository.save(artistFound);
        } else {
            System.out.println("Artist not found.");
        }
    }
}
