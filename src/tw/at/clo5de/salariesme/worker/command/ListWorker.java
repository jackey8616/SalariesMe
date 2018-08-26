package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.command.CommandSender;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;
import tw.at.clo5de.salariesme.worker.Worker;

import java.util.ArrayList;

public class ListWorker {

    /*
     *  sm (list/ls)
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        ArrayList<Worker> workers = handler.getWorkers();
        if (workers.size() != 0) {
            for (Worker w : workers) {
                commandSender.sendMessage(w.getLessInfo());
            }
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("List_No_Worker_Record"));
        }
        return true;
    }

}
