package fr.syrql.hctraining.arena.factory;

import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.arena.data.ArenaPoint;
import fr.syrql.hctraining.arena.data.ArenaType;

import java.util.UUID;

public class ArenaFactory {

    public Arena create(String id, String world, int x1, int z1, int x2, int z2, ArenaType arenaType, ArenaPoint arenaPointOne, ArenaPoint arenaPointTwo) {
        return new Arena(id, world, x1, z1, x2, z2, arenaType, arenaPointOne, arenaPointTwo);
    }
}
