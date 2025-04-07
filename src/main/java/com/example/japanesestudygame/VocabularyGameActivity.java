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

public class VocabularyGameActivity extends AppCompatActivity {

    private TextView questionText;
    private Button option1Button, option2Button, option3Button, option4Button;
    private Button nextButton;
    private TextView scoreText;

    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<VocabularyQuestion> questions;
    private boolean questionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_game);

        // Initialize views
        questionText = findViewById(R.id.question_text);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        nextButton = findViewById(R.id.next_button);
        scoreText = findViewById(R.id.score_text);

        // Create vocabulary questions
        createVocabularyQuestions();

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
                    Toast.makeText(VocabularyGameActivity.this, 
                            "Game Over! Final Score: " + score, 
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        // Display first question
        displayQuestion();
    }

    private void createVocabularyQuestions() {
        // Create a map of Japanese vocabulary words and their English translations
        Map<String, String> vocabularyMap = new HashMap<>();
        vocabularyMap.put("おはよう", "good morning");
        vocabularyMap.put("こんにちは", "hello");
        vocabularyMap.put("こんばんは", "good evening");
        vocabularyMap.put("さようなら", "goodbye");
        vocabularyMap.put("ありがとう", "thank you");
        vocabularyMap.put("すみません", "excuse me/sorry");
        vocabularyMap.put("はい", "yes");
        vocabularyMap.put("いいえ", "no");
        vocabularyMap.put("お願いします", "please");
        vocabularyMap.put("わかりました", "I understand");
        vocabularyMap.put("わかりません", "I don't understand");
        vocabularyMap.put("名前", "name");
        vocabularyMap.put("学生", "student");
        vocabularyMap.put("先生", "teacher");
        vocabularyMap.put("友達", "friend");
        vocabularyMap.put("家族", "family");
        vocabularyMap.put("父", "father");
        vocabularyMap.put("母", "mother");
        vocabularyMap.put("兄", "older brother");
        vocabularyMap.put("姉", "older sister");
        vocabularyMap.put("弟", "younger brother");
        vocabularyMap.put("妹", "younger sister");
        vocabularyMap.put("犬", "dog");
        vocabularyMap.put("猫", "cat");
        vocabularyMap.put("食べる", "to eat");
        vocabularyMap.put("飲む", "to drink");
        vocabularyMap.put("見る", "to see/watch");
        vocabularyMap.put("聞く", "to hear/listen");
        vocabularyMap.put("話す", "to speak");
        vocabularyMap.put("読む", "to read");
        vocabularyMap.put("書く", "to write");
        vocabularyMap.put("行く", "to go");
        vocabularyMap.put("来る", "to come");
        vocabularyMap.put("帰る", "to return");
        vocabularyMap.put("学校", "school");
        vocabularyMap.put("家", "house/home");
        vocabularyMap.put("病院", "hospital");
        vocabularyMap.put("駅", "station");
        vocabularyMap.put("お店", "store/shop");
        vocabularyMap.put("レストラン", "restaurant");

        // Create questions from the map
        questions = new ArrayList<>();
        List<String> vocabularyList = new ArrayList<>(vocabularyMap.keySet());
        Collections.shuffle(vocabularyList);

        // Take 20 random vocabulary words for the game
        for (int i = 0; i < Math.min(20, vocabularyList.size()); i++) {
            String japanese = vocabularyList.get(i);
            String correctAnswer = vocabularyMap.get(japanese);
            
            // Create wrong options
            List<String> allAnswers = new ArrayList<>(vocabularyMap.values());
            allAnswers.remove(correctAnswer);
            Collections.shuffle(allAnswers);
            
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);
            options.add(allAnswers.get(0));
            options.add(allAnswers.get(1));
            options.add(allAnswers.get(2));
            Collections.shuffle(options);
            
            questions.add(new VocabularyQuestion(japanese, correctAnswer, options));
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
        option1Button.setBackgroundColor(getResources().getColor(R.color.vocabulary_color));
        option2Button.setBackgroundColor(getResources().getColor(R.color.vocabulary_color));
        option3Button.setBackgroundColor(getResources().getColor(R.color.vocabulary_color));
        option4Button.setBackgroundColor(getResources().getColor(R.color.vocabulary_color));
        
        // Get current question
        VocabularyQuestion currentQuestion = questions.get(currentQuestionIndex);
        
        // Display question and options
        questionText.setText(currentQuestion.getJapanese());
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
        VocabularyQuestion currentQuestion = questions.get(currentQuestionIndex);
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

    // Inner class to represent a vocabulary question
    private static class VocabularyQuestion {
        private String japanese;
        private String correctAnswer;
        private List<String> options;

        public VocabularyQuestion(String japanese, String correctAnswer, List<String> options) {
            this.japanese = japanese;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getJapanese() {
            return japanese;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getOptions() {
            return options;
        }
    }
}