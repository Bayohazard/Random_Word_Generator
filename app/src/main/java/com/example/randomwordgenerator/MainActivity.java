package com.example.randomwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Strings used as keys when launching new activity. Usually starts with package name
    public static final String EXTRA_SPEECH_PART = "com.example.SPEECH_PART";
    public static final String EXTRA_WORD_LENGTH_LIMIT = "com.example.WORD_LENGTH";

    public static int wordLength = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Sends the user to another activity and gives information about the current
     * activity to the new one.
     *
     * @param v
     */
    public void handleClick(View v) {
        // Creates Intent object. Second parameter is which activity will be launched
        Intent intent = new Intent(this, WordGeneratorActivity.class);

        // Gets the text from the button that called the method
        Button b = (Button)v;
        String btnText = b.getText().toString();

        // Adds the speech part and word length limit and as a key-value pair
        intent.putExtra(EXTRA_SPEECH_PART, btnText);
        intent.putExtra(EXTRA_WORD_LENGTH_LIMIT, wordLength);

        // Launches activity
        startActivity(intent);
    }

    /**
     * Changes the word length to whatever was entered. If the button
     * was clicked and the input box is empty, defaults to 99.
     *
     * @param v The Button that called the method
     */
    public void applyChanges(View v) {
        EditText editText = findViewById(R.id.numberInput);
        String text = editText.getText().toString();

        if(text.isEmpty()) {
            wordLength = 99;
        } else {
            wordLength = Integer.parseInt(text);
        }
    }

}
