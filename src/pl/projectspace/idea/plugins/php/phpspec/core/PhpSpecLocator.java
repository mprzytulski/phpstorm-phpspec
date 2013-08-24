package pl.projectspace.idea.plugins.php.phpspec.core;

import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.code.locator.GenericObjectLocator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecLocator extends GenericObjectLocator {

    protected PhpSpecLocator(PhpIndex index) {
        super(index);
    }

    @Override
    public <T> T locate(String name) throws MissingElementException {
        return null;
    }

    public boolean is(PhpClass phpClass) {
        return phpClass.getNamespaceName().startsWith("spec");
    }
}
