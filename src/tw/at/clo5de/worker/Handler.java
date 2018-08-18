package tw.at.clo5de.worker;

import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class Handler {

    private Listener listener = null;

    public String dateFormat = null;
    public File savedPath = null;
    private ArrayList<Worker> workers = new ArrayList<>();
    public Reward reward = null;

    public Handler (MemorySection config) {
        try {
            this.dateFormat = config.getString("DateFormat");
            this.savedPath = new File(SalariesMe.INSTANCE.getDataFolder().getAbsolutePath() + "/" + config.getString("Path"));
            this.reward = new Reward((MemorySection) config.get("Reward"));
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
            w.saveFile(savedPath.getAbsolutePath());
        }
    }

    public boolean addWorker (UUID uuid, String positionName) {
        Worker w = new Worker(uuid, positionName);
        workers.add(w);
        return w.saveFile(savedPath);
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
}
