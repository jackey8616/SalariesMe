package tw.at.clo5de.worker.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Handler;
import tw.at.clo5de.worker.Worker;

import static org.bukkit.Bukkit.getServer;

public class InfoWorker {

    /*
     *  sm (list/ls)
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Player p = getServer().getPlayer(strings[1]);
        Worker w = handler.getWorker(p);
        if (w != null) {
            commandSender.sendMessage(w.getInfo());
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("Worker_Not_Online"));
        }
        return true;
    }

}
