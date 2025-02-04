package xyz.nightshogun.smptrial.utilities;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class PluginUtils {

    static final Field COMMAND_MAP;

    static {
        try {
            COMMAND_MAP = SimplePluginManager.class.getDeclaredField("commandMap");
            COMMAND_MAP.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerCommand(String name, Command command) {

        try {
            CommandMap map = (CommandMap) COMMAND_MAP.get(Bukkit.getPluginManager());

            map.register(name, command);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
