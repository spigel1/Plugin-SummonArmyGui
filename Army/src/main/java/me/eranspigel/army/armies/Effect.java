
package me.eranspigel.army.armies;

/*
 * Import statements for necessary classes and packages.
 */
import org.bukkit.potion.PotionEffectType;

/*
 * Class definition for the Effect representing a potion effect with a level.
 */
public class Effect {
    /*
     * Instance variables to store the effect type and level.
     */
    private PotionEffectType effectType;
    private int level;

    /*
     * Constructor to initialize the Effect with the effect type and level.
     */
    public Effect(PotionEffectType effectType, int level) {
        this.effectType = effectType;
        this.level = level;
    }

    /*
     * Getter method to retrieve the effect type.
     */
    public PotionEffectType getEffectType() {
        return effectType;
    }

    /*
     * Getter method to retrieve the effect level.
     */
    public int getLevel() {
        return level;
    }

    /*
     * Setter method to set the effect level.
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
