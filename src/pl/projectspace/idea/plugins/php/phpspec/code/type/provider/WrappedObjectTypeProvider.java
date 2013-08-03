package pl.projectspace.idea.plugins.php.phpspec.code.type.provider;

import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.parser.parsing.classes.ClassReference;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.elements.*;
import com.jetbrains.php.lang.psi.resolve.types.PhpType;
import com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider2;
import com.jetbrains.php.lang.psi.stubs.indexes.PhpInterfaceIndex;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class WrappedObjectTypeProvider implements PhpTypeProvider2 {

    final static char TRIM_KEY = '\u0180';

    @Override
    public char getKey() {
        return '\u0150';
    }

    @Nullable
    @Override
    public String getType(PsiElement psiElement) {

        if (psiElement instanceof MethodReference) {
            MethodReference method = (MethodReference)psiElement;
            if (!method.getName().equals("getWrappedObject")) {
                return null;
            }

            PhpClass phpClass = PsiTreeUtil.getContextOfType(psiElement, PhpClass.class, false);
            PhpClass returnType = PhpSpecClass.getClassForSpec(phpClass, psiElement.getProject());

            return returnType.getFQN();
        }

        return null;
    }

    @Override
    public Collection<? extends PhpNamedElement> getBySignature(String expression, Project project) {

        PhpIndex phpIndex = PhpIndex.getInstance(project);

//        PhpClass t = phpIndex.getClassesByFQN("\\Test\\Test").iterator().next();
        PhpClass phpClass = phpIndex.getClassesByFQN(expression).iterator().next();



//        if (phpClass == null) {
//            return null;
//        }
//
        return Arrays.asList(phpClass);
    }
}
