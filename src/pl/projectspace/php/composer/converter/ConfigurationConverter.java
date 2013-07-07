package pl.projectspace.php.composer.converter;

import com.google.gson.*;
import pl.projectspace.php.composer.Configuration;
import pl.projectspace.php.composer.Package;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class ConfigurationConverter implements JsonDeserializer<Configuration> {
    @Override
    public Configuration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Configuration config = new Configuration();

        for (Map.Entry<String, JsonElement>element : jsonElement.getAsJsonObject().entrySet()) {
            config.set(element.getKey(), element.getValue().getAsString());
        }

        return config;
    }
}
