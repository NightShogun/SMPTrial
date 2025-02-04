package xyz.nightshogun.smptrial.player.impl;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import xyz.nightshogun.smptrial.effects.Effect;
import xyz.nightshogun.smptrial.player.Participant;

import static org.bukkit.ChatColor.RED;

public class TrialPlayer implements Participant {

    @Getter
    public Player handle;

    private Effect effect;

    private Color color;

    public TrialPlayer(Player player) {
        this.handle = player;
    }

    @Override
    public void setEffect(Effect effect, Color color) {
        this.effect = effect;
        this.color = color;
    }

    @Override
    public void summonEffect() {
        if(effect == null) {
            System.out.println("[DEBUG] effect is null");
            return;
        }

        if(effect.isActive()){
            getHandle().sendMessage(RED + "You already have an active effect!");
            return;
        }

        effect.activate(this, color);
    }

    @Override
    public void removeEffect() {
        if(effect == null || !effect.isActive()) {
            getHandle().sendMessage(RED + "You don't have an active effect!");
            return;
        }
        effect.deactivate();
    }

}
