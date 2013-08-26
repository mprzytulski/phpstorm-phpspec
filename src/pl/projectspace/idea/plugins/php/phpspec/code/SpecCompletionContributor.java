package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecCompletionContributor extends CompletionContributor {

    public SpecCompletionContributor() {
        SpecCompletionProvider p = new SpecCompletionProvider();

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(), p);
    }
}
