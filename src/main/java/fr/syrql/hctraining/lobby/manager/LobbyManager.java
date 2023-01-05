package fr.syrql.hctraining.lobby.manager;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.profile.data.ProfileState;
import fr.syrql.hctraining.utils.item.ItemBuilder;
import org.bukkit.Bukkit;
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
                .setName("§4✔ §bKits")
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

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.hidePlayer(player);
            player.hidePlayer(players);
        }

        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());
        if (profile == null) return;

        profile.setProfileState(ProfileState.LOBBY);
    }

    public void giveWaitItems(Player player) {

        ItemStack leave = new ItemBuilder(Material.REDSTONE_TORCH_ON)
                .setName("§4✘ §cQuitter la file d'attente")
                .toItemStack();

        player.getInventory().clear();
        player.getInventory().setItem(8, leave);
        this.clearArmor(player);

        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());
        if (profile == null) return;

        profile.setProfileState(ProfileState.QUEUE);
    }

    private void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
