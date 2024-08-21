package me.pashmash.clearlag.commands;

import me.pashmash.clearlag.ClearLag;
import me.pashmash.clearlag.task.ClearLagTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ClearLagCommand implements CommandExecutor {
    private final ClearLag clearLag;

    public ClearLagCommand(@NotNull ClearLag clearLag) {
        this.clearLag = clearLag;
        clearLag.getCommand("clearlag").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        new ClearLagTask(clearLag).run();
        return false;
    }
}