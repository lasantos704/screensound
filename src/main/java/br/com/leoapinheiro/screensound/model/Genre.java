package br.com.leoapinheiro.screensound.model;

public enum Genre {
    POP("Pop"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    HIPHOP("Hip-Hop"),
    ELECTRONIC("Electronic"),
    COUNTRY("Country"),
    REGGAE("Reggae"),
    BLUES("Blues"),
    METAL("Metal"),
    FOLK("Folk"),
    PUNK("Punk"),
    SOUL("Soul"),
    RNB("R&B"),
    DISCO("Disco"),
    FUNK("Funk"),
    GOSPEL("Gospel"),
    LATIN("Latin"),
    ALTERNATIVE("Alternative"),
    INDIE("Indie"),
    OTHER("Other");

    private String musicGenre;

    Genre(String genre) {
        this.musicGenre = genre;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.musicGenre.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Did not find the music genre entered: " + text);
    }
}
