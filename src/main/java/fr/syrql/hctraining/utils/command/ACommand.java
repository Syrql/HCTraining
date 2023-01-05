package fr.syrql.spigot.utils.command;

import fr.syrql.spigot.Hades;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ACommand implements CommandExecutor {

    private final String commandName;
    private final String permission;
    private final boolean consoleCanExecute;
    private final Hades hades;

    public ACommand(Hades hades, String commandName, String permission, boolean consoleCanExecute) {
        this.permission = permission;
        this.commandName = commandName;
        this.consoleCanExecute = consoleCanExecute;
        this.hades = hades;
        hades.getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getLabel().equalsIgnoreCase(commandName))
            return true;

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(hades.getConfigManager().getString("no-permission"));
            return true;
        }

        if (!consoleCanExecute && !(sender instanceof Player)) {
            sender.sendMessage(hades.getConfigManager().getString("not-player"));
            return true;
        }
        return execute(sender, args);
    }

    public abstract boolean execute(CommandSender sender, String[] args);
}
