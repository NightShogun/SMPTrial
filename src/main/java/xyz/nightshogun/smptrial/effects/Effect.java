package xyz.nightshogun.smptrial.effects;

import org.bukkit.Color;
import xyz.nightshogun.smptrial.player.Participant;

/**
 * Represents an effect that takes different shapes
 *
 * @author NightShogun
 * */
public interface Effect {

    /**
     * Activates the effect for the given participant
     *
     * @param participant the participant to activate the effect for
     * @param color the color of the effect
     * */
    void activate(Participant participant, Color color);

    void deactivate();

    /**
     * Checks if the effect is currently active
     * */
    boolean isActive();

}
