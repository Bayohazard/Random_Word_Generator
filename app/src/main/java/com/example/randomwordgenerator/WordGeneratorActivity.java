package com.example.randomwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class WordGeneratorActivity extends AppCompatActivity {
  private static int wordLengthLimit;

  private static ArrayList<Word> wordList = new ArrayList<>();
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_word_generator);

    textView = findViewById(R.id.wordDisplayer);

    // Gets access to the intent that launched the activity and gets information from it
    Intent intent = getIntent();
    // Fields given from Intent that launched Activity
    String speechPart = intent.getStringExtra(MainActivity.EXTRA_SPEECH_PART);
    wordLengthLimit = intent.getIntExtra(MainActivity.EXTRA_WORD_LENGTH_LIMIT, 99);

    // Gets file name and calls method to get word list
    String fileName = speechPart.concat(".txt").toLowerCase();
    wordList = getWordList(fileName);

  }

  public ArrayList<Word> getWordList(String fileName) {

    ArrayList<Word> wordArrayList = new ArrayList<>();

    try {
      // Gets hold of assets folder
      AssetManager assetManager = getAssets();

      // Opens file inside "assets" folder
      InputStream inputStream = assetManager.open(fileName);


      // Somehow allows file to be read
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


      String word;

      //Investigate how code inside while loop works
      while ((word = bufferedReader.readLine()) != null) {
        // Gets the word's length
        int wordLength = word.length();

        // Gets the word's part of speech based on the file name
        SpeechPart speechPart;
        switch (fileName) {
          case "nouns.txt":
            speechPart = SpeechPart.NOUN;
            break;

          case "verbs.txt":
            speechPart = SpeechPart.VERB;
            break;

          case "adjectives.txt":
            speechPart = SpeechPart.ADJECTIVE;
            break;

          case "adverbs.txt":
            speechPart = SpeechPart.ADVERB;
            break;

          default:
            speechPart = null;
        }
        Word currentWord = new Word(word, wordLength, speechPart);
        wordArrayList.add(currentWord);
      }

      inputStream.close();

    } catch (IOException e) {
      System.out.println("THERE WAS AN IOEXCEPTION");
    }

    return wordArrayList;
  }

  public void generateWord(View v) {
    Random rng = new Random();

    // Chooses a random word from the list
    int randomIndex = rng.nextInt(wordList.size());
    String word = wordList.get(randomIndex).getWord();

    // If the word is longer than the limit, choose again
    while (word.length() > wordLengthLimit) {
      randomIndex = rng.nextInt(wordList.size());
      word = wordList.get(randomIndex).getWord();
    }

    textView.setText(word);
  }
}
