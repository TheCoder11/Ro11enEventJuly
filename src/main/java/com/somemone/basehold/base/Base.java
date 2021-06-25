package com.somemone.basehold.base;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.somemone.basehold.BaseHold;
import com.somemone.basehold.util.DistanceFinder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class Base {

    private final Location location1;
    private final Location location2;

    public static NamespacedKey barKey = new NamespacedKey(BaseHold.getPlugin(BaseHold.class), "basehold-bossbar-key");

    public double increment = 1.0 / 12.0;

    public BossBar bossBar;

    public String currentHolder;

    public Base (Location location1, Location location2) {
        this.location1 = location1;
        this.location2 = location2;

        bossBar = Bukkit.getServer().createBossBar(barKey, "No Holder", BarColor.BLUE, BarStyle.SEGMENTED_12);

        Bukkit.getOnlinePlayers().forEach((player) -> bossBar.addPlayer(player));
    }

    public ArrayList<Player> getPlayersInBase () {

        // Iterates through online player, checks players in base;

        ArrayList<Player> players = new ArrayList<Player>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (DistanceFinder.inRegion(player, location1, location2)) {
                players.add(player);
            }
        }

        return players;

    }



}
