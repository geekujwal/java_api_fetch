package org.example;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        String name;
        System.out.println("Enter a pokemon name:");
        name = scan.nextLine();
        Transcript transcript = new Transcript();
        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon/"+name.trim().toLowerCase()))
                .header("Auth","abc")
                .GET()
                .build();
        try {
            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println("Pokemon Pokedex number: " + transcript.getId());
            System.out.println("Pokemon name: " + transcript.getName());
            System.out.println("Pokemon type: ");
            for (int i = 0; i < transcript.getTypes().length; i++) {
                System.out.println("   " + transcript.getTypes()[i].getSlot() + ". " + transcript.getTypes()[i].getType().getName());
            }
            System.out.println("Pokemon weight: " + transcript.getWeight());
            System.out.println("Pokemon height: " + transcript.getHeight());
        }catch (Exception e){
            System.out.println("Can't find this pokemon.");
        }
        }

}