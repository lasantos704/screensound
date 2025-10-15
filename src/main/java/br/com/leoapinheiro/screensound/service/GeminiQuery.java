package br.com.leoapinheiro.screensound.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GeminiQuery {
    public static String getArtistInfo(String artistName) {
        Client client = new Client();
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                "Give me a brief summary in 255 characters total about the artist " + artistName + ".",
                null );
        return response.text();
    }
}
