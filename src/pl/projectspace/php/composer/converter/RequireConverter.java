package pl.projectspace.php.composer.converter;

import com.google.gson.*;
import pl.projectspace.php.composer.*;
import pl.projectspace.php.composer.Package;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class RequireConverter  implements JsonDeserializer<Require> {
    @Override
    public Require deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Require require = new Require();

        for (Map.Entry<String, JsonElement>element : jsonElement.getAsJsonObject().entrySet()) {
            require.addPackage(new Package(element.getKey(), element.getValue().getAsString()));
        }

        return require;
    }
}
