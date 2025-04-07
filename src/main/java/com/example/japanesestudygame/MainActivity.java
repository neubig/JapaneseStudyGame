package com.example.japanesestudygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up button click listeners
        Button hiraganaButton = findViewById(R.id.hiragana_button);
        Button katakanaButton = findViewById(R.id.katakana_button);
        Button kanjiButton = findViewById(R.id.kanji_button);
        Button vocabularyButton = findViewById(R.id.vocabulary_button);

        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HiraganaGameActivity.class));
            }
        });

        katakanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KatakanaGameActivity.class));
            }
        });

        kanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KanjiGameActivity.class));
            }
        });

        vocabularyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VocabularyGameActivity.class));
            }
        });
    }
}