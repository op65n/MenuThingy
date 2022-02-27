package org.op65n.menuthingy.register;

import org.jetbrains.annotations.NotNull;
import org.op65n.menuthingy.MenuThingyPlugin;

public interface Registerable {

    void register(final @NotNull MenuThingyPlugin plugin);

    void unregister(final @NotNull MenuThingyPlugin plugin);

}
