package fr.syrql.hctraining.inventory.type;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.queue.QueueType;
import fr.syrql.hctraining.queue.io.IQueue;
import fr.syrql.hctraining.queue.type.unranked.HCFQueueUnranked;
import fr.syrql.hctraining.utils.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UnrankedInventory {
    private final HCTraining hcTraining;

    public UnrankedInventory(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    public void openUnrankedInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "§aNon-Classé");

        for (int i : new int[]{0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44}) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
        }

        IQueue iQueue = this.hcTraining.getQueueManager().getQueues().stream().filter(queue -> queue.getQueueType() == QueueType.UNRANKED).findFirst().orElse(null);

        if (iQueue == null) return;

        if (iQueue instanceof HCFQueueUnranked) {

            HCFQueueUnranked queue = (HCFQueueUnranked) iQueue;

            if (queue.getQueueType() == QueueType.UNRANKED) {
                inventory.setItem(22, new ItemBuilder(Material.BLAZE_ROD).setName("§aNon-Classé")
                        .setLore("", "§eEn jeu: §f0", "§eEn queue: §f" + iQueue.getQueues().size())
                        .toItemStack());
            }
        }
        player.openInventory(inventory);
    }
}
