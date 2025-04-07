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
import java.util.Random;

public class HiraganaGameActivity extends AppCompatActivity {

    private TextView questionText;
    private Button option1Button, option2Button, option3Button, option4Button;
    private Button nextButton;
    private TextView scoreText;

    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<HiraganaQuestion> questions;
    private boolean questionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana_game);

        // Initialize views
        questionText = findViewById(R.id.question_text);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        nextButton = findViewById(R.id.next_button);
        scoreText = findViewById(R.id.score_text);

        // Create hiragana questions
        createHiraganaQuestions();

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
                    Toast.makeText(HiraganaGameActivity.this, 
                            "Game Over! Final Score: " + score, 
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        // Display first question
        displayQuestion();
    }

    private void createHiraganaQuestions() {
        // Create a map of hiragana characters and their romanized equivalents
        Map<String, String> hiraganaMap = new HashMap<>();
        hiraganaMap.put("あ", "a");
        hiraganaMap.put("い", "i");
        hiraganaMap.put("う", "u");
        hiraganaMap.put("え", "e");
        hiraganaMap.put("お", "o");
        hiraganaMap.put("か", "ka");
        hiraganaMap.put("き", "ki");
        hiraganaMap.put("く", "ku");
        hiraganaMap.put("け", "ke");
        hiraganaMap.put("こ", "ko");
        hiraganaMap.put("さ", "sa");
        hiraganaMap.put("し", "shi");
        hiraganaMap.put("す", "su");
        hiraganaMap.put("せ", "se");
        hiraganaMap.put("そ", "so");
        hiraganaMap.put("た", "ta");
        hiraganaMap.put("ち", "chi");
        hiraganaMap.put("つ", "tsu");
        hiraganaMap.put("て", "te");
        hiraganaMap.put("と", "to");
        hiraganaMap.put("な", "na");
        hiraganaMap.put("に", "ni");
        hiraganaMap.put("ぬ", "nu");
        hiraganaMap.put("ね", "ne");
        hiraganaMap.put("の", "no");
        hiraganaMap.put("は", "ha");
        hiraganaMap.put("ひ", "hi");
        hiraganaMap.put("ふ", "fu");
        hiraganaMap.put("へ", "he");
        hiraganaMap.put("ほ", "ho");
        hiraganaMap.put("ま", "ma");
        hiraganaMap.put("み", "mi");
        hiraganaMap.put("む", "mu");
        hiraganaMap.put("め", "me");
        hiraganaMap.put("も", "mo");
        hiraganaMap.put("や", "ya");
        hiraganaMap.put("ゆ", "yu");
        hiraganaMap.put("よ", "yo");
        hiraganaMap.put("ら", "ra");
        hiraganaMap.put("り", "ri");
        hiraganaMap.put("る", "ru");
        hiraganaMap.put("れ", "re");
        hiraganaMap.put("ろ", "ro");
        hiraganaMap.put("わ", "wa");
        hiraganaMap.put("を", "wo");
        hiraganaMap.put("ん", "n");

        // Create questions from the map
        questions = new ArrayList<>();
        List<String> hiraganaList = new ArrayList<>(hiraganaMap.keySet());
        Collections.shuffle(hiraganaList);

        // Take 20 random hiragana characters for the game
        for (int i = 0; i < Math.min(20, hiraganaList.size()); i++) {
            String hiragana = hiraganaList.get(i);
            String correctAnswer = hiraganaMap.get(hiragana);
            
            // Create wrong options
            List<String> allAnswers = new ArrayList<>(hiraganaMap.values());
            allAnswers.remove(correctAnswer);
            Collections.shuffle(allAnswers);
            
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);
            options.add(allAnswers.get(0));
            options.add(allAnswers.get(1));
            options.add(allAnswers.get(2));
            Collections.shuffle(options);
            
            questions.add(new HiraganaQuestion(hiragana, correctAnswer, options));
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
        option1Button.setBackgroundColor(getResources().getColor(R.color.hiragana_color));
        option2Button.setBackgroundColor(getResources().getColor(R.color.hiragana_color));
        option3Button.setBackgroundColor(getResources().getColor(R.color.hiragana_color));
        option4Button.setBackgroundColor(getResources().getColor(R.color.hiragana_color));
        
        // Get current question
        HiraganaQuestion currentQuestion = questions.get(currentQuestionIndex);
        
        // Display question and options
        questionText.setText(currentQuestion.getHiragana());
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
        HiraganaQuestion currentQuestion = questions.get(currentQuestionIndex);
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

    // Inner class to represent a hiragana question
    private static class HiraganaQuestion {
        private String hiragana;
        private String correctAnswer;
        private List<String> options;

        public HiraganaQuestion(String hiragana, String correctAnswer, List<String> options) {
            this.hiragana = hiragana;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getHiragana() {
            return hiragana;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getOptions() {
            return options;
        }
    }
}