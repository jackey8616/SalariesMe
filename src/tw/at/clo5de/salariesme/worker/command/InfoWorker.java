package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;
import tw.at.clo5de.salariesme.worker.Worker;

import static org.bukkit.Bukkit.getServer;

public class InfoWorker {

    /*
     *  sm (list/ls)
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length < 2) {
            commandSender.sendMessage(SalariesMe.language.getText("Miss_Or_Wrong_Player_Name"));
        } else {
            Player p = getServer().getPlayer(strings[1]);
            if (p != null) {
                Worker w = handler.getWorker(p);
                if (w != null) {
                    commandSender.sendMessage(w.getInfo());
                } else {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Is_Not_Worker"));
                }
            } else {
                commandSender.sendMessage(SalariesMe.language.getText("Worker_Not_Online"));
            }
        }
        return true;
    }

}
