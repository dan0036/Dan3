import java.util.*;

public class homeWork6 {
    /*
    Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
    Создать множество ноутбуков.
    Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
    отвечающие фильтру. Критерии фильтрации можно хранить в Map.
        Например:
        “Введите цифру, соответствующую необходимому критерию:
        1 - ОЗУ
        2 - Объем ЖД
        3 - Операционная система
        4 - Цвет …
    Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
    Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
    Работу сдать как обычно ссылкой на гит репозиторий
    Частые ошибки:
        1. Заставляете пользователя вводить все существующие критерии фильтрации
        2. Невозможно использовать более одного критерия фильтрации одновременно
        3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
        4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков или
        добавить еще ноутбук, то программа начинает работать некорректно
*/

    public static void main(String[] args) {
        HashSet<Laptop> laptops = new HashSet<>();
        HashMap<String, Object> filterCriteria = new HashMap<>();
        // Создание и добавление ноутбуков в множество

        Laptop laptop1 = new Laptop();
        laptop1.hd = 1000;
        laptop1.model = "DVX09238".toUpperCase();
        laptop1.brand = "ASUS".toUpperCase();
        laptop1.cpu = 8;
        laptop1.os = "Windows".toUpperCase();
        laptop1.color = "черный".toUpperCase();
        laptops.add(laptop1);

        Laptop laptop2 = new Laptop();
        laptop2.hd = 256;
        laptop2.model = "MacBookPro_Late2017".toUpperCase();
        laptop2.brand = "Apple".toUpperCase();
        laptop2.cpu = 8;
        laptop2.os = "Mac OS".toUpperCase();
        laptop2.color = "серебристый".toUpperCase();
        laptops.add(laptop2);

        Laptop laptop3 = new Laptop();
        laptop3.hd = 512;
        laptop3.model = "2000hse".toUpperCase();
        laptop3.brand = "HP".toUpperCase();
        laptop3.cpu = 16;
        laptop3.os = "Dos".toUpperCase();
        laptop3.color = "черный".toUpperCase();
        laptops.add(laptop3);

        peeker(-1, laptops, filterCriteria);

    }
        public static void peeker(int peek, HashSet laptops, HashMap filterCriteria){


            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            if (peek >0 && peek<6) {
                choice = peek;
            } else {
                System.out.print("""
                        Выберите одну из опций или введите пробел для начала поиска:
                        1 - CPU\s
                        2 - HD\s
                        3 - OS\s
                        4 - Производитель\s
                        5 - Цвет\s
                        """);
                if (scanner.hasNextInt()) choice = scanner.nextInt();
                else filterAndPrint(laptops, filterCriteria);
            }
            if (choice >0 && choice <6) {
                switch (choice) {
                    case 1:
                        System.out.println("Введите необходимый объем CPU: ");
                        if (scanner.hasNextInt()) {
                            filterCriteria.put("cpu", scanner.nextInt());
                            newEntry(laptops, filterCriteria);
                            break;
                        }
                    case 2:
                        System.out.println("Введите необходимый объем HD: ");
                        if (scanner.hasNextInt()) filterCriteria.put("hd", scanner.nextInt());
                        newEntry(laptops, filterCriteria);
                        break;
                    case 3:
                        System.out.println("Введите необходимую ОС: ");
                        if (scanner.hasNextLine()) filterCriteria.put("os", scanner.nextLine().toUpperCase());
                        newEntry(laptops, filterCriteria);
                        break;
                    case 4:
                        System.out.println("Введите необходимого производителя: ");
                        if (scanner.hasNextLine()) filterCriteria.put("brand", scanner.nextLine().toUpperCase());
                        newEntry(laptops, filterCriteria);
                        break;
                    case 5:
                        System.out.println("Введите необходимый цвет: ");
                        if (scanner.hasNextLine()) filterCriteria.put("color", scanner.nextLine().toUpperCase());
                        newEntry(laptops, filterCriteria);
                        break;
                    default:
                        System.out.println("Вы не ввели корректное число.");
                        break;
                }
            }
        }
        public static void newEntry(HashSet laptops, HashMap filterCriteria){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите следующую опцию-цифру или любой символ для поиска.");
            if (scanner.hasNextInt()) {
                peeker(scanner.nextInt(), laptops, filterCriteria);
            }
            else {
                scanner.close();
                filterAndPrint(laptops, filterCriteria);

            }
        }




    public static void filterAndPrint(HashSet<Laptop> laptops, Map<String, Object> filterCriteria) {
        System.out.println("Список подходящих вам ноутбуков: ");
        for (Laptop laptop : laptops) {
            if (laptop.match(filterCriteria)) {
                // Вывод информации о подходящем ноутбуке
                System.out.println("Brand: " + laptop.brand + ", Model: " + laptop.model + ", CPU: "
                        + laptop.cpu + "GB, HD: " + laptop.hd + "GB, OS: " + laptop.os + ", Color: " + laptop.color);
            }
        }
    }


    public static class Laptop {
        String model; // Модель ноутбука.
        String brand; // Производитель ноутбука.
        int cpu; // Объем оперативной памяти.
        int hd; // Объем жесткого диска.
        String os; // Предустановленная ОС.
        String color; // Цвет ноутбука.

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Laptop laptop = (Laptop) o;
            return cpu == laptop.cpu && hd == laptop.hd && Objects.equals(os, laptop.os) && Objects.equals(color, laptop.color) && Objects.equals(brand, laptop.brand) && Objects.equals(model, laptop.model);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cpu, hd, os, color, brand, model);
        }

        public boolean match(Map o) {
            return (o.get("brand") == null || this.brand.equals(o.get("brand"))) &&
                    (o.get("os") == null || this.os.equals(o.get("os"))) &&
                    (o.get("color") == null || this.color.equals(o.get("color"))) &&
                    (o.get("cpu") == null || (Integer) this.cpu >= (Integer) o.get("cpu")) &&
                    (o.get("hd") == null || (Integer) this.hd >= (Integer) o.get("hd"));
        }
    }
}
