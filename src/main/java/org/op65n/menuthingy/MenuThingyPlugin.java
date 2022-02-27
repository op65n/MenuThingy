package org.op65n.menuthingy;

import org.bukkit.plugin.java.JavaPlugin;
import org.op65n.menuthingy.listener.ListenerRegisterable;
import org.op65n.menuthingy.register.Registerable;

import java.util.Set;

public final class MenuThingyPlugin extends JavaPlugin {

    private static final Set<Registerable> REGISTERABLES = Set.of(
            new ListenerRegisterable()
    );

    @Override
    public void onEnable() {
        REGISTERABLES.forEach(it -> it.register(this));

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        REGISTERABLES.forEach(it -> it.unregister(this));

        reloadConfig();
    }

}
