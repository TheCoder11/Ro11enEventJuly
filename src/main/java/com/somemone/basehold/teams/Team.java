package com.somemone.basehold.teams;

import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.*;

public class Team {

    public String name;
    public BarColor color;
    public ArrayList<Player> players;

    public Team (String name) {
        List<BarColor> VALUES = Collections.unmodifiableList(Arrays.asList(BarColor.values()));
        int SIZE = VALUES.size();
        Random RANDOM = new Random();

        this.color = VALUES.get(RANDOM.nextInt(SIZE));
        this.name = name;
    }

}
