package pl.projectspace.idea.plugins.php.phpspec.code.type.provider;

import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.*;
import com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider2;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.commons.php.code.type.GenericTypeProvider;
import pl.projectspace.idea.plugins.php.phpspec.core.PhpSpecClass;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class WrappedObjectTypeProvider extends GenericTypeProvider {

    @Nullable
    @Override
    public String getTypeFor(PsiElement psiElement) {

        if (DumbService.isDumb(psiElement.getProject())) {
            return null;
        }

        if (psiElement instanceof MethodReference) {
            MethodReference method = (MethodReference)psiElement;
            if (!method.getName().equals("getWrappedObject")) {
                return null;
            }

            PhpClass phpClass = PsiTreeUtil.getContextOfType(psiElement, PhpClass.class, false);
//            PhpClass returnType = PhpSpecClass.getClassForSpec(phpClass, psiElement.getProject());

//            return returnType.getFQN();
        }

        return null;
    }

}
