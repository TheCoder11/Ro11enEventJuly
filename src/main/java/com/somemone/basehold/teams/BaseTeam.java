package com.somemone.basehold.teams;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BaseTeam {

    RED(ChatColor.RED + "Red", BarColor.RED, ChatColor.RED + "[RED] "),
    BLUE(ChatColor.BLUE + "Blue", BarColor.BLUE, ChatColor.BLUE + "[BLUE] "),
    GREEN(ChatColor.GREEN + "Green", BarColor.GREEN, ChatColor.GREEN + "[GREEN] "),
    YELLOW(ChatColor.GOLD + "Yellow", BarColor.YELLOW, ChatColor.GOLD + "[YELLOW] "),
    PINK(ChatColor.LIGHT_PURPLE + "Pink", BarColor.PINK, ChatColor.LIGHT_PURPLE + "[PINK] "),
    PURPLE(ChatColor.DARK_PURPLE + "Purple", BarColor.PURPLE, ChatColor.DARK_PURPLE + "[PURPLE] "),
    WHITE(ChatColor.WHITE + "White", BarColor.WHITE, ChatColor.WHITE + "[WHITE] ");

    private final String title;
    private final BarColor barColor;
    private final String nameTagTitle;

    private BaseTeam(final String title, final BarColor barColor, String nameTagTitle) { this.title = title; this.barColor = barColor;
        this.nameTagTitle = nameTagTitle;
    }

    public String getTitle() { return this.title; }
    public BarColor getColor() { return this.barColor; }
    public String getNameTagTitle() { return this.nameTagTitle; }

    private static final List<BaseTeam> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BaseTeam randomLetter() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
