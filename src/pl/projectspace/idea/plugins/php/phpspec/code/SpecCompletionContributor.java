package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.php.lang.PhpLanguage;
import com.jetbrains.php.lang.documentation.phpdoc.lexer.PhpDocTokenTypes;
import com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocComment;
import com.jetbrains.php.lang.parser.PhpElementTypes;
import com.jetbrains.php.lang.psi.elements.*;

import java.util.ArrayList;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecCompletionContributor extends CompletionContributor {

    public SpecCompletionContributor() {
        SpecCompletionProvider p = new SpecCompletionProvider();

//        ArrayList<IElementType> l = new ArrayList<IElementType>();
//        l.add(PhpElementTypes.CLASS_CONSTANTS);
//        l.add(PhpElementTypes.CLASS_FIELDS);
//        l.add(PhpElementTypes.CLASS_FIELD);
//        l.add(PhpElementTypes.CLASS_CONSTANT_REFERENCE);
//        l.add(PhpElementTypes.CLASS_REFERENCE);
//        l.add(PhpElementTypes.CLASS_METHOD);
//        l.add(PhpElementTypes.METHOD_REFERENCE);
//        l.add(PhpDocTokenTypes.DOC_IDENTIFIER);
//        l.add(PhpElementTypes.ISSET_EXPRESSION);
////        l.add(PhpElementTypes.CLASS_METHOD);

//        StandardPatterns.and();

//        extend(CompletionType.BASIC, StandardPatterns.instanceOf(MethodReference.class), p);
//        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withParent(Method.class), p);
//        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withParent(MemberReference.class), p);
//        extend(CompletionType.SMART, PlatformPatterns.psiElement().withParent(MemberReference.class), p);
//        extend(CompletionType.SMART, PlatformPatterns.psiElement(MemberReference.class), p);
//        extend(CompletionType.SMART, PlatformPatterns.psiElement(PhpClass.class), p);
//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(), p);

//        for (IElementType i : l) {
//            PsiElementPattern.Capture<PsiElement> c = PlatformPatterns.psiElement(i)
//                    .withLanguage(PhpLanguage.INSTANCE);
//
//            extend(CompletionType.BASIC, c, p);
//            extend(CompletionType.SMART, c, p);
//        }
    }

    private PsiElementPattern.Capture<PsiElement> getPattern()
    {

        return PlatformPatterns
//                .psiElement(PhpDocTokenTypes.DOC_IDENTIFIER)
                .psiElement(PhpElementTypes.METHOD_REFERENCE)
//                .withParent(PlatformPatterns
//                        .psiElement(PhpDocComment.class)
//                        .afterSibling(PlatformPatterns
//                                .psiElement(PhpElementTypes.CLASS_FIELDS)
//                        )
//                )
//                .inside(PlatformPatterns
//                        .psiElement(PhpElementTypes.NAMESPACE)
//                )
                .withLanguage(PhpLanguage.INSTANCE)
                ;

//        return PlatformPatterns.psiElement(PhpElementTypes.VARIABLE)
//                .withLanguage(PhpLanguage.INSTANCE);
//                .psiElement(YAMLTokenTypes.TEXT)
//                .withParent(PlatformPatterns
//                        .psiElement(YAMLKeyValue.class)
//                        .withName(
//                                PlatformPatterns.string().equalTo(keyName)
//                        )
//                );
    }
}
