package tw.at.clo5de.worker;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class Worker {

    private UUID playerUUID = null;
    private Date lastLoginDate = null;
    private Duty duty = null;

    public Worker (File file) {
        this.loadFile(file);
    }

    public Worker (UUID uuid, String positionName) {
        this.playerUUID = uuid;
        this.lastLoginDate = new Date();
        this.duty = new Duty(this, positionName);
    }

    public void loadFile (File file) {
        try {
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
            this.playerUUID = UUID.fromString(fileConfig.getString("UUID"));
            this.lastLoginDate = SalariesMe.workerHandler.getFormatter().parse(fileConfig.getString("LastLoginDate"));
            this.duty = new Duty(this, (MemorySection) fileConfig.get("Duty"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public boolean saveFile (String path) {
        try {
            File file = new File(path + "/" + this.getUUID() + ".yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
            fileConfig.set("UUID", this.getUUID().toString());
            fileConfig.set("LastLoginDate", SalariesMe.workerHandler.getFormatter().format(this.getLastLoginDate()));
            fileConfig = duty.saveFile(fileConfig);
            fileConfig.save(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveFile (File file) {
        return this.saveFile(file.getAbsolutePath());
    }

    public UUID getUUID () {
        return this.playerUUID;
    }

    public Player getPlayer () {
        return getServer().getPlayer(this.playerUUID);
    }

    public Date getLastLoginDate () {
        return this.lastLoginDate;
    }

    public void setLastLoginDate(Date date) {
        this.lastLoginDate = date;
    }

    public Duty getDuty() {
        return this.duty;
    }
}
