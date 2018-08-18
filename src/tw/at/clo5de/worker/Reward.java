package tw.at.clo5de.worker;

import org.bukkit.configuration.MemorySection;

public class Reward {

    private int day = 0, month = 0;

    public Reward (MemorySection config) {
        this.day = config.getInt("DayAmount");
        this.month = config.getInt("MonthAmount");
    }

}
