package pl.projectspace.idea.plugins.php.phpspec.core;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.psi.element.PhpClassDecorator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.commons.php.utils.PhpClassUtils;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecFactory;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PsiTreeUtils;

import java.util.Collection;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecClass extends PhpSpecClassDecorator {

    public PhpSpecClass(PhpClass phpClass) {
        super(phpClass);
    }

    public PhpSpecDescribedClass getDescribedClass() throws MissingElementException {
        return getService(PhpSpecLocator.class).locateDescriptionFor(getDecoratedObject());
    }
}
