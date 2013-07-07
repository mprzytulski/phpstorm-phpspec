package pl.projectspace.php.composer;

import com.google.gson.annotations.SerializedName;
import com.jetbrains.php.composer.addDependency.ComposerPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Composer {

    private String path;

    enum Type {
        LIBRARY,
        PROJECT,
        METAPACKAGE,
        COMPOSER_INSTALLER
    }

    private String configFile;

    private String name;
    private String description;
    private String version;
    private Type type;
    private String keywords;
    private String homepage;
    private String license;

    private Configuration config = new Configuration();


    @SerializedName("minimum-stability")
    private String minimumStability;

    private Require require;

    public Composer() {

    }

    public void setFile(String path) {
        this.path = path;
    }

    public Collection<Package> getRequiredPackages() {
        return require.getCollection();
    }

    public boolean isRequired(String packageName) {
        return require.hasPackage(packageName);
    }

    public String getMinimumStability() {
        return minimumStability;
    }

//    public String get

    public Configuration getConfig() {
        return config;
    }

}
