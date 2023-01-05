package fr.syrql.hctraining.commands;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.arena.data.ArenaPoint;
import fr.syrql.hctraining.arena.data.ArenaType;
import fr.syrql.hctraining.arena.factory.ArenaFactory;
import fr.syrql.hctraining.selector.Selector;
import fr.syrql.hctraining.utils.command.ACommand;
import fr.syrql.hctraining.utils.location.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ArenaCommand extends ACommand {

    private final HCTraining hcTraining;

    public ArenaCommand(HCTraining hcTraining, String name, String permission, boolean canConsoleExecute) {
        super(hcTraining, name, permission, canConsoleExecute);
        this.hcTraining = hcTraining;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§7§m-----------------------");
            player.sendMessage("");
            player.sendMessage("§6• §b/arena create");
            player.sendMessage("§6• §b/arena remove <id>");
            player.sendMessage("§6• §b/arena selector <id>");
            player.sendMessage("§6• §b/arena pos1 <id>");
            player.sendMessage("§6• §b/arena pos2 <id>");
            player.sendMessage("");
            player.sendMessage("§7§m-----------------------");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                String id = UUID.randomUUID().toString().substring(1, 6);
                this.hcTraining.getArenaProvider().provide(id, new ArenaFactory()
                        .create(id, player.getWorld().getName(), -1, -1, -1, -1, ArenaType.NORMAL, null, null));
                this.hcTraining.getArenaProvider().write();
                player.sendMessage("§bTu viens de créer l'arène " + id + "!");
            }
            return true;
        }

        if (args.length == 2) {

            Arena arena = this.hcTraining.getArenaProvider().get(args[1]);

            if (arena == null) {
                player.sendMessage("§cCette arène n'existe pas.");
                return false;
            }
            switch (args[0]) {
                case "remove":
                    this.hcTraining.getArenaProvider().remove(arena.getId());
                    player.sendMessage("§bTu as supprimé l'arène §f" + arena.getId() + "§b.");
                    break;
                case "selector":
                    if (!this.hcTraining.getSelectorManager().getSelectorMap().containsKey(player)) {
                        player.sendMessage("§bTu dois dabord créer une sélection.");
                        this.hcTraining.getSelectorManager().addPlayerSelector(player);
                        return false;
                    }

                    Selector selector = this.hcTraining.getSelectorManager().getSelectorMap().get(player);

                    if (selector.getLocationOne() == null || selector.getLocationTwo() == null) {
                        player.sendMessage("§cTu dois séléctionner deux positions.");
                        return false;
                    }
                    arena.setX1(selector.getLocationOne().getBlockX());
                    arena.setZ1(selector.getLocationOne().getBlockZ());

                    arena.setZ2(selector.getLocationTwo().getBlockZ());
                    arena.setZ2(selector.getLocationTwo().getBlockZ());

                    player.sendMessage("§bTu as changé les localisations de l'arène " + arena.getId() + ".");
                    break;
                case "pos1":
                case "pos2":
                    Location cornerOne = new Location(Bukkit.getWorld(arena.getWorld()), arena.getX1(), 0, arena.getZ1());
                    Location cornerTwo = new Location(Bukkit.getWorld(arena.getWorld()), arena.getX2(), 0, arena.getZ2());
                    Cuboid cuboid = new Cuboid(cornerOne, cornerTwo);

                    if (cuboid.isIn(player)) {
                        player.sendMessage("§cTu n'es pas dans l'arène!");
                        return false;
                    }

                    ArenaPoint arenaPoint = new ArenaPoint(player.getWorld().getName(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    if (args[0].equalsIgnoreCase("pos1")) {
                        arena.setPlayerLocationOne(arenaPoint);
                        player.sendMessage("Tu as modifié la position 1.");
                    } else {
                        arena.setPlayerLocationTwo(arenaPoint);
                        player.sendMessage("Tu as modifié la position 2.");
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}
