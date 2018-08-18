package tw.at.clo5de.worker.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tw.at.clo5de.SalariesMe;

public class Command implements CommandExecutor {

    public Command () {}

    public void setExecutor () {
        SalariesMe.INSTANCE.getCommand("SalariesMe").setExecutor(this);
        SalariesMe.INSTANCE.getCommand("sm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings[0].equalsIgnoreCase("Add")) {
            return new AddWorker().onCommand(commandSender, command, s, strings);
        } else if (strings[0].equalsIgnoreCase("onDuty")) {
            return new SetDuty().onCommand(commandSender, command, s, strings);
        }
        return false;
    }
}
