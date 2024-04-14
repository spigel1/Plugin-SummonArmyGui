
package me.eranspigel.army.listeners;

/*
 * Import statements for necessary classes and packages.
 */
import me.eranspigel.army.Army;
import me.eranspigel.army.armies.CreateArmy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffectType;

/*
 * Class definition for the MenuHandler implementing the Listener interface.
 */
public class MenuHandler implements Listener {

    /*
     * Reference to the main plugin class.
     */
    private final Army plugin;

    /*
     * Constants for inventory titles.
     */
    private static final String MAIN_MENU = ChatColor.AQUA + "Summon your personal armies";
    private static final String TYPE_MENU = ChatColor.RED + "Type of your army";
    private static final String EFFECT_MENU = ChatColor.GRAY + "Choose effects";
    private static final String LEVEL_MENU = ChatColor.GOLD + "Choose level of effect";

    /*
     * Constructor to initialize the MenuHandler with the plugin instance.
     */
    public MenuHandler(Army plugin) {
        this.plugin = plugin;
    }

    /*
     * Event handler for inventory click events.
     */
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String inventoryTitle = e.getView().getTitle();
        Material currentItem = e.getCurrentItem() != null ? e.getCurrentItem().getType() : Material.AIR;

        // Determine the inventory clicked and handle accordingly
        if (inventoryTitle.equalsIgnoreCase(MAIN_MENU)) {
            handleMainMenuClick(player, e);
        } else if (inventoryTitle.equalsIgnoreCase(TYPE_MENU)) {
            handleTypeMenuClick(player, currentItem, e);
        } else if (inventoryTitle.equalsIgnoreCase(EFFECT_MENU)) {
            handleEffectsMenuClick(player, currentItem, e);
        } else if (inventoryTitle.equalsIgnoreCase(LEVEL_MENU)) {
            handleLevelMenuClick(player, currentItem, e);
        }
    }

    /*
     * Method to handle click events in the main menu.
     */
    private void handleMainMenuClick(Player player, InventoryClickEvent e) {
        switch (e.getCurrentItem().getType()) {
            case NETHERITE_SWORD:
                handleMainMenuAction(player, "Opened Main Menu");
                player.closeInventory();
                plugin.openTypeMenu(player);
                break;
            case BARRIER:
                handleMainMenuAction(player, "Closing Main Menu");
                player.closeInventory();
                break;
        }
        e.setCancelled(true);
    }

    /*
     * Method to handle click events in the type selection menu.
     */
    private void handleTypeMenuClick(Player player, Material currentItem, InventoryClickEvent e) {
        CreateArmy army = getOrCreateArmy(player);
        switch (currentItem) {
            case IRON_BLOCK:
                handleTypeMenuAction(player, army, "iron golem", "you choose iron golem");
                break;
            case ZOMBIE_HEAD:
                handleTypeMenuAction(player, army, "zombie", "you choose zombie");
                break;
            case CARVED_PUMPKIN:
                handleTypeMenuAction(player, army, "snow golem", "you choose snow golem");
                break;
            case CREEPER_HEAD:
                handleTypeMenuAction(player, army, "creeper", "you choose creeper");
                break;
            case PLAYER_HEAD:
                handleTypeMenuAction(player, army, "sniffer", "you choose sniffer");
                break;
            case BARRIER:
                player.closeInventory();
                break;
        }
        e.setCancelled(true);
    }

    /*
     * Method to handle click events in the effects selection menu.
     */
    private void handleEffectsMenuClick(Player player, Material currentItem, InventoryClickEvent e) {
        CreateArmy army = getOrCreateArmy(player);
        switch (currentItem) {
            case DIAMOND_SWORD:
                handleEffectsMenuAction(player, army, PotionEffectType.INCREASE_DAMAGE, "strength");
                break;
            case FIREWORK_ROCKET:
                handleEffectsMenuAction(player, army, PotionEffectType.SPEED, "speed");
                break;
            case BEACON:
                handleEffectsMenuAction(player, army, PotionEffectType.REGENERATION, "regeneration");
                break;
            case GLASS:
                handleEffectsMenuAction(player, army, PotionEffectType.INVISIBILITY, "invisibility");
                break;
            case SHIELD:
                handleEffectsMenuAction(player, army, PotionEffectType.DAMAGE_RESISTANCE, "resistance");
                break;
            case BARRIER:
                // Reset the effects and level because the player wants to change type
                army.removeAllEffect(army);
                player.closeInventory();
                plugin.openTypeMenu(player);
                break;
            case TNT:
                // Summon the army
                player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "YOU SUMMON THE ARMY");
                e.setCancelled(true);
                player.closeInventory();
                plugin.summonMobsAroundPlayer(army);
                // Delete all the data about this player for the next summon to have different effects
                plugin.armyHashMap.remove(player);
                break;
        }
        e.setCancelled(true);
    }

    /*
     * Method to handle click events in the effects level selection menu.
     */
    private void handleLevelMenuClick(Player player, Material currentItem, InventoryClickEvent e) {
        CreateArmy army = getOrCreateArmy(player);
        switch (currentItem) {
            case WOODEN_SWORD:
                handleLevelMenuAction(player, army, 1);
                break;
            case GOLDEN_SWORD:
                handleLevelMenuAction(player, army, 2);
                break;
            case NETHERITE_SWORD:
                handleLevelMenuAction(player, army, 3);
                break;
            case ENCHANTED_GOLDEN_APPLE:
                handleLevelMenuAction(player, army, 4);
                break;
            case RED_WOOL:
                player.closeInventory();
                plugin.openEffectsMenu(army);
                break;
        }
        e.setCancelled(true);
    }


    /*
     * Method to handle actions in the main menu.
     */
    private void handleMainMenuAction(Player player, String message) {
        player.sendMessage(message);
        player.closeInventory();
    }

    /*
     * Method to handle actions in the type selection menu.
     */
    private void handleTypeMenuAction(Player player, CreateArmy army, String type, String message) {
        army.setType(type);
        player.sendMessage(message);
        player.closeInventory();
        plugin.openEffectsMenu(army); // Call the openEffectsMenu method from the Army class
    }

    /*
     * Method to handle actions in the effects selection menu.
     */
    private void handleEffectsMenuAction(Player player, CreateArmy army, PotionEffectType effectType, String effectName) {
        army.addEffect(effectType, 0);
        player.closeInventory();
        player.sendMessage("you choose " + effectName);
        plugin.openLevelsMenu(army); // Call the openLevelsMenu method from the Army class
    }

    /*
     * Method to handle actions in the effects level selection menu.
     */
    private void handleLevelMenuAction(Player player, CreateArmy army, int level) {
        army.editLastEffect(army, level);
        player.closeInventory();
        plugin.openEffectsMenu(army); // Call the summonMobsAroundPlayer method from the Army class
    }

    /*
     * Method to get or create an army for the player.
     */
    private CreateArmy getOrCreateArmy(Player player) {
        return plugin.armyHashMap.computeIfAbsent(player, CreateArmy::new);
    }
}
