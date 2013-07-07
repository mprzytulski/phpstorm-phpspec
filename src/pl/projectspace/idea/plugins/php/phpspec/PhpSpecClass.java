package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.PhpClass;

import java.util.Collection;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecClass {

    public static PhpClass getSpecForClass(PhpClass phpClass, Project project) {
        Collection<PhpClass> result = PhpIndex.getInstance(project).getAnyByFQN(
                getSpecClassNameForClass(phpClass)
        );

        if (result.isEmpty()) {
            return null;
        }

        return result.iterator().next();
    }

    protected static String getSpecClassNameForClass(PhpClass phpClass) {
        return "\\spec" + phpClass.getFQN() + "Spec";
    }

}
