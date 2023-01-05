package fr.syrql.hctraining.manager;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {

    private final HCTraining hcTraining;

    public LobbyManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    public void giveLobbyItems(Player player) {

        ItemStack unranked = new ItemBuilder(Material.IRON_SWORD)
                .setName("§a✔ §bNon-classé")
                .toItemStack();

        ItemStack ranked = new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("§4✔ §bClassé")
                .toItemStack();

        ItemStack party = new ItemBuilder(Material.BOOK_AND_QUILL)
                .setName("§4✔ §bPartie")
                .toItemStack();

        ItemStack kitEdit = new ItemBuilder(Material.CHEST)
                .setName("§4✔ §bPartie")
                .toItemStack();

        ItemStack train = new ItemBuilder(Material.ENCHANTMENT_TABLE)
                .setName("§4✔ §bEntrainement")
                .toItemStack();

        player.getInventory().clear();
        player.getInventory().setItem(0, unranked);
        player.getInventory().setItem(1, ranked);
        player.getInventory().setItem(4, party);
        player.getInventory().setItem(7, kitEdit);
        player.getInventory().setItem(8, train);

        this.clearArmor(player);
    }

    private void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
