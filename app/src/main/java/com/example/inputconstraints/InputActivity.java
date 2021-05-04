package com.example.inputconstraints;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inputconstraints.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {

    ActivityInputBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Input");
        b = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setUpErrorForEditText();
    }

    /**
     * This function will check whether the data is getting changed in the edit text and according
     * to that it will remove the error from it.
     */
    private void setUpErrorForEditText() {
        TextWatcher myTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hideError();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        b.inputTIL.getEditText().addTextChangedListener(myTextWatcher);
    }


    /**
     * This function will take the input from the user and checks whether the input matches with the
     * above constraints or not.
     * @param view
     */
    public void saveData(View view) {
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString(Constant.STRING);

        String input = b.inputTIL.getEditText().getText().toString().trim();
        System.out.println("s : " + s);
        if(!input.matches(s)){
            //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            b.inputTIL.setError("Wrong input");
            return;
        }
        b.inputTIL.setError(null);

        Intent intent = new Intent();
        intent.putExtra(Constant.RETURN_STRING, input);
        setResult(RESULT_OK,intent);
        finish();
    }

    /**
     * This function will hide the error from the edit text.
     */
    private void hideError() {
        b.inputTIL.setError(null);
    }
}