package fr.syrql.hctraining.listener;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.inventory.type.UnrankedInventory;
import fr.syrql.hctraining.utils.ArenaUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class UnrankedSelectListener implements Listener {

    private final HCTraining hcTraining;

    public UnrankedSelectListener(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @EventHandler
    public void onClickSword(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = event.getPlayer().getItemInHand();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (itemStack.getType() != Material.IRON_SWORD) return;
        if (!itemStack.hasItemMeta()) return;
        if (!itemStack.getItemMeta().hasDisplayName()) return;

        if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§a✔ §bNon-classé")) {
            Collection<Arena> arenas = this.hcTraining.getArenaProvider().getArenas();
            if (arenas == null || arenas.isEmpty()) {
                player.sendMessage("§cIl n'y a pas d'arène de disponible.");
                return;
            }

            Arena arena = ArenaUtils.getRandom(arenas);

            if (arena.getPlayerLocationOne() == null || arena.getPlayerLocationTwo() == null) {
                player.sendMessage("§cAucune arène n'est disponible.");
                return;
            }

            if (arena.getX1() == -1 && arena.getZ1() == -1 && arena.getX2() == -1 && arena.getZ2() == -1) {
                player.sendMessage("§cIl n'y a pas de localisation disponible.");
                return;
            }

            new UnrankedInventory(hcTraining).openUnrankedInventory(player);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (itemStack.getType() != Material.BLAZE_ROD) return;

        if (event.getInventory().getName().equalsIgnoreCase("§aNon-Classé")) {
            event.setCancelled(true);
            player.closeInventory();

            if (!this.hcTraining.getQueueManager().getPlayersQueue().contains(player)) {
                this.hcTraining.getQueueManager().addToQueue(player);
                player.sendMessage("§bTu as été ajouté à la file d'attente.");
                this.hcTraining.getLobbyManager().giveWaitItems(player);
            }
        }
    }

    @EventHandler
    public void onClickLeave(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = event.getPlayer().getItemInHand();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (itemStack.getType() != Material.REDSTONE_TORCH_ON) return;
        if (!itemStack.hasItemMeta()) return;
        if (!itemStack.getItemMeta().hasDisplayName()) return;

        if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§4✘ §cQuitter la file d'attente")) {
            event.setCancelled(true);
            this.hcTraining.getQueueManager().removeFromQueue(player);
            player.sendMessage("§bTu as quitté la file d'attente.");
            this.hcTraining.getLobbyManager().giveLobbyItems(player);
        }
    }
}
