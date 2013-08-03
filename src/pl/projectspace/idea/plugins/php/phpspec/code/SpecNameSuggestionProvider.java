package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.refactoring.rename.NameSuggestionProvider;
import com.jetbrains.php.refactoring.rename.PhpNameSuggestionProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecNameSuggestionProvider implements NameSuggestionProvider {
    @Nullable
    @Override
    public SuggestedNameInfo getSuggestedNames(PsiElement element, @Nullable PsiElement nameSuggestionContext, Set<String> result) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
