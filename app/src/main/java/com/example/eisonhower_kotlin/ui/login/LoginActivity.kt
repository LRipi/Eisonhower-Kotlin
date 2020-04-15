package com.example.eisonhower_kotlin.ui.login

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.eisonhower_kotlin.MatrixActivity
import com.example.eisonhower_kotlin.R
import com.example.eisonhower_kotlin.RegisterPage
import com.example.eisonhower_kotlin.network.ApiCalls
import com.example.eisonhower_kotlin.network.EisonhowerService
import com.example.eisonhower_kotlin.network.LoginData
import com.example.eisonhower_kotlin.network.responseObject.Login
import okhttp3.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val register_button = findViewById<Button>(R.id.registerButton)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            val intent = Intent(this@LoginActivity, MatrixActivity::class.java)
            startActivity(intent)
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val eisonhowerService = retrofit.create(EisonhowerService::class.java)
                val callAsync = eisonhowerService.login(LoginData(username.text.toString(), password.text.toString()))

                callAsync.enqueue(object: Callback<Login>
                {

                    override fun onResponse(call: retrofit2.Call<Login>, response: Response<Login>)
                    {
                        if (response.isSuccessful())
                        {
                            val apiResponse = response.body()
                            Log.d("Login Response: ", "Id: " + apiResponse?.user?.id.toString() + "\nToken: " + apiResponse?.user?.token.toString())
                            if (apiResponse?.user?.token.toString() != null) {
                                val nextScreenIntent = Intent(this@LoginActivity, MatrixActivity::class.java).apply {
                                    putExtra("JWT_TOKEN", apiResponse?.user?.token.toString())
                                }
                                startActivity(nextScreenIntent)
                            }
                        }
                        else
                        {
                            System.out.println("Request Error :: " + response.code() + "\nReponse message :: " + response.message());
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Login>,t: Throwable)
                    {
                        Log.e("Api_test_call", "Error: " + t.getLocalizedMessage());
                    }
                })
            }

            register_button.setOnClickListener {
                ApiCalls().Auth("toto", "tata")
                val intent = Intent(this@LoginActivity, RegisterPage::class.java)
                intent.putExtra("Name", "Billy le Bob")
                startActivity(intent)
            }



        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = "Welcome"
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
