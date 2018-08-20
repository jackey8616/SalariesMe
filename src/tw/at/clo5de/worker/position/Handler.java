package tw.at.clo5de.worker.position;

import org.bukkit.configuration.MemorySection;
import tw.at.clo5de.SalariesMe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class Handler {

    private tw.at.clo5de.worker.Handler handler;
    private Listener listener;
    
    private ArrayList<Position> positions = new ArrayList<>();

    public Handler (tw.at.clo5de.worker.Handler handler, MemorySection config) {
        if (this.loadPositions(config)) {
            this.handler = handler;
            this.listener = new Listener(this);
        } else {
            SalariesMe.logger.warning("There is not Position set in config.");
            getServer().getPluginManager().disablePlugin(SalariesMe.INSTANCE);
        }
    }

    public ArrayList<Position> getPositions () {
        return this.positions;
    }

    public Position getByString (String positionName) {
        for (Position p : this.positions) {
            if (p.getPositionName().equalsIgnoreCase(positionName)) {
                return p;
            }
        }
        return null;
    }

    public boolean positionExists (String positionName) {
        for (Position p : positions) {
            if (p.getPositionName().equalsIgnoreCase(positionName)) {
                return true;
            }
        }
        return false;
    }

    public boolean loadPositions (MemorySection config) {
        if (config.get("Position") == null) {
            SalariesMe.logger.warning("Can not find Position setting in SalariesMe config.");
            return false;
        }
        List list = config.getList("Position");
        for (int i = 0; i < list.size(); ++i) {
            Position p = new Position ((Map) list.get(i));
            positions.add(p);
        }
        return true;
    }

}