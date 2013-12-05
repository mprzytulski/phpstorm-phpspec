package pl.projectspace.idea.plugins.php.phpspec.core.inspection;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.commons.php.code.inspection.PhpClassInspection;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.commons.php.utils.PhpClassUtils;
import pl.projectspace.idea.plugins.commons.php.utils.annotation.DependsOnPlugin;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.inspection.actions.GenerateSpecForClassFix;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;

@DependsOnPlugin("phpspec")
public class PhpSpecClassInspection extends PhpClassInspection {

    @Override
    protected PhpElementVisitor getVisitor(@NotNull ProblemsHolder holder) {
        return new ClassInspector(holder);
    }

    public final class ClassInspector extends PhpElementVisitor {

        private ProblemsHolder holder;

        private PhpSpecLocator locator;

        public ClassInspector(ProblemsHolder holder) {
            this.holder = holder;
            this.locator = holder.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
        }

        @Override
        public void visitPhpClass(PhpClass phpClass) {
            if (phpClass.isInterface() || phpClass.isAbstract()) {
                return;
            }

            if (!locator.isSpec(phpClass)) {
                try {
                    locator.locate(locator.getSpecFQNFor(phpClass));
                } catch (MissingElementException e) {
                    LeafPsiElement id = PhpClassUtils.getClassNameIdentifierFrom(phpClass);
                    holder.registerProblem(id, "Missing spec for class: " + phpClass.getName(), new GenerateSpecForClassFix(phpClass));
                }
            }
        }
    }

}
