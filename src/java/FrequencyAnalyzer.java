package src.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyAnalyzer {

    //TODO: не работает
    static List<Character> alphabet = generateSymbols();

    private static List<Character> generateSymbols() {
        List<Character> symbols = new ArrayList<>();
        for (int i = 32; i <= 47; i++) symbols.add((char) i);
        for (char c = '0'; c <= '9'; c++) symbols.add(c);
        for (char c = 'А'; c <= 'Я'; c++) symbols.add(c);
        for (char c = 'а'; c <= 'я'; c++) symbols.add(c);
        return symbols;
    }

    private static HashMap<Character, Integer> analyze(String text) {
        HashMap<Character, Integer> frequenciesMap = new HashMap<>();

        for (char character : text.toCharArray()) {
            if (alphabet.contains(character)) {
                frequenciesMap.put(character, frequenciesMap.getOrDefault(character, 0) + 1);
            }
        }
        return frequenciesMap;
    }

    public static String decode(Path encryptedFile, Path bible) throws IOException {

        String bibleText = Files.readString(bible);
        String encryptedText = Files.readString(encryptedFile);

        List<Map.Entry<Character, Integer>> referenceList = new ArrayList<>(analyze(bibleText).entrySet());
        referenceList.sort(Map.Entry.comparingByValue());

        List<Map.Entry<Character, Integer>> encryptedList = new ArrayList<>(analyze(encryptedText).entrySet());
        encryptedList.sort(Map.Entry.comparingByValue());

        Map<Character, Character> charMapping = new HashMap<>();
        for (int i = 0; i < encryptedList.size() && i < referenceList.size(); i++) {
            charMapping.put(encryptedList.get(i).getKey(), referenceList.get(i).getKey());
        }

        char mostCommonReferenceChar = referenceList.get(referenceList.size() - 1).getKey();

        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            decryptedText.append(charMapping.getOrDefault(c, mostCommonReferenceChar));
        }

        return decryptedText.toString();
    }
}
