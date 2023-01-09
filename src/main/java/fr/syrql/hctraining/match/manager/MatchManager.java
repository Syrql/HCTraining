package fr.syrql.hctraining.match.manager;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.match.Match;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.profile.data.ProfileState;
import fr.syrql.hctraining.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {

    private final HCTraining hcTraining;
    private List<Match> matchs;

    public MatchManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.matchs = new ArrayList<>();
    }

    public void createMatch(Player playerOne, Player playerTwo) {
        Match match = new Match(this.hcTraining.getArenaManager().getRandomArena(), playerOne, playerTwo, new ArrayList<>());
        this.matchs.add(match);

        this.setupPlayer(playerOne, playerTwo, match);

        playerOne.teleport(match.getArena().getPlayerLocationOne().toLocation());
        playerTwo.teleport(match.getArena().getPlayerLocationTwo().toLocation());
    }

    public void stopMatch(Player winner, Player loser, Match match) {

        Profile profileWinner = this.hcTraining.getProfileProvider().get(winner.getUniqueId());
        Profile profileLoser = this.hcTraining.getProfileProvider().get(loser.getUniqueId());
        this.stopPlayer(winner, profileWinner);
        this.stopPlayer(loser, profileLoser);

        this.matchs.remove(match);
        winner.sendMessage("§bTu as gagné!");
        loser.sendMessage("§bTu as perdu!");
    }

    private void stopPlayer(Player player, Profile profile) {
        profile.setProfileState(ProfileState.LOBBY);

        this.hcTraining.getServer().getScheduler().scheduleSyncDelayedTask(this.hcTraining,
                () -> this.hcTraining.getLobbyManager().giveLobbyItems(player), 3L);
    }

    private void setupPlayer(Player player, Player target, Match match) {
        Profile profile = this.hcTraining.getProfileProvider().get(player.getUniqueId());
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        if (profile == null) return;

        profile.setProfileState(ProfileState.MATCH);
        profile.setCurrentEnemy(target.getName());

        this.hcTraining.getServer().getScheduler().scheduleSyncDelayedTask(this.hcTraining, () -> player.showPlayer(target));

        profile.setCurrentMatch(match);
        player.getInventory().setItem(0, new ItemBuilder(Material.WOOD_SWORD).setName("§4§lDESTRUCTION").toItemStack());
    }
}
