package tw.at.clo5de.worker;

import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;

public class Position {

    private String positionName;

    public Position (Player player, String positionName) {
        if (SalariesMe.gmInvoke.getGroups(player).contains(positionName)) {
            this.positionName = positionName;
        }
    }

}
