package fr.syrql.hctraining.commands;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.utils.command.ACommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectorCommand extends ACommand {
    private HCTraining hcTraining;

    public SelectorCommand(HCTraining hcTraining, String name, String permission, boolean canConsoleExecute) {
        super(hcTraining, name, permission, canConsoleExecute);
        this.hcTraining = hcTraining;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        this.hcTraining.getSelectorManager().addPlayerSelector(player);
        player.sendMessage("§bTu dois maintenant séléctionner deux points.");
        return true;
    }
}
