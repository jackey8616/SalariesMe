package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.command.CommandSender;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;
import tw.at.clo5de.salariesme.worker.position.Position;

import java.util.ArrayList;

public class ListPosition {

    /*
     *  sm (list-position/ls-pos)
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        ArrayList<Position> positions = handler.positionHandler.getPositions();
        if (positions.size() != 0) {
            String concat = "";
            for (Position p : positions) {
                concat += p.getPositionName() + " ";
            }
            commandSender.sendMessage(concat);
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("List_No_Position_Record"));
        }
        return true;
    }

}
