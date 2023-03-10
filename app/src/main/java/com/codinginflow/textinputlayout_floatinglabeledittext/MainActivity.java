package com.codinginflow.textinputlayout_floatinglabeledittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

	private static final Pattern PASSWORD_PATTERN =
			Pattern.compile("^" +
					"(?=.*[0-9])" +				// at least 1 digit
					"(?=.*[a-z])" +				// at least 1 lower case letter
					"(?=.*[A-Z])" +				// at least 1 upper case lettr
					//"(?=.*[a-zA-Z])" +			// any letter
					"(?=.*[@#$%^&+=])" +		// at least 1 special character
					"(?=\\S+$)" +				// no white spaces
					".{6,}" +					//at least 6 characters
					"$");
	private TextInputLayout textInputEmail, textInputUsername, textInputPassword;
	private Button buttonConfirmInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textInputEmail = findViewById(R.id.text_input_email);
		textInputUsername = findViewById(R.id.text_input_username);
		textInputPassword = findViewById(R.id.text_input_password);
		buttonConfirmInput = findViewById(R.id.button_confirm_input);

		buttonConfirmInput.setOnClickListener(View -> {confirmInput(View);});
	}

	private boolean validadeEmail() {
		String emailInput = textInputEmail.getEditText().getText().toString().trim();
		if (emailInput.isEmpty()) {
			textInputEmail.setError("field can't be empty");
			return false;
		} else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
			textInputEmail.setError("Please enter a valid email address");
			return false;
		}

		textInputEmail.setError("");
		//textInputEmail.setErrorEnabled(false);
		return true;
	}

	private boolean validadeUsername() {
		String usernameInput = textInputUsername.getEditText().getText().toString().trim();

		if (usernameInput.isEmpty()) {
			textInputUsername.setError("Field can't be empty");
			return false;
		} else if (usernameInput.length() > 15) {
			textInputUsername.setError("Username too long");
			return false;
		}

		textInputUsername.setError(null);
		return true;
	}

	private boolean validadePassword() {
		String passwordInput = textInputPassword.getEditText().getText().toString().trim();
		if (passwordInput.isEmpty()) {
			textInputPassword.setError("field can't be empty");
			return false;
		} else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
			textInputPassword.setError("Password too weak");
			return false;
		}

		textInputPassword.setError("");
		return true;
	}

	public void confirmInput(View v) {
		if (!validadeEmail() | !validadeUsername() | !validadePassword()) {
			return;
		}

		String input = "Email: " + textInputEmail.getEditText().getText().toString().trim();
		input += "\n";
		input += "Username: " + textInputUsername.getEditText().getText().toString().trim();

		Toast.makeText(this, input, Toast.LENGTH_LONG).show();
	}
}