
package me.eranspigel.army.commands;

/*
 * Import statements for necessary classes and packages.
 */
import me.eranspigel.army.Army;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * Class definition for the ArmyCommand implementing the CommandExecutor interface.
 */
public class ArmyCommand implements CommandExecutor {

    /*
     * Reference to the main plugin class.
     */
    Army plugin;

    /*
     * Constructor to initialize the ArmyCommand with the plugin instance.
     */
    public ArmyCommand(Army plugin) {
        this.plugin = plugin;
    }

    /*
     * Method to handle command execution.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if(sender instanceof Player){
            Player player = (Player) sender;
            // Open the main menu for the player
            plugin.openMainMenu(player);
        }
        return true;
    }
}
