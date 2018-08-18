package tw.at.clo5de.worker.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Handler;

import static org.bukkit.Bukkit.getServer;

public class RemoveWorker {

    /*
     *  sm (rm/remove) [Player]
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender || (commandSender instanceof Player && ((Player) commandSender).isOp())) {
            Player p = getServer().getPlayer(strings[1]);
            if (p != null) {
                if (SalariesMe.workerHandler.removeWorker(p.getUniqueId())) {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Remove_Success", strings[1]));
                } else {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Remove_Failed"));
                }
            } else {
                commandSender.sendMessage(SalariesMe.language.getText("Worker_Not_Online"));
            }
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("You_Do_Not_Have_Permission"));
        }
        return true;
    }

}
