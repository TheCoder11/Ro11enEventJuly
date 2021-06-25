package com.somemone.basehold;

import com.somemone.basehold.base.Base;
import com.somemone.basehold.command.DefineCommand;
import com.somemone.basehold.teams.BaseTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class BaseHold extends JavaPlugin {

    public static Base base;

    public static HashMap<Player, BaseTeam> teams = new HashMap<>();

    @Override
    public void onEnable() {
        this.getCommand("definebase").setExecutor(new DefineCommand());
    }

    @Override
    public void onDisable() {
        this.base.bossBar.removeAll();
    }


}
