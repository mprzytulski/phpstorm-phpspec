package pl.projectspace.idea.plugins.php.phpspec.core;

import com.jetbrains.php.lang.psi.elements.PhpClass;
import pl.projectspace.idea.plugins.commons.php.psi.element.PhpClassDecorator;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PsiTreeUtils;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecDescribedClass extends PhpSpecClassDecorator {

    public PhpSpecDescribedClass(PhpClass phpClass) {
        super(phpClass);
    }

    public boolean isSpec() {
        return false;
    }

    public PhpSpecClass getSpecClass() {
        return null;
//        return new PhpSpecDescribedClass(
//            getDecoratedObject().getProject().getComponentClass(PhpSpecProject.class)
//                .getService(PsiTreeUtils.class).getClassByFQN(
//                    getSpecNamespaceFor(getDecoratedObject()) + "\\"+ getSpecNameFor(getDecoratedObject())
//                )
//        );
    }


    //    public static PhpClass getSpecForClass(PhpClass phpClass) {
//        return getClassByFQN(
//                getSpecNameForClass(phpClass, project)
//        );
//    }
//
//    public static boolean isSpec(PhpClass phpClass) {
//        if (!phpClass.getFQN().endsWith("Spec")) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public static PhpClass getClassForSpec(PhpClass phpClass, Project project) {
//        return getClassByFQN(
//            getClassNameForSpec(phpClass, project), project
//        );
//    }
//
//    public static PhpClass getClassForSpec(String phpClass, Project project) {
//        return getClassByFQN(
//                getClassNameForSpec(phpClass, project), project
//        );
//    }
//
//    protected static PhpClass getClassByFQN(String fqn, Project project) {
//        Collection<PhpClass> result = PhpIndex.getInstance(project).getAnyByFQN(fqn);
//
//        if (result.isEmpty()) {
//            return null;
//        }
//
//        return result.iterator().next();
//    }
//
//    protected static String getSpecNameForClass(PhpClass phpClass, Project project) {
//        return "\\spec" + phpClass.getFQN() + "Spec";
//    }
//
//    protected static String getClassNameForSpec(PhpClass phpClass, Project project) {
//        return getClassNameForSpec(phpClass.getFQN(), project);
//    }
//
//    protected static String getClassNameForSpec(String phpClass, Project project) {
//        return phpClass.replace("\\spec", "").replaceAll("Spec$", "");
//    }
}
