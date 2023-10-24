package screen.signin

class SignInViewModel {
    private val signInUseCase: SignInUseCase
    ): ViewModel() {
        var  signInScreenState by mutableStateOf(SignInScreenState())
        fun onAction(action:Action){
            when(action){
                is Action.OnResetState ->{
                    signInScreenState = SignInScreenState()
                }
                is Action.OnUserNameChanged ->{
                    signInScreenState = if (action.userName.isNotEmpty()) {
                        signInScreenState.copy(userName = action.userName, useNameError = null)
                    }else{
                        signInScreenState.copy(useNameError = "")}
                }
                is Action.OnPasswordChanged ->{
                    signInScreenState = if (action.password.isNotEmpty()){
                        signInScreenState.copy(password = action.password, passwordError = null )
                    }else{
                        signInScreenState.copy(passwordError = "")
                    }
                }
                is Action.OnSubmit -> {
                    signIn()
                }
            }
            signInScreenState = signInScreenState.copy(
                isSignInButtonActive =
                signInScreenState.userName.isNotEmpty()
                        && signInScreenState.useNameError == null
                        && signInScreenState.password.isNotEmpty()
                        && signInScreenState.passwordError == null
            )
        }
        private  fun signIn(){
            val signInRequest = SignInRequest(email = signInScreenState.userName,
                password = signInScreenState.password
            )
            signInScreenState = signInScreenState.copy(
                isLoading = true
            )
            viewModelScope.launch(Dispatchers.IO){
                signInUseCase.execute(signInRequest).fold(
                    onSuccess = {
                        signInScreenState = signInScreenState.copy(
                            isLoading = false,
                            navigateToNextScreen = true
                        )
                    },
                    onFailure = {
                        signInScreenState = signInScreenState.copy(
                            isLoading = false,
                            navigateToNextScreen = true
                        )
                    }
                }
            }
        }

    }
}
sealed interface Action{
    data class OnUserNameChanged(val userName:String):Action
    data class OnPasswordChanged(val password:String):Action
    object OnSubmit:Action
    object OnResetState:Action
}
