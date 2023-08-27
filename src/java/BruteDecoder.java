package src.java;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BruteDecoder {

    private static List<Character> generateSymbols() {
        List<Character> symbols = new ArrayList<Character>();
        for (int i=33; i<=47;i++) symbols.add((char) i);
        for (char c='0'; c<='9'; c++) symbols.add(c);
        for (char c='А'; c<='Я'; c++) symbols.add(c);
        for (char c='а'; c<='я'; c++) symbols.add(c);
        return symbols;
    }

    public void decode(Path path, Integer key) {

        List<Character> alphabet = generateSymbols();
        for (char i:alphabet) {
            System.out.println(i);
        }
        System.out.println("BruteDecoder");
    }
}
