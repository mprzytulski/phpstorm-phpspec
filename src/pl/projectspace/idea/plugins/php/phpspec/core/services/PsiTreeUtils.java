package pl.projectspace.idea.plugins.php.phpspec.core.services;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.PhpIndex;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PsiTreeUtils extends pl.projectspace.idea.plugins.commons.php.psi.PsiTreeUtils {
    public PsiTreeUtils(Project project, PhpIndex index) {
        super(project, index);
    }
}
