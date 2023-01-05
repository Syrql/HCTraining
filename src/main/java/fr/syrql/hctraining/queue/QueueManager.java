package fr.syrql.hctraining.queue;

import fr.syrql.hctraining.HCTraining;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class QueueManager {

    private final HCTraining hcTraining;
    private final LinkedList<Player> playersQueue;

    public QueueManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.playersQueue = new LinkedList<>();
    }

    public void addToQueue(Player player)  {
        this.playersQueue.addLast(player);
    }

    public void removeFromQueue(Player player) {
        this.playersQueue.remove(player);
    }

    public Player peekFirst() {

        if (this.playersQueue.isEmpty()) return null;

        Player player = this.playersQueue.get(0);
        this.playersQueue.remove(player);
        return player;
    }

    public Player peekSecond() {

        if (this.playersQueue.isEmpty()) return null;

        Player player = this.playersQueue.get(0);
        this.playersQueue.remove(player);
        return player;
    }

    public LinkedList<Player> getPlayersQueue() {
        return playersQueue;
    }
}
