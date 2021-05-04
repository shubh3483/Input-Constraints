package com.example.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inputconstraints.databinding.ActivityInputConstraintsBinding;

public class InputConstraintsActivity extends AppCompatActivity {

    ActivityInputConstraintsBinding b;
    private static final int REQUEST_COUNT = 1;
    String s = "^[";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInputConstraintsBinding.inflate(getLayoutInflater());
        setTitle("Choose Constraints");
        setContentView(b.getRoot());
    }


    /**
     * This function will control the selection of the check box and will create the reg exp
     * according to that.
     * @param view
     */
    public void takeInput(View view) {

        int check = 0;
        if(b.ucAlphaCB.isChecked()){
            s = s + "A-Z";
            check = 1;
        }
        if(b.lcAlphaCB.isChecked()){
            s = s + "a-z";
            check = 1;
        }
        if(b.digitsCB.isChecked()){
            s = s + "0-9";
            check = 1;
        }
        if(b.mathCB.isChecked()){
            s = s + "+-/*";
            check = 1;
        }
        if(b.otherSymCB.isChecked()){
            s = s + "~`!@#$%^&()_={};':,./?><-";
            check = 1;
        }
        if(check == 0){
            Toast.makeText(this, "Choose from the options", Toast.LENGTH_SHORT).show();
            b.returnStringTV.setText(null);
            return;
        }

        s = s + "]*$";
        Bundle bundle = new Bundle();
        bundle.putString(Constant.STRING, s);
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_COUNT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_COUNT && resultCode == RESULT_OK){
            String resultString = data.getStringExtra(Constant.RETURN_STRING);
            System.out.println("Return String : " + resultString);
            b.returnStringShowTV.setVisibility(View.VISIBLE);
            b.returnStringTV.setText(resultString);
            s = "^[";
        }

    }
}