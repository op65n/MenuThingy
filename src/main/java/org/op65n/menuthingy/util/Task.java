package org.op65n.menuthingy.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.op65n.menuthingy.MenuThingyPlugin;

public final class Task {

    private static final MenuThingyPlugin PLUGIN = JavaPlugin.getPlugin(MenuThingyPlugin.class);
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    /**
     * Executes the given {@link Runnable} synchronously
     *
     * @param runnable Given code to be executed
     */
    public static void queue(final @NotNull Runnable runnable, final long delay) {
        SCHEDULER.scheduleSyncDelayedTask(PLUGIN, runnable, delay);
    }

}