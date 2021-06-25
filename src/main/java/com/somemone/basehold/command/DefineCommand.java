package com.somemone.basehold.command;

import com.somemone.basehold.BaseHold;
import com.somemone.basehold.base.Base;
import com.somemone.basehold.base.PlayersCheck;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DefineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(!(player.isOp())) return false;

        if (args.length != 6) return false;
        ArrayList<Integer> ints = new ArrayList<>();

        for (String arg : args) {
            ints.add(Integer.parseInt(arg));
        }

        Location location1 = new Location(player.getWorld(), ints.get(0), ints.get(1), ints.get(2));
        Location location2 = new Location(player.getWorld(), ints.get(3), ints.get(4), ints.get(5));

        Base base = new Base(location1, location2);

        BaseHold.base = base;

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(BaseHold.getPlugin(BaseHold.class), new PlayersCheck(base), 0L, 20L);

        return true;
    }
}
