package tw.at.clo5de.worker;

import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;

public class Position {

    private String positionName;
    private GameMode gamemode;

    public Position (Map map) {
        this.positionName = map.get("Name");
        this.gamemode = GameMode.get(map.get("GameMode"));
    }

    public String getPositionName () {
        return this.positionName;
    }

    public GameMode getGameMode () {
        return this.gamemode;
    }

    public void setPositionName (String newName) {
        this.positionName = newName;
    }

    public void setGameMode (GameMode newMode) {
        this.gamemode = newMode;
    }

}
