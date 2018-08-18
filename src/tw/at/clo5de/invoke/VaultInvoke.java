package tw.at.clo5de.invoke;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import tw.at.clo5de.SalariesMe;

import static org.bukkit.Bukkit.getServer;

public class VaultInvoke {

    private Permission permission = null;
    private Economy economy = null;

    public VaultInvoke () {}

    public void invoke () {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            SalariesMe.logger.warning("Can not detect Vault, Please make sure you do install a Economy plugin support Vault.");
            getServer().getPluginManager().disablePlugin(SalariesMe.INSTANCE);
        } else {
            SalariesMe.logger.info("Vault detected, Try to load Vault Economy plugin.");
            this.setEconomy();
            this.setPermission();
        }
    }

    public Economy getEconomy () {
        return economy;
    }

    private boolean setEconomy () {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public Permission getPermission () {
        return permission;
    }

    private boolean setPermission () {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return false;
        }
        permission = rsp.getProvider();
        return permission != null;
    }

}
