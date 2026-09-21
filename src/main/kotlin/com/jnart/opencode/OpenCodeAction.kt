package com.jnart.opencode

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

/**
 * Action triggered by the icon in the IntelliJ main header toolbar or keyboard shortcut.
 * Opens a new terminal tab and executes the 'opencode' command.
 */
class OpenCodeAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return

        try {
            val terminalManager = TerminalToolWindowManager.getInstance(project)
            val workingDir = project.basePath

            // Create and show a new terminal widget tab named "OpenCode"
            val widget = terminalManager.createShellWidget(workingDir, "OpenCode", true, true)

            // Send the 'opencode' command for execution in the terminal
            widget.sendCommandToExecute("opencode")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun update(e: AnActionEvent) {
        // Display button only when an active project is open in the IDE
        e.presentation.isEnabledAndVisible = e.project != null
    }
}
