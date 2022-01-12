package main;

import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        initPokemons();
        openMenu();
    }

    private static void initPokemons() {
        Utils.pokemons.add(new Pokemon("Pikatchu", 60));
    }

    private static void openMenu() {
        String menu = "--- Pokemon Game --- \n" +
                "Main menu: \n" +
                "1. Pokemon suchen \n" +
                "2. Inventar anzeigen \n" +
                "3. Alle Pokemons auflisten \n" +
                "4. Spiel beenden \n" +
                "--------------------";
        System.out.println(menu);
        Scanner scanner = new Scanner(System.in);
        String selectionString = scanner.next();
        if(Utils.isInt(selectionString)) {
            System.out.println("Bitte gebe eine Zahl ein!");
            openMenu();
        }
        int selection = Integer.parseInt(selectionString);
        if(selection > 4) {
            System.out.println("Diese Auswahl existiert nicht!");
            openMenu();
        }
    }

}
