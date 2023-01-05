package fr.syrql.hctraining.listener;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.match.Match;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.profile.data.ProfileState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MatchListener implements Listener {

    private final HCTraining hcTraining;

    public MatchListener(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());

        if (profile == null) return;
        if (event.getEntity().getKiller() == null) return;

        if (profile.getProfileState() == ProfileState.MATCH) {
            Match match = profile.getCurrentMatch();
            Player winner = player.getKiller();

            this.hcTraining.getMatchManager().stopMatch(winner, player, match);
        }
    }

    @EventHandler
    public void onDeath(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());

        if (profile == null) return;

        if (profile.getProfileState() == ProfileState.MATCH) {
            profile.setProfileState(ProfileState.LOBBY);

            Player winner = Bukkit.getPlayer(profile.getCurrentEnemy());
            Match match = profile.getCurrentMatch();

            this.hcTraining.getMatchManager().stopMatch(winner, player, match);
        }
    }
}
