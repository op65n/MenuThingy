package org.op65n.menuthingy.listener;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.op65n.menuthingy.MenuThingyPlugin;
import org.op65n.menuthingy.listener.impl.MenuItemInteractListener;
import org.op65n.menuthingy.register.Registerable;

import java.util.HashSet;
import java.util.Set;

public final class ListenerRegisterable implements Registerable {

    private static final Set<Listener> LISTENERS = new HashSet<>();

    @Override
    public void register(final @NotNull MenuThingyPlugin plugin) {
        LISTENERS.addAll(Set.of(
                new MenuItemInteractListener(plugin)
        ));

        final PluginManager manager = plugin.getServer().getPluginManager();

        LISTENERS.forEach(it -> manager.registerEvents(it, plugin));
    }

    @Override
    public void unregister(final @NotNull MenuThingyPlugin plugin) {
        LISTENERS.forEach(HandlerList::unregisterAll);
    }

}
