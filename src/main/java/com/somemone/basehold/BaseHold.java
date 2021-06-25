package com.somemone.basehold;

import com.somemone.basehold.base.Base;
import com.somemone.basehold.command.AcceptInviteCommand;
import com.somemone.basehold.command.ClearAllBarsCommand;
import com.somemone.basehold.command.DefineCommand;
import com.somemone.basehold.teams.ActiveInvite;
import com.somemone.basehold.teams.Team;
import com.somemone.basehold.teams.TeamsCommand;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BaseHold extends JavaPlugin {

    public static Base base;
    public static ArrayList<Team> teams;
    public static ArrayList<ActiveInvite> activeInvites = new ArrayList<>();

    @Override
    public void onEnable() {
        this.getCommand("definebase").setExecutor(new DefineCommand());
    }

    @Override
    public void onDisable() {
        this.base.bossBar.removeAll();
    }

    public static Team findTeamFromPlayer(Player player) {
        for (Team team : teams) {
            if (team.players.contains(player)) {
                return team;
            }
        }

        return null;
    }


}
