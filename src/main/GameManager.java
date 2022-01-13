package main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GameManager {

    public GameManager() {

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String selectionString = scanner.next();
        if(Utils.isInt(selectionString)) {
            System.out.println("Bitte gebe eine Zahl ein!");
            GameMain.openMenu();
            return;
        }
        int selection = Integer.parseInt(selectionString);
        if(selection == 5) {
            saveGame();
            System.exit(1);
        } else if(selection == 4) {
            showInfo();
        } else if(selection == 3) {
            showPokemonIndex();
        } else if(selection == 2) {
            showInventory();
        } else if(selection == 1) {
            searchPokemons();
        } else {
            System.out.println("Diese Auswahl existiert nicht!");
            GameMain.openMenu();
        }
    }

    public void saveGame() {
        try {
            FileOutputStream out = new FileOutputStream("Inventory.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(Inventory.pokemons);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSavedGame() {
        try {
            if(!new File("Inventory.bin").exists()) return;
            ObjectInputStream in = new ObjectInputStream( new FileInputStream("Inventory.bin"));
            Inventory.pokemons = (ArrayList) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInfo() {
        String message = "--- Spielinformationen ---\n" +
                "Dieses Spiel ist ein Text Adventure wo es einzig und allein darum \n" +
                "geht alle 15 verschiedenen Pokémon zu fangen. Jedes Pokémon \n" +
                "hat eine unterschiedliche Wahrscheinlichkeit gefangen zu werden, \n" +
                "dabei gibt die Rate an, um welche Wahrscheinlichkeit es sich handelt. \n" +
                "Aufgrund dessen gibt es gewöhnliche und sehr seltene Pokémon. \n" +
                "Im Menü kann man sich jedes Pokémon anschauen, dazu sieht man \n" +
                "die Wahrscheinlichkeit und die Stärke des Pokémon. \n" +
                "Zudem kann man an unterschiedlichen Orten nach Pokémon suchen. \n" +
                "Deine gefangenen Pokémon werden dir im Inventar angezeigt.\n" +
                "--------------------------\n" +
                "Drücke Enter um wieder ins Hauptmenü zu kommen.";
        System.out.println(message);
        new Scanner(System.in).nextLine();
        GameMain.openMenu();
    }

    public void showPokemonIndex() {
        Utils.pokemons.sort(Comparator.comparing(Pokemon::getRate).reversed());
        StringBuilder index = new StringBuilder();
        index.append("--- Pokemon Index ---\n");
        for (Pokemon pokemon : Utils.pokemons) {
            index.append(pokemon.getName())
                    .append(" - Rate: ")
                    .append(pokemon.getRate())
                    .append("% | ")
                    .append(pokemon.getElement())
                    .append(" | ")
                    .append(pokemon.getRareness())
                    .append(" | ")
                    .append(pokemon.getHp())
                    .append("HP \n");
        }
        index.append("---------------------\n");
        index.append("Drücke Enter um wieder ins Hauptmenü zu kommen.");
        System.out.println(index);
        new Scanner(System.in).nextLine();
        GameMain.openMenu();
    }

    public void showInventory() {
        Inventory.pokemons.sort(Comparator.comparing(Pokemon::getRate).reversed());
        StringBuilder inventory = new StringBuilder();
        inventory.append("--- Deine gesammelten Pokemons ---\n");
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (Pokemon pokemon : Inventory.pokemons) {
            String date = simpleDateFormat.format(pokemon.getTimestamp());
            inventory.append(pokemon.getName())
                    .append(" - Rate: ")
                    .append(pokemon.getRate())
                    .append("% | ")
                    .append(pokemon.getElement())
                    .append(" | ")
                    .append(pokemon.getRareness())
                    .append(" | ")
                    .append(pokemon.getHp())
                    .append("HP | ")
                    .append("Gefangen am: " + date + "\n");
        }
        inventory.append("---------------------------------\n");
        inventory.append("Drücke Enter um wieder ins Hauptmenü zu kommen.");
        System.out.println(inventory);
        new Scanner(System.in).nextLine();
        GameMain.openMenu();
    }

    public void searchPokemons() {
        String locations = "--- Wo willst du hin? ---\n" +
                "1. Wald\n" +
                "2. Meer\n" +
                "3. Höhlen\n" +
                "-------------------------";
        System.out.println(locations);
        Scanner scanner = new Scanner(System.in);
        String selectionString = scanner.next();
        if(Utils.isInt(selectionString)) {
            System.out.println("Bitte gebe eine Zahl ein!");
            GameMain.openMenu();
            return;
        }
        int selection = Integer.parseInt(selectionString);
        if(selection == 1) {
            System.out.println("Du begibst dich nun in den Wald...");
        } else if(selection == 2) {
            System.out.println("Du begibst dich nun zum Meer...");
        } else if(selection == 3) {
            System.out.println("Du begibst dich nun zu den Höhlen...");
        } else {
            System.out.println("Diese Auswahl existiert nicht!");
            GameMain.openMenu();
            return;
        }
        Random random = new Random();
        if((random.nextInt(3)+1) < 2) {
            String message = "Hier scheint sich kein Pokemon aufzuhalten.\n" +
                    "Versuche es doch mal bei einem anderem Ort.";
            System.out.println(message);
            new Scanner(System.in).nextLine();
            GameMain.openMenu();
            return;
        }

        Pokemon pokemon = Utils.pokemons.get(random.nextInt(Utils.pokemons.size()));
        System.out.println("Ein wildes \"" + pokemon.getName() + "\" erscheint.");
        catchPokemon(pokemon);
    }

    public void catchPokemon(Pokemon pokemon) {
        String message = "--- Willst du das Pokemon fangen? ---\n" +
                "Die Rate um das " + pokemon.getRareness() + " Pokemon zu fangen liegt bei " + pokemon.getRate() + "% \n" +
                "1. Ja\n" +
                "2. Nein\n" +
                "-------------------------";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String selectionString = scanner.next();
        if(Utils.isInt(selectionString)) {
            System.out.println("Bitte gebe eine Zahl ein!");
            catchPokemon(pokemon);
            return;
        }
        int selection = Integer.parseInt(selectionString);
        if(selection == 1) {
            if(pokemon.catchPokemon()) {
                String catched = "Du hast erfolgreich das Pokemon \"" + pokemon.getName() + "\" gefangen.\n" +
                        "--- Stats deines neuen Pokemons ---\n" +
                        "Name: " + pokemon.getName() + "\n" +
                        "Rate: " + pokemon.getRate() + "%\n" +
                        "Element: " + pokemon.getElement() + "\n" +
                        "Seltenheit: " + pokemon.getRareness() + "\n" +
                        "Lebenspunkte: " + pokemon.getHp() + "HP\n" +
                        "-----------------------------------\n" +
                        "Dein neues Pokemon wurde in dein Inventar abgelegt.\n" +
                        "Drücke Enter um wieder ins Hauptmenü zu kommen.";
                saveGame();
                System.out.println(catched);
                new Scanner(System.in).nextLine();
                GameMain.openMenu();
            } else {
                System.out.println("Das " + pokemon.getName() + " ist dir leider entwischt...");
                System.out.println("Drücke Enter um wieder ins Hauptmenü zu kommen.");
                new Scanner(System.in).nextLine();
                GameMain.openMenu();
            }
        } else if(selection == 2) {
            GameMain.openMenu();
        } else {
            System.out.println("Diese Auswahl existiert nicht!");
            catchPokemon(pokemon);
        }
    }
}
