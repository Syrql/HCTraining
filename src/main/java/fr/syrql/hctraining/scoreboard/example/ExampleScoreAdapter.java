package fr.syrql.hctraining.scoreboard.example;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.profile.data.ProfileState;
import fr.syrql.hctraining.scoreboard.PlayerScoreboard;
import fr.syrql.hctraining.scoreboard.adapter.ScoreAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExampleScoreAdapter implements ScoreAdapter {

    private final HCTraining hcTraining;

    public ExampleScoreAdapter(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @Override
    public String getTitle() {
        return ChatColor.GOLD + "§bHCTraining §f❘ §fSaison I";
    }

    @Override
    public void updateLines(PlayerScoreboard scoreboard) {
        Player player = scoreboard.getPlayer();

        scoreboard.addLine(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "---------------");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        scoreboard.addLine("     §7§o" + dtf.format(now));
        scoreboard.addLine("");

        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());
        if (profile == null) return;

        System.out.println(profile.getProfileState());
        if (profile.getProfileState() == ProfileState.LOBBY) {
            scoreboard.addLine("§f• §bEn ligne: §f" + Bukkit.getOnlinePlayers().size());
            scoreboard.addLine("");
            scoreboard.addLine("§f• §bEn Queue: §f" + hcTraining.getQueueManager().getPlayersQueue().size());
            scoreboard.addLine("§f• §bEn Match: §f0");
        }

        if (profile.getProfileState() == ProfileState.QUEUE) {
            scoreboard.addLine("§f» §bNon-Classé");
            scoreboard.addLine("§f• §bEn Queue: §f" + hcTraining.getQueueManager().getPlayersQueue().size());
        }

        if (profile.getProfileState() == ProfileState.MATCH) {
            scoreboard.addLine("§f» §bNon-Classé");
            scoreboard.addLine("");
            scoreboard.addLine("§f• §bAdversdaire: §f" + profile.getCurrentEnemy());
        }

        scoreboard.addLine("");
        scoreboard.addLine("   §fplay.lazury.fr");
        scoreboard.addLine(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "---------------");
    }
}
