package de.frahm_net.flashcards;

/**
 * Created by lars on 12.03.17.
 */

public class FlashCard {
    final static String DELIMITER = "::";
    String question;
    String answer;
    int level;

    public FlashCard(String question, String answer) {
        this(question, answer, 0);
    }

    public FlashCard(String question, String answer, int level) {
        this.question = question;
        this.answer = answer;
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        String s = question + DELIMITER + answer + DELIMITER + String.valueOf(level);
        return s;
    }

    public static FlashCard parseCard(String s) {
        String[] tokens = s.split(DELIMITER);
        String q = tokens[0];
        String a = tokens[1];
        int l = Integer.parseInt(tokens[2]);
        return new FlashCard(q,a,l);
    }

    public boolean checkAnswer(String guess) {
        String g = guess.trim();
        String a = answer.trim();
        return a.equalsIgnoreCase(g);
    }

}
