package me.eranspigel.army.armies;

import me.eranspigel.army.armies.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CreateArmy {
    private Player player;
    private String type;
    private List<Effect> effects;

    public CreateArmy() {
        this.effects = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void addEffect(PotionEffectType effectType, int level) {
        Effect effect = new Effect(effectType, level);
        effects.add(effect);
    }

    public void removeEffect(PotionEffectType effectType) {
        effects.removeIf(effect -> effect.getEffectType() == effectType);
    }
    public void removeAllEffect(CreateArmy army) {
        army.effects = new ArrayList<>();
    }
    public void editLastEffect(CreateArmy army, int newLevel) {
        List<Effect> effects = army.getEffects();
        int lastIndex = effects.size() - 1; // Get the index of the last effect
        if (lastIndex >= 0) {
            Effect lastEffect = effects.get(lastIndex); // Get the last effect
            lastEffect.setLevel(newLevel); // Set the new level for the last effect
        }
    }
    public CreateArmy(Player player) {
        this.player = player;
        this.effects = new ArrayList<>();
    }

    public void printArmy() {
        System.out.println("Player: " + player.getName());
        System.out.println("Type: " + type);
        System.out.println("Effects and Levels:");

        for (Effect effect : effects) {
            System.out.println("Effect: " + effect.getEffectType() + " Level: " + effect.getLevel());
        }
    }
}
