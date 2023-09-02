package src.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        //TODO: Убрать лишние строки и сделать логические пробелы
        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                //TODO: Вынести все меню строки в отдельный класс констант
                System.out.println("1 - зашифровать файл;\n2 - расшифровать файл;\n3 - Brute Force;\n4 - Частотный анализ");
                System.out.println("Введите номер режима работы [Для выхода нажмите E]: ");
                String mode = console.nextLine();
                InputSaver inputSaver;

                switch (mode) {
                    case "1":
                        inputSaver = new InputSaver(console, Integer.parseInt(mode));
                        inputSaver.saveFile(CaesarCipher.encrypt(inputSaver.getPath(), inputSaver.getKey()));
                        break;
                    case "2":
                        inputSaver = new InputSaver(console, Integer.parseInt(mode));
                        inputSaver.saveFile(CaesarCipher.decode(inputSaver.getPath(), inputSaver.getKey()));
                        break;
                    case "3":
                        inputSaver = new InputSaver(console, Integer.parseInt(mode));
                        inputSaver.saveFile(CaesarCipher.bruteDecode(inputSaver.getPath()));
                        break;
                    case "4":
                        inputSaver = new InputSaver(console, Integer.parseInt(mode));
                        inputSaver.saveFile(FrequencyAnalyzer.decode(inputSaver.getPath(),inputSaver.getBible()));
                        break;
                    case "E":
                        System.out.println("Программа завершила работу.");
                        return;
                    default:
                        throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Wrong input");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected error");
        }
    }
}
