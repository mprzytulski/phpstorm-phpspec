package pl.projectspace.idea.plugins.php.phpspec.core.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.commons.php.psi.element.MethodDecorator;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.InvalidArgumentException;
import pl.projectspace.idea.plugins.commons.php.psi.exceptions.MissingElementException;
import pl.projectspace.idea.plugins.commons.php.utils.PhpClassUtils;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.inspection.actions.GenerateSpecForClassFix;
import pl.projectspace.idea.plugins.php.phpspec.core.services.PhpSpecLocator;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecClassInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new ClassInspector(holder);
    }

    public final class ClassInspector extends PhpElementVisitor {

        private ProblemsHolder holder;

        public ClassInspector(ProblemsHolder holder) {
            this.holder = holder;
        }

        @Override
        public void visitPhpClass(PhpClass phpClass) {
            if (phpClass.isInterface() || phpClass.isAbstract()) {
                return;
            }

            PhpSpecLocator locator = phpClass.getProject().getComponent(PhpSpecProject.class).getService(PhpSpecLocator.class);
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
