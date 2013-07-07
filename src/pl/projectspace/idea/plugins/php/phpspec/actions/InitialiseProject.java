package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import pl.projectspace.idea.plugins.php.phpspec.Settings;
import pl.projectspace.idea.plugins.php.phpspec.command.Locator;
import pl.projectspace.idea.plugins.php.phpspec.dialog.EnablePhpSupportDialog;


/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InitialiseProject extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        String path = ServiceManager.getService(e.getProject(), Locator.class).getPath();

        if (path != null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        EnablePhpSupportDialog dialog = new EnablePhpSupportDialog(e.getProject());
        dialog.setVisible(true);
    }
}
