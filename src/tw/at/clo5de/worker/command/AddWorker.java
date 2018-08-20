package tw.at.clo5de.worker.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Handler;

import static org.bukkit.Bukkit.getServer;

public class AddWorker {

    /*
    *  sm add [Player]
    * */
    public boolean onCommand(Handler handler, CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender || (commandSender instanceof Player && commandSender.isOp())) {
            if (strings.length < 2) {
                commandSender.sendMessage(SalariesMe.language.getText("Miss_Or_Wrong_Player_Name"));
            } else {
                Player p = getServer().getPlayer(strings[1]);
                if (p != null && p.isOnline()) {
                    if (strings.length < 3) {
                        commandSender.sendMessage(SalariesMe.language.getText("Miss_Position_Name"));
                    } else {
                        if (SalariesMe.workerHandler.addWorker(p.getUniqueId(), strings[2])) {
                            commandSender.sendMessage(SalariesMe.language.getText("Player_Add_Success", strings[1], strings[2]));
                        } else {
                            commandSender.sendMessage(SalariesMe.language.getText("Player_Add_Failed"));
                        }
                    }
                } else {
                    commandSender.sendMessage(SalariesMe.language.getText("Worker_Not_Online"));
                }
            }
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("You_Do_Not_Have_Permission"));
        }
        return true;
    }

}
