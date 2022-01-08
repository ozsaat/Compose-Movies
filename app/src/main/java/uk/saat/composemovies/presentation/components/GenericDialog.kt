package uk.saat.composemovies.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenericDialog(
    dialogInfo: GenericDialogInfo,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = dialogInfo.onDismiss,
        title = { Text(text = dialogInfo.title) },
        text = {
            if (dialogInfo.description != null) {
                Text(text = dialogInfo.description)
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (dialogInfo.negativeAction != null) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = dialogInfo.negativeAction.onNegativeAction
                    ) {
                        Text(text = dialogInfo.negativeAction.negativeBtnTxt)
                    }
                }
                if (dialogInfo.positiveAction != null) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = dialogInfo.positiveAction.onPositiveAction
                    ) {
                        Text(text = dialogInfo.positiveAction.positiveBtnTxt)
                    }
                }
            }
        }
    )
}

data class PositiveAction(
    val positiveBtnTxt: String,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: String,
    val onNegativeAction: () -> Unit,
)

class GenericDialogInfo
private constructor(builder: GenericDialogInfo.Builder) {

    val title: String
    val onDismiss: () -> Unit
    val description: String?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if (builder.title == null) {
            throw NullPointerException("GenericDialogInfo title cannot be null")
        }

        if (builder.onDismiss == null) {
            throw NullPointerException("GenericDialogInfo onDismiss function cannot be null")
        }

        this.title = builder.title!!
        this.onDismiss = builder.onDismiss!!
        this.description = builder.description
        this.positiveAction = builder.positiveAction
        this.negativeAction = builder.negativeAction
    }

    class Builder {

        var title: String? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var description: String? = null
            private set

        var positiveAction: PositiveAction? = null
            private set

        var negativeAction: NegativeAction? = null
            private set

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder {
            this.onDismiss = onDismiss
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun positiveAction(positiveAction: PositiveAction): Builder {
            this.positiveAction = positiveAction
            return this
        }

        fun negativeAction(negativeAction: NegativeAction): Builder {
            this.negativeAction = negativeAction
            return this
        }

        fun build() = GenericDialogInfo(this)
    }
}