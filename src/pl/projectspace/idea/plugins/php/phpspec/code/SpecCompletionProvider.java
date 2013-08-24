package pl.projectspace.idea.plugins.php.phpspec.code;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupItem;
import com.intellij.util.ProcessingContext;

import com.jetbrains.php.lang.psi.elements.MethodReference;
import org.jetbrains.annotations.NotNull;

//import fr.adrienbrault.idea.symfony2plugin.Symfony2Icons;
//import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;
//import fr.adrienbrault.idea.symfony2plugin.config.doctrine.DoctrineStaticTypeLookupBuilder;
//import fr.adrienbrault.idea.symfony2plugin.util.completion.AnnotationIndex;
//import fr.adrienbrault.idea.symfony2plugin.util.completion.AnnotationLookupElement;
//import fr.adrienbrault.idea.symfony2plugin.util.completion.AnnotationValue;
//import fr.adrienbrault.idea.symfony2plugin.util.completion.annotations.AnnotationTagInsertHandler;
//import fr.adrienbrault.idea.symfony2plugin.util.completion.annotations.AnnotationMethodInsertHandler;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class SpecCompletionProvider extends CompletionProvider<CompletionParameters> {

    public class MethodLookup extends LookupElement {

        private String method;

        public MethodLookup(String method) {
            this.method = method;
        }

        @NotNull
        @Override
        public String getLookupString() {
            return method;
        }

        public void renderElement(LookupElementPresentation presentation) {
            presentation.setItemText(getLookupString());
            presentation.setTypeText(method);
            presentation.setTypeGrayed(true);
//            presentation.setIcon(Symfony2Icons.SERVICE);
        }
    }

    public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        System.out.println("test");

//            if(!Symfony2ProjectComponent.isEnabled(parameters.getPosition())) {
//                return;
//            }
//
//            PsiElement element = parameters.getOriginalPosition();
//
//            if(element == null) {
//                return;
//            }
//
//            Map<String,String> map = ServiceXmlParserFactory.getInstance(element.getProject(), XmlServiceParser.class).getServiceMap().getMap();

        resultSet.addElement(new MethodLookup("test()"));

//
//            for( Map.Entry<String, String> entry: map.entrySet() ) {
//                resultSet.addElement(
//                        new ServiceStringLookupElement(entry.getKey(), entry.getValue())
//                );
//            }
//
//            for( Map.Entry<String, String> entry: YamlHelper.getLocalServiceMap(element).entrySet()) {
//                if(!map.containsKey(entry.getKey())) {
//                    resultSet.addElement(
//                            new ServiceStringLookupElement(entry.getKey(), entry.getValue())
//                    );
//                }
//            }

    }
}
