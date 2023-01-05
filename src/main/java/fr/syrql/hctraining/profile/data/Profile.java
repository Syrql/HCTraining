package fr.syrql.hctraining.profile.data;

import fr.syrql.hctraining.match.Match;

import java.util.UUID;

public class Profile {

    private UUID uuid;
    private int elo;
    private int winMatch;
    private int lostMatch;
    private UUID lastFight;
    private transient Match currentMatch;
    private transient String currentEnemy;
    private transient ProfileState profileState;

    public Profile(UUID uuid, int elo, int winMatch, int lostMatch, UUID lastFight) {
        this.uuid = uuid;
        this.elo = elo;
        this.winMatch = winMatch;
        this.lostMatch = lostMatch;
        this.lastFight = lastFight;
        this.profileState = ProfileState.LOBBY;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public int getWinMatch() {
        return winMatch;
    }

    public void setWinMatch(int winMatch) {
        this.winMatch = winMatch;
    }

    public int getLostMatch() {
        return lostMatch;
    }

    public void setLostMatch(int lostMatch) {
        this.lostMatch = lostMatch;
    }

    public UUID getLastFight() {
        return lastFight;
    }

    public void setLastFight(UUID lastFight) {
        this.lastFight = lastFight;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public String getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(String currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public ProfileState getProfileState() {
        return profileState;
    }

    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }
}
