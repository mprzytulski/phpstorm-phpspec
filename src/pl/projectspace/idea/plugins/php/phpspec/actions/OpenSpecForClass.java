package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.actions.OpenFileAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecClass;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class OpenSpecForClass extends PhpClassAction {
    public void actionPerformed(AnActionEvent e) {
        PhpClass phpClass = (PhpClass)getPhpClassFromContext(e);
        PhpClass specClass = PhpSpecClass.getSpecForClass(phpClass, e.getProject());

        if (specClass == null) {
            return;
        }

        OpenFileAction.openFile(specClass.getContainingFile().getVirtualFile(), e.getProject());
    }
}
