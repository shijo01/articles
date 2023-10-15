package com.pv.shijo.articles.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pv.shijo.theme.ArticlesTheme


@Composable
fun GameTag(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        TextButton(
            onClick = { },
            enabled = true,
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = TagDefaults.DisabledTagContainerAlpha,
                ),
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}


@Preview
@Composable
fun TagPreview() {
    ArticlesTheme {
        GameTag {
            Text("Topic".uppercase())
        }
    }
}

object TagDefaults {
    const val DisabledTagContainerAlpha = 0.12f
}