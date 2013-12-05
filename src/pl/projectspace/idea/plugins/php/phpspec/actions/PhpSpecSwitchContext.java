package pl.projectspace.idea.plugins.php.phpspec.actions;

import com.intellij.ide.actions.OpenFileAction;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.action.PhpClassAction;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.commons.php.utils.annotation.PluginAction;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClass;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClassDecorator;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecDescribedClass;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecFactory;

@PluginAction("phpspec")
public class PhpSpecSwitchContext extends PhpClassAction {

    @Override
    protected void perform(PhpClass phpClass) {
        PhpSpecClassDecorator currentContext = getCurrentContext(phpClass);

        if (currentContext == null) {
            // @todo - propose creation of missing spec / class
            return;
        }

        PhpClass destination = null;

        try {
            if (currentContext instanceof PhpSpecClass) {
                destination = ((PhpSpecClass) currentContext).getDescribedClass().getDecoratedObject();
            } else {
                destination = ((PhpSpecDescribedClass) currentContext).getSpecClass().getDecoratedObject();
            }
        } catch (MissingElementException e1) {
            return;
        }

        OpenFileAction.openFile(destination.getContainingFile().getVirtualFile(), phpClass.getProject());
    }

    @Override
    protected boolean isAvailable(final PhpClass phpClass) {
        try {
            return (super.isAvailable(phpClass) && getCurrentContext(phpClass).hasRelatedClass());
        } catch (NullPointerException e1) {
        }

        return false;
    }

    @Override
    protected String getLabel(PhpClass phpClass) {
        PhpSpecClassDecorator currentContext = getCurrentContext(phpClass);

        try {
            if (currentContext instanceof PhpSpecClass) {
                return "Go to: " + ((PhpSpecClass) currentContext).getDescribedClass().getDecoratedObject().getName() + " class";
            } else {
                return "Go to: " + currentContext.getDecoratedObject().getName()  + " spec";
            }
        } catch (MissingElementException e) {
        } catch (NullPointerException e) {
        }

        return null;
    }

    private PhpSpecClassDecorator getCurrentContext(PhpClass phpClass) {
        try {
            PhpSpecFactory factory = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecFactory.class);

            return factory.create(phpClass);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
