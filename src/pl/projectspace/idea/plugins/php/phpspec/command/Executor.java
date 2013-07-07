package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.jetbrains.php.PhpBundle;
import com.jetbrains.php.composer.ComposerCommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Executor {

    private Project project;

    private Locator locator;

    public Executor setLocator(Locator locator) {
        this.locator = locator;
        return this;
    }

    public Executor setProject(Project project) {
        this.project = project;
        return this;
    }

    public void execute(final CommandInterface command, @Nullable final Runnable callback) {
        Task task = new Task.Modal(command.getProject(), command.getTitle(), true) {
            public void run(ProgressIndicator indicator) {
//                if (indicator == null) {
//                    throw new IllegalArgumentException(
//                        "Argument 0 for @NotNull parameter of com/jetbrains/php/composer/ComposerCommandExecutor$1.run must not be null"
//                    );
////                    indicator.setIndeterminate(true);
//                }
                StringBuilder sb = new StringBuilder();
                sb.append("Generating spec for class: ");
                indicator.setText(sb.toString());

                boolean cancelledByUser = false;
                ExecutionException exception = null;
                final StringBuilder outputBuilder = new StringBuilder();
                try {
                    String phpspecPath = locator.getPath();
                    OSProcessHandler processHandler = ScriptRunnerUtil.execute(
                        phpspecPath,
                        getWorkingDir(),
                        null,
                        command.getCommandLineArgs()
                    );

                    String c = processHandler.getCommandLine();

                    processHandler.addProcessListener(new ProcessAdapter()
                    {
                        public void onTextAvailable(ProcessEvent event, Key outputType) {
                            outputBuilder.append(event.getText());
                        }
                    });
                    processHandler.startNotify();
                    while (true)
                    {
                        boolean finished = processHandler.waitFor(1000L);
                        if (finished) {
                            break;
                        }
                        if (indicator.isCanceled()) {
                            cancelledByUser = true;
                            OSProcessManager.getInstance().killProcessTree(processHandler.getProcess());
                            break;
                        }
                    }
                }
                catch (ExecutionException e) {
                    exception = e;
                }

                String output = outputBuilder.toString();

                final boolean success =
                    (exception == null)
                    && (!cancelledByUser)
                    && (output != null)
                    && (command.validateOutput(output));

                if (!success)
                {
                    String outputToReport;
                    String message;
                    if (exception != null) {
                        message = "m1";
                        outputToReport = exception.getMessage();
                    }
                    else
                    {
                        if (cancelledByUser) {
                            message = "m2";
                            outputToReport = output;
                        }
                        else {
//                            message = PhpBundle.message("framework.composer.failed.to.0.1.version.2", new Object[] { ComposerCommandExecutor.this.getActionName(), ComposerCommandExecutor.this.myPackage.getName(), ComposerCommandExecutor.this.getPresentableVersion() });
                            message = "m3";
                            outputToReport = output;
                        }
                    }
                    final boolean finalCancelledByUser = cancelledByUser;
//                    ApplicationManager.getApplication().invokeLater(new Runnable()
//                    {
//                        public void run() {
//                            ComposerCommandExecutor.this.showFailureOutput(outputToReport, finalCancelledByUser, message, ComposerCommandExecutor.1.this.myProject);
//                        }
//                    });
                }

                final String finalOutput = output;
//                ApplicationManager.getApplication().invokeLater(new Runnable()
//                {
//                    public void run()
//                    {
//                        ComposerCommandExecutor.this.provideFeedback(success, finalOutput, ComposerCommandExecutor.this.myPackage, ComposerCommandExecutor.this.myVersion);
//                    }
//                });
                if (callback != null)
                    callback.run();
            }
        };
        ProgressManager.getInstance().run(task);
    }

    private String getWorkingDir() {
        return project.getBaseDir().getPath();
    }

}
