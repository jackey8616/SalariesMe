package tw.at.clo5de.worker.position;

import org.bukkit.GameMode;
import org.bukkit.World;
import tw.at.clo5de.SalariesMe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class Position {

    private String positionName;
    private GameMode gamemode;
    private ArrayList<World> worlds = new ArrayList<World>();

    public Position (Map map) {
        this.positionName = map.get("Name").toString();
        this.gamemode = GameMode.valueOf((String) map.get("GameMode"));
        if (map.containsKey("World")) {
            List worldList = (List) map.get("World");
            for (int i = 0; i < worldList.size(); ++i) {
                this.worlds.add(getServer().getWorld(worldList.get(i).toString()));
            }
        }
    }

    public String getPositionName () {
        return this.positionName;
    }

    public GameMode getGameMode () {
        return this.gamemode;
    }

    public boolean containWorld (World world) {
        return this.worlds.size() == 0 || this.worlds.contains(world);
    }

    public String getGameModeString () {
        return SalariesMe.language.getText(this.gamemode.name());
    }

    public void setPositionName (String newName) {
        this.positionName = newName;
    }

    public void setGameMode (GameMode newMode) {
        this.gamemode = newMode;
    }

}
