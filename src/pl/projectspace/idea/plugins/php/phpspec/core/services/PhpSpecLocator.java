package pl.projectspace.idea.plugins.php.phpspec.core.services;

import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.code.locator.GenericObjectLocator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PsiTreeUtils;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecLocator extends GenericObjectLocator {

    private final PsiTreeUtils utils;

    protected PhpSpecLocator(PhpIndex index, PsiTreeUtils utils) {
        super(index);
        this.utils = utils;
    }

    @Override
    public <T> T locate(String name) throws MissingElementException {
        return (T) utils.getClassByFQN(name);
    }

    public boolean isSpec(PhpClass phpClass) {
        return (phpClass.getNamespaceName().startsWith("\\spec") && phpClass.getNamespaceName().endsWith("Spec"));
    }

    public boolean isDescribed(PhpClass phpClass) {
        try {
            String name = getSpecFQNFor(phpClass);
            return (!isSpec(phpClass) && (locate(name) != null));
        } catch (MissingElementException e) {
            return false;
        }
    }

    public String getSpecFQNFor(PhpClass phpClass) {
        return getSpecNamespaceFor(phpClass) + "\\"+ getSpecNameFor(phpClass);
    }

    public String getSpecNameFor(PhpClass phpClass) {
        return phpClass.getName() + "Spec";
    }

    public String getSpecNamespaceFor(PhpClass phpClass) {
        return "\\spec" + phpClass.getNamespaceName().replaceAll("\\\\$", "");
    }


}
