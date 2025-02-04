package xyz.nightshogun.smptrial.commands;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;
import xyz.nightshogun.smptrial.TrialSMP;
import xyz.nightshogun.smptrial.effects.impl.SpiralParticle;
import xyz.nightshogun.smptrial.player.ParticipantManager;
import xyz.nightshogun.smptrial.player.impl.TrialPlayer;
import xyz.nightshogun.smptrial.utilities.ColorUtil;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.GREEN;

public class EffectCommand extends Command {
    public EffectCommand() {
        super("smpeffect");
    }

    @Override
    public boolean execute(CommandSender actor, String label, String[] arguments) {

        int argLength = arguments.length;

        if(argLength == 0 || argLength > 1){

            actor.sendMessage(RED + "/smpeffect <enable|disable>");

            return false;
        }

        ParticipantManager participantManager = TrialSMP.getInstance().getParticipantManager();

        TrialPlayer trialPlayer = participantManager.getAsTrial((Player) actor);

        switch(arguments[0]) {
            case "enable":

                trialPlayer.getHandle().sendMessage(GREEN + "The effect is now enabled!");

                Color color = ColorUtil.mapToColor(participantManager.get(trialPlayer)).orElse(Color.WHITE);

                trialPlayer.setEffect(new SpiralParticle(), color);

                trialPlayer.getHandle().playSound(
                        trialPlayer.getHandle().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 10f);

                break;
            case "disable":

                trialPlayer.getHandle().sendMessage(RED + "The effect is now disabled!");

                trialPlayer.removeEffect();

                break;
            default:
                actor.sendMessage(RED + "Invalid syntax");
                break;
        }

        return true;
    }

}
