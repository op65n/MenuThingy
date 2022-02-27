package org.op65n.menuthingy.data;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.op65n.menuthingy.MenuThingyPlugin;
import org.op65n.menuthingy.util.Task;

import java.util.logging.Level;

public final class ItemData {

    private static final Plugin PLUGIN = JavaPlugin.getPlugin(MenuThingyPlugin.class);

    private final Player holder;
    private final ItemStack item;

    public ItemData(final @NotNull ItemStack item, final @NotNull Player player) {
        this.item = item;
        this.holder = player;
    }

    @NotNull
    public DataResponse process(final @NotNull String key) {
        return new DataResponse(key, this.item, this.holder);
    }

    public record DataResponse(String key, ItemStack item, Player holder) {

        public DataResponse(final @NotNull String key, final @NotNull ItemStack item, final @NotNull Player holder) {
            this.key = key;
            this.item = item;
            this.holder = holder;
        }

        public void execute(final @NotNull String command) {
            final CustomStack stack = CustomStack.byItemStack(this.item);
            if (stack == null) return;

            final String data = stack.getNamespacedID().split(":")[1];
            if (data == null || data.length() == 0) return;

            if (!data.equalsIgnoreCase(this.key)) return;
            Task.queue(() -> {
                final boolean result = Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player_name%", holder.getName()));
                if (!result)
                    PLUGIN.getLogger().log(Level.WARNING, String.format("Failed to execute command '%s' for player '%s'", command, holder.getName()));
            }, 1);
        }

    }

}
