package pl.projectspace.idea.plugins.php.phpspec.core;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.Variable;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.commons.php.code.type.GenericTypeProvider;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecTypeProvider extends GenericTypeProvider {

    private PhpSpecLocator locator = null;

    @Nullable
    @Override
    protected String getTypeFor(PsiElement element) {

        PhpSpecLocator locator1 = getLocator(element);
        PhpClass phpClass = PsiTreeUtil.getParentOfType(element, PhpClass.class);

        if (!(element instanceof Variable) || !((Variable)element).getName().equals("this")
                || (phpClass == null) || !locator1.isSpec(phpClass)) {
            return null;
        }

        try {
            PhpSpecDescribedClass spec = locator1.locateDescriptionFor(phpClass);

            return spec.getDecoratedObject().getFQN();
        } catch (MissingElementException e) {
        }

        return null;
    }

    private PhpSpecLocator getLocator(PsiElement element) {
        if (locator == null) {
            locator = element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
        }

        return locator;
    }
}
