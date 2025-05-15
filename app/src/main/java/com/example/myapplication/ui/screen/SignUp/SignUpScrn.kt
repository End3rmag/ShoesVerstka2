package com.example.myapplication.ui.screen.SignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.data.remote.User
import com.example.myapplication.ui.screen.SignIn.SignInScrn
import com.example.myapplication.ui.screen.component.AuthButton
import com.example.myapplication.ui.screen.component.AuthTextField
import com.example.myapplication.ui.screen.component.PasswordTextField
import com.example.myapplication.ui.screen.component.TitleWithSubtitleText
import com.example.myapplication.ui.theme.MatuleTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun asad(){
MatuleTheme{

//SignUpScrn ()
}


}

@Composable
fun SignUpScrn(viewModel: SignUpViewModel, onNavigationToProfile: () -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }
    val signUpViewModel:SignUpViewModel = koinViewModel()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
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
                        contentDescription = null
                    )
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
                    text = stringResource(R.string.sign_in),
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        }
    )
    {
        paddingValues ->
        SignUpContent(paddingValues, signUpViewModel)

        val registrationScreenState = signUpViewModel.signUpState
        LaunchedEffect(registrationScreenState.value.isSignUp) {
            if(registrationScreenState.value.isSignUp) {
                onNavigationToProfile()
            }
        }

        LaunchedEffect(registrationScreenState.value.errorMessage) {
            registrationScreenState.value.errorMessage?.let {
                snackBarHostState.showSnackbar(it)
            }
        }
    }
}

@Composable
fun SignUpContent(paddingValues: PaddingValues, signUpViewModel: SignUpViewModel) {
    val signUpState = signUpViewModel.signUpState

    Column(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleWithSubtitleText(
            title = stringResource(R.string.registration),
            subTitle = stringResource(R.string.sign_in_subtitle)
        )

        AuthTextField(
            value = signUpState.value.name,
            onChangeValue = { signUpViewModel.setName(it) },
            isError = false,
            placeholder = { Text(text = stringResource(R.string.enter_name)) },
            supportingText = {Text(text = stringResource(R.string.incorrect_name)) },
            label = { Text(text = stringResource(R.string.name)) }
        )

        AuthTextField(
            value = signUpState.value.email,
            onChangeValue = { signUpViewModel.setEmail(it) },
            isError = signUpViewModel.emailHasError.value,
            placeholder = { Text(text = stringResource(R.string.template_email)) },supportingText = { if (signUpViewModel.emailHasError.value) Text(text = stringResource(R.string.enter_email)) },
            label = { Text(text = stringResource(R.string.email)) }
        )

        PasswordTextField(
            value = signUpState.value.password,
            onValueChange = { signUpViewModel.setPassword(it) },
            placeHolderText = stringResource(R.string.star_password),
            labelText = stringResource(R.string.password)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.policy_check),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = stringResource(R.string.privacy_policy),
                modifier = Modifier.padding(start = 20.dp),
                style = MatuleTheme.typography.bodyRegular12.copy(
                    color = MatuleTheme.colors.subTextDark,
                    textDecoration = TextDecoration.Underline
                )
            )
        }

        AuthButton(onClick = {
            signUpViewModel.signUp()
        }) {
            Text(stringResource(R.string.sign_up))
            if (signUpState.value.isLoading) CircularProgressIndicator(color = Color.White)
        }
    }
}