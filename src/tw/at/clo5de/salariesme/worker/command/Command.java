package tw.at.clo5de.salariesme.worker.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tw.at.clo5de.salariesme.SalariesMe;
import tw.at.clo5de.salariesme.worker.Handler;

public class Command implements CommandExecutor {

    private Handler handler;

    public Command () {}

    public void setExecutor (Handler handler) {
        this.handler = handler;
        SalariesMe.INSTANCE.getCommand("SalariesMe").setExecutor(this);
        SalariesMe.INSTANCE.getCommand("sm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length > 0) {
            if (strings[0].equalsIgnoreCase("add")) {
                return new AddWorker().onCommand(this.handler, commandSender, command, s, strings);
            } else if (strings[0].equalsIgnoreCase("remove") || strings[0].equalsIgnoreCase("rm")) {
                return new RemoveWorker().onCommand(this.handler, commandSender, command, s, strings);
            } else if (strings[0].equalsIgnoreCase("list") || strings[0].equalsIgnoreCase("ls")) {
                return new ListWorker().onCommand(this.handler, commandSender, command, s, strings);
            } else if (strings[0].equalsIgnoreCase("list-position") || strings[0].equalsIgnoreCase("ls-pos")) {
                return new ListPosition().onCommand(this.handler, commandSender, command, s, strings);
            } else if (strings[0].equalsIgnoreCase("info")) {
                return new InfoWorker().onCommand(this.handler, commandSender, command, s, strings);
            } else if (strings[0].equalsIgnoreCase("onDuty")) {
                return new SetDuty().onCommand(this.handler, commandSender, command, s, strings);
            }
        }
        return false;
    }
}
