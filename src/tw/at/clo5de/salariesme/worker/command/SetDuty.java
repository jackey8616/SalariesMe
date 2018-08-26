package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;
import tw.at.clo5de.salariesme.worker.Worker;

import static org.bukkit.Bukkit.getServer;

public class SetDuty {

    public boolean onCommand(Handler handler, CommandSender commandSender, Command command, String s, String[] strings) {
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
            p.sendMessage(SalariesMe.language.getText("Worker_Duty_Off"));
        } else {
            if (w.getDuty().getPosition().containWorld(p.getWorld())) {
                w.getDuty().on();
                p.sendMessage(SalariesMe.language.getText("Worker_Duty_On", w.getDuty().getPosition().getGameModeString()));
            } else {
                p.getPlayer().sendMessage(SalariesMe.language.getText("No_Duty_In_This_World"));
            }
        }
        return true;
    }

}
