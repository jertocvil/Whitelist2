package es.jertocvil.whitelist2;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Whitelist2 extends JavaPlugin {

    final Whitelist2 plugin = this;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public final Whitelist2PL playerListener = new Whitelist2PL(this);
    public static FileConfiguration config;
    public static File f;
    
    
    @Override
    public void onDisable() {
        Whitelist2.logger.log(Level.INFO, "Whitelist2 disabled");

    }

    @Override
    public void onEnable() {
        config = this.getConfig();
        f = new File(config.getString("user_denied.file"));
        if (f.exists()) {
            PluginManager pm = getServer().getPluginManager();
            pm.registerEvent(Event.Type.PLAYER_LOGIN, this.playerListener, Event.Priority.Normal, this);
            pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener, Event.Priority.Normal, this);
            Whitelist2.logger.log(Level.INFO, "Whitelist2 enabled");
        } else {
            Whitelist2.logger.log(Level.WARNING, "Whitelist file not found.");
            this.getPluginLoader().disablePlugin(this);
        }
    }
}
