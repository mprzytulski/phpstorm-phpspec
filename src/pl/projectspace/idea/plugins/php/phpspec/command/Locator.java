package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.composer.ComposerDataService;
import pl.projectspace.php.composer.Composer;

import java.io.File;
import java.util.Map;

@State(
        name = "Locator",
        storages = {
                @Storage(id = "default", file="$PROJECT_CONFIG_DIR$/locator.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Locator {

    private Project project;

    public Locator setProject(Project project) {
        this.project = project;

        return this;
    }

    public String getPath() {
        String path = getGlobalPath();
        if (path != null) {
            return path;
        }

        return getComposerPath();
    }

    private String getGlobalPath() {
        Map<String, String> env = System.getenv();
        String paths = env.get("PATH");
        String separator = System.getProperty("path.separator");

        for (String path : paths.split(separator)) {
            File file = new File(path.concat("/phpspec"));
            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }

        return null;
    }

    private String getComposerPath() {
        ComposerDataService service = ComposerDataService.getInstance(project);
        if (service.getConfigPath() == null) {
            return null;
        }

        Composer composer = new pl.projectspace.php.composer.File(service.getConfigPath()).parse();

        if (!composer.isRequired("phpspec/phpspec")) {
            return null;
        }

        String path = project.getBasePath() + "/" + composer.getConfig().getBinDir() + "/phpspec";

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        return path;
    }

}
