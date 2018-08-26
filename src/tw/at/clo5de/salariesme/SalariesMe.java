package tw.at.clo5de.salariesme;

import org.bukkit.plugin.java.JavaPlugin;
import tw.at.clo5de.salariesme.invoke.GMInvoke;
import tw.at.clo5de.salariesme.invoke.VaultInvoke;
import tw.at.clo5de.salariesme.utils.ConfigManager;
import tw.at.clo5de.salariesme.utils.lang.Language;
import tw.at.clo5de.salariesme.worker.Handler;
import tw.at.clo5de.salariesme.worker.command.Command;

import java.util.logging.Logger;

public class SalariesMe extends JavaPlugin {

    public static SalariesMe INSTANCE = null;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public static VaultInvoke vaultInvoke = new VaultInvoke();
    public static GMInvoke gmInvoke = new GMInvoke();

    public static ConfigManager configManager = null;
    public static Language language = null;
    public static tw.at.clo5de.salariesme.worker.Handler workerHandler = null;
    public static Command command = null;

    @Override
    public void onEnable () {
        INSTANCE = this;
        vaultInvoke.invoke();
        gmInvoke.invoke();

        this.configManager = new ConfigManager(this);
        configManager.loadConfig();
        this.language = new Language(configManager.loadLangConfig());
        this.workerHandler = new Handler(configManager.loadWorkerConfig());
        workerHandler.loadWorkers();
        this.command = new Command();
        command.setExecutor(workerHandler);
    }

    @Override
    public void onDisable () {}

}
