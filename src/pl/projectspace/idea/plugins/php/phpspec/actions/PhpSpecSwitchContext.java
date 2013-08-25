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

        PhpFile file = (PhpFile) LangDataKeys.PSI_FILE.getData(e.getDataContext());

        if (file == null) {
            return;
        }

        PhpClass phpClass = PhpPsiUtil.findClass(file, Condition.TRUE);

        PhpSpecFactory factory = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class);

        PhpSpecClassDecorator destination = factory.create(phpClass);

        if (destination == null) {
            // @todo - propose creation of missing spec / class
            return;
        }

        OpenFileAction.openFile(destination.getDecoratedObject().getContainingFile().getVirtualFile(), e.getProject());
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);

        PsiFile psiFile = LangDataKeys.PSI_FILE.getData(e.getDataContext());

        if (!(psiFile instanceof PhpFile)) {
            e.getPresentation().setVisible(false);
            return;
        }

        PhpFile phpFile = (PhpFile) psiFile;
        PhpClass phpClass = PhpPsiUtil.findClass(phpFile, Condition.TRUE);
        PhpSpecFactory factory = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class);
        PhpSpecClassDecorator destination = factory.create(phpClass);

        String label = null;
        boolean enabled = false;

        if (destination != null) {
            label = "Go to: " + destination.getDecoratedObject().getName();
            enabled = true;
        }

        e.getPresentation().setText(label);
        e.getPresentation().setVisible(enabled);
        e.getPresentation().setEnabled(enabled);
    }
}
