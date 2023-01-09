package fr.syrql.hctraining.task;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.queue.io.IQueue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class QueueTask implements Runnable {

    private final HCTraining hcTraining;

    public QueueTask(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @Override
    public void run() {

        for (IQueue iQueue : this.hcTraining.getQueueManager().getQueues()) {
            if (iQueue.getQueues().size() < 2) return;
            Player playerOne = Bukkit.getPlayer(iQueue.peekFirstPlayer());
            Player playerTwo = Bukkit.getPlayer(iQueue.peekSecondPlayer());
            if (playerOne == null || playerTwo == null) return;

            this.hcTraining.getMatchManager().createMatch(playerOne, playerTwo);
        }
    }
}
