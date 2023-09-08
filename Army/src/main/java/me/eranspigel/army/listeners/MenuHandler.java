package me.eranspigel.army.listeners;

import me.eranspigel.army.Army;
import me.eranspigel.army.armies.CreateArmy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffectType;


public class MenuHandler implements Listener {

    Army plugin;

    public MenuHandler(Army plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        //Menu list
        final String MAIN_MENU = ChatColor.AQUA + "Summon your personal armies";
        final String TYPE_MENU = ChatColor.RED + "Type of your army";
        final String EFFECT_MENU = ChatColor.GRAY + "Choose effects";
        final String LEVEL_MENU = ChatColor.GOLD + "Choose level of effect";



        // CREATE AN ARMY WITHE initialized VALUES if this player don't have an army
        if (! plugin.armyHashMap.containsKey(player)) {
            CreateArmy army = new CreateArmy(player);
            plugin.armyHashMap.put(player, army);
        }

        CreateArmy army = plugin.armyHashMap.get(player);

        String inventoryTitle = e.getView().getTitle();
        Material currentItem = e.getCurrentItem().getType();

        if (inventoryTitle.equalsIgnoreCase(MAIN_MENU)) {
            switch (currentItem) {
                case NETHERITE_SWORD:
                    player.sendMessage("Opened Main Menu");
                    player.closeInventory();
                    plugin.openTypeMenu(player);
                    break;
                case BARRIER:
                    player.sendMessage("Closing Main Menu");
                    player.closeInventory();
                    break;
            }

            // abort the click Prevents player from moving items
            e.setCancelled(true);

        }else if(inventoryTitle.equalsIgnoreCase(TYPE_MENU)) {

            switch (currentItem) {
                case IRON_BLOCK:
                    army.setType("iron golem");
                    player.sendMessage("you choose iron golem");
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case ZOMBIE_HEAD:
                    army.setType("zombie");
                    player.sendMessage("you choose zombie");
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case CARVED_PUMPKIN:
                    army.setType("snow golem");
                    player.sendMessage("you choose snow golem");
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case CREEPER_HEAD:
                    army.setType("creeper");
                    player.sendMessage("you choose creeper");
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case PLAYER_HEAD:
                    army.setType("sniffer");
                    player.sendMessage("you choose suprise");
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
            }

            // abort the click Prevents player from moving items
            e.setCancelled(true);


        }else if(inventoryTitle.equalsIgnoreCase(EFFECT_MENU)) {

            switch (currentItem) {
                case DIAMOND_SWORD:
                    army.addEffect(PotionEffectType.INCREASE_DAMAGE,0);
                    player.closeInventory();
                    plugin.openLevelsMenu(army);
                    break;
                case FIREWORK_ROCKET:
                    army.addEffect(PotionEffectType.SPEED,0);
                    player.closeInventory();
                    plugin.openLevelsMenu(army);
                    break;
                case BEACON:
                    army.addEffect(PotionEffectType.REGENERATION,0);
                    player.closeInventory();
                    plugin.openLevelsMenu(army);
                    break;
                case GLASS:
                    army.addEffect(PotionEffectType.INVISIBILITY,0);
                    player.closeInventory();
                    plugin.openLevelsMenu(army);
                    break;
                case SHIELD:
                    army.addEffect(PotionEffectType.DAMAGE_RESISTANCE,0);
                    player.closeInventory();
                    plugin.openLevelsMenu(army);
                    break;
                case BARRIER:
                    //reset the effects and level because the player want to change type
                    army.removeAllEffect(army);
                    player.closeInventory();
                    plugin.openTypeMenu(player);
                    break;
                case TNT:
                    // summon the army
                    player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "YOU SUMMON THE ARMY");
                    e.setCancelled(true);
                    player.closeInventory();
                    plugin.summonMobsAroundPlayer(army);
                    // delete all the data about this player for the next summon don't be the same effects
                    plugin.armyHashMap.remove(player);
                    break;
            }

            // abort the click Prevents player from moving items
            e.setCancelled(true);

        }else if(inventoryTitle.equalsIgnoreCase(LEVEL_MENU)) {

            int lastIndex = army.getEffects().size() - 1; // Get the index of the last element

            switch (currentItem) {
                case WOODEN_SWORD:
                    army.editLastEffect(army,1);
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case GOLDEN_SWORD:
                    army.editLastEffect(army,2);
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case NETHERITE_SWORD:
                    army.editLastEffect(army,3);
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                    break;
                case ENCHANTED_GOLDEN_APPLE:
                    army.editLastEffect(army,4);
                    player.closeInventory();
                    plugin.openEffectsMenu(army);
                case RED_WOOL:
                    player.closeInventory();
                    plugin.openEffectsMenu(army);

            }
            // abort the click Prevents player from moving items
            army.printArmy();
            e.setCancelled(true);
        }
    }


}






