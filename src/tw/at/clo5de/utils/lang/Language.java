package tw.at.clo5de.utils.lang;

import com.sun.javafx.binding.StringFormatter;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import tw.at.clo5de.SalariesMe;

import java.io.*;

public class Language {

    private File langFile = null;
    private MemorySection config = null;

    public Language (String langFileName) {
        this.langFile = new File(SalariesMe.INSTANCE.getDataFolder().getAbsolutePath() + "/Lang/" + langFileName + ".yml");
        if (!this.langFile.exists()) {
            this.langFile.getParentFile().mkdirs();
            try {
                InputStream fin = getClass().getResourceAsStream("/tw/at/clo5de/utils/lang/" + langFileName + ".yml");
                OutputStream os = new FileOutputStream(this.langFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    writer.write(line + "\n");
                }
                writer.close();
                reader.close();
                os.close();
                fin.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.langFile);
    }

    public String getText (String key, String ... value) {
        String text = config.getString(key);
        if (value.length != 0) {
            return StringFormatter.format(text, (Object[]) value).get();
        } else {
            return text;
        }
    }

}
