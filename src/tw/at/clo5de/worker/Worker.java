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

    private File file = null;
    private UUID playerUUID = null;
    private Date lastLoginDate = null;
    private Duty duty = null;

    public Worker (File file) {
        this.file = file;
        this.loadFile();
    }

    public Worker (UUID uuid, String positionName, File folder) {
        this(uuid, positionName, folder.getAbsolutePath());
    }

    public Worker (UUID uuid, String positionName, String filePath) {
        this.playerUUID = uuid;
        this.lastLoginDate = new Date();
        this.duty = new Duty(this, positionName);
        this.file = new File(filePath + "/" + playerUUID.toString() + ".yml");
    }

    public Worker (UUID uuid, Position position, String filePath) {
        this.playerUUID = uuid;
        this.lastLoginDate = new Date();
        
    }

    public void loadFile () {
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

    public boolean saveFile () {
        try {
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

    public boolean removeFile () {
        return this.file.delete();
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

    public String getInfo () {
        Player p = getServer().getPlayer(this.playerUUID);
        if (p != null) {
            return String.format("Name: %s\n%s", p.getDisplayName(), this.duty.getDutyInfo());
        } else {
            return String.format("%s %s", getServer().getOfflinePlayer(this.playerUUID).getName(), this.duty.getDutyInfo());
        }

    }

    public String getLessInfo () {
        Player p = getServer().getPlayer(this.playerUUID);
        if (p != null) {
            return String.format("%s %s", p.getDisplayName(), this.duty.getPositionName());
        } else {
            return String.format("%s %s", getServer().getOfflinePlayer(this.playerUUID).getName(), this.duty.getPositionName());
        }
    }
}
