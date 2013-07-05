package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.php.composer.ComposerDataService;
import pl.projectspace.idea.plugins.php.phpspec.Settings;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InitialiseProject extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        String path = Settings.getInstance(e.getProject()).getLocator().getPath();

        if (path != null) {
            return;
        }
    }

//    @Override
//    public void update(AnActionEvent e) {
//
//    }
}
