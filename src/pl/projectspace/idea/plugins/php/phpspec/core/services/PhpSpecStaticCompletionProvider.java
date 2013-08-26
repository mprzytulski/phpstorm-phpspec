package pl.projectspace.idea.plugins.php.phpspec.core.services;

import com.jetbrains.php.PhpIndex;
import com.jetbrains.php.lang.psi.elements.Method;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.PhpModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecStaticCompletionProvider {

    private PhpIndex index;
    private PsiTreeUtils utils;

    private Map<String, List<Method>> map;

    public final static String OBJECT_BEHAVIOUR_CLASS = "\\PhpSpec\\ObjectBehavior";

    public PhpSpecStaticCompletionProvider(PhpIndex index, PsiTreeUtils utils) {
        this.index = index;
        this.utils = utils;

        PhpClass objectBehaviourClass = utils.getClassByFQN(OBJECT_BEHAVIOUR_CLASS);

        map = new HashMap<String, List<Method>>();
        map.put(objectBehaviourClass.getFQN(), buildCompletionFor(objectBehaviourClass));
    }

    public List<Method> getMethodsFor(PhpClass phpClass) {
        return getMethodsFor(phpClass.getFQN());
    }

    public List<Method> getMethodsFor(String className) {
        if (!map.containsKey(className)) {
            return null;
        }

        return map.get(className);
    }

    private List<Method> buildCompletionFor(PhpClass phpClass) {
        List<Method> list = new ArrayList<Method>();
        for (Method method : phpClass.getMethods()) {
            if (method.getAccess().isWeakerThan(PhpModifier.Access.PROTECTED)) {
                list.add(method);
            }
        }

        return list;
    }
}
