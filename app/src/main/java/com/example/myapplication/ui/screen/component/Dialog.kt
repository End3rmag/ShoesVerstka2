package com.example.myapplication.ui.screen.component

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MatuleTheme

@Preview
@Composable
fun jfd(){
    MatuleTheme{
        PasswordRecoveryDialog (
                isDialogOpen = true,
            onDismiss = {}
        )
    }
}
@Composable
fun PasswordRecoveryDialog(
    isDialogOpen: Boolean,
    onDismiss: () -> Unit
) {
    if (isDialogOpen) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                        .clickable { onDismiss() }, // Закрытие при клике
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Синий круг с изображением
                    Box(
                        modifier = Modifier.size(64.dp)
                            .background(MatuleTheme.colors.color, shape = MaterialTheme.shapes.extraLarge),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.email_1),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Text(
                        text = "Проверьте Ваш Email",
                        style = MatuleTheme.typography.headingBold16,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Мы отправили код восстановления пароля на вашу электронную почту.",
                        style =MatuleTheme.typography.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
