package fr.syrql.hctraining.queue.type.unranked;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.queue.QueueType;
import fr.syrql.hctraining.queue.io.IQueue;
import org.bukkit.entity.Player;

public class HCFQueueUnranked implements IQueue {

    @Override
    public QueueType getQueueType() {
        return QueueType.UNRANKED;
    }

    @Override
    public void addPlayer(Player player) {
        queue.add(player.getUniqueId());
    }

    @Override
    public void removePlayer(Player player) {
        queue.remove(player.getUniqueId());
    }
}
