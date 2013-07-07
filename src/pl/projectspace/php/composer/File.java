package pl.projectspace.php.composer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.projectspace.php.composer.converter.RequireConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class File {

    private final String path;

    public File(String path) {
        this.path = path;
    }

    public Composer parse() {
        try {
            InputStreamReader is = new InputStreamReader(
                    new FileInputStream(this.path)
            );

            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(Require.class, new RequireConverter());

            Gson parser = gson.create();

            Composer composer = parser.fromJson(is, Composer.class);
            composer.setFile(this.path);

            return composer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
