
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnswerView(modifier: Modifier = Modifier, isTrue: Boolean?, setAnswer: (answer: String) -> Unit) {
    var value by remember {
        mutableStateOf("")
    }

    var colorDivider = remember(isTrue) {
       when (isTrue) {
           null -> Color.Black
           true -> Color.Green.copy(alpha = 0.3f)
           false -> Color.Red.copy(alpha = 0.3f)
       }
    }

    BasicTextField(
        value = value,
        modifier = modifier
            .defaultMinSize(
                minWidth = 6.dp,
            )
            .padding(horizontal = 3.dp),
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            value = it
            setAnswer(it)
                        },
        textStyle = LocalTextStyle.current,
        decorationBox = @Composable { innerTextField ->
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                innerTextField()
                Divider(color = colorDivider)
            }
        }
    )
}