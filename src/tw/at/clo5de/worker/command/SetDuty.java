package tw.at.clo5de.worker.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.SalariesMe;
import tw.at.clo5de.worker.Worker;

import static org.bukkit.Bukkit.getServer;

public class SetDuty {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p;
        if (commandSender instanceof ConsoleCommandSender) {
            // sm onduty [Player Name]
            p = getServer().getPlayer(strings[1]);
        } else { // (commandSender instanceof Player) {
            // sm onduty
            p = ((Player) commandSender);
        }
        Worker w = SalariesMe.workerHandler.getWorker(p);
        if (w.getDuty().isOnDuty()) {
            w.getDuty().off();
            p.sendMessage(SalariesMe.language.getText("Worker_Duty_On"));
        } else {
            w.getDuty().on();
            p.sendMessage(SalariesMe.language.getText("Worker_Duty_Off"));
        }
        return true;
    }

}
