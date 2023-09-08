package me.eranspigel.army;

import me.eranspigel.army.armies.CreateArmy;
import me.eranspigel.army.armies.Effect;
import me.eranspigel.army.commands.ArmyCommand;
import me.eranspigel.army.listeners.MenuHandler;
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
import java.util.ArrayList;
import java.util.HashMap;

public final class Army extends JavaPlugin {

    public HashMap<Player, CreateArmy> armyHashMap = new HashMap<>();

    @Override
    public void onEnable() {

        // Plugin startup logic
        System.out.println("Plugin has started up.");

        getCommand("summonarmy").setExecutor(new ArmyCommand(this));

        getServer().getPluginManager().registerEvents(new MenuHandler(this), this );



    }
    public void openMainMenu(Player player){

        Inventory main_menu = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Summon your personal armies");

        //Options for main menu
        ItemStack create = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta create_meta = create.getItemMeta();
        create_meta.setDisplayName(ChatColor.GREEN + "Summon");
        ArrayList<String> create_lore = new ArrayList<>();
        create_lore.add(ChatColor.DARK_PURPLE + "Summon an army.");
        create_meta.setLore(create_lore);
        create.setItemMeta(create_meta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(close_meta);

        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        player.openInventory(main_menu);
    }

    public void openTypeMenu(Player player){
        Inventory Type_menu = Bukkit.createInventory(player, 9, ChatColor.RED + "Type of your army");

        //Options
        ItemStack ironGolem = new ItemStack(Material.IRON_BLOCK);
        ItemStack zombie = new ItemStack(Material.ZOMBIE_HEAD);
        ItemStack snowGolem = new ItemStack(Material.CARVED_PUMPKIN);
        ItemStack creepers = new ItemStack(Material.CREEPER_HEAD);
        ItemStack sniffer = new ItemStack(Material.PLAYER_HEAD); //Finish creating your armor stand
        ItemStack closeTypeMenu = new ItemStack(Material.BARRIER);

        ItemMeta ironGolem_meta = ironGolem.getItemMeta();
        ironGolem_meta.setDisplayName(ChatColor.YELLOW + "Iron golem");

        ItemMeta zombie_meta = zombie.getItemMeta();
        zombie_meta.setDisplayName(ChatColor.RED + "Zombie"); // Add this line to set the display name


        ItemMeta snowGolem_meta = snowGolem.getItemMeta();
        snowGolem_meta.setDisplayName(ChatColor.AQUA + "snowGolem");

        ItemMeta creepers_meta = creepers.getItemMeta();
        creepers_meta.setDisplayName(ChatColor.GOLD + "creepers");

        ItemMeta sniffer_meta = sniffer.getItemMeta();
        sniffer_meta.setDisplayName(ChatColor.GOLD + "surprise");

        ItemMeta close_meta = closeTypeMenu.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Close");

        ironGolem.setItemMeta(ironGolem_meta);
        zombie.setItemMeta(zombie_meta);
        snowGolem.setItemMeta(snowGolem_meta);
        creepers.setItemMeta(creepers_meta);
        sniffer.setItemMeta(sniffer_meta);
        closeTypeMenu.setItemMeta(close_meta);

        //Add items
        Type_menu.setItem(0, ironGolem);
        Type_menu.setItem(1, zombie);
        Type_menu.setItem(2, snowGolem);
        Type_menu.setItem(3, creepers);
        Type_menu.setItem(4, sniffer);
        Type_menu.setItem(8, closeTypeMenu);


        player.openInventory(Type_menu);
    }

    public void openEffectsMenu(CreateArmy army){
        Player player = army.getPlayer();
        Inventory openEffectsMenu = Bukkit.createInventory(player, 9, ChatColor.GRAY + "Choose effects");

        ItemStack strength = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack speed = new ItemStack(Material.FIREWORK_ROCKET);
        ItemStack regeneration = new ItemStack(Material.BEACON);
        ItemStack invisibility = new ItemStack(Material.GLASS);
        ItemStack resistance = new ItemStack(Material.SHIELD);

        ItemMeta strength_meta = strength.getItemMeta();
        strength_meta.setDisplayName(ChatColor.GOLD + "strength");
        strength.setItemMeta(strength_meta);

        ItemMeta speed_meta = speed.getItemMeta();
        speed_meta.setDisplayName(ChatColor.GOLD + "speed");
        speed.setItemMeta(speed_meta);

        ItemMeta regeneration_meta = regeneration.getItemMeta();
        regeneration_meta.setDisplayName(ChatColor.GOLD + "regeneration");
        regeneration.setItemMeta(regeneration_meta);

        ItemMeta invisibility_meta = invisibility.getItemMeta();
        invisibility_meta.setDisplayName(ChatColor.GOLD + "invisibility");
        invisibility.setItemMeta(invisibility_meta);

        ItemMeta resistance_meta = resistance.getItemMeta();
        resistance_meta.setDisplayName(ChatColor.GOLD + "resistance");
        resistance.setItemMeta(resistance_meta);

        ItemStack summon = new ItemStack(Material.TNT);
        ItemMeta summon_meta = summon.getItemMeta();
        summon_meta.setDisplayName(ChatColor.GREEN + "SUMMON THE ARMY");
        summon.setItemMeta(summon_meta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.RED + "Back to choosing type");
        back.setItemMeta(back_meta);

        openEffectsMenu.setItem(0, strength);
        openEffectsMenu.setItem(1, speed);
        openEffectsMenu.setItem(2, regeneration);
        openEffectsMenu.setItem(3, invisibility);
        openEffectsMenu.setItem(4, resistance);

        openEffectsMenu.setItem(7, summon);
        openEffectsMenu.setItem(8, back);

        player.openInventory(openEffectsMenu);
    }

    public void openLevelsMenu(CreateArmy army){
        Player player = army.getPlayer();
        Inventory openLevelsMenu = Bukkit.createInventory(player, 9, ChatColor.GOLD + "Choose level of effect");

        ItemStack level1 = new ItemStack(Material.WOODEN_SWORD);
        ItemStack level2 = new ItemStack(Material.GOLDEN_SWORD);
        ItemStack level3 = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack level4 = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        ItemStack back = new ItemStack(Material.RED_WOOL);

        ItemMeta level1_meta = level1.getItemMeta();
        level1_meta.setDisplayName(ChatColor.GOLD + "LEVEL I");
        level1.setItemMeta(level1_meta);

        ItemMeta level2_meta = level2.getItemMeta();
        level2_meta.setDisplayName(ChatColor.GOLD + "LEVEL II");
        level2.setItemMeta(level2_meta);

        ItemMeta level3_meta = level3.getItemMeta();
        level3_meta.setDisplayName(ChatColor.GOLD + "LEVEL III");
        level3.setItemMeta(level3_meta);

        ItemMeta level4_meta = level4.getItemMeta();
        level4_meta.setDisplayName(ChatColor.GOLD + "LEVEL IV");
        level4.setItemMeta(level4_meta);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.GOLD + "Back");
        back.setItemMeta(back_meta);

        openLevelsMenu.setItem(0, level1);
        openLevelsMenu.setItem(1, level2);
        openLevelsMenu.setItem(2, level3);
        openLevelsMenu.setItem(3, level4);

        openLevelsMenu.setItem(8, back);


        player.openInventory(openLevelsMenu);
    }

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



