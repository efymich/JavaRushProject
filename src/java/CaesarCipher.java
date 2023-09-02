package src.java;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    //TODO: Убрать лишние строки и сделать логические пробелы
    static List<Character> alphabet = generateSymbols(); //TODO: сделать private

    public static String encrypt(Path path, int key) {
        return run(path, key);
    }

    public static String decode(Path path, int key) {
        return run(path, -key);
    }

    public static String bruteDecode(Path path) {
        int maxCount = 0;
        ArrayList<Integer> statistic = new ArrayList<>();
        for (int i = 1; i < CaesarCipher.alphabet.size(); i++) {
            String file = run(path, -i);
            int counterWhiteSpaces = 0;
            for (char character : file.toCharArray()) {
                if (character == ' ') {
                    counterWhiteSpaces++;
                }
            }
            maxCount = Math.max(counterWhiteSpaces, maxCount);
            statistic.add(counterWhiteSpaces);
        }
        return run(path, -(statistic.indexOf(maxCount) + 1));
    }

    private static List<Character> generateSymbols() { //TODO: Повторяется в FrequencyAnalyzer
        List<Character> symbols = new ArrayList<>();
        for (int i = 32; i <= 47; i++) symbols.add((char) i);
        for (char c = '0'; c <= '9'; c++) symbols.add(c);
        for (char c = 'А'; c <= 'Я'; c++) symbols.add(c);
        for (char c = 'а'; c <= 'я'; c++) symbols.add(c);
        return symbols;
    }

    private static String run(Path path, int key) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(path.toFile())) {
            //TODO: Вынести это в отдельный private method
            char[] input = new char[(int) path.toFile().length()];
            fileReader.read(input);
            for (char character : input) {
                if (!alphabet.contains(character) /*|| Character.isWhitespace(character)*/) {
                    result.append(character);
                } else {
                    //TODO: Вынести это в отдельный private method
                    int index = alphabet.indexOf(character);
                    int shiftedIndex = (index + key + alphabet.size()) % alphabet.size();
                    // добавляем размер алфавита,чтобы при дешифровке не получить отрицательного значения и не выйти за пределы цикла
                    result.append(alphabet.get(shiftedIndex));
                }
            }
            return result.toString();
        } catch (Exception e) { //TODO: Указать какой-то конкретный Exception
            e.printStackTrace();
            return null;
        }
    }
}
