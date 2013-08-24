package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.commons.php.ProjectComponent;

public class PhpSpecProject extends ProjectComponent {

    public PhpSpecProject(Project project, PhpIndex index) {
        super(project, index);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "phpspec";
    }

    public static boolean isEnabled() {
        return true;
    }
}
