package fr.syrql.hctraining.scoreboard.adapter;

import fr.syrql.hctraining.scoreboard.PlayerScoreboard;

public interface ScoreAdapter {

    String getTitle();

    void updateLines(PlayerScoreboard scoreboard);
}
