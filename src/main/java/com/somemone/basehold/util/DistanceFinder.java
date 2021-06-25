package com.somemone.basehold.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DistanceFinder {

    public static boolean inRegion(Player p, Location loc1, Location loc2) {
        double largeX, smallX, largeY, smallY, largeZ, smallZ;
        if (loc1.getX() > loc2.getX()) {
            largeX = loc1.getX();
            smallX = loc2.getX();
        } else {
            largeX = loc2.getX();
            smallX = loc1.getX();
        }

        if (loc1.getY() > loc2.getY()) {
            largeY = loc1.getY();
            smallY = loc2.getY();
        } else {
            largeY = loc2.getY();
            smallY = loc1.getY();
        }

        if (loc1.getZ() > loc2.getZ()) {
            largeZ = loc1.getZ();
            smallZ = loc2.getZ();
        } else {
            largeZ = loc2.getZ();
            smallZ = loc1.getZ();
        }

        return (p.getLocation().getX() <= largeX) && (p.getLocation().getX() >= smallX) &&
                (p.getLocation().getY() <= largeY) && (p.getLocation().getY() >= smallY) &&
                (p.getLocation().getZ() <= largeZ) && (p.getLocation().getZ() >= smallZ);
    }
}
