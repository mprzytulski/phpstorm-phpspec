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

public class PhpSpecProjectComponent implements ProjectComponent {

    private Project project;

    public PhpSpecProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        Locator locator = ServiceManager.getService(project, Locator.class)
            .setProject(project);

        ServiceManager.getService(project, Executor.class)
            .setProject(project)
            .setLocator(locator);
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
        return "PhpSpecProjectComponent";
    }

    public static boolean isEnabled(Project project) {
//        return Settings.getInstance(project).pluginEnabled;
        return true;
    }
}
