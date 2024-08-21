package me.pashmash.clearlag.noutil;

import org.bukkit.Bukkit;

public class Sender {
    public static void disable() {
        Bukkit.getConsoleSender().sendRichMessage("<rainbow>ClearedLag</rainbow> Disable!");
    }
    public static void enable() {
        Bukkit.getConsoleSender().sendRichMessage("<rainbow>ClearedLag</rainbow> enabled!");
    }
}
