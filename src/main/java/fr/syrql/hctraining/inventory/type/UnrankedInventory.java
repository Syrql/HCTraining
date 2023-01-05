package fr.syrql.hctraining.inventory.type;

import fr.syrql.hctraining.HCTraining;
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

        for (int i : new int[]{0, 1, 7, 8, 9, 17, 27, 34, 35, 36, 42, 43}) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 0).setName("").toItemStack());
        }

        inventory.setItem(22, new ItemBuilder(Material.BLAZE_ROD).setName("§aNon-Classé")
                .setLore("", "§eEn jeu: §f0", "§eEn queue: §f" + hcTraining.getQueueManager().getPlayersQueue().size())
                .toItemStack());

        player.openInventory(inventory);
    }
}
