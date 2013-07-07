package com.jetbrains.php.composer;

import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.containers.MultiMap;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.php.PhpBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.projectspace.idea.plugins.php.phpspec.dialog.EnablePhpSupportDialog;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class InitProjectWithComposerAction {

    private Project project;

    public InitProjectWithComposerAction(Project project) {
        this.project = project;
    }


    public void perform(DataContext context) {
        if (project == null) {
            return;
        }
        VirtualFile parentFolder = getParentFolder(context, project);
        if (parentFolder == null) return;

        ComposerDataService service = ComposerDataService.getInstance(project);
        if (!service.askForValidConfigurationIfNeeded()) {
            return;
        }

        VirtualFile config = parentFolder.findChild("composer.json");
        if ((config == null) || (!config.exists())) {
            config = findSuitableConfig(project);
        }

        if ((config != null) && (config.exists())) {
            NotificationListener listener = new NotificationListener()
            {
                public void hyperlinkUpdate(@NotNull Notification notification, @NotNull HyperlinkEvent event) {
                    if (notification == null) {
                        throw new IllegalArgumentException("Argument 0 for @NotNull parameter of com/jetbrains/php/composer/ComposerInitSupportAction$1.hyperlinkUpdate must not be null");
                    }
                    if (event == null) {
                        throw new IllegalArgumentException("Argument 1 for @NotNull parameter of com/jetbrains/php/composer/ComposerInitSupportAction$1.hyperlinkUpdate must not be null");
                    }
                    ShowSettingsUtil.getInstance().editConfigurable(project, new ComposerConfigurable(project));
                }
            };
            ComposerDataService.getInstance(project).setConfigPath(config.getPath());
            Notification errorNotification = new Notification(
                ComposerUtils.COMPOSER_GROUP_DISPLAY_ID,
//                PhpBundle.message("framework.composer.notification.title.init.composer", new Object[0]),
//                PhpBundle.message("framework.composer.file.0.set.as.composer.config.change.setting.a.href.here.a",
//                new Object[] {
//                        FileUtil.toSystemIndependentName(config.getPath())
//                }
//            ),
                "Test",
                "Test",
                NotificationType.INFORMATION,
                listener
            );

            Notifications.Bus.notify(errorNotification, project);
            return;
        }

        final VirtualFile finalParentFolder = parentFolder;
        Runnable initializer = new Runnable()
        {
            public void run() {
                ComposerUtils.initConfig(finalParentFolder, project, InitProjectWithComposerAction.this);
            }
        };
        ApplicationManager.getApplication().runWriteAction(initializer);
    }

    private static VirtualFile findSuitableConfig(Project project) {
        if (project == null) {
            return null;
        }

        GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
        Collection<VirtualFile> files = FilenameIndex.getVirtualFilesByName(project, "composer.json", scope);
        if (files.isEmpty()) {
            return null;
        }

        MultiMap parentToConfig = new MultiMap();
        for (VirtualFile file : files) {
            VirtualFile parent = file.getParent();
            if (parent != null) {
                parent = parent.getParent();
                if (parent != null) {
                    parentToConfig.putValue(parent, file);
                }
            }
        }

        VfsUtilCore.DistinctVFilesRootsCollection roots = new VfsUtilCore.DistinctVFilesRootsCollection(parentToConfig.keySet());
        VirtualFile potentialConfigParent = null;
        for (VirtualFile root : roots) {
            if ((parentToConfig.get(root).size() == 1) && (!"vendor".equals(root.getName()))) {
                if (potentialConfigParent != null) {
                    return null;
                }
                potentialConfigParent = root;
            }
        }

        return (VirtualFile)parentToConfig.get(potentialConfigParent).iterator().next();
    }

    @Nullable
    private static VirtualFile getParentFolder(DataContext context, Project project) {
        VirtualFile file = (VirtualFile) PlatformDataKeys.VIRTUAL_FILE.getData(context);
        VirtualFile parentFolder = null;
        if (file != null) {
            parentFolder = file.isDirectory() ? file : file.getParent();
        }

        if (parentFolder == null) {
            parentFolder = project.getBaseDir();
        }

        if (parentFolder == null) {
            return null;
        }
        return parentFolder;
    }

}
