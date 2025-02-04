package xyz.nightshogun.smptrial.effects.impl;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import xyz.nightshogun.smptrial.TrialSMP;
import xyz.nightshogun.smptrial.effects.Effect;
import xyz.nightshogun.smptrial.player.Participant;
import xyz.nightshogun.smptrial.player.impl.TrialPlayer;

import java.util.Objects;

public class SpiralParticle implements Effect {

    private BukkitTask runnable;
    @Getter
    private boolean isActive = false;
    private int angle = 0;

    @Override
    public void activate(Participant participant, Color color) {

        if(isActive) {
            return;
        }

        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {

                Particle.DustOptions options = new Particle.DustOptions(color, 1);
                Location center = ((TrialPlayer)participant).getHandle().getLocation().clone();

                double radius = 1.5;
                double heightOffset = 0.5;
                int particlesPerLoop = 20;

                for (int i = 0; i < particlesPerLoop; i++) {

                    double theta = angle + (2 * Math.PI * i / particlesPerLoop);
                    double currentRadius = radius + theta * 0.1;
                    double x = currentRadius * Math.cos(theta);
                    double z = currentRadius * Math.sin(theta);
                    double y = heightOffset + theta * 0.1;

                    Location particleLoc = center.add(x, y, z);
                    Objects.requireNonNull(center.getWorld()).spawnParticle(
                            Particle.REDSTONE, particleLoc, 1, 0, 0, 0, 0, options);
                    System.out.println("[DEBUG] Spawning particle at " + particleLoc);
                    center.subtract(x, y, z);
                }

                angle += 0.2;
                if (angle > 2 * Math.PI) angle = 0;

            }
        }.runTaskTimerAsynchronously(TrialSMP.getInstance(), 0L, 20);

        isActive = true;

    }

    @Override
    public void deactivate() {
        if(runnable == null) {
            return;
        }

        this.runnable.cancel();
        this.isActive = false;

    }
}
