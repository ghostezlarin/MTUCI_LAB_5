import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFinder {
    public static void main(String[] args) {
        System.out.println(np("The price of the product is $19.99 and the discount is 5%"));

        System.out.println(check_pass("DD2hj374!"));

        System.out.println(bigChar("aBcDefGhiJkL Tony ждлотЯ"));

        System.out.println(check_ip("2643.2761.2312.4365"));
        System.out.println(check_ip("192.168.1.250"));

        System.out.println(search_words("Tt TTrgrwagw TWEfwadwgarttTt gwrgRTWWTtt REAERtTt"));
    }

    //задание 1. Программа, что ищет все числа в заданном тексте и выводит их на экран.
    public static String np(String text) {
        String res = ""; //создаем пустую строчку
        try {
            //создаем шаблон
            //-? - - может присутствовать или отсутствовать, \\d+ любая цифра повторяется один или более раз, (\\.\\d+) цифра с децимал сепаратор.
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // ищет число(либо целое, либо дробное)
            //сравниваем по паттерну
            Matcher matcher = pattern.matcher(text);
            // добавление в результаты
            while (matcher.find()) {
                res += matcher.group();
                res += ' ';
            }
        } catch (Exception e) {
            System.out.println("ошибка!" + e.getMessage());
        }
        return res;
    }

    //Задание 2. Проверка корректности ввода пароля
    public static String check_pass(String text) {
        String res = "";
        //^ - начало строки; ?= предыдущие символы могут присутствовать так и не могут; . - любой символ; *[0-9] любой кол-во символов от 0-9;
        // длина пароля от 8-16; любой пробельный символ
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,16}$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            res = "Valid!";
        } else {
            res = "Invalid!";
        }
        return res;
    }

    //3 задание. Поиск заглавной буквы после строчной.
    public static String bigChar(String text) {
        Pattern pattern = Pattern.compile("([a-zа-я])([A-ZА-Я])");
        Matcher matcher = pattern.matcher(text);

        String result = text;


        int offset = 0;

        while (matcher.find()) {
            result = result.substring(0, matcher.start(2) + offset) + "!" + result.substring(matcher.start(2) + offset);
            offset++;

            result = result.substring(0, matcher.end(2) + offset) + "!" + result.substring(matcher.end(2) + offset);
            offset++;
        }

        return result;
    }

    // задание 4. Проверка корректности ввода IP-адреса.
    public static String check_ip(String text) {
        String res = "";
        //{} - кол-во повторений
        //От 250 - 255. От 200 до 249. От 100 - 199. От 1 - 99. 
        Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$");
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            res = "valid ip";
        } else {
            res = "invalid ip";
        }
        return res;
    }

    // задание 5 Поиск всех слов, начинающихся с заданной буквы
    public static String search_words(String text) {
        String letter = "Tt";
        String res = "";
        // В границах слова встречается letter2
        Pattern pattern = Pattern.compile("\\b[" + letter + "]\\w*\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            res += matcher.group() + " ";
        }
        return res;
    }
}