package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.data.AuthRepository
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import com.example.myapplication.ui.data.local.LocalStorage
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.screen.RecoverPassword.RecoverPasswordScrn
import com.example.myapplication.ui.screen.SignIn.SignInScrn

import com.example.myapplication.ui.screen.SignUp.SignUpScrn
import com.example.myapplication.ui.screen.SignUp.SignUpViewModel


//import com.example.myapplication.ui.screen.SplashScreen
import com.example.myapplication.ui.theme.MatuleTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val authRepository = AuthRepository(RetrofitClient.retrofit)
            val localStorage = LocalStorage(applicationContext)
            val authUseCase = AuthUseCase(localStorage, authRepository )
            val singUpViewModel: SignUpViewModel by viewModel()
            val navController = rememberNavController()
            MatuleTheme {
                NavHost(navController, startDestination = "registration") {

                  //  composable("splash") {
                      //  SplashScreen(
                       //     authUseCase = authUseCase,
                       //     onNavigationToProfileScreen = {
                         //       navController.navigate("profile")
                       //     }
                        //) {
                      //      navController.navigate("registration")
                     //   }
                    //}
                    composable("registration") {
                        SignUpScrn(singUpViewModel) {
                            navController.navigate("profile")
                        }
                    }
                    composable("profile") {
                        SignInScrn(navController)
                    }

                    composable("recover_password") {
                        RecoverPasswordScrn()
                    }
                }
            }
        }
    }
}

@Serializable
object SplashScreen
@Serializable
object Registration
@Serializable
object Profile