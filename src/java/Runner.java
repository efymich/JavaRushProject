package src.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Runner {

/*    static {
        Cipher cipher = new Cipher();
        Decoder decoder;
    }*/
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            InputSaver inputSaver = new InputSaver();
            System.out.println("Введите номер режима работы [Для выхода нажмите E]: ");

            switch (reader.readLine()) {
                case "1":
                    inputSaver.welcome(reader);
                    Cipher cipher = new CeasarCipher();
                    cipher.encrypt(inputSaver.getPath(), inputSaver.getKey());
                    break;
                case "2":
                    inputSaver.welcome(reader);
                    Decoder decoder = new CeasarDecoder();
                    decoder.decode(inputSaver.getPath(), inputSaver.getKey());
                    break;
                case "3":
                    decoder = new BruteDecoder();
                    decoder.decode(inputSaver.getPath(), inputSaver.getKey());
                    break;
                case "E":
                    System.out.println("Программа завершила работу.");
                    return;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }
}
