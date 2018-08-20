package tw.at.clo5de.worker.position;

import java.util.ArrayList;
import java.util.List;

import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Position;

class Handler {

    private tw.at.clo5de.worker.Handler handler;
    private Listener listener;
    
    private ArrayList<Position> positions = new ArrayList<>();

    public Handler (tw.at.clo5de.worker.Handler hanlder, MemorySection config) {
        if (this.loadPositions(config)) {
            this.handler = hanlder;
            this.listener = new Listener(this);
        }
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
        if (config.get("Positions") == null) {
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