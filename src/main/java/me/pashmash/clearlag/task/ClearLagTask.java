package me.pashmash.clearlag.task;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ClearLagTask extends BukkitRunnable {
    private final JavaPlugin plugin;

    public ClearLagTask(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startCountdown() {
        new BukkitRunnable() {
            int countdown = 30;

            @Override
            public void run() {
                if (countdown == 30 || countdown == 20) {
                    String message = "<bold><color:#FF0000>Cleaning up in <color:#FF7B00>" + countdown + "<color:#FF0000> seconds...</bold>";
                    Component componentMessage = MiniMessage.miniMessage().deserialize(message);
                    Bukkit.broadcast(componentMessage);
                } else if (countdown <= 10 && countdown > 0) {
                    String message = "<bold><color:#FF0000>Cleaning up in <color:#FF7B00>" + countdown + "<color:#FF0000> seconds...</bold>";
                    Component componentMessage = MiniMessage.miniMessage().deserialize(message);
                    Bukkit.broadcast(componentMessage);
                } else if (countdown == 0) {
                    this.cancel();
                    runClearLagTask();
                }
                countdown--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void runClearLagTask() {
        int removedEntities = 0;

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (shouldRemoveEntity(entity)) {
                    entity.remove();
                    removedEntities++;
                }
            }
        }

        String message = plugin.getConfig().getString("clearlag.message")
                .replace("{count}", String.valueOf(removedEntities));

        Component componentMessage = MiniMessage.miniMessage().deserialize(message);
        Bukkit.broadcast(componentMessage);
    }

    @Override
    public void run() {
        startCountdown();
    }

    private boolean shouldRemoveEntity(@NotNull Entity entity) {
        if (entity instanceof Player || entity instanceof EnderDragon || entity instanceof Wither || entity instanceof Zombie || entity instanceof NPC) {
            return false;
        }

        List<String> removeEntities = plugin.getConfig().getStringList("clearlag.remove_entities");
        if (entity instanceof Monster && removeEntities.contains("MONSTERS")) {
            return true;
        } else if (entity instanceof Item && removeEntities.contains("DROPPED_ITEMS")) {
            return true;
        } else if (entity instanceof Projectile && removeEntities.contains("PROJECTILES")) {
            return true;
        } else if (entity instanceof ExperienceOrb && removeEntities.contains("EXPERIENCE_ORBS")) {
            return true;
        }

        return false;
    }

}