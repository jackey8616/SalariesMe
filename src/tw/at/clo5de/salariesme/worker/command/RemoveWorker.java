package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;

import static org.bukkit.Bukkit.getServer;

public class RemoveWorker {

    /*
     *  sm (rm/remove) [Player]
     * */
    public boolean onCommand(Handler handler, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender || (commandSender instanceof Player && ((Player) commandSender).isOp())) {
            if (strings.length < 2) {
                commandSender.sendMessage(SalariesMe.language.getText("Miss_Or_Wrong_Player_Name"));
            } else {
                Player p = getServer().getPlayer(strings[1]);
                if (p != null && p.isOnline() && SalariesMe.workerHandler.removeWorker(p.getUniqueId())) {
                    commandSender.sendMessage(SalariesMe.language.getText("Player_Remove_Success", strings[1]));
                } else {
                    OfflinePlayer offlinePlayer = SalariesMe.workerHandler.findOfflinePlayer(strings[1]);
                    if (SalariesMe.workerHandler.removeWorker(offlinePlayer.getUniqueId())) {
                        commandSender.sendMessage(SalariesMe.language.getText("Player_Remove_Success", strings[1]));
                    } else {
                        commandSender.sendMessage(SalariesMe.language.getText("Player_Remove_Failed"));
                    }
                }
            }
        } else {
            commandSender.sendMessage(SalariesMe.language.getText("You_Do_Not_Have_Permission"));
        }
        return true;
    }

}
