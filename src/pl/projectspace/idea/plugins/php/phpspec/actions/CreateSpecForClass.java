package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.actions.OpenFileAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.impl.VirtualFileImpl;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.ui.PhpSingleConfigurableEditor;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecClass;
import pl.projectspace.idea.plugins.php.phpspec.command.Describe;
import pl.projectspace.idea.plugins.php.phpspec.command.Executor;

import java.io.File;
import java.util.Collection;

public class CreateSpecForClass extends PhpClassAction {
    public void actionPerformed(final AnActionEvent e) {
        final PhpClass phpClass = getPhpClassFromContext(e);

        final Describe cmd = new Describe(e.getProject(), phpClass);
        Executor executor = ServiceManager.getService(e.getProject(), Executor.class);
        executor.execute(cmd, new Runnable() {
            @Override
            public void run() {
                String s = "";
                OpenFileAction.openFile(cmd.getSpecPath(), e.getProject());
            }
        });
    }

    @Override
    public void update(AnActionEvent e) {
        PhpClass phpClass = getPhpClassFromContext(e);

        if (phpClass == null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        if (PhpIndex.getInstance(e.getProject()).getAnyByFQN("\\spec" + phpClass.getFQN() + "Spec").size() > 0) {
            e.getPresentation().setEnabled(false);
            return;
        }

        super.update(e);
    }

}
