package fr.syrql.hctraining.listener;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.profile.data.ProfileState;
import fr.syrql.hctraining.profile.factory.ProfileFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final HCTraining hcTraining;

    public PlayerJoinListener(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());

        this.hcTraining.getServer().getScheduler().scheduleSyncDelayedTask(this.hcTraining, () -> {
            if (profile == null) {
                Profile playerProfile = new ProfileFactory().create(player.getUniqueId());
                this.hcTraining.getProfileProvider().provide(player.getUniqueId(), playerProfile);
                playerProfile.setProfileState(ProfileState.LOBBY);
            } else {
                profile.setProfileState(ProfileState.LOBBY);
            }
        },2L);

        this.hcTraining.getServer().getScheduler().scheduleSyncDelayedTask(this.hcTraining,
                () -> this.hcTraining.getLobbyManager().giveLobbyItems(player), 3L);

        this.hcTraining.getConfigManager().getStringList("JOIN-MESSAGE").forEach(player::sendMessage);

    }
}
