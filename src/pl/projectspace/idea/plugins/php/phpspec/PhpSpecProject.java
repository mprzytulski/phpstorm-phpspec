package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.commons.php.ProjectComponent;
import pl.projectspace.idea.plugins.commons.php.StateComponentInterface;
import pl.projectspace.idea.plugins.php.phpspec.config.PhpSpec;

public class PhpSpecProject extends ProjectComponent implements StateComponentInterface {

    private final PhpSpec config;

    public PhpSpecProject(Project project, PhpIndex index) {
        super(project, index);
        config = new PhpSpec(project);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "phpspec";
    }

    public boolean isEnabled() {
        return true;
    }

    public PhpSpec getConfig() {
        return config;
    }
}
