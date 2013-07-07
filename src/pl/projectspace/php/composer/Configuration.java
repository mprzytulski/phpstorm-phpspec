package pl.projectspace.php.composer;

import java.util.HashMap;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Configuration {

    private HashMap<String, String>values = new HashMap<String, String>();

    public void set(String key, String value) {
        values.put(key, value);
    }

    public String getBinDir() {
        if (!values.containsKey("bin-dir")) {
            return "vendor/bin";
        }

        return values.get("bin-dir");
    }

}
