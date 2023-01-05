package fr.syrql.hctraining.selector.manager;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.selector.Selector;
import fr.syrql.hctraining.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SelectorManager {

    private final HCTraining hcTraining;
    private HashMap<Player, Selector> selectorMap;

    public SelectorManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.selectorMap = new HashMap<>();
    }

    public void addPlayerSelector(Player player) {
        selectorMap.remove(player);
        this.selectorMap.put(player, new Selector(null, null));
        player.getInventory().addItem(this.getSelectorItem());
    }

    public void removePlayerSelector(Player player) {
        selectorMap.remove(player);
        player.getInventory().remove(this.getSelectorItem());
    }

    public HashMap<Player, Selector> getSelectorMap() {
        return selectorMap;
    }

    public ItemStack getSelectorItem() {
        return new ItemBuilder(Material.BONE)
                .setName("§b§lSélécteur")
                .toItemStack();
    }
}
