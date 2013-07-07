package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.command.Locator;

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

    private String phpSpecPath;

    public static Settings getInstance(Project project) {
        Settings settings = ServiceManager.getService(project, Settings.class);

        Locator l = ServiceManager.getService(project, Locator.class);

        return settings;
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

    public String getPhpSpecPath() {
        return phpSpecPath;
    }

    public void setPhpSpecPath(String phpSpecPath) {
        this.phpSpecPath = phpSpecPath;
    }
}
