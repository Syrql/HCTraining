package fr.syrql.hctraining.arena;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.utils.ArenaUtils;

public class ArenaManager {

    private final HCTraining hcTraining;

    public ArenaManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
    }

    public Arena getRandomArena() {
        return ArenaUtils.getRandom(this.hcTraining.getArenaProvider().getArenas());
    }
}
