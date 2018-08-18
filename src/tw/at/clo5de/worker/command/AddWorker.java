package tw.at.clo5de.worker.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;

import static org.bukkit.Bukkit.getServer;

public class AddWorker {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender || (commandSender instanceof Player && ((Player) commandSender).isOp())) {
            Player p = getServer().getPlayer(strings[1]);
            if (p != null) {
                if (SalariesMe.workerHandler.addWorker(p.getUniqueId(), strings[2])) {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Add_Success", strings[1], strings[2]));
                    return true;
                } else {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Add_Failed"));
                }
            } else {
                commandSender.sendMessage(SalariesMe.language.getText("Player_Add_Not_Online"));
            }
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("You_Do_Not_Have_Permission"));
        }
        return false;
    }

}
