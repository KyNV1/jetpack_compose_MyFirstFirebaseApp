package com.example.jetpack_compose_myfirstfirebaseapp.extenstion

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


/**
 * A modifier that makes a composable clickable and prevents rapid, repeated clicks
 * by enforcing a debounce interval.
 *
 * This is useful for preventing unintended multiple actions, such as navigating to the
 * same screen multiple times or submitting a form more than once.
 *
 * @param debounceInterval The minimum time in milliseconds that must pass between clicks.
 *                         Defaults to 1000L (1 second).
 * @param interactionSource The [MutableInteractionSource] that will be used to dispatch
 *                          [PressInteraction] when this clickable is pressed.
 * @param indication indication to be shown when modified element is pressed. Be default,
 *                   indication from [LocalIndication] will be used. Pass `null` to show no
 *                   indication.
 * @param onClick The lambda to be executed when the composable is clicked after the debounce
 *                interval has passed.
 */
fun Modifier.debouncedClicked(
    debounceInterval: Long = 1000L,
    interactionSource: MutableInteractionSource?=null,
    indication: Indication?=null,
    onClick:()->Unit
): Modifier = composed{
    var lastClickTime by remember { mutableLongStateOf(0L) }
    this.clickable(
        interactionSource = interactionSource,
        indication = indication
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceInterval) {
            lastClickTime = currentTime
            onClick()
        }
    }
}