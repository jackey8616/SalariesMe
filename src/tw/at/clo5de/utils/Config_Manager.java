package tw.at.clo5de.utils;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.utils.lang.Language;
import tw.at.clo5de.worker.Handler;

public class Config_Manager {

    private JavaPlugin plugin = null;
    private FileConfiguration config = null;

    public boolean debug = false;
    public String lang = null;

    public Config_Manager (JavaPlugin plugin) {
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

    public Handler loadWorkerConfig () {
        return new Handler((MemorySection) this.config.get("Worker"));
    }

    public Language loadLangConfig () {
        return new Language(this.lang);
    }
}
