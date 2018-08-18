package tw.at.clo5de.invoke;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Worker;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class GMInvoke {

    private GroupManager groupManager;

    public GMInvoke () {}

    public void invoke () {
        Plugin plugin = getServer().getPluginManager().getPlugin("GroupManager");
        if (plugin == null) {
            getServer().getPluginManager().disablePlugin(SalariesMe.INSTANCE);
        } else {
            this.groupManager = (GroupManager) plugin;
        }
    }

    public String getGroup(final Player base)
    {
        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null)
        {
            return null;
        }
        return handler.getGroup(base.getName());
    }

    public String getGroup(final Worker worker) {
        return this.getGroup(worker.getPlayer());
    }

    public boolean setGroup(final Player base, final String group)
    {
        final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
        if (handler == null)
        {
            return false;
        }
        handler.getUser(base.getName()).setGroup(handler.getGroup(group));
        return true;
    }

    public boolean setGroup(final Worker worker, final String group) {
        return this.setGroup(worker.getPlayer(), group);
    }

    public List<String> getGroups(final Player base)
    {
        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null)
        {
            return null;
        }
        return Arrays.asList(handler.getGroups(base.getName()));
    }

    public String getPrefix(final Player base)
    {
        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null)
        {
            return null;
        }
        return handler.getUserPrefix(base.getName());
    }

    public String getSuffix(final Player base)
    {
        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null)
        {
            return null;
        }
        return handler.getUserSuffix(base.getName());
    }

    public boolean hasPermission(final Player base, final String node)
    {
        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null)
        {
            return false;
        }
        return handler.has(base, node);
    }

}
