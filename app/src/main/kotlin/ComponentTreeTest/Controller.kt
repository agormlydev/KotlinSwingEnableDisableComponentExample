package ComponentTreeTest

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JToggleButton

class Controller(appView: AppView) {
    private val appScreen = appView.appScreen
    val toggleButtonListener = ToggleButtonListener()
    val buttonAListener = ButtonAListener()
    val buttonBListener = ButtonBListener()
    val buttonCListener = ButtonCListener()

    init {
        addListeners()
    }

    private fun addListeners() {
        appScreen.addListeners(this)
    }

    inner class ToggleButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val toggleButton: JToggleButton
            try {
                toggleButton = e?.source as JToggleButton
            } catch (_: Exception) {
                return
                // toggleButton should be only component calling this listener
            }
            if (toggleButton.isSelected) {
                appScreen.disableComponentsExcept(
                    exceptComponents = setOf(toggleButton),
                    exceptTypes = setOf(
                        JLabel().javaClass
                    )
                )
            } else {
                appScreen.enableAllComponents()
            }
        }
    }

    inner class ButtonAListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val textAsInt = appScreen.textA.text.toIntOrNull() ?: 0
            appScreen.textA.text = (textAsInt + 1).toString()
        }
    }

    inner class ButtonBListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val textAsInt = appScreen.textB.text.toIntOrNull() ?: 0
            appScreen.textB.text = (textAsInt + 1).toString()
        }
    }

    inner class ButtonCListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val textAsInt = appScreen.textC.text.toIntOrNull() ?: 0
            appScreen.textC.text = (textAsInt + 1).toString()
        }
    }

}