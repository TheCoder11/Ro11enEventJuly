package com.somemone.basehold.event;

import com.somemone.basehold.BaseHold;
import com.somemone.basehold.teams.BaseTeam;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import java.util.*;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BaseHold.base.createBoard(event.getPlayer());

        if (!BaseHold.teams.containsKey(event.getPlayer())) {
            BaseHold.teams.put(event.getPlayer(), BaseTeam.randomLetter());
        }

        BaseTeam team = BaseHold.teams.get(event.getPlayer());

        event.getPlayer().setDisplayName(team.getNameTagTitle() + event.getPlayer().getDisplayName());

        event.getPlayer().sendMessage("You are on Team " + team.getTitle());

        /**
        if (!BaseHold.teams.containsKey(event.getPlayer())) {

            HashMap<BaseTeam, Integer> diffs = findDifferenceFromLargestTeam();

            int totalWeight = 0;
            for (Map.Entry<BaseTeam, Integer> entry : diffs.entrySet()) {
                totalWeight += entry.getValue();
            }

            int idx = 0;
            for (int r = (int) Math.round(Math.random() * totalWeight); idx < diffs.size(); ++idx) {
                r -= (int) diffs.values().toArray()[idx];
                if (r <= 0) break;
            }

            BaseTeam team = (BaseTeam) diffs.keySet().toArray()[idx];

            BaseHold.teams.put(event.getPlayer(), team);
        }
         **/

    }

    /**
    private HashMap<BaseTeam, Integer> findDifferenceFromLargestTeam() {
        HashMap<BaseTeam, Integer> ints = new HashMap<BaseTeam, Integer>();
        for (Map.Entry<Player, BaseTeam> entry : BaseHold.teams.entrySet()) {
            if (ints.containsKey(entry.getValue())) {
                int value = ints.get(entry.getValue());
                value = value + 1;
                ints.put(entry.getValue(), value);
            } else {
                ints.put(entry.getValue(), 1);
            }
        }

        int maxMembers = Collections.max(ints.values());
        HashMap<BaseTeam, Integer> diffs = new HashMap<BaseTeam, Integer>();

        for (Map.Entry<BaseTeam, Integer> entry : ints.entrySet()) {
            int entryMembers = entry.getValue();
            entryMembers = entryMembers - maxMembers;
            diffs.put(entry.getKey(), entryMembers);
        }

        return diffs;

    }

    public static HashMap<BaseTeam, Integer> sortByValue(HashMap<BaseTeam, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<BaseTeam, Integer> > list =
                new LinkedList<Map.Entry<BaseTeam, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<BaseTeam, Integer> >() {
            public int compare(Map.Entry<BaseTeam, Integer> o1,
                               Map.Entry<BaseTeam, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<BaseTeam, Integer> temp = new LinkedHashMap<BaseTeam, Integer>();
        for (Map.Entry<BaseTeam, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

     **/

}
