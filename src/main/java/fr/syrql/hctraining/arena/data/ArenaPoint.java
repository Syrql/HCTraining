package fr.syrql.hctraining.arena.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaPoint {
    private String world;
    private int x, y, z;

    public ArenaPoint(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
    }
}