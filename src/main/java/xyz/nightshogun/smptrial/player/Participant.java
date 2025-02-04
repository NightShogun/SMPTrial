package xyz.nightshogun.smptrial.player;

import org.bukkit.Color;
import xyz.nightshogun.smptrial.effects.Effect;

/**
 * Represents a participant who have the ability to get effects
 *
 * @author NightShogun
 * @see Effect
 * */
public interface Participant {

    /**
     * Set the current participant's effect to the given value
     *
     * @param effect the effect to give to the participant
     * @param color the color of the effect
     * */
    void setEffect(Effect effect, Color color);

    /**
    * Launches the current effect for the participant
    * */
    void summonEffect();

    /**
     * Disables the current effect for the participant
     * */
    void removeEffect();
}
