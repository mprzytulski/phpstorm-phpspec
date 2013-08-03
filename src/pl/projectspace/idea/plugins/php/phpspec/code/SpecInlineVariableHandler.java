package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.lang.Language;
import com.intellij.lang.refactoring.InlineActionHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecInlineVariableHandler extends InlineActionHandler {
    @Override
    public boolean isEnabledForLanguage(Language l) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canInlineElement(PsiElement element) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void inlineElement(Project project, Editor editor, PsiElement element) {

    }
}
