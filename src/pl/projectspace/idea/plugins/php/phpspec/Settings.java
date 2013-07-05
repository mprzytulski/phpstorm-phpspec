package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.command.PhpSpecLocator;

@State(
        name = "Settings",
        storages = {
                @Storage(id = "default", file="$PROJECT_CONFIG_DIR$/phpspec.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)

public class Settings implements PersistentStateComponent<Settings> {

    public enum PhpSpecLocationMode {
        LOCAL,
        GLOBAL
    }

    protected Project project;

    protected PhpSpecLocator locator = null;

    public static Settings getInstance(Project project) {
        Settings settings = ServiceManager.getService(project, Settings.class);
        if (settings.locator == null) {
            settings.project = project;
            settings.locator = new PhpSpecLocator(project);
        }

        return settings;
    }

    public PhpSpecLocator getLocator() {
        return locator;
    }

    @Nullable
    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(Settings settings) {
        XmlSerializerUtil.copyBean(settings, this);
    }

}
