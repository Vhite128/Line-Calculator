import java.io.IOException;
import java.util.Scanner;

public class test {

    static char action;
    static String[] data;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String in = input.replace("\"", "");
        action = searchSign(in);
        takeOneDoTwo(in, action);
        String d1 = data[0];
        String d2 = data[1];

        if (action == '*' || action == '/') {
            int d3 = Integer.parseInt(d2);
            searchExeptionInt(d3);
        }

        if (action == '+' || action == '-') {
            int d4 = Integer.parseInt(d1);
            if (d4 >= 1){throw new IOException();
            }
        }

        searchExeptionStrings(d1, d2);
        calculateString(d1, d2, action);
    }

    //Поиск знака
    private static char searchSign(String in) throws Exception {
        if (in.contains(" + ")) {
            action = '+';
        } else if (in.contains(" - ")) {
            action = '-';
        } else if (in.contains(" * ")) {
            action = '*';
        } else if (in.contains(" / ")) {
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }
        return action;
    }

    //Создание массива
    private static String [] takeOneDoTwo(String in, char action) {
        if (in.contains(" + ")) {
            data = in.split(" \\+ ");
        } else if (in.contains(" - ")) {
            data = in.split(" - ");
        } else if (in.contains(" * ")) {
            data = in.split(" \\* ");
        } else if (in.contains(" / ")) {
            data = in.split(" / ");
        }
        return data;
    }

    //Вычисление
    public static void calculateString(String d1, String d2, char action) throws IOException {
        switch (action) {
            case '+':
                printResult(data[0] + data[1]);
                break;
            case '-':
                int index = data[0].indexOf(data[1]);
                if (index == -1) {
                    printResult(data[0]);
                } else {
                    String result = data[0].substring(0, index);
                    result += data[0].substring(index + data[1].length());
                    printResult(result);
                }
                break;
            case '*':
                if (action == '*') {
                    int multiplier = Integer.parseInt(data[1]);
                    String result = "";
                    for (int i = 0; i < multiplier; i++) {
                        result += data[0];
                    }
                    if (result.length() > 40) {
                        result = result.substring(0, 40);
                        result+="...";
                    }
                    printResult(result);
                }
                break;
            case '/':
                int newLen = data[0].length() / Integer.parseInt(data[1]);
                String result = data[0].substring(0, newLen);
                printResult(result);
                break;

            default: throw new IOException();
        }
    }

    //Исключение по числу
    public static void searchExeptionInt(int d3) {
        int multiplier1 = Integer.parseInt(String.valueOf(d3));
        if (multiplier1 < 1 || multiplier1 >10){
            try {
                throw new IOException();
            } catch (Exception e) {
                System.out.println("Калькулятор может принимать на вход числа от 1 до 10 включительно, не более.");
            }
            System.exit (1);
        }

    }

    //Исключение по строке
    public static void searchExeptionStrings(String d1, String d2) {
        if (d1.length() > 10 || d2.length() > 10) {
            try {
                throw new IOException();
            } catch (Exception e) {
                System.out.println("Калькулятор может принимать на вход строки не более 10 символов");
            }
            System.exit (1);
        }
    }

    //Вывод кекса))
    static void printResult(String text){
        System.out.println("\""+text+"\"");
    }
}
