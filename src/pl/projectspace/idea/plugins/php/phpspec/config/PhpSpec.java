package pl.projectspace.idea.plugins.php.phpspec.config;

import com.intellij.openapi.project.Project;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpec {

    private Project project;

    public PhpSpec(Project project) {
        this.project = project;
    }

    public String getSpecsDir() {
        return "spec/";
    }
}
