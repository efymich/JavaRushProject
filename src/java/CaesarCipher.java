package src.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CaesarCipher {

    public String encrypt(Path path, int key) {
        return run(path,key);
    }

    public String decode(Path path,int key) {
        return run(path,-key);
    }

    private static List<Character> generateSymbols() {
        List<Character> symbols = new ArrayList<Character>();
        for (int i=32; i<=47;i++) symbols.add((char) i);
        for (char c='0'; c<='9'; c++) symbols.add(c);
        for (char c='А'; c<='Я'; c++) symbols.add(c);
        for (char c='а'; c<='я'; c++) symbols.add(c);
        return symbols;
    }

    private String run(Path path,int key) {
        StringBuilder result = new StringBuilder();
        List<Character> alphabet = generateSymbols();
        try(FileReader fileReader = new FileReader(path.toFile())){
            char[] input = new char[(int) path.toFile().length()];
            fileReader.read(input);
            for (char character : input) {
                if (!alphabet.contains(character) || Character.isWhitespace(character)) {
                    result.append(character);
                } else {
                    int index = alphabet.indexOf(character);
                    int shiftedIndex = (index + key + alphabet.size()) % alphabet.size();
                    // добавляем размер алфавита,чтобы при дешифровке не получить отрицательного значения и не выйти за пределы цикла
                    result.append(alphabet.get(shiftedIndex));
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
