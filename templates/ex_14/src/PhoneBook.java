import java.io.*;
import java.util.*;

public class PhoneBook {
    private static class SubscriberInfo {
        String surname;
        String name;
        String patronymic;
        String address;
        String email;
        SubscriberInfo(String surname, String name, String patronymic, String address, String email) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.address = address;
            this.email = email;
        }
    }

    private Map<String, SubscriberInfo> phoneToInfo;
    private String filename;

    public PhoneBook(String filename) {
        this.phoneToInfo = new HashMap<>();
        this.filename = filename;
        loadFromFile();
    }

    private boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) return false;
        String cleaned = phone.replaceAll("[\\s\\-()]", "");
        return cleaned.matches("^\\+?\\d{7,15}$");
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return true;
        if (email.contains(" ")) return false;
        int atIndex = email.indexOf('@');
        if (atIndex < 1) return false;
        int dotIndex = email.indexOf('.', atIndex + 1);
        return dotIndex > atIndex + 1 && dotIndex < email.length() - 1;
    }

    private boolean isValidNamePart(String s) {
        if (s == null || s.isEmpty()) return true;
        return s.matches("^[\\p{L}\\s\\-]+$");
    }

    private void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл не найден, будет создан новый при сохранении.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 2) {
                    System.err.println("Некорректная строка (меньше 2 полей): " + line);
                    continue;
                }
                String phone = parts[0].trim();
                String surname = parts[1].trim();
                String name = parts.length > 2 ? parts[2].trim() : "";
                String patronymic = parts.length > 3 ? parts[3].trim() : "";
                String address = parts.length > 4 ? parts[4].trim() : "";
                String email = parts.length > 5 ? parts[5].trim() : "";
                if (!phone.isEmpty() && !surname.isEmpty() && isValidPhone(phone) && isValidEmail(email)) {
                    phoneToInfo.put(phone, new SubscriberInfo(surname, name, patronymic, address, email));
                } else {
                    System.err.println("Пропущена запись с невалидными данными: " + line);
                }
            }
            System.out.println("Загружено " + phoneToInfo.size() + " записей.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, SubscriberInfo> entry : phoneToInfo.entrySet()) {
                String phone = entry.getKey();
                SubscriberInfo info = entry.getValue();
                writer.write(phone + "|" + info.surname + "|" + info.name + "|" + info.patronymic + "|" + info.address + "|" + info.email);
                writer.newLine();
            }
            System.out.println("Данные сохранены в файл " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void addSubscriber(String phone, String surname, String name, String patronymic, String address, String email) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Номер телефона обязателен.");
            return;
        }
        if (surname == null || surname.trim().isEmpty()) {
            System.out.println("Фамилия обязательна.");
            return;
        }
        phone = phone.trim();
        surname = surname.trim();
        name = (name == null) ? "" : name.trim();
        patronymic = (patronymic == null) ? "" : patronymic.trim();
        address = (address == null) ? "" : address.trim();
        email = (email == null) ? "" : email.trim();
        if (!isValidPhone(phone)) {
            System.out.println("Некорректный номер телефона. Допускаются только цифры и ведущий '+', длина от 7 до 15 символов.");
            return;
        }
        if (!isValidNamePart(surname)) {
            System.out.println("Фамилия содержит недопустимые символы.");
            return;
        }
        if (!isValidNamePart(name)) {
            System.out.println("Имя содержит недопустимые символы.");
            return;
        }
        if (!isValidNamePart(patronymic)) {
            System.out.println("Отчество содержит недопустимые символы.");
            return;
        }
        if (!isValidEmail(email)) {
            System.out.println("Некорректный email. Должен содержать @ и точку после неё, без пробелов.");
            return;
        }
        if (phoneToInfo.containsKey(phone)) {
            System.out.println("Абонент с номером " + phone + " уже существует. Данные обновлены.");
        } else {
            System.out.println("Абонент добавлен.");
        }
        phoneToInfo.put(phone, new SubscriberInfo(surname, name, patronymic, address, email));
        saveToFile();
    }

    public boolean removeSubscriber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Номер не может быть пустым.");
            return false;
        }
        phone = phone.trim();
        if (phoneToInfo.containsKey(phone)) {
            phoneToInfo.remove(phone);
            saveToFile();
            System.out.println("Абонент с номером " + phone + " удалён.");
            return true;
        } else {
            System.out.println("Абонент с номером " + phone + " не найден.");
            return false;
        }
    }

    public String findNameByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Номер не может быть пустым.");
            return null;
        }
        phone = phone.trim();
        SubscriberInfo info = phoneToInfo.get(phone);
        if (info == null) {
            System.out.println("Абонент с номером " + phone + " не найден.");
            return null;
        } else {
            System.out.println("Найден абонент: " + info.surname + " " + info.name + " " + info.patronymic);
            if (!info.address.isEmpty()) System.out.println("Адрес: " + info.address);
            if (!info.email.isEmpty()) System.out.println("Email: " + info.email);
            return info.surname;
        }
    }

    public void printAll() {
        if (phoneToInfo.isEmpty()) {
            System.out.println("Справочник пуст.");
            return;
        }
        System.out.println("=== Телефонный справочник ===");
        for (Map.Entry<String, SubscriberInfo> entry : phoneToInfo.entrySet()) {
            String phone = entry.getKey();
            SubscriberInfo info = entry.getValue();
            System.out.print(phone + " -> " + info.surname);
            if (!info.name.isEmpty()) System.out.print(" " + info.name);
            if (!info.patronymic.isEmpty()) System.out.print(" " + info.patronymic);
            if (!info.address.isEmpty()) System.out.print(", адрес: " + info.address);
            if (!info.email.isEmpty()) System.out.print(", email: " + info.email);
            System.out.println();
        }
        System.out.println("Всего записей: " + phoneToInfo.size());
    }

    public void showMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Добавить абонента");
        System.out.println("2. Удалить абонента (по номеру)");
        System.out.println("3. Найти фамилию по номеру");
        System.out.println("4. Показать всех абонентов");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    public static void start() {
        String filename = "./data/phonebook.txt";
        PhoneBook phoneBook = new PhoneBook(filename);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            phoneBook.showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": {
                    String phone, surname, name, patronymic, address, email;
                    while (true) {
                        System.out.print("Введите номер телефона: ");
                        phone = scanner.nextLine().trim();
                        if (phoneBook.isValidPhone(phone)) break;
                        System.out.println("Некорректный номер. Допускаются только цифры и ведущий '+', длина от 7 до 15 символов.");
                    }
                    while (true) {
                        System.out.print("Введите фамилию: ");
                        surname = scanner.nextLine().trim();
                        if (!surname.isEmpty() && phoneBook.isValidNamePart(surname)) break;
                        System.out.println("Фамилия обязательна и не должна содержать недопустимые символы.");
                    }
                    while (true) {
                        System.out.print("Введите имя (Enter - пропустить): ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty() || phoneBook.isValidNamePart(name)) break;
                        System.out.println("Имя содержит недопустимые символы.");
                    }
                    while (true) {
                        System.out.print("Введите отчество (Enter - пропустить): ");
                        patronymic = scanner.nextLine().trim();
                        if (patronymic.isEmpty() || phoneBook.isValidNamePart(patronymic)) break;
                        System.out.println("Отчество содержит недопустимые символы.");
                    }
                    System.out.print("Введите адрес (Enter - пропустить): ");
                    address = scanner.nextLine().trim();
                    while (true) {
                        System.out.print("Введите email (Enter - пропустить): ");
                        email = scanner.nextLine().trim();
                        if (email.isEmpty() || phoneBook.isValidEmail(email)) break;
                        System.out.println("Некорректный email. Должен содержать @ и точку после неё, без пробелов.");
                    }
                    phoneBook.addSubscriber(phone, surname, name, patronymic, address, email);
                    break;
                }
                case "2":
                    System.out.print("Введите номер телефона для удаления: ");
                    String delPhone = scanner.nextLine().trim();
                    phoneBook.removeSubscriber(delPhone);
                    break;
                case "3":
                    System.out.print("Введите номер телефона для поиска: ");
                    String searchPhone = scanner.nextLine().trim();
                    phoneBook.findNameByPhone(searchPhone);
                    break;
                case "4":
                    phoneBook.printAll();
                    break;
                case "0":
                    running = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}