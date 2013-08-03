package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.actions.OpenFileAction;
import com.intellij.ide.impl.DataManagerImpl;
import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.file.impl.FileManagerImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.PhpFileImpl;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.util.pathmapper.PhpPathMapper;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecClass;

import java.awt.*;
import java.awt.event.InputEvent;

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
        PhpClass classToOpen;

        if (PhpSpecClass.isSpec(phpClass)) {
            classToOpen = PhpSpecClass.getClassForSpec(phpClass, e.getProject());
        }
        else {
            classToOpen = PhpSpecClass.getSpecForClass(phpClass, e.getProject());
        }

        if (classToOpen == null) {
            return;
        }

        OpenFileAction.openFile(classToOpen.getContainingFile().getVirtualFile(), e.getProject());
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

        if (phpFile == null) {
            e.getPresentation().setVisible(false);
            return;
        }

        PhpClass phpClass = PhpPsiUtil.findClass(phpFile, Condition.TRUE);
        PhpClass specClass = PhpSpecClass.getSpecForClass(phpClass, e.getProject());


        if (!PhpSpecClass.isSpec(phpClass) && phpClass != null && specClass != null) {
            e.getPresentation().setText("Go to spec class");
            e.getPresentation().setVisible(true);
            return;
        }
        else if (PhpSpecClass.isSpec(phpClass) && phpClass != null) {
            e.getPresentation().setText("Go to specified class");
            e.getPresentation().setVisible(true);
            return;
        }

        e.getPresentation().setVisible(false);
    }
}
