package main;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Pokemon> pokemons = new ArrayList<>();

    public static boolean isInt(String data) {
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

}
