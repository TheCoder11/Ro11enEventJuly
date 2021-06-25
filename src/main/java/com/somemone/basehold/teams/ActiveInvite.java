package com.somemone.basehold.teams;

import org.bukkit.entity.Player;

public class ActiveInvite {

    public Player target;
    public Team sender;

    public ActiveInvite (Player target, Team sender) {
        this.target = target;
        this.sender = sender;
    }

    public void sendInviteToTarget() {
        target.sendMessage("You have been invited to " + sender.name + "! Use /staccept");
    }

    public void activateInvite() {
        sender.players.add(target);
    }

}
