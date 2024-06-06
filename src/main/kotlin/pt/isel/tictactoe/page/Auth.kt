package pt.isel.tictactoe.page

import pt.isel.SHORT.Page
import pt.isel.SHORT.client.compare
import pt.isel.SHORT.client.EventHandler
import pt.isel.SHORT.client.Var
import pt.isel.SHORT.client.Variable
import pt.isel.SHORT.client.equal
import pt.isel.SHORT.client.switch
import pt.isel.SHORT.html.base.element.Tag
import pt.isel.tictactoe.element.AuthLine
import pt.isel.tictactoe.element.Center
import pt.isel.tictactoe.element.InputLine
import pt.isel.tictactoe.element.Menu
import pt.isel.tictactoe.element.SideBar
import pt.isel.tictactoe.element.Title

@Page("/sign")
fun Tag.SignUp() = apply {
    AuthPage(AuthOption.SIGN_UP)
}

@Page("/login")
fun Tag.LogIn() = apply {
    AuthPage(AuthOption.LOG_IN)
}

enum class AuthOption {
    SIGN_UP,
    LOG_IN
}

fun Tag.AuthPage(option: AuthOption) = apply {
    SideBar()
    Center {
        Title("Tic Tac Toe")
        Menu {
            val username = Var("")
            val password = Var("")
            val pwdCheck = Var("")

            fun genOnInput(variable: Variable<String>): EventHandler = {
                set(variable, event.target.value)
            }

            val onUsernameInput = genOnInput(username)
            val onPasswordInput = genOnInput(password)
            val onPwdCheckInput = genOnInput(pwdCheck)

            val signUp: EventHandler = {
                console.log("Signing up...")
                val admin = Var("admin")
                compare(username equal admin, {
                    console.log("Admin")
                }) {
                    console.log("Not Admin")
                }
            }

            val logIn: EventHandler = {
                switch(username) {
                    case("admin") {
                        console.log("Admin")
                    }
                    case("manager") {
                        console.log("Manager")
                    }
                    default {
                        console.log("User")
                    }
                }
            }

            val action = when (option) {
                AuthOption.SIGN_UP -> signUp
                AuthOption.LOG_IN -> logIn
            }

            InputLine("Username:", "secondary-color-5", onUsernameInput)
            InputLine("Password:", "secondary-color-4", onPasswordInput)

            if (option == AuthOption.SIGN_UP) {
                InputLine("Verify Password:", "secondary-color-3", onPwdCheckInput)
            }

            AuthLine(option, action)
        }
    }
    SideBar()
}