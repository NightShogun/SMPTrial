package xyz.nightshogun.smptrial;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.nightshogun.smptrial.commands.EffectCommand;
import xyz.nightshogun.smptrial.listeners.ConnectionListener;
import xyz.nightshogun.smptrial.player.ParticipantManager;
import xyz.nightshogun.smptrial.utilities.PluginUtils;

import java.util.Objects;

public class TrialSMP extends JavaPlugin {

    @Getter
    private static TrialSMP instance;
    @Getter
    private ParticipantManager participantManager;

    @Override
    public void onEnable() {

        instance = this;

        participantManager = new ParticipantManager();

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        PluginUtils.registerCommand("smpeffect", new EffectCommand());

        participantManager.reload();

    }

    @Override
    public void onDisable() {
        participantManager.getParticipants().keySet().forEach(participantManager::eject);
    }

}
