package pl.projectspace.idea.plugins.php.phpspec.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.psi.element.PhpClassDecorator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PsiTreeUtils;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecDescribedClass extends PhpSpecClassDecorator {

    public PhpSpecDescribedClass(PhpClass phpClass) {
        super(phpClass);
    }

    public PhpSpecClass getSpecClass() throws MissingElementException {
        return getService(PhpSpecLocator.class).locateSpecFor(getDecoratedObject());
    }

}
