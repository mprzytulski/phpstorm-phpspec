package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.actions.OpenFileAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiFile;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.action.PhpClassAction;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClass;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClassDecorator;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecDescribedClass;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecFactory;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecSwitchContext extends PhpClassAction {
    public void actionPerformed(AnActionEvent e) {

        PhpSpecClassDecorator currentContext = getCurrentContext(e);

        if (currentContext == null) {
            // @todo - propose creation of missing spec / class
            return;
        }

        PhpClass destination = null;

        try {
            if (currentContext instanceof PhpSpecClass) {
                destination = ((PhpSpecClass) currentContext).getDescribedClass().getDecoratedObject();
            } else {
                destination = ((PhpSpecDescribedClass) currentContext).getSpecClass().getDecoratedObject();
            }
        } catch (MissingElementException e1) {
            return;
        }

        OpenFileAction.openFile(destination.getContainingFile().getVirtualFile(), e.getProject());
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);

        PhpSpecClassDecorator current = getCurrentContext(e);

        String label = null;
        boolean enabled = false;

        try {
            if (current != null) {
                if (current instanceof PhpSpecClass) {
                    label = "Go to: " + ((PhpSpecClass) current).getDescribedClass().getDecoratedObject().getName();
                } else {
                    label = "Go to: " + ((PhpSpecDescribedClass) current).getSpecClass().getDecoratedObject().getName();
                }

                enabled = true;
            }
        } catch (MissingElementException e1) {
            enabled = false;
        }

        e.getPresentation().setText(label);
        e.getPresentation().setVisible(enabled);
        e.getPresentation().setEnabled(enabled);
    }

    private PhpSpecClassDecorator getCurrentContext(AnActionEvent e) {
        PsiFile psiFile = LangDataKeys.PSI_FILE.getData(e.getDataContext());

        if (!(psiFile instanceof PhpFile)) {
            return null;
        }

        PhpFile phpFile = (PhpFile) psiFile;
        PhpClass phpClass = PhpPsiUtil.findClass(phpFile, Condition.TRUE);
        PhpSpecFactory factory = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class);

        return factory.create(phpClass);
    }
}
