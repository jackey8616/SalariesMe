package tw.at.clo5de.salariesme.worker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tw.at.clo5de.salariesme.SalariesMe;

import java.util.Date;

import static org.bukkit.Bukkit.getServer;

public class Listener implements org.bukkit.event.Listener {

    private Handler handler = null;

    public Listener (Handler handler, SalariesMe plugin) {
        this.handler = handler;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Worker w = handler.getWorker(event.getPlayer().getUniqueId());
        if (w != null) {
            w.setLastLoginDate(new Date());
        }
    }

    @EventHandler
    public void onPlayerChangeGameMode (PlayerGameModeChangeEvent event) {
        Worker w = handler.getWorker(event.getPlayer().getUniqueId());
        if (w != null) {
            w.getDuty().processPlayerChangeEvent(event);
        }
    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {
        Worker w = handler.getWorker(event.getPlayer());
        if (w != null) {
            w.saveFile();
        }
    }

}
