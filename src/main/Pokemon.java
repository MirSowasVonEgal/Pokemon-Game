package main;

import java.io.Serializable;
import java.util.Random;

public class Pokemon implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private int rate; // Fang Rate

    public String getRareness() {
        return rareness;
    }

    public void setRareness(String rareness) {
        this.rareness = rareness;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    private String rareness;

    private String element;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    private int hp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

    public Pokemon(String name, int rate, String rareness, String element, int hp) {
        this.name = name;
        this.rate = rate;
        this.rareness = rareness;
        this.element = element;
        this.hp = hp;
        setTimestamp(System.currentTimeMillis());
    }

    public boolean catchPokemon() {
        int random = new Random().nextInt(100)+1;
        if(random <= this.rate) {
            Inventory.pokemons.add(this);
            return true;
        } else {
            return false;
        }
    }
}
