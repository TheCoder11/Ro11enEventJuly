package com.somemone.basehold.base;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.somemone.basehold.BaseHold;
import com.somemone.basehold.teams.BaseTeam;
import com.somemone.basehold.util.DistanceFinder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Base {



    private final Location location1;
    private final Location location2;

    public HashMap<BaseTeam, Integer> scores;

    public static NamespacedKey barKey = new NamespacedKey(BaseHold.getPlugin(BaseHold.class), "basehold-bossbar-key");

    public double increment = 1.0 / 12.0;

    public BossBar bossBar;

    public BaseTeam currentHolder;

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

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("BaseHold-1", "dummy",
                ChatColor.translateAlternateColorCodes('&', "&a&1<< &2&1SCORES &a&1>>"));

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = obj.getScore(ChatColor.BLUE + "=-=-=-=-=-=-=-=-=");
        score.setScore(8);

        Score score2 = obj.getScore(ChatColor.RED + "Team Red: " + scores.get(BaseTeam.RED));
        score2.setScore(7);

        Score score3 = obj.getScore(ChatColor.BLUE + "Team Blue: " + scores.get(BaseTeam.BLUE));
        score2.setScore(6);

        Score score4 = obj.getScore(ChatColor.YELLOW + "Team Yellow: " + scores.get(BaseTeam.YELLOW));
        score2.setScore(5);

        Score score5 = obj.getScore(ChatColor.GREEN + "Team Green: " + scores.get(BaseTeam.GREEN));
        score2.setScore(4);

        Score score6 = obj.getScore(ChatColor.LIGHT_PURPLE + "Team Pink: " + scores.get(BaseTeam.PINK));
        score2.setScore(3);

        Score score7 = obj.getScore(ChatColor.DARK_PURPLE + "Team Purple: " + scores.get(BaseTeam.PURPLE));
        score2.setScore(2);

        Score score8 = obj.getScore(ChatColor.WHITE + "Team White: " + scores.get(BaseTeam.WHITE));
        score2.setScore(1);

        player.setScoreboard(board);
    }





}
