package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.php.lang.PhpFileType;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.action.PhpClassAction;
import pl.projectspace.idea.plugins.commons.php.utils.PhpClassUtils;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;
import pl.projectspace.idea.plugins.php.phpspec.core.annotations.PhpSpecAction;
import pl.projectspace.idea.plugins.php.phpspec.core.services.FileFactory;

import java.io.IOException;
import java.util.Properties;

public class CreateSpecForClass extends PhpClassAction {
    public void actionPerformed(final AnActionEvent e) {
        final PhpClass phpClass = getPhpClassFromContext(e);
        Project project = phpClass.getProject();
        try {
            createSpecFor(phpClass);
        } catch (Exception e1) {
            Messages.showErrorDialog(project, "Failed creating spec for given class.", "Failed Creating Spec File.");
        }
    }

    @Override
    @PhpSpecAction
    public void update(AnActionEvent e) {
        PhpSpecLocator locator = e.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
        PhpClass phpClass = getPhpClassFromContext(e);
        e.getPresentation().setEnabled(
            (phpClass != null && !locator.isSpec(phpClass))
        );
    }

    public void createSpecFor(PhpClass phpClass) throws IOException {
        Project project = phpClass.getProject();
        PhpSpecLocator locator = project.getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);

        String className = locator.getSpecNameFor(phpClass);
        String namespace = locator.getSpecNamespaceFor(phpClass);

        Properties properties = FileTemplateManager.getInstance().getDefaultProperties();
        properties.setProperty("CLASS_NAME", className);
        properties.setProperty("NAMESPACE", namespace);

        String specPath = PhpClassUtils.getPSRPathFromClassNamespace(namespace);

        VirtualFile baseDir = project.getBaseDir();
        VirtualFile dir = baseDir.findFileByRelativePath(specPath);

        if (dir == null) {
            dir = project.getComponent(PhpSpecProject.class).getService(FileFactory.class)
                .createNamespaceDirectory(baseDir, specPath);
        }

        ApplicationManager.getApplication().runWriteAction(
            project.getComponent(PhpSpecProject.class).getService(FileFactory.class)
                .getCreateFileFromTemplateWriteAction(
                    dir,
                    className.concat(".php"),
                    PhpFileType.INSTANCE,
                    "Spec Class.php",
                    properties
                )
        );
    }

}
