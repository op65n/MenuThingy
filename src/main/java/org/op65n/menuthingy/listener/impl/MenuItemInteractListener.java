package org.op65n.menuthingy.listener.impl;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.op65n.menuthingy.MenuThingyPlugin;
import org.op65n.menuthingy.data.ConfigData;
import org.op65n.menuthingy.data.ItemData;

import java.util.logging.Level;

public final class MenuItemInteractListener implements Listener {

    private final MenuThingyPlugin plugin;

    public MenuItemInteractListener(final @NotNull MenuThingyPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemMenuInteract(final @NotNull InventoryClickEvent event) {
        final ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final HumanEntity humanEntity = event.getWhoClicked();

        final ItemData itemData = new ItemData(clickedItem, (Player) humanEntity);
        final ConfigData configData = new ConfigData(plugin.getConfig());

        for (final ConfigData.DataResponse response : configData.process()) {
            final ItemData.DataResponse itemDataResponse = itemData.process(response.id());

            itemDataResponse.execute(response.command());
        }
    }

}
