package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.composer.*;
import com.jetbrains.php.composer.lib.ComposerLibraryManager;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: mprzytulski
 * Date: 04/07/2013
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class PhpSpecProjectComponent implements ProjectComponent {

    private Project project;

    public PhpSpecProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
//        ComposerDataService service = ComposerDataService.getInstance(project).getConfigPath();
//        boolean enabled = (!service.isPharWellConfigured()) || (!service.isConfigWellConfigured());
    }

    @Override
    public void projectClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("disposeComponent");
    }

    @Override
    public void initComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void disposeComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
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
