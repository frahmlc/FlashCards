package de.frahm_net.flashcards;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by lars on 13.03.17.
 */

public class DeckIOHelper {
    public final static String FILE_EXTENSION = ".txt";
    private Context context;

    public DeckIOHelper(Context context) {
        this.context = context;
    }

    public ArrayList<String> getDeckNames() {
        String[] files = getFileList();
        ArrayList<String> deckNames = new ArrayList<String>();
        for (String file : files) {
            if (file.endsWith(FILE_EXTENSION)) {
                deckNames.add(readDeckName(file));
            }
        }
        return deckNames;
    }

    private String[] getFileList() {
        return context.fileList();
    }

    private String readDeckName(String fileName) {
        String deckName = null;
        try {
            BufferedReader b = getBufferedReader(fileName);
            deckName = b.readLine();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return deckName;
    }

    private BufferedReader getBufferedReader(String fileName) throws Exception {
        FileInputStream inputStream = context.openFileInput(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        return new BufferedReader(streamReader);
    }

    public Deck readDeck(String fileName) {
        Deck deck = null;
        try {
            BufferedReader reader = getBufferedReader(fileName);
            String deckName = reader.readLine();
            deck = new Deck(deckName);
            String line;
            while ((line = reader.readLine()) != null) {
                FlashCard card = FlashCard.parseCard(line);
                deck.addCard(card);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return deck;
    }

    public void writeDeck(Deck deck) {
        try {
            String fileName = deck.getName() + FILE_EXTENSION;
            BufferedWriter writer = getBufferedWriter(fileName);

            writer.write(deck.getName());
            writer.newLine();

            for (FlashCard card : deck.getCards()) {
                writer.write(card.toString());
                writer.newLine();
            }
            writer.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private BufferedWriter getBufferedWriter(String fileName) throws Exception {
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
        return new BufferedWriter(streamWriter);
    }


}
