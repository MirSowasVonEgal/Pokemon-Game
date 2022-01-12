package main;

import java.io.File;
import java.util.Scanner;

public class GameMain {

    private static GameManager gameManager;

    public static void main(String[] args) {
        initPokemons();
        gameManager = new GameManager();
        gameManager.loadSavedGame();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            gameManager.saveGame();
            for (int i = 0; i < 10; i++) {
                System.out.println(" ");
            }
            System.out.println("Deine Spieldaten wurden gespeichert.");
        }));
        openMenu();
    }

    private static void initPokemons() {
        Utils.pokemons.add(new Pokemon("Kokuna", 80, "Gewöhnlich", "Käfer", 70));
        Utils.pokemons.add(new Pokemon("Raupy", 70, "Gewöhnlich", "Käfer", 50));
        Utils.pokemons.add(new Pokemon("Pummelluff", 65, "Gewöhnlich", "Normal", 120));
        Utils.pokemons.add(new Pokemon("Taubsi", 69, "Gewöhnlich", "Normal Flug", 115));

        Utils.pokemons.add(new Pokemon("Quappo", 58, "Selten", "Wasser Kampf", 130));
        Utils.pokemons.add(new Pokemon("Digdri", 56, "Selten", "Boden Stahl", 140));
        Utils.pokemons.add(new Pokemon("Inkocnito", 55, "Selten", "Psycho", 25));

        Utils.pokemons.add(new Pokemon("Pikatchu", 35, "Extrem Selten", "Elektro", 125));
        Utils.pokemons.add(new Pokemon("Schiggy", 32, "Extrem Selten", "Wasser", 150));
        Utils.pokemons.add(new Pokemon("Bisasam", 34, "Extrem Selten", "Pflanze", 150));
        Utils.pokemons.add(new Pokemon("Glumanda", 30, "Extrem Selten", "Feuer", 150));

        Utils.pokemons.add(new Pokemon("Arktos", 15, "Legendär", "Eis Flug", 190));
        Utils.pokemons.add(new Pokemon("Lavados", 12, "Legendär", "Feuer Flug", 185));
        Utils.pokemons.add(new Pokemon("Zapdos", 8, "Legendär", "Elektro Flug", 188));
        Utils.pokemons.add(new Pokemon("Mewtu", 3, "Mythisch", "Psycho", 220));
    }

    public static void openMenu() {
        String menu = "--- Pokemon Game (Menü) --- \n" +
                "1. Pokemon suchen \n" +
                "2. Inventar anzeigen \n" +
                "3. Alle Pokemons auflisten \n" +
                "4. Spielinformationen \n" +
                "5. Spiel beenden und speichern \n" +
                "--------------------";
        System.out.println(menu);
        gameManager.start();
    }

}
