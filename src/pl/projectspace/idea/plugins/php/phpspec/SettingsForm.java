package pl.projectspace.idea.plugins.php.phpspec;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: mprzytulski
 * Date: 04/07/2013
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class SettingsForm implements Configurable {

    private Project project;

    private JCheckBox isEnabled;
    private JRadioButton useSystemProvidedPhpspecRadioButton;
    private JRadioButton useComposerInstalledPhpspecRadioButton;
    private JPanel configuration;


    public SettingsForm(@NotNull final Project project) {
        this.project = project;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "phpspec Plugin";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        useSystemProvidedPhpspecRadioButton.addMouseListener(createUseGlobalInstallationMouseListener(useSystemProvidedPhpspecRadioButton));
        useComposerInstalledPhpspecRadioButton.addMouseListener(createUseGlobalInstallationMouseListener(useComposerInstalledPhpspecRadioButton));

        return (JComponent) configuration;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {

//        getSettings().setPhpSpecLocationMode();

    }

    @Override
    public void reset() {
        updateUIFromSettings();
    }

    @Override
    public void disposeUIResources() {
    }

    protected void updateUIFromSettings()
    {

    }

    private Settings getSettings() {
        return Settings.getInstance(project);
    }

    private MouseListener createUseGlobalInstallationMouseListener(final JRadioButton button) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    private MouseListener createUseLocalComposerInstallMouseListener(final JRadioButton button) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }
}
