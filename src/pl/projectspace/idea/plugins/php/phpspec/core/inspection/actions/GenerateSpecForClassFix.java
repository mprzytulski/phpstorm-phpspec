package pl.projectspace.idea.plugins.php.phpspec.core.inspection.actions;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import org.jetbrains.annotations.NotNull;
import pl.projectspace.idea.plugins.php.phpspec.actions.CreateSpecForClassAction;

import java.io.IOException;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class GenerateSpecForClassFix implements LocalQuickFix {

    private final PhpClass relatedClass;

    public GenerateSpecForClassFix(PhpClass phpClass) {
        relatedClass = phpClass;
    }

    @NotNull
    @Override
    public String getName() {
        return "Create missing spec for: '" + relatedClass.getName() + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return getName();
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor problemDescriptor) {
        if (!(problemDescriptor.getPsiElement() instanceof LeafPsiElement)
            || !((LeafPsiElement) problemDescriptor.getPsiElement()).getElementType().toString().equals("identifier")) {
            return;
        }

        CreateSpecForClassAction action = new CreateSpecForClassAction();
        action.perform(relatedClass);
    }

}
