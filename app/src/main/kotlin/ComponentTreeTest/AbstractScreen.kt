package ComponentTreeTest

import java.awt.Component
import javax.swing.JPanel

abstract class AbstractScreen : JPanel() {
    enum class Toggle {
        ENABLE,
        DISABLE
    }

    private fun toggleComponentsEnabledAttribute(
        toggle: Toggle,
        exceptComponents: Set<Component>? = null,
        exceptTypes: Set<Class<Any>>? = null
    ) {
        fun recursiveToggleComponentsEnabledAttribute(panel: JPanel) {
            panel.components.forEach {
                if (it is JPanel) {
                    recursiveToggleComponentsEnabledAttribute(it)
                } else {
                    if (exceptComponents?.contains(it) == true ||
                        exceptTypes?.contains(it.javaClass) == true
                    ) {
                        // don't toggle component if it's in the exceptComponents set
                        // don't toggle component if it's type is in the exceptTypes set
                    } else {
                        // if Toggle.ENABLE then enable the component else disable the component
                        it.isEnabled = toggle == Toggle.ENABLE
                    }
                }
            }
        }
        recursiveToggleComponentsEnabledAttribute(this)
    }

    fun disableAllComponents() {
        toggleComponentsEnabledAttribute(Toggle.DISABLE)
    }

    fun enableAllComponents() {
        toggleComponentsEnabledAttribute(Toggle.ENABLE)
    }

    fun disableComponentsExcept(exceptComponents: Set<Component>? = null, exceptTypes: Set<Class<Any>>? = null) {
        toggleComponentsEnabledAttribute(Toggle.DISABLE, exceptComponents, exceptTypes)
    }

}