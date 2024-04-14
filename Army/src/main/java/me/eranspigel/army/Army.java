
package me.eranspigel.army;

/*
 * Import statements for necessary classes and packages.
 */
import me.eranspigel.army.armies.CreateArmy;
import me.eranspigel.army.armies.Effect;
import me.eranspigel.army.commands.ArmyCommand;
import me.eranspigel.army.listeners.MenuHandler;
import me.eranspigel.army.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/*
 * Main class definition extending JavaPlugin to create a Bukkit plugin.
 */
public final class Army extends JavaPlugin {

    /*
     * HashMap to store army data for each player.
     */
    public HashMap<Player, CreateArmy> armyHashMap = new HashMap<>();

    /*
     * Method executed when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin has started up.");

        // Registering the /summonarmy command executor
        getCommand("summonarmy").setExecutor(new ArmyCommand(this));

        // Registering the MenuHandler as an event listener
        getServer().getPluginManager().registerEvents(new MenuHandler(this), this);
    }

    /*
     * Method to open the main menu for army summoning.
     */
    public void openMainMenu(Player player) {
        // Creating the main menu inventory
        Inventory main_menu = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Summon your personal armies");

        // Creating ItemStacks for menu options
        ItemStack create = ItemUtils.createItem(Material.NETHERITE_SWORD, ChatColor.GREEN + "Summon", ChatColor.DARK_PURPLE + "Summon an army.");
        ItemStack close = ItemUtils.createItem(Material.BARRIER, ChatColor.RED + "close", null);

        // Setting menu items
        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        player.openInventory(main_menu);
    }

    /*
     * Method to open the menu for selecting the type of army.
     */
    public void openTypeMenu(Player player) {
        // Creating the type selection menu inventory
        Inventory Type_menu = Bukkit.createInventory(player, 9, ChatColor.RED + "Type of your army");

        // Creating ItemStacks for army types
        ItemStack ironGolem = ItemUtils.createItem(Material.IRON_BLOCK, ChatColor.YELLOW + "Iron golem", null);
        ItemStack zombie = ItemUtils.createItem(Material.ZOMBIE_HEAD, ChatColor.RED + "Zombie", null);
        ItemStack snowGolem = ItemUtils.createItem(Material.CARVED_PUMPKIN, ChatColor.AQUA + "snowGolem", null);
        ItemStack creepers = ItemUtils.createItem(Material.CREEPER_HEAD, ChatColor.GOLD + "creepers", null);
        ItemStack sniffer = ItemUtils.createItem(Material.PLAYER_HEAD, ChatColor.GOLD + "surprise", null);
        ItemStack closeTypeMenu = ItemUtils.createItem(Material.BARRIER, ChatColor.RED + "Close", null);

        // Add items to menu
        Type_menu.setItem(0, ironGolem);
        Type_menu.setItem(1, zombie);
        Type_menu.setItem(2, snowGolem);
        Type_menu.setItem(3, creepers);
        Type_menu.setItem(4, sniffer);
        Type_menu.setItem(8, closeTypeMenu);

        player.openInventory(Type_menu);
    }

    /*
     * Method to open the menu for selecting effects for the army.
     */
    public void openEffectsMenu(CreateArmy army) {
        Player player = army.getPlayer();
        Inventory openEffectsMenu = Bukkit.createInventory(player, 9, ChatColor.GRAY + "Choose effects");

        // Creating ItemStacks for effects
        ItemStack strength = ItemUtils.createItem(Material.DIAMOND_SWORD, ChatColor.GOLD + "strength", null);
        ItemStack speed = ItemUtils.createItem(Material.FIREWORK_ROCKET, ChatColor.GOLD + "speed", null);
        ItemStack regeneration = ItemUtils.createItem(Material.BEACON, ChatColor.GOLD + "regeneration", null);
        ItemStack invisibility = ItemUtils.createItem(Material.GLASS, ChatColor.GOLD + "invisibility", null);
        ItemStack resistance = ItemUtils.createItem(Material.SHIELD, ChatColor.GOLD + "resistance", null);

        ItemStack summon = ItemUtils.createItem(Material.TNT, ChatColor.GREEN + "SUMMON THE ARMY", null);
        ItemStack back = ItemUtils.createItem(Material.BARRIER, ChatColor.RED + "Back to choosing type", null);

        openEffectsMenu.setItem(0, strength);
        openEffectsMenu.setItem(1, speed);
        openEffectsMenu.setItem(2, regeneration);
        openEffectsMenu.setItem(3, invisibility);
        openEffectsMenu.setItem(4, resistance);

        openEffectsMenu.setItem(7, summon);
        openEffectsMenu.setItem(8, back);

        player.openInventory(openEffectsMenu);
    }

