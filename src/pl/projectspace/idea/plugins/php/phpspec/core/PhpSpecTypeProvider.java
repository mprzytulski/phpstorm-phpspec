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
    @Nullable
    @Override
    public String getType(PsiElement element) {

        PhpSpecLocator locator = element.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
        PhpClass phpClass = PsiTreeUtil.getParentOfType(element, PhpClass.class);

        if (!(element instanceof Variable) || !((Variable)element).getName().equals("this") || (phpClass == null) || !locator.isSpec(phpClass)) {
            return null;
        }

        try {
            PhpSpecDescribedClass spec = locator.locateDescriptionFor(phpClass);

            return spec.getDecoratedObject().getFQN();
        } catch (MissingElementException e) {
            System.out.println("test");
        }

        return null;
    }
}
