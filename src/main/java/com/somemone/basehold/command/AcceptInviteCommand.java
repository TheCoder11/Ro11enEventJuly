package com.somemone.basehold.command;

import com.somemone.basehold.BaseHold;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptInviteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            BaseHold.activeInvites.forEach((activeInvite) -> {
                if (activeInvite.target.equals(player)) {
                    activeInvite.activateInvite();
                    return;
                }
            });

            player.sendMessage("You have been accepted!");
        }

        return true;
    }
}
