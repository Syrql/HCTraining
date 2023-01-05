package fr.syrql.hctraining.match;

import fr.syrql.hctraining.arena.data.Arena;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Match {

    private Arena arena;
    private Player playerOne;
    private Player playerTwo;
    private List<UUID> spectators;

    public Match(Arena arena, Player playerOne, Player playerTwo, List<UUID> spectators) {
        this.arena = arena;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.spectators = spectators;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public List<UUID> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<UUID> spectators) {
        this.spectators = spectators;
    }
}
