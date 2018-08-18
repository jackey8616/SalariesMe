package tw.at.clo5de;

import org.bukkit.plugin.java.JavaPlugin;
import tw.at.clo5de.invoke.GMInvoke;
import tw.at.clo5de.invoke.VaultInvoke;
import tw.at.clo5de.utils.Config_Manager;
import tw.at.clo5de.utils.lang.Language;
import tw.at.clo5de.worker.command.Command;

import java.util.logging.Logger;

public class SalariesMe extends JavaPlugin {

    public static SalariesMe INSTANCE = null;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public static VaultInvoke vaultInvoke = new VaultInvoke();
    public static GMInvoke gmInvoke = new GMInvoke();

    public static Config_Manager configManager = null;
    public static Language language = null;
    public static tw.at.clo5de.worker.Handler workerHandler = null;
    public static Command command = null;

    @Override
    public void onEnable () {
        INSTANCE = this;
        vaultInvoke.invoke();
        gmInvoke.invoke();

        this.configManager = new Config_Manager(this);
        configManager.loadConfig();
        this.language = configManager.loadLangConfig();
        this.workerHandler = configManager.loadWorkerConfig();
        workerHandler.loadWorkers();
        this.command = new Command();
        command.setExecutor();
    }

    @Override
    public void onDisable () {}

}
