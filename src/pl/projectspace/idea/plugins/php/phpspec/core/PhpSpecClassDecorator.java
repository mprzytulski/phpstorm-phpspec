package pl.projectspace.idea.plugins.php.phpspec.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.ProjectComponent;
import pl.projectspace.idea.plugins.commons.php.psi.element.PhpClassDecorator;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public abstract class PhpSpecClassDecorator extends PhpClassDecorator {

    /**
     * Create PhpClass decorator
     *
     * @param phpClass
     */
    public PhpSpecClassDecorator(PhpClass phpClass) {
        super(phpClass);
    }

    @Override
    protected ProjectComponent getComponent() {
        return getDecoratedObject().getProject().getComponent(PhpSpecProject.class);
    }
}
