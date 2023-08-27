package src.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                CaesarCipher caesarCipher = new CaesarCipher();
                BruteDecoder bruteDecoder = new BruteDecoder();
                System.out.println("1 - зашифровать файл;\n2 - расшифровать файл;\n3 - Brute Force");
                System.out.println("Введите номер режима работы [Для выхода нажмите E]: ");
                String mode = console.nextLine();
                InputSaver inputSaver;

                switch (mode) {
                    case "1":
                        inputSaver = new InputSaver(console);
                        inputSaver.saveFile(caesarCipher.encrypt(inputSaver.getPath(),inputSaver.getKey()),Integer.parseInt(mode));
                        break;
                    case "2":
                        inputSaver = new InputSaver(console);
                        inputSaver.saveFile(caesarCipher.decode(inputSaver.getPath(),inputSaver.getKey()),Integer.parseInt(mode));
                        break;
                    case "3":
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
