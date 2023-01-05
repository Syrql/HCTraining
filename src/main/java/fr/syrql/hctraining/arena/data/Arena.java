package fr.syrql.hctraining.arena.data;

import java.util.UUID;

public class Arena {

    private String id;
    private String world;
    private int x1, z1;
    private int x2, z2;
    private ArenaType arenaType;
    private ArenaPoint playerLocationOne;
    private ArenaPoint playerLocationTwo;

    public Arena(String id, String world, int x1, int z1, int x2, int z2, ArenaType arenaType, ArenaPoint playerLocationOne, ArenaPoint playerLocationTwo) {
        this.id = id;
        this.world = world;
        this.x1 = x1;
        this.z1 = z1;
        this.x2 = x2;
        this.z2 = z2;
        this.arenaType = arenaType;
        this.playerLocationOne = playerLocationOne;
        this.playerLocationTwo = playerLocationTwo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getZ1() {
        return z1;
    }

    public void setZ1(int z1) {
        this.z1 = z1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getZ2() {
        return z2;
    }

    public void setZ2(int z2) {
        this.z2 = z2;
    }

    public ArenaType getArenaType() {
        return arenaType;
    }

    public void setArenaType(ArenaType arenaType) {
        this.arenaType = arenaType;
    }

    public ArenaPoint getPlayerLocationOne() {
        return playerLocationOne;
    }

    public void setPlayerLocationOne(ArenaPoint playerLocationOne) {
        this.playerLocationOne = playerLocationOne;
    }

    public ArenaPoint getPlayerLocationTwo() {
        return playerLocationTwo;
    }

    public void setPlayerLocationTwo(ArenaPoint playerLocationTwo) {
        this.playerLocationTwo = playerLocationTwo;
    }
}