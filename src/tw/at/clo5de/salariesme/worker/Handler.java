package tw.at.clo5de.salariesme.worker;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.position.Position;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class Handler {

    private Listener listener = null;
    public tw.at.clo5de.salariesme.worker.position.Handler positionHandler;

    public String dateFormat = null;
    public File savedPath = null;
    public Reward reward = null;
    private ArrayList<Worker> workers = new ArrayList<>();

    public Handler (MemorySection config) {
        try {
            this.dateFormat = config.getString("DateFormat");
            this.savedPath = new File(SalariesMe.INSTANCE.getDataFolder().getAbsolutePath() + "/" + config.getString("Path"));
            this.reward = new Reward((MemorySection) config.get("Reward"));
            this.positionHandler = new tw.at.clo5de.salariesme.worker.position.Handler(this, config);
            listener = new Listener(this, SalariesMe.INSTANCE);
            SalariesMe.logger.info("Worker Handler loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadWorkers () {
        if (!savedPath.exists()) {
            savedPath.mkdir();
        }
        for (File f : savedPath.listFiles()) {
            workers.add(new Worker(f));
        }
    }

    public void saveWorkers () {
        for (Worker w : this.workers) {
            w.saveFile();
        }
    }

    public boolean addWorker (UUID uuid, Position position) {
        Worker w = new Worker(uuid, position, savedPath);
        workers.add(w);
        return w.saveFile();
    }

    public boolean removeWorker (UUID uuid) {
        Worker w = this.getWorker(uuid);
        if (w != null) {
            if (w.getPlayer() != null && w.getPlayer().isOnline()) {
                if (w.getDuty().isOnDuty()) {
                    w.getDuty().off();
                }
                w.getPlayer().sendMessage(SalariesMe.language.getText("Your_Position_Has_Been_Removed"));
            }
            w.removeFile();
            this.workers.remove(w);
            return true;
        }
        return false;
    }

    public boolean removeWorker (Player player) {
        return this.removeWorker(player.getUniqueId());
    }

    public ArrayList<Worker> getWorkers () {
        return this.workers;
    }

    public Worker getWorker (UUID uuid) {
        for (Worker w : this.workers) {
            if (w.getUUID().equals(uuid)) {
                return w;
            }
        }
        return null;
    }

    public Worker getWorker (Player player) {
        return this.getWorker(player.getUniqueId());
    }

    public SimpleDateFormat getFormatter () {
        return new SimpleDateFormat(this.dateFormat);
    }

    public OfflinePlayer findOfflinePlayer (String name) {
        for (OfflinePlayer player : getServer().getOfflinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

}
