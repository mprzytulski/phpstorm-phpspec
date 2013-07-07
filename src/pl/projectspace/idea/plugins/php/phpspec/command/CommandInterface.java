package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.openapi.project.Project;

import java.util.HashMap;
import java.util.List;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public interface CommandInterface {

    public String getTitle();
    public String getCommand();
    public HashMap<String, String>getNamedParams();
    public List<String>getParams();
    public void addNamedParameter(String name, String val);
    public void addParameter(String param);
    public Project getProject();
    public boolean validateOutput(String output);
    public String[] getCommandLineArgs();

}
