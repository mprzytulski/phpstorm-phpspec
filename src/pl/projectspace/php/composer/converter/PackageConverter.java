package pl.projectspace.php.composer.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import pl.projectspace.php.composer.Package;

import java.lang.reflect.Type;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PackageConverter implements JsonDeserializer<pl.projectspace.php.composer.Package> {
    @Override
    public pl.projectspace.php.composer.Package deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new pl.projectspace.php.composer.Package();
    }
}
