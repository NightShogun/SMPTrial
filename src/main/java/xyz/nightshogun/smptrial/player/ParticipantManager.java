package xyz.nightshogun.smptrial.player;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import xyz.nightshogun.smptrial.effects.impl.SpiralParticle;
import xyz.nightshogun.smptrial.internal.Registry;
import xyz.nightshogun.smptrial.player.impl.TrialPlayer;
import xyz.nightshogun.smptrial.utilities.ColorUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This manager is used to cache and manage the participants
 * that are either currently playing in the server or just joined
 *
 * @author NightShogun
 * @see Participant
 * */
public class ParticipantManager implements Registry<TrialPlayer, Integer> {

    @Getter
    private final Map<TrialPlayer, Integer> participants;
    private final Random random;

    public ParticipantManager() {
        this.participants = new ConcurrentHashMap<>();
        this.random = new Random();
    }

    public void register(TrialPlayer player) {
        int randomColor = random.nextInt(8) + 1;

        this.inject(player, randomColor);

    }

    @Override
    public void inject(TrialPlayer key, Integer value) {
        if (participants.containsKey(key))
            return;

        participants.put(key, value);
    }

    @Override
    public void eject(TrialPlayer key) {
        if(!participants.containsKey(key))
            return;

        participants.remove(key);

    }

    @Override
    public Integer get(TrialPlayer key) {
        return participants.get(key);
    }

    public TrialPlayer getAsTrial(Player player) {
        return participants.keySet().stream().filter(
                p -> p.getHandle().equals(player)
        ).findFirst().orElse(null);
    }

    public void reload(){
        participants.clear();

        for(Player player : Bukkit.getOnlinePlayers()){

            TrialPlayer trialPlayer = new TrialPlayer(player);

            register(trialPlayer);

            Color effectColor = ColorUtil.mapToColor(get(trialPlayer)).orElse(Color.WHITE);

            trialPlayer.setEffect(new SpiralParticle(), effectColor);

            trialPlayer.summonEffect();

        }
    }

}
