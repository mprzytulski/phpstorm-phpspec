package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.composer.*;
import com.jetbrains.php.composer.lib.ComposerLibraryManager;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.php.phpspec.command.Executor;
import pl.projectspace.idea.plugins.php.phpspec.command.Locator;

public class PhpSpecProject implements ProjectComponent {

    private Project project;

    public PhpSpecProject(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
        System.out.println("disposeComponent");
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "phpspec";
    }

    public static boolean isEnabled(Project project) {
        return true;
    }
}
