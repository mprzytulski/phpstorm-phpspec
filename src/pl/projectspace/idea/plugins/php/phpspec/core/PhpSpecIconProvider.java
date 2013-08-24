package pl.projectspace.idea.plugins.php.phpspec.core;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;

import javax.swing.*;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecIconProvider extends IconProvider {

    private PhpSpecLocator locator;

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement element, @Iconable.IconFlags int i) {
        if (!PhpSpecProject.isEnabled()) {
            return null;
        }

        PhpSpecLocator locator = getUtils(element);
        if ((element instanceof PhpFile)) {
            for (PsiNamedElement el : ((PhpFile)element).getTopLevelDefs().values()) {
                if (el instanceof PhpClass && locator.is((PhpClass) el)) {
                    return PhpSpecIcons.File;
                }
            }
        }
        return null;
    }

    private PhpSpecLocator getUtils(PsiElement element) {
        if (locator == null) {
            locator = element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
        }

        return locator;
    }

}
