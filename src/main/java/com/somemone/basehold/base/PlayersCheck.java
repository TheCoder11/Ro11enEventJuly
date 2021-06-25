package com.somemone.basehold.base;

import com.somemone.basehold.BaseHold;
import com.somemone.basehold.teams.BaseTeam;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;

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

        base.bossBar.setTitle("Gaining: " + base.currentHolder.getTitle());
        base.bossBar.setColor(base.currentHolder.getColor());

        if ((currentProgress + base.increment) >= 1.0) {

            base.bossBar.setTitle("Held by: " + base.currentHolder.getTitle());

        }

        this.base.bossBar.setProgress( (currentProgress + base.increment) );

    }

    public void removeIncrement() {

        if (base.bossBar.getProgress() <= 0.0) return;

        double currentProgress = base.bossBar.getProgress() - base.increment;

        base.bossBar.setTitle("Losing: " + base.currentHolder.getTitle());

        if (currentProgress <= 0.0) {
            base.bossBar.setTitle("No Holder");
            base.bossBar.setColor(BarColor.WHITE);
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
        ArrayList<BaseTeam> baseHolders = new ArrayList<>();

        playersInBase.forEach((player) -> {

            BaseTeam team = BaseHold.teams.get(player);
            if (!baseHolders.contains(team)) {
                baseHolders.add(team);
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
