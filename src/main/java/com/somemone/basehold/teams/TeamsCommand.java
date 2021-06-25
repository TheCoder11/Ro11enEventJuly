package com.somemone.basehold.teams;

import com.somemone.basehold.BaseHold;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        switch (args[0]) {

            case "new":
                if (args.length != 2) {
                    player.sendMessage("State a Team Name!");
                    return false;
                } else {
                    Team team = new Team(args[1]);
                    team.players.add(player);
                    BaseHold.teams.add(team);
                }

                break;
            case "add":
                if (args.length != 2) {
                    player.sendMessage("Give a player!");
                    return false;
                } else {
                    if (Bukkit.getPlayer(args[1]) != null) {

                        boolean noPlayerFound = true;
                        Team teamFound = null;

                        for (Team team : BaseHold.teams) {
                            if (team.players.contains(player)) {
                                noPlayerFound = true;
                                teamFound = team;
                                break;
                            }
                        }

                        if (noPlayerFound) return false;

                        BaseHold.activeInvites.add( new ActiveInvite( Bukkit.getPlayer(args[1]), teamFound ));

                    }
                }
                break;
            case "leave":
                for (Team team : BaseHold.teams) {
                    if (team.players.contains(player)) {
                        team.players.remove(player);
                    }
                }
                break;
            default:
                return false;
        }

        return true;
    }
}
