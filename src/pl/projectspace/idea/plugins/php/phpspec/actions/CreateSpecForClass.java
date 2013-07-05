package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;

/**
 * Created with IntelliJ IDEA.
 * User: mprzytulski
 * Date: 04/07/2013
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class CreateSpecForClass extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        PhpClass c = getPhpClassFromContext(e);

//        if (c == null) {
//            return;
//        }
    }

    private PhpClass getPhpClassFromContext(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        PhpClass phpClass = PsiTreeUtil.getParentOfType(elementAt, PhpClass.class);
        return phpClass;
    }
}
