package com.example.myapplication.ui.screen.SignIn

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.screen.component.AuthButton
import com.example.myapplication.ui.screen.component.AuthTextField
import com.example.myapplication.ui.screen.component.TitleWithSubtitleText
import com.example.myapplication.ui.theme.MatuleTheme

@Preview
@Composable
fun SignInScreenPreview(){
    MatuleTheme{
        SignInScrn()
    }
}

@Composable
fun SignInScrn(){
    val signInViewModel: SignInViewModel = viewModel()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        SignInContent(paddingValues, signInViewModel)
    }
}

@Composable
fun SignInContent(paddingValues: PaddingValues, signInViewModel: SignInViewModel){
    val signInState = signInViewModel.signInState
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TitleWithSubtitleText(
            title = stringResource(R.string.hello),
            subTitle = stringResource(R.string.sign_in_subtitle)
        )
        Spacer(modifier = Modifier.height(35.dp))

        AuthTextField(
            value = signInState.value.email,
            onChangeValue = {
                signInViewModel.setEmail(it)
            },
            isError = signInViewModel.emailHasError.value,
            placeholder = {
                Text(text = stringResource(R.string.template_email))
            },
            supportingText = {
                Text(text = stringResource(R.string.incorrect_email))
            },
            label = {
                Text(text = stringResource(R.string.email))
            }
        )
        AuthTextField(
            value = signInState.value.password,
            onChangeValue = {
                signInViewModel.setPassword(it)
            },
            isError = false,
            placeholder = {
                Text(text = stringResource(R.string.password_template))
            },
            supportingText = {
                Text(text = stringResource(R.string.incorrect_password))
            },
            label = {
                Text(text = stringResource(R.string.password))
            }
        )
        AuthButton(
            onClick = {}
        ) {
            Text(stringResource(R.string.sign_in))
        }
    }
}
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String? = null,
    labelText: String? = null
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (labelText != null) {
            Text(
                text = labelText,
                style = MatuleTheme.typography.bodyRegular16.copy(MatuleTheme.colors.text),
                textAlign = TextAlign.Start
            )
        }

        // Используем TextField
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(end = 8.dp),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            interactionSource = remember { MutableInteractionSource() },
            placeholder = {
                if (placeHolderText != null) {
                    Text(
                        text = placeHolderText,
                        style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = "Переключить видимость пароля"
                    )
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MatuleTheme.colors.background,
                unfocusedContainerColor = MatuleTheme.colors.background,
                disabledContainerColor = MatuleTheme.colors.background,
                errorContainerColor = MatuleTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            isError = false
        )
    }
}