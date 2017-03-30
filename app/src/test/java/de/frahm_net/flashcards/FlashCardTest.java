package de.frahm_net.flashcards;

import org.junit.Test;


import static org.junit.Assert.*;
/**
 * Created by lars on 12.03.17.
 */

public class FlashCardTest {

    @Test
    public void testConstructor() {
        FlashCard card = new FlashCard("One plus one?", "Two");
        assertEquals("Failure - the answer is not correct", "Two", card.getAnswer());
        assertEquals("Failure - the question is not correct", "One plus one?", card.getQuestion());
    }

    @Test
    public void testCheckAnswer() {
        FlashCard card = new FlashCard("Question","Answer");
        assertEquals("Failure - the answer should be wrong", false, card.checkAnswer("Wrong Answer"));
        assertEquals("Failure - the answer should be right", true, card.checkAnswer("Answer"));
    }

    @Test
    public void testCheckAnswerWithWhiteSpace() {
        FlashCard card = new FlashCard("Question?"," Answer");
        assertEquals("Failure - White space should not matter", true, card.checkAnswer("Answer"));
    }

    @Test
    public void testCheckAnswerWithCases() {
        FlashCard card = new FlashCard("Question", "ANSWER");
        assertEquals("Failure - Case should not matter", true, card.checkAnswer("Answer"));
    }

    @Test
    public void testLearningLevel() {
        FlashCard card = new FlashCard("Question", "Answer");
        FlashCard card2 = new FlashCard("Question", "Answer", 2);
        assertEquals("Failure - wrong level", 0, card.getLevel());
        assertEquals("Failure - wrong level", 2, card2.getLevel());
        card.setLevel(2);
        assertEquals("Failure - wrong level", 2, card.getLevel());
    }

    @Test
    public void testToString() {
        FlashCard card = new FlashCard("q", "a", 0);
        assertEquals("Failure - toString returns wrong string", "q::a::0", card.toString());
    }

    @Test
    public void testParseCard() {
        FlashCard card = FlashCard.parseCard("question::answer::0");
        assertEquals("Failure - wrong question", "question", card.getQuestion());
        assertEquals("Failure - wrong answer", "answer", card.getAnswer());
        assertEquals("Failure - wrong level", 0, card.getLevel());
    }

    @Test
    public void testParseCardForWrongFormat() {
    }
}
