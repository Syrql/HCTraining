package fr.syrql.hctraining.listener;

import fr.syrql.hctraining.HCTraining;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SelectorListener implements Listener {

    private final HCTraining hcTraining;

    public SelectorListener(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @EventHandler
    public void onBreak(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getPlayer().getItemInHand();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (itemStack.getType() != Material.BONE) return;
        if (!itemStack.hasItemMeta()) return;
        if (!itemStack.getItemMeta().hasDisplayName()) return;
        if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§b§lSélécteur")) {
            event.setCancelled(true);

            switch (event.getAction()) {
                case LEFT_CLICK_BLOCK:
                    this.hcTraining.getSelectorManager().getSelectorMap().get(player)
                            .setLocationOne(event.getClickedBlock().getLocation());
                    player.sendMessage("§bTu viens de placer la première localisation");
                    break;
                case RIGHT_CLICK_BLOCK:
                    this.hcTraining.getSelectorManager().getSelectorMap().get(player)
                            .setLocationTwo(event.getClickedBlock().getLocation());
                    player.sendMessage("§bTu viens de placer la seconde localisation");
                    break;
                case LEFT_CLICK_AIR:
                    this.hcTraining.getSelectorManager().removePlayerSelector(player);
                    player.sendMessage("§bTu as annulé la sélection.");
                    break;
                default:
                    break;
            }
        }
    }
}
