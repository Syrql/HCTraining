package fr.syrql.hctraining.queue.io;

import fr.syrql.hctraining.queue.QueueType;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.UUID;

public interface IQueue {

    LinkedList<UUID> queue = new LinkedList<>();

    QueueType getQueueType();

    void addPlayer(Player player);

    void removePlayer(Player player);

    default UUID peekFirstPlayer() {
        if (queue.size() < 2) return null;

        return queue.get(0);
    }

    default UUID peekSecondPlayer() {
        if (queue.size() < 2) return null;

        return queue.get(1);
    }

    default LinkedList<UUID> getQueues() {
        return queue;
    }
}
