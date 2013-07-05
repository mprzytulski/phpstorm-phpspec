package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.composer.ComposerDataService;
import com.jetbrains.php.composer.addDependency.ComposerPackage;
import com.jetbrains.php.composer.lib.ComposerLibrariesTreeStructureProvider;
import pl.projectspace.idea.plugins.php.phpspec.Settings;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecLocator {

    private final Project project;

    public PhpSpecLocator(Project project) {
        this.project = project;
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


        ComposerPackage.loadPackages();
        return null;
    }

}
