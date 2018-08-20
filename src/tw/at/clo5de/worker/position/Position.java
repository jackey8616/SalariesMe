package tw.at.clo5de.worker.position;

import org.bukkit.GameMode;
import tw.at.clo5de.SalariesMe;

import java.util.Map;

public class Position {

    private String positionName;
    private GameMode gamemode;

    public Position (Map map) {
        this.positionName = map.get("Name").toString();
        this.gamemode = GameMode.valueOf((String) map.get("GameMode"));
    }

    public String getPositionName () {
        return this.positionName;
    }

    public GameMode getGameMode () {
        return this.gamemode;
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
