package de.frahm_net.flashcards;

/**
 * Created by lars on 18.03.17.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import java.util.*;
import java.io.*;

@RunWith(MockitoJUnitRunner.class)
public class DeckIOHelperTest {
    final static String FILE_NAME = "test.txt";
    private static File testFile = new File(FILE_NAME);
    private Deck testDeck;

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        testDeck = new Deck("testDeck");
        testDeck.addCard(new FlashCard("Question1", "Answer1"));
        testDeck.addCard(new FlashCard("Question2", "Answer2"));
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testEmptyFileList() {
        String[] emptyList = new String[0];
        when(mockContext.fileList()).thenReturn(emptyList);
        DeckIOHelper deckIOHelper = new DeckIOHelper(mockContext);
        ArrayList<String> result = deckIOHelper.getDeckNames();
        assertTrue("There should be no file in the list", result.isEmpty());
    }

    @Test
    public void testGetFileList() {
        String[] fileList = {FILE_NAME, "wrongExtension.blub"};
        when(mockContext.fileList()).thenReturn(fileList);
        DeckIOHelper deckIOHelper = getDeckIOHelperWithInputStream();
        ArrayList<String> result = deckIOHelper.getDeckNames();
        assertThat("There should be only one file in the list", result.size()==1);
        assertEquals("Failure - wrong deck name", "testDeck", result.get(0));
    }



    @Test
    public void testFileCreation() {
        writeTestDeck();
        assertThat("There should be a file now", testFile.exists());
    }

    @Test
    public void testReadDeck() {
        DeckIOHelper deckIOHelper = getDeckIOHelperWithInputStream();
        Deck deck = deckIOHelper.readDeck(FILE_NAME);
        assertEquals("Failure - deck name not correct", "testDeck", deck.getName());

    }

    @After
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }


    private void writeTestDeck() {
        try {
            FileOutputStream outputStream = new FileOutputStream(testFile);
            when(mockContext.openFileOutput("testDeck.txt",0)).thenReturn(outputStream);
            DeckIOHelper deckIOHelper = new DeckIOHelper(mockContext);
            deckIOHelper.writeDeck(testDeck);

        }catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    private DeckIOHelper getDeckIOHelperWithInputStream() {
        DeckIOHelper deckIOHelper = null;
        writeTestDeck();
        try {
            when(mockContext.openFileInput(FILE_NAME))
                    .thenReturn(new FileInputStream(testFile));
            deckIOHelper = new DeckIOHelper(mockContext);

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return deckIOHelper;
    }
}
