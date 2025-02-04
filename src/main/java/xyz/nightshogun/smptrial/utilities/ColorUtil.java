package xyz.nightshogun.smptrial.utilities;

import org.bukkit.Color;

import java.util.Map;
import java.util.Optional;

public class ColorUtil {

    static final Map<Integer, Color> COLOR_MAP = Map.ofEntries(
            Map.entry(1, Color.BLUE),
            Map.entry(2, Color.GREEN),
            Map.entry(3, Color.AQUA),
            Map.entry(4, Color.RED),
            Map.entry(5, Color.PURPLE),
            Map.entry(6, Color.ORANGE),
            Map.entry(7, Color.GRAY),
            Map.entry(8, Color.BLACK)
    );

    public static Optional<Color> mapToColor(int value) {
        if(!COLOR_MAP.containsKey(value)) {
            return Optional.empty();
        }
        return Optional.of(COLOR_MAP.get(value));
    }

}
