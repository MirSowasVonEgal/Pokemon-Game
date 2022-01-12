package main;

import java.util.Random;

public class Pokemon {

    private String name;
    private int rate; // Fangrate

    public Pokemon(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public void catchPokemon() {
        int random = new Random().nextInt(100);
        if(random <= this.rate) {
            Inventory.pokemons.add(this);
        }
    }
}
