package pl.projectspace.idea.plugins.php.phpspec.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import pl.projectspace.idea.plugins.php.phpspec.PhpSpecProject;
import pl.projectspace.idea.plugins.php.phpspec.core.annotations.PhpSpecAction;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
@Aspect
public class PhpSpecMethodAspect {

//    @Around("execution(* *.getReferencesByElement(..)) && @annotation(behatReferenceProviderAnnotation)")
//    public PsiReference[] returnEmptyReferenceArray(BehatReferenceProvider behatReferenceProviderAnnotation, ProceedingJoinPoint jp, JoinPoint.EnclosingStaticPart enc) throws Throwable {
//        if (!BehatProject.isEnabled()) {
//            return new PsiReference[0];
//        }
//        return (PsiReference[])jp.proceed();
//    }

//    @Around("execution(* *.*(..)) && @annotation(condition)")
//    public boolean returnBehatStatus(BehatCondition condition, ProceedingJoinPoint jp, JoinPoint.EnclosingStaticPart enc) throws Throwable {
//        return BehatProject.isEnabled();
//    }
//
//    @Around("execution(* *.getCompletions(..)) && @annotation(condition)")
//    public List<String> returnBehatStatus(BehatNameProvider condition, ProceedingJoinPoint jp, JoinPoint.EnclosingStaticPart enc) throws Throwable {
//        if (!BehatProject.isEnabled()) {
//            return Collections.emptyList();
//        }
//        return (List<String>) jp.proceed();
//    }
//
    @Around("execution(* *.update(com.intellij.openapi.actionSystem.AnActionEvent)) && args(anActionEvent) && @annotation(action)")
    public void disablePresentation(PhpSpecAction action, com.intellij.openapi.actionSystem.AnActionEvent anActionEvent, ProceedingJoinPoint jp, JoinPoint.EnclosingStaticPart enc) throws Throwable {
        if (!PhpSpecProject.isEnabled()) {
            anActionEvent.getPresentation().setVisible(false);
            anActionEvent.getPresentation().setEnabled(false);
            return;
        }
        jp.proceed();
    }

}