    /*
     * Method to open the menu for selecting the level of effects for the army.
     */
    public void openLevelsMenu(CreateArmy army) {
        Player player = army.getPlayer();
        Inventory openLevelsMenu = Bukkit.createInventory(player, 9, ChatColor.GOLD + "Choose level of effect");

        // Creating ItemStacks for effect levels
        ItemStack level1 = ItemUtils.createItem(Material.WOODEN_SWORD, ChatColor.GOLD + "LEVEL I", null);
        ItemStack level2 = ItemUtils.createItem(Material.GOLDEN_SWORD, ChatColor.GOLD + "LEVEL II", null);
        ItemStack level3 = ItemUtils.createItem(Material.NETHERITE_SWORD, ChatColor.GOLD + "LEVEL III", null);
        ItemStack level4 = ItemUtils.createItem(Material.ENCHANTED_GOLDEN_APPLE, ChatColor.GOLD + "LEVEL IV", null);

        ItemStack back = ItemUtils.createItem(Material.RED_WOOL, ChatColor.GOLD + "Back", null);

        openLevelsMenu.setItem(0, level1);
        openLevelsMenu.setItem(1, level2);
        openLevelsMenu.setItem(2, level3);
        openLevelsMenu.setItem(3, level4);

        openLevelsMenu.setItem(8, back);

        player.openInventory(openLevelsMenu);
    }

    /*
     * Method to summon mobs around the player based on army specifications.
     */
    public void summonMobsAroundPlayer(CreateArmy army) {
        Player player = army.getPlayer();
        String type = army.getType();
        EntityType entity;

        switch (type) {
            case "iron golem":
                entity = EntityType.IRON_GOLEM;
                break;
            case "zombie":
                entity = EntityType.ZOMBIE;
                break;
            case "creeper":
                entity = EntityType.CREEPER;
                break;
            case "snow golem":
                entity = EntityType.SNOWMAN;
                break;
            case "sniffer":
                entity = EntityType.SNIFFER;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        // Get the player's location
        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        // Specify the number of mobs to summon (e.g., 20)
        int numberOfMobsToSummon = 20;

        // Define the radius of the circle
        double radius = 10.0; // Adjust the radius as needed

        // Loop to summon the desired number of mobs around the player in a circle
        for (int i = 0; i < numberOfMobsToSummon; i++) {
            // Calculate the angle for this mob's position around the circle
            double angle = (2 * Math.PI / numberOfMobsToSummon) * i;

            // Calculate the position around the circle
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            // Create the mob's location
            Location mobLocation = playerLocation.clone().add(xOffset, 0, zOffset);

            // Summon a mob of the specified type
            LivingEntity mob = (LivingEntity) world.spawnEntity(mobLocation, entity);

            // Apply effects from the army's effects list
            for (Effect effect : army.getEffects()) {
                PotionEffectType effectType = effect.getEffectType();
                int level = effect.getLevel();

                if (level > 0) {
                    PotionEffect potionEffect = new PotionEffect(effectType, Integer.MAX_VALUE, level);
                    mob.addPotionEffect(potionEffect);
                }
            }
        }
    }
}
