package fr.syrql.zevents.utils.cube;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Cuboid {

    private final int xMin;
    private final int xMax;

    private final int yMin;
    private final int yMax;
    private final int zMin;
    private final int zMax;

    private final World world;

    public Cuboid(final Location point1, final Location point2) {
        this.xMin = Math.min(point1.getBlockX(), point2.getBlockX());
        this.xMax = Math.max(point1.getBlockX(), point2.getBlockX());
        this.yMin = Math.min(point1.getBlockY(), point2.getBlockY());
        this.yMax = Math.max(point1.getBlockY(), point2.getBlockY());
        this.zMin = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.zMax = Math.max(point1.getBlockZ(), point2.getBlockZ());

        this.world = point1.getWorld();
    }

    private boolean contains(World world, int x, int y, int z) {
        return world.getName().equals(this.world.getName()) && x >= xMin && x <= xMax && y >= yMin && y <= yMax && z >= zMin && z <= zMax;
    }

    private boolean contains(Location loc) {
        return this.contains(Objects.requireNonNull(loc.getWorld()), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public boolean isIn(final Player player) {
        return this.contains(player.getLocation());
    }

    public boolean isIn(final Location location) {
        return this.contains(location);
    }
}