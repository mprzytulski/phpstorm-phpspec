package pl.projectspace.idea.plugins.php.phpspec.dialog;

import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.event.SelectionListener;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.containers.MultiMap;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.php.PhpBundle;
import com.jetbrains.php.composer.ComposerDataService;
import com.jetbrains.php.composer.ComposerInitSupportAction;
import com.jetbrains.php.composer.ComposerUtils;
import com.jetbrains.php.composer.InitProjectWithComposerAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.Settings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;

public class EnablePhpSupportDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton usePhpspecFromGivenRadioButton;
    private JRadioButton installPhpspecWithComposerRadioButton;
    private JRadioButton addComposerSupportToRadioButton;
    private TextFieldWithBrowseButton phpSpecLocation;
    private JPanel installWithComposerPanel;

    private Project project;

    public EnablePhpSupportDialog(Project project) {
        this.project = project;

        if (ComposerDataService.getInstance(project).getConfigPath() == null
                || ComposerDataService.getInstance(project).getConfigPath().isEmpty()) {
            installWithComposerPanel.setVisible(false);
        }

        setContentPane(contentPane);
        setSize(new Dimension(480, 200));
        setResizable(false);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        phpSpecLocation.getButton().addMouseListener(
            createPathSelectionListener()
        );

        usePhpspecFromGivenRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JRadioButton) e.getSource()).isSelected()) {
                    phpSpecLocation.setEnabled(true);
                } else {
                    phpSpecLocation.setEnabled(false);
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(e);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(ActionEvent e) {
        if (usePhpspecFromGivenRadioButton.isSelected()) {
            Settings.getInstance(project).setPhpSpecPath(phpSpecLocation.getText());
        }
        else if (installPhpspecWithComposerRadioButton.isSelected()) {

        }
        else if (addComposerSupportToRadioButton.isSelected()) {
            JButton radio = (JButton)e.getSource();
            ActionToolbar actionToolbar = UIUtil.getParentOfType(ActionToolbar.class, radio);
            DataContext context =
                    actionToolbar != null ? actionToolbar.getToolbarDataContext() : DataManager.getInstance().getDataContext(radio);

            InitProjectWithComposerAction action = new InitProjectWithComposerAction(project);
            action.perform(context);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public MouseListener createPathSelectionListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                VirtualFile projectDirectory = project.getBaseDir();
                VirtualFile selectedFile = FileChooser.chooseFile(
                        FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor(),
                        project,
                        VfsUtil.findRelativeFile(phpSpecLocation.getText(), projectDirectory)
                );

                if (null == selectedFile) {
                    return; // Ignore but keep the previous path
                }

                String path = VfsUtil.getRelativePath(selectedFile, projectDirectory, '/');
                if (null == path) {
                    path = selectedFile.getPath();
                }

                phpSpecLocation.setText(path);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        };
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
