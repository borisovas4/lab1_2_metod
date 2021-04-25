import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InfoAboutPeoples{
    private static DataBase myDB = new DataBase();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner scan = new Scanner(System.in);
        mark:
        while (true) {
            System.out.println("1. Добавить человека в базу");
            System.out.println("2. Показать всех");
            System.out.println("3. Определить человека с самым большим размером одежды");
            System.out.println("4. Определить средний размер одежды для людей страше 40 лет");
            System.out.println("5. Организовать поиск/исправить человека в базе");
            System.out.println("0. Выход");
            int result = scan.nextInt();

            switch (result) {
                case 1:
                    myDB.addHuman(myDB.newHuman());
                    break;
                case 2:
                    myDB.showHumans();
                    break;
                case 3:
                    myDB.showHumanWithMaxCSize();
                    break;
                case 4:
                    myDB.showAverage();
                    break;
                case 5:
                    myDB.replaceHuman(myDB.findHuman(), myDB.newHuman());
                    break;
                default:
                    break mark;
            }
        }
    }
}

class DataBase {
    private List<Human> db = new ArrayList<>();

    void addHuman(Human human) {
        db.add(human);
    }

    void showHumanWithMaxCSize() {
        Collections.sort(db);
        System.out.println("Человек с самым большим размером одежды: ");
        System.out.println(db.get(db.size() - 1));
    }

    void showAverage() {
        int count = 0;
        int sum = 0;
        for (Human aDb : db) {
            if (aDb.getAge() < 40) continue;
            sum = +aDb.getClothing_size();
            count++;
        }
        System.out.println("Средний размер одежды у людей старше 40: " + (1. * sum / count));
    }

    int findHuman() {
        Scanner scan = new Scanner(System.in);
        String find;
        while (true) {
            System.out.print("Введите фамилию: ");
            find = scan.next();
            for (int i = 0; i < db.size(); i++) {
                if (find.equals(db.get(i).getLastName())) {
                    System.out.println("Найден номер записи: " + i);
                    return i;
                }
            }
            System.out.println("Совпадений не найдено!");
        }
    }

    Human newHuman() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        String lastName = scan.next();
        System.out.print("Введите имя: ");
        String firstName = scan.next();
        System.out.print("Введите отчество: ");
        String patronymic = scan.next();
        System.out.print("Введите возраст: ");
        int age = scan.nextInt();
        System.out.print("Введите размер одежды: ");
        int clothing_size = scan.nextInt();
        Human h = new Human(lastName, firstName, patronymic, age, clothing_size);
        System.out.println(h);
        return h;
    }

    void replaceHuman(int index, Human human) {
        db.add(index, human);
    }

    void showHumans() {
        db.forEach(System.out::println);
    }
}

class Human implements Comparable<Human> {
    private String lastName;
    private String firstName;
    private String patronymic;
    private int age;
    private int clothing_size;

    Human(String lastName, String firstName, String patronymic, int age, int clothing_size) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        this.clothing_size = clothing_size;
    }

    int getAge() {
        return age;
    }

    int getClothing_size() {
        return clothing_size;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Фамилия: " + lastName + ", Имя: " + firstName + ", Отчество: " + patronymic + "\n" +
                "Возраст: " + age + ", Размер одежды: " + clothing_size + "\n";
    }

    @Override
    public int compareTo(Human human) {
        return clothing_size - human.getClothing_size();
    }
}

