package com.grjt.socioinfonavit.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grjt.socioinfonavit.R;
import com.grjt.socioinfonavit.ws.Callback;
import com.grjt.socioinfonavit.ws.OnTaskCompleted;
import com.grjt.socioinfonavit.ws.PostRequest;

import static com.grjt.socioinfonavit.control.Global.typeOfPostRequest;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private EditText editTextNumberPassword;
    private Button buttonInto;
    private TextView textViewForgetPassword;
    private ProgressBar progressBar;

    private Context context;

    private String mailInput;
    private String passwordInput;

    private Callback callBack = new Callback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        //quitamos la barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        initActions();
    }

    private void initView(){
        editTextNumberPassword = (EditText) findViewById(R.id.editTextNumberPassword);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        buttonInto = (Button) findViewById(R.id.buttonInto);
        textViewForgetPassword = (TextView) findViewById(R.id.textViewForgetPassword);
        progressBar = (ProgressBar) findViewById(R.id.pb_load);
    }

    private void initActions(){
        editTextTextEmailAddress.addTextChangedListener(loginTextWatcher);
        editTextNumberPassword.addTextChangedListener(loginTextWatcher);
        buttonInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        editTextNumberPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    loginUser();
                    return true;
                }
                return false;
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mailInput = editTextTextEmailAddress.getText().toString().trim();
            passwordInput = editTextNumberPassword.getText().toString().trim();

            if(!mailInput.isEmpty() && !passwordInput.isEmpty()){
                buttonInto.setEnabled(true);
                buttonInto.setBackground(context.getResources().getDrawable(R.drawable.button_into_enabled));
            } else {
                buttonInto.setEnabled(false);
                buttonInto.setBackground(context.getResources().getDrawable(R.drawable.button_into_disabled));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void loginUser(){
        progressBar.setVisibility(View.VISIBLE);
        typeOfPostRequest = "login";
        PostRequest asyncT = new PostRequest(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(JSONObject result, String type) {
                progressBar.setVisibility(View.GONE);
                switch (type){
                    case "OK":
                        callBack.processingResult(result, "loginUser");
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        break;
                    case "errorLogin":
                        alertDialog("errorLogin");
                        break;
                    default:
                        break;
                }
            }
        });
        asyncT.execute(mailInput, passwordInput);
    }

    private void alertDialog(String message){
        String title = "";
        String description = "";
        switch (message){
            case "errorLogin":
                title = context.getString(R.string.login_title_error);
                description = context.getString(R.string.login_description_error);
                break;
        }

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(description);
        alertDialog.setPositiveButton(context.getString(R.string.login_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();

        /*TextView titleTV = new TextView(context); // You Can Customise your Title here
        titleTV.setText(title);
        titleTV.setBackgroundColor(Color.WHITE);
        titleTV.setPadding(10, 15, 15, 10);
        titleTV.setGravity(Gravity.CENTER);
        titleTV.setTextColor(Color.WHITE);
        titleTV.setTextSize(18);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCustomTitle(titleTV);
        alertDialog.setMessage(description);
        alertDialog.setButton(0, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show(); // You Can Customise your Message here
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);*/
    }
}