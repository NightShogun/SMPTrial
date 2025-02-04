package xyz.nightshogun.smptrial.listeners;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.nightshogun.smptrial.TrialSMP;
import xyz.nightshogun.smptrial.effects.impl.SpiralParticle;
import xyz.nightshogun.smptrial.player.ParticipantManager;
import xyz.nightshogun.smptrial.player.impl.TrialPlayer;
import xyz.nightshogun.smptrial.utilities.ColorUtil;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TrialPlayer trialPlayer = new TrialPlayer(player);

        ParticipantManager participantManager = TrialSMP.getInstance().getParticipantManager();
        participantManager.register(trialPlayer);

        Color effectColor = ColorUtil.mapToColor(participantManager.get(trialPlayer)).orElse(Color.WHITE);

        trialPlayer.setEffect(new SpiralParticle(), effectColor);

        trialPlayer.summonEffect();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        ParticipantManager participantManager = TrialSMP.getInstance().getParticipantManager();
        TrialPlayer trialPlayer = participantManager.getAsTrial(player);

        trialPlayer.removeEffect();

        participantManager.eject(trialPlayer);

    }

}
