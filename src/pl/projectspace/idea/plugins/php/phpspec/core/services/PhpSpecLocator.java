package pl.projectspace.idea.plugins.php.phpspec.core.services;

import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.code.locator.GenericObjectLocator;
import pl.projectspace.idea.plugins.commons.php.psi.element.PhpClassDecorator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClass;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecDescribedClass;
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
        PhpClass phpClass = utils.getClassByFQN(name);

        if (phpClass != null) {
            return (T) phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class).create(phpClass);
        }

        throw new MissingElementException("Failed to locate object");
    }

    public boolean isSpec(PhpClass phpClass) {
        return (phpClass.getFQN().startsWith("\\spec") && phpClass.getFQN().endsWith("Spec"));
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

    public String getDescribedFQNFor(PhpClass phpClass) {
        return phpClass.getFQN().replaceAll("^\\\\spec", "").replaceAll("Spec$", "");
    }

    public String getSpecNameFor(PhpClass phpClass) {
        return phpClass.getName() + "Spec";
    }

    public String getSpecNamespaceFor(PhpClass phpClass) {
        return getSpecNamespaceFor(phpClass.getFQN());
    }

    public String getSpecNamespaceFor(String fqn) {
        return "spec" + fqn.substring(0, fqn.lastIndexOf("\\"));
    }


    public PhpSpecClass locateSpecFor(PhpClass decoratedObject) throws MissingElementException {
        return locate(getSpecFQNFor(decoratedObject));
    }

    public PhpSpecDescribedClass locateDescriptionFor(PhpClass phpClass) throws MissingElementException {
        return locate(getDescribedFQNFor(phpClass));
    }
}
