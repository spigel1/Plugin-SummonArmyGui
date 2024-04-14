
package me.eranspigel.army.armies;

/*
 * Import statements for necessary classes and packages.
 */
import me.eranspigel.army.armies.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/*
 * Class definition for the CreateArmy representing a player's army with effects.
 */
public class CreateArmy {
    /*
     * Instance variables to store the player, army type, and effects.
     */
    private Player player;
    private String type;
    private List<Effect> effects;

    /*
     * Constructor to initialize a new army with an empty list of effects.
     */
    public CreateArmy() {
        this.effects = new ArrayList<>();
    }

    /*
     * Getter method to retrieve the player associated with the army.
     */
    public Player getPlayer() {
        return player;
    }

    /*
     * Setter method to set the player associated with the army.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /*
     * Getter method to retrieve the type of the army.
     */
    public String getType() {
        return type;
    }

    /*
     * Setter method to set the type of the army.
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     * Getter method to retrieve the list of effects associated with the army.
     */
    public List<Effect> getEffects() {
        return effects;
    }

    /*
     * Method to add a new effect with a specified type and level to the army.
     */
    public void addEffect(PotionEffectType effectType, int level) {
        Effect effect = new Effect(effectType, level);
        effects.add(effect);
    }

    /*
     * Method to remove an effect with a specified type from the army.
     */
    public void removeEffect(PotionEffectType effectType) {
        effects.removeIf(effect -> effect.getEffectType() == effectType);
    }

    /*
     * Method to remove all effects from the army.
     */
    public void removeAllEffect(CreateArmy army) {
        army.effects = new ArrayList<>();
    }

    /*
     * Method to edit the level of the last effect in the army.
     */
    public void editLastEffect(CreateArmy army, int newLevel) {
        List<Effect> effects = army.getEffects();
        int lastIndex = effects.size() - 1; // Get the index of the last effect
        if (lastIndex >= 0) {
            Effect lastEffect = effects.get(lastIndex); // Get the last effect
            lastEffect.setLevel(newLevel); // Set the new level for the last effect
        }
    }

    /*
     * Constructor to initialize a new army with a specified player and an empty list of effects.
     */
    public CreateArmy(Player player) {
        this.player = player;
        this.effects = new ArrayList<>();
    }

    /*
     * Method to print the details of the army (for debugging purposes).
     */
    public void printArmy() {
        System.out.println("Player: " + player.getName());
        System.out.println("Type: " + type);
        System.out.println("Effects and Levels:");

        for (Effect effect : effects) {
            System.out.println("Effect: " + effect.getEffectType() + " Level: " + effect.getLevel());
        }
    }
}
