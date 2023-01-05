package fr.syrql.hctraining.task;

import fr.syrql.hctraining.HCTraining;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class QueueTask implements Runnable {

    private final HCTraining hcTraining;

    public QueueTask(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @Override
    public void run() {

        if (this.hcTraining.getQueueManager().getPlayersQueue().size() < 2) return;

        Player playerOne = this.hcTraining.getQueueManager().peekFirst();
        Player playerTwo = this.hcTraining.getQueueManager().peekSecond();

        Bukkit.broadcastMessage("§bRecherche d'adversaires en Unranked...");

        if (playerOne == null || playerTwo == null) return;

        this.hcTraining.getMatchManager().createMatch(playerOne, playerTwo);
    }
}
