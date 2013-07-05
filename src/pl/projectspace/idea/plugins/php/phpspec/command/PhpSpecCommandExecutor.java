package pl.projectspace.idea.plugins.php.phpspec.command;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.util.Key;
import com.jetbrains.php.PhpBundle;
import com.jetbrains.php.composer.ComposerCommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecCommandExecutor {

    public void execute(@Nullable final Runnable afterInitialization) {
//        Task task = new Task.Modal(this.myProject, getProgressTitle(), true) {
//            public void run(@NotNull ProgressIndicator indicator) {
//                if (indicator == null) throw new IllegalArgumentException("Argument 0 for @NotNull parameter of com/jetbrains/php/composer/ComposerCommandExecutor$1.run must not be null"); indicator.setIndeterminate(true);
//
//                String[] myCommand = ComposerCommandExecutor.this.getCommand(this.myProject, ComposerCommandExecutor.this.myPackage, ComposerCommandExecutor.this.myVersion, ComposerCommandExecutor.this.myComposerPath);
//
//                StringBuilder sb = new StringBuilder();
//                sb.append(PhpBundle.message("framework.composer.add.dependency.running", new Object[0]));
//                for (String aCommandToRun : myCommand) {
//                    if (aCommandToRun.length() > 25) {
//                        aCommandToRun = "..." + aCommandToRun.substring(aCommandToRun.length() - 25);
//                    }
//                    sb.append(" ").append(aCommandToRun);
//                }
//                indicator.setText(sb.toString());
//
//                boolean cancelledByUser = false;
//                ExecutionException exception = null;
//                final StringBuilder outputBuilder = new StringBuilder();
//                try {
//                    OSProcessHandler processHandler = ScriptRunnerUtil.execute(myCommand[0], ComposerCommandExecutor.this.myWorkingDir.getPath(), null, (String[]) Arrays.copyOfRange(myCommand, 1, myCommand.length));
//
//                    processHandler.addProcessListener(new ProcessAdapter()
//                    {
//                        public void onTextAvailable(ProcessEvent event, Key outputType) {
//                            outputBuilder.append(event.getText());
//                        }
//                    });
//                    processHandler.startNotify();
//                    while (true)
//                    {
//                        boolean finished = processHandler.waitFor(1000L);
//                        if (finished) {
//                            break;
//                        }
//                        if (indicator.isCanceled()) {
//                            cancelledByUser = true;
//                            OSProcessManager.getInstance().killProcessTree(processHandler.getProcess());
//                            break;
//                        }
//                    }
//                }
//                catch (ExecutionException e) {
//                    exception = e;
//                }
//
//                String output = outputBuilder.toString();
//
//                final boolean success = (exception == null) && (!cancelledByUser) && (output != null) && (ComposerUtils.checkInstallationOutput(output));
//
//                if (!success)
//                {
//                    String outputToReport;
//                    final String message;
//                    final String outputToReport;
//                    if (exception != null) {
//                        String message = PhpBundle.message("framework.composer.failed.to.0.1.version.2", new Object[]{ComposerCommandExecutor.this.getActionName(), ComposerCommandExecutor.this.myPackage.getName(), ComposerCommandExecutor.this.getPresentableVersion()});
//
//                        outputToReport = exception.getMessage();
//                    }
//                    else
//                    {
//                        String outputToReport;
//                        if (cancelledByUser) {
//                            String message = PhpBundle.message("framework.composer.failed.to.0.1.version.2.script.was.cancelled", new Object[] { ComposerCommandExecutor.this.getActionName(), ComposerCommandExecutor.this.myPackage.getName(), ComposerCommandExecutor.this.getPresentableVersion() });
//
//                            outputToReport = output;
//                        }
//                        else {
//                            message = PhpBundle.message("framework.composer.failed.to.0.1.version.2", new Object[] { ComposerCommandExecutor.this.getActionName(), ComposerCommandExecutor.this.myPackage.getName(), ComposerCommandExecutor.this.getPresentableVersion() });
//
//                            outputToReport = output;
//                        }
//                    }
//                    final boolean finalCancelledByUser = cancelledByUser;
//                    ApplicationManager.getApplication().invokeLater(new Runnable()
//                    {
//                        public void run() {
//                            ComposerCommandExecutor.this.showFailureOutput(outputToReport, finalCancelledByUser, message, ComposerCommandExecutor.1.this.myProject);
//                        }
//                    });
//                }
//
//                final String finalOutput = output;
//                ApplicationManager.getApplication().invokeLater(new Runnable()
//                {
//                    public void run()
//                    {
//                        ComposerCommandExecutor.this.provideFeedback(success, finalOutput, ComposerCommandExecutor.this.myPackage, ComposerCommandExecutor.this.myVersion);
//                    }
//                });
//                if (afterInitialization != null)
//                    afterInitialization.run();
//            }
//        };
//        ProgressManager.getInstance().run(task);
    }

}
