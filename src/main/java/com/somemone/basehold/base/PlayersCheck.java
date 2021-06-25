package com.somemone.basehold.base;

import com.massivecraft.factions.FPlayer;
import com.somemone.basehold.BaseHold;
import com.somemone.basehold.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayersCheck implements Runnable {
    private Base base;

    public PlayersCheck(Base base) {
        this.base = base;
    }

    public void addIncrement() {

        double currentProgress = base.bossBar.getProgress();

        if (currentProgress >= 1.0) {
            return;
        }

        base.bossBar.setTitle("Gaining: " + base.currentHolder);

        if ((currentProgress + base.increment) >= 1.0) {

            base.bossBar.setTitle("Held by: " + base.currentHolder);

        }

        this.base.bossBar.setProgress( (currentProgress + base.increment) );

    }

    public void removeIncrement() {

        if (base.bossBar.getProgress() <= 0.0) return;

        double currentProgress = base.bossBar.getProgress() - base.increment;

        base.bossBar.setTitle("Losing: " + base.currentHolder);

        if (currentProgress <= 0.0) {
            base.bossBar.setTitle("No Holder");
        }

        if (currentProgress < 0.0) {
            currentProgress = 0.0;
        }

        this.base.bossBar.setProgress(currentProgress);

    }

    @Override
    public void run() {


        // Adds any online players to BossBar if not already present

        Bukkit.getOnlinePlayers().forEach((player) -> {
            if (!(base.bossBar.getPlayers().contains(player))) {
                base.bossBar.addPlayer(player);
            }
        });

        // Checks for players in base

        ArrayList<Player> playersInBase = base.getPlayersInBase();
        ArrayList<String> baseHolders = new ArrayList<>();

        playersInBase.forEach((player) -> {

            if (BaseHold.findTeamFromPlayer(player) != null) {
                baseHolders.add(player.getName());
            } else {
                Team team = BaseHold.findTeamFromPlayer(player);
                baseHolders.add(team.name);
            }

        });

        // Assign behavior

        if (baseHolders.size() > 1) {
            if (base.bossBar.getProgress() < 0.99) {
                this.removeIncrement();
            }
        } else if (baseHolders.size() == 1) {
            if ( baseHolders.get(0).equals(base.currentHolder) ) {
                this.addIncrement();
            } else {

                if (base.bossBar.getProgress() <= 0.0) {
                    this.base.currentHolder = baseHolders.get(0);
                    this.addIncrement();
                } else {
                    this.removeIncrement();
                }
            }
        } else if (baseHolders.size() == 0) {
            if (base.bossBar.getProgress() < 0.99) {
                this.removeIncrement();
            }
        }
    }

}
