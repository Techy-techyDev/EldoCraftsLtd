package screen.signin

class SignInScreenState {
    data class SignInScreenState(
        val isLoading: Boolean = false
        val isLoading: Boolean = false,
        val userName: String = "",
        val useNameError: String? = null,
        val password: String = "",
        val passwordError: String? = null,
        val isSignInButtonActive: Boolean = false,
        val navigateToNextScreen: Boolean = false
}