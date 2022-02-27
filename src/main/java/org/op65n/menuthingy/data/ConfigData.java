package org.op65n.menuthingy.data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class ConfigData {

    private final FileConfiguration configuration;

    public ConfigData(final @NotNull FileConfiguration configuration) {
        this.configuration = configuration;
    }

    public Set<DataResponse> process() {
        final Set<DataResponse> result = new HashSet<>();

        final ConfigurationSection section = configuration.getConfigurationSection("trigger");
        if (section == null) return Collections.emptySet();

        for (final String key : section.getKeys(false)) {
            final ConfigurationSection subSection = section.getConfigurationSection(key);

            if (subSection == null) continue;

            final String identifier = subSection.getString("itemsadder-nbt-id");
            final String command = subSection.getString("command");

            if (identifier == null || command == null) continue;

            result.add(new DataResponse(identifier, command));
        }

        return result;
    }

    public record DataResponse(String id, String command) {

        public DataResponse(final @NotNull String id, final @NotNull String command) {
            this.id = id;
            this.command = command;
        }

    }
}
