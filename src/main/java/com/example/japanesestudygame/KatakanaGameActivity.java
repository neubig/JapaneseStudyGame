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

public class KatakanaGameActivity extends AppCompatActivity {

    private TextView questionText;
    private Button option1Button, option2Button, option3Button, option4Button;
    private Button nextButton;
    private TextView scoreText;

    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<KatakanaQuestion> questions;
    private boolean questionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katakana_game);

        // Initialize views
        questionText = findViewById(R.id.question_text);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        nextButton = findViewById(R.id.next_button);
        scoreText = findViewById(R.id.score_text);

        // Create katakana questions
        createKatakanaQuestions();

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
                    Toast.makeText(KatakanaGameActivity.this, 
                            "Game Over! Final Score: " + score, 
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        // Display first question
        displayQuestion();
    }

    private void createKatakanaQuestions() {
        // Create a map of katakana characters and their romanized equivalents
        Map<String, String> katakanaMap = new HashMap<>();
        katakanaMap.put("ア", "a");
        katakanaMap.put("イ", "i");
        katakanaMap.put("ウ", "u");
        katakanaMap.put("エ", "e");
        katakanaMap.put("オ", "o");
        katakanaMap.put("カ", "ka");
        katakanaMap.put("キ", "ki");
        katakanaMap.put("ク", "ku");
        katakanaMap.put("ケ", "ke");
        katakanaMap.put("コ", "ko");
        katakanaMap.put("サ", "sa");
        katakanaMap.put("シ", "shi");
        katakanaMap.put("ス", "su");
        katakanaMap.put("セ", "se");
        katakanaMap.put("ソ", "so");
        katakanaMap.put("タ", "ta");
        katakanaMap.put("チ", "chi");
        katakanaMap.put("ツ", "tsu");
        katakanaMap.put("テ", "te");
        katakanaMap.put("ト", "to");
        katakanaMap.put("ナ", "na");
        katakanaMap.put("ニ", "ni");
        katakanaMap.put("ヌ", "nu");
        katakanaMap.put("ネ", "ne");
        katakanaMap.put("ノ", "no");
        katakanaMap.put("ハ", "ha");
        katakanaMap.put("ヒ", "hi");
        katakanaMap.put("フ", "fu");
        katakanaMap.put("ヘ", "he");
        katakanaMap.put("ホ", "ho");
        katakanaMap.put("マ", "ma");
        katakanaMap.put("ミ", "mi");
        katakanaMap.put("ム", "mu");
        katakanaMap.put("メ", "me");
        katakanaMap.put("モ", "mo");
        katakanaMap.put("ヤ", "ya");
        katakanaMap.put("ユ", "yu");
        katakanaMap.put("ヨ", "yo");
        katakanaMap.put("ラ", "ra");
        katakanaMap.put("リ", "ri");
        katakanaMap.put("ル", "ru");
        katakanaMap.put("レ", "re");
        katakanaMap.put("ロ", "ro");
        katakanaMap.put("ワ", "wa");
        katakanaMap.put("ヲ", "wo");
        katakanaMap.put("ン", "n");

        // Create questions from the map
        questions = new ArrayList<>();
        List<String> katakanaList = new ArrayList<>(katakanaMap.keySet());
        Collections.shuffle(katakanaList);

        // Take 20 random katakana characters for the game
        for (int i = 0; i < Math.min(20, katakanaList.size()); i++) {
            String katakana = katakanaList.get(i);
            String correctAnswer = katakanaMap.get(katakana);
            
            // Create wrong options
            List<String> allAnswers = new ArrayList<>(katakanaMap.values());
            allAnswers.remove(correctAnswer);
            Collections.shuffle(allAnswers);
            
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);
            options.add(allAnswers.get(0));
            options.add(allAnswers.get(1));
            options.add(allAnswers.get(2));
            Collections.shuffle(options);
            
            questions.add(new KatakanaQuestion(katakana, correctAnswer, options));
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
        option1Button.setBackgroundColor(getResources().getColor(R.color.katakana_color));
        option2Button.setBackgroundColor(getResources().getColor(R.color.katakana_color));
        option3Button.setBackgroundColor(getResources().getColor(R.color.katakana_color));
        option4Button.setBackgroundColor(getResources().getColor(R.color.katakana_color));
        
        // Get current question
        KatakanaQuestion currentQuestion = questions.get(currentQuestionIndex);
        
        // Display question and options
        questionText.setText(currentQuestion.getKatakana());
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
        KatakanaQuestion currentQuestion = questions.get(currentQuestionIndex);
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

    // Inner class to represent a katakana question
    private static class KatakanaQuestion {
        private String katakana;
        private String correctAnswer;
        private List<String> options;

        public KatakanaQuestion(String katakana, String correctAnswer, List<String> options) {
            this.katakana = katakana;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getKatakana() {
            return katakana;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getOptions() {
            return options;
        }
    }
}