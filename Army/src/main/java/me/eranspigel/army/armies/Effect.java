package me.eranspigel.army.armies;

import org.bukkit.potion.PotionEffectType;

public class Effect {
    private PotionEffectType effectType;
    private int level;

    public Effect(PotionEffectType effectType, int level) {
        this.effectType = effectType;
        this.level = level;
    }

    public PotionEffectType getEffectType() {
        return effectType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
