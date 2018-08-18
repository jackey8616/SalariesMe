package tw.at.clo5de.worker;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import tw.at.clo5de.SalariesMe;

import static org.bukkit.Bukkit.getServer;

public class Duty {

    private Worker worker;
    private boolean onDuty = false;
    private long dutyTick = 0;
    private Location location = null;
    private GameMode originMode = null;
    private String positionName;
    private String originPositionName = null;

    public Duty (Worker worker, MemorySection config) {
        this.worker = worker;
        this.onDuty = config.getBoolean("OnDuty");
        this.positionName = config.getString("PositionName");
        if (this.onDuty) {
            this.dutyTick = config.getLong("Tick");
            World world = getServer().getWorld(config.getString("World"));
            this.location = new Location(world, config.getDouble("X"), config.getDouble("Y"), config.getDouble("Z"));
            this.originMode = GameMode.valueOf(config.getString("GameMode"));
            this.originPositionName = SalariesMe.gmInvoke.getGroup(worker.getPlayer());
        }
    }

    public Duty (Worker worker, String positionName) {
        this.worker = worker;
        this.positionName = positionName;
    }

    public FileConfiguration saveFile (FileConfiguration fileConfig) {
        if (this.onDuty) {
            fileConfig.set("Duty.Tick", this.dutyTick);
            fileConfig.set("Duty.World", this.location.getWorld().getName());
            fileConfig.set("Duty.X", this.location.getX());
            fileConfig.set("Duty.Y", this.location.getY());
            fileConfig.set("Duty.Z", this.location.getZ());
            fileConfig.set("Duty.GameMode", this.originMode.name());
            fileConfig.set("Duty.OriginPositionName", this.originPositionName);
        }
        fileConfig.set("Duty.OnDuty", this.onDuty);
        fileConfig.set("Duty.PositionName", this.positionName);
        return fileConfig;
    }

    public String getDutyInfo () {
        if (this.onDuty) {
            return String.format("---------- [ " + SalariesMe.language.getText("Duty_Info") + " ] ----------\nDuty: %b\nWorld: %s\nXYZ: [%f, %f, %f]\nOriginGameMode: %s\nPosition: %s",
                    this.onDuty,
                    this.location.getWorld().getName(),
                    this.location.getX(), this.location.getY(), this.location.getZ(),
                    this.originMode.name(),
                    this.positionName);
        } else {
            return String.format("---------- [ " + SalariesMe.language.getText("Duty_Info") + " ] ----------\nDuty: %b\nPosition: %s", this.onDuty, this.positionName);
        }
    }

    public String getPositionName () {
        return this.positionName;
    }

    public boolean isOnDuty () {
        return this.onDuty;
    }

    public void on () {
        this.location = worker.getPlayer().getLocation();
        this.originMode = worker.getPlayer().getGameMode();
        this.originPositionName = SalariesMe.gmInvoke.getGroup(this.worker);
        worker.getPlayer().setGameMode(GameMode.SPECTATOR);
        SalariesMe.gmInvoke.setGroup(this.worker, this.positionName);
        this.onDuty = true;
    }

    public void off () {
        this.onDuty = false;
        worker.getPlayer().teleport(this.location);
        worker.getPlayer().setGameMode(this.originMode);
        SalariesMe.gmInvoke.setGroup(this.worker, this.originPositionName);

        this.location = null;
        this.originMode = null;
        this.originPositionName = null;
    }

    @EventHandler
    public void processPlayerChangeEvent(PlayerGameModeChangeEvent event) {
        if (this.onDuty) {
            event.setCancelled(true);
        }
    }
}
