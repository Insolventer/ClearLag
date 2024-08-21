package me.pashmash.clearlag;

import me.pashmash.clearlag.commands.ClearLagCommand;
import me.pashmash.clearlag.noutil.Sender;
import me.pashmash.clearlag.task.ClearLagTask;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearLag extends JavaPlugin {

    private static ClearLag instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        new ClearLagCommand(this);
        new ClearLagTask(ClearLag.this).runTaskTimer(this, 0L, 6000L);
        Sender.enable();
    }

    @Override
    public void onDisable() {
        Sender.disable();
    }
    public static ClearLag getInstance() {
        return instance;
    }
}
