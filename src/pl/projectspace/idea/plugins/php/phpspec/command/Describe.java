package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.lang.psi.elements.PhpClass;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Describe extends AbstractCommand implements CommandInterface {

    private final PhpClass phpClass;

    public Describe(Project project, PhpClass phpClass) {
        super(project);
        this.phpClass = phpClass;
        String fqn = phpClass.getFQN();
        fqn = fqn.substring(1, fqn.length());

        addParameter(fqn);
    }

    public String getSpecPath() {
        return "spec/Test/TestSpec.php";
    }

    @Override
    public String getTitle() {
        return "Generate spec for class";
    }

    @Override
    public String getCommand() {
        return "desc";
    }

    @Override
    public boolean validateOutput(String output) {
        return true;
    }

}
