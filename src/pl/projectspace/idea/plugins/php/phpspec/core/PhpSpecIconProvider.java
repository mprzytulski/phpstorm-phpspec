package pl.projectspace.idea.plugins.php.phpspec.core;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.commons.php.psi.element.PluginIconProvider;
import pl.projectspace.idea.plugins.commons.php.utils.annotation.DependsOnPlugin;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;

import javax.swing.*;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
@DependsOnPlugin("phpspec")
public class PhpSpecIconProvider extends PluginIconProvider {

    private PhpSpecLocator locator;

    @Nullable
    protected Icon getIconForElement(@NotNull PsiElement element, @Iconable.IconFlags int i) {
        if ((element instanceof PhpFile)) {
            PhpSpecLocator locator = getUtils(element);

            for (PsiNamedElement el : ((PhpFile)element).getTopLevelDefs().values()) {
                if (el instanceof PhpClass && locator.isSpec((PhpClass) el)) {
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
