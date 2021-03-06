package tw.at.clo5de.salariesme.utils;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tw.at.clo5de.salariesme.SalariesMe;

public class ConfigManager {

    private JavaPlugin plugin = null;
    private FileConfiguration config = null;

    public boolean debug = false;
    public String lang = null;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = this.plugin.getConfig();
    }

    public void loadConfig () {
        try {
            this.config.options().copyDefaults(true);
            this.plugin.saveConfig();

            this.debug = this.config.getBoolean("Setting.Debug");
            this.lang = this.config.getString("Setting.Lang");
            SalariesMe.logger.info("ConfigManager loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadLangConfig () {
        return this.lang;
    }

    public MemorySection loadWorkerConfig () {
        return (MemorySection) this.config.get("Worker");
    }
}
