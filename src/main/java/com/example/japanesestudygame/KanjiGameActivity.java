package com.example.japanesestudygame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanjiGameActivity extends AppCompatActivity {

    private TextView questionText;
    private Button option1Button, option2Button, option3Button, option4Button;
    private Button nextButton;
    private TextView scoreText;

    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<KanjiQuestion> questions;
    private boolean questionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_game);

        // Initialize views
        questionText = findViewById(R.id.question_text);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        nextButton = findViewById(R.id.next_button);
        scoreText = findViewById(R.id.score_text);

        // Create kanji questions
        createKanjiQuestions();

        // Set up click listeners
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option3Button.getText().toString());
            }
        });

        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option4Button.getText().toString());
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion();
                } else {
                    // End of game
                    Toast.makeText(KanjiGameActivity.this, 
                            "Game Over! Final Score: " + score, 
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        // Display first question
        displayQuestion();
    }

    private void createKanjiQuestions() {
        // Create a map of basic kanji characters and their meanings
        Map<String, String> kanjiMap = new HashMap<>();
        kanjiMap.put("一", "one");
        kanjiMap.put("二", "two");
        kanjiMap.put("三", "three");
        kanjiMap.put("四", "four");
        kanjiMap.put("五", "five");
        kanjiMap.put("六", "six");
        kanjiMap.put("七", "seven");
        kanjiMap.put("八", "eight");
        kanjiMap.put("九", "nine");
        kanjiMap.put("十", "ten");
        kanjiMap.put("百", "hundred");
        kanjiMap.put("千", "thousand");
        kanjiMap.put("万", "ten thousand");
        kanjiMap.put("人", "person");
        kanjiMap.put("日", "day/sun");
        kanjiMap.put("月", "month/moon");
        kanjiMap.put("火", "fire");
        kanjiMap.put("水", "water");
        kanjiMap.put("木", "tree/wood");
        kanjiMap.put("金", "gold/money");
        kanjiMap.put("土", "earth/soil");
        kanjiMap.put("山", "mountain");
        kanjiMap.put("川", "river");
        kanjiMap.put("田", "rice field");
        kanjiMap.put("上", "up/above");
        kanjiMap.put("下", "down/below");
        kanjiMap.put("左", "left");
        kanjiMap.put("右", "right");
        kanjiMap.put("中", "middle/inside");
        kanjiMap.put("大", "big/large");
        kanjiMap.put("小", "small");
        kanjiMap.put("年", "year");
        kanjiMap.put("時", "time/hour");
        kanjiMap.put("分", "minute/part");
        kanjiMap.put("先", "before/ahead");
        kanjiMap.put("生", "life/birth");
        kanjiMap.put("学", "study/learning");
        kanjiMap.put("校", "school");
        kanjiMap.put("本", "book/origin");
        kanjiMap.put("文", "text/writing");

        // Create questions from the map
        questions = new ArrayList<>();
        List<String> kanjiList = new ArrayList<>(kanjiMap.keySet());
        Collections.shuffle(kanjiList);

        // Take 20 random kanji characters for the game
        for (int i = 0; i < Math.min(20, kanjiList.size()); i++) {
            String kanji = kanjiList.get(i);
            String correctAnswer = kanjiMap.get(kanji);
            
            // Create wrong options
            List<String> allAnswers = new ArrayList<>(kanjiMap.values());
            allAnswers.remove(correctAnswer);
            Collections.shuffle(allAnswers);
            
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);
            options.add(allAnswers.get(0));
            options.add(allAnswers.get(1));
            options.add(allAnswers.get(2));
            Collections.shuffle(options);
            
            questions.add(new KanjiQuestion(kanji, correctAnswer, options));
        }
    }

    private void displayQuestion() {
        // Reset state
        questionAnswered = false;
        nextButton.setVisibility(View.INVISIBLE);
        
        // Enable all option buttons
        option1Button.setEnabled(true);
        option2Button.setEnabled(true);
        option3Button.setEnabled(true);
        option4Button.setEnabled(true);
        
        // Reset button colors
        option1Button.setBackgroundColor(getResources().getColor(R.color.kanji_color));
        option2Button.setBackgroundColor(getResources().getColor(R.color.kanji_color));
        option3Button.setBackgroundColor(getResources().getColor(R.color.kanji_color));
        option4Button.setBackgroundColor(getResources().getColor(R.color.kanji_color));
        
        // Get current question
        KanjiQuestion currentQuestion = questions.get(currentQuestionIndex);
        
        // Display question and options
        questionText.setText(currentQuestion.getKanji());
        option1Button.setText(currentQuestion.getOptions().get(0));
        option2Button.setText(currentQuestion.getOptions().get(1));
        option3Button.setText(currentQuestion.getOptions().get(2));
        option4Button.setText(currentQuestion.getOptions().get(3));
        
        // Update score text
        scoreText.setText(getString(R.string.score, score));
    }

    private void checkAnswer(String selectedAnswer) {
        if (questionAnswered) {
            return;
        }
        
        questionAnswered = true;
        KanjiQuestion currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer();
        
        // Disable all option buttons
        option1Button.setEnabled(false);
        option2Button.setEnabled(false);
        option3Button.setEnabled(false);
        option4Button.setEnabled(false);
        
        // Show correct and wrong answers
        if (option1Button.getText().toString().equals(correctAnswer)) {
            option1Button.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (option1Button.getText().toString().equals(selectedAnswer)) {
            option1Button.setBackgroundColor(getResources().getColor(R.color.incorrect_color));
        }
        
        if (option2Button.getText().toString().equals(correctAnswer)) {
            option2Button.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (option2Button.getText().toString().equals(selectedAnswer)) {
            option2Button.setBackgroundColor(getResources().getColor(R.color.incorrect_color));
        }
        
        if (option3Button.getText().toString().equals(correctAnswer)) {
            option3Button.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (option3Button.getText().toString().equals(selectedAnswer)) {
            option3Button.setBackgroundColor(getResources().getColor(R.color.incorrect_color));
        }
        
        if (option4Button.getText().toString().equals(correctAnswer)) {
            option4Button.setBackgroundColor(getResources().getColor(R.color.correct_color));
        } else if (option4Button.getText().toString().equals(selectedAnswer)) {
            option4Button.setBackgroundColor(getResources().getColor(R.color.incorrect_color));
        }
        
        // Update score if correct
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            scoreText.setText(getString(R.string.score, score));
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
        
        // Show next button
        nextButton.setVisibility(View.VISIBLE);
    }

    // Inner class to represent a kanji question
    private static class KanjiQuestion {
        private String kanji;
        private String correctAnswer;
        private List<String> options;

        public KanjiQuestion(String kanji, String correctAnswer, List<String> options) {
            this.kanji = kanji;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getKanji() {
            return kanji;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getOptions() {
            return options;
        }
    }
}