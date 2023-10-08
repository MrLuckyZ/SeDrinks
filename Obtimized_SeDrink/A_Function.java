package Obtimized_SeDrink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class A_Function {
    File userFile_data = new File("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Login.txt");
    File machineFile_data = new File("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Machine.txt");
    File order_File = new File("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Order.txt");
    File drinks_File = new File("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Menu.txt");

    Scanner keyboard_input = new Scanner(System.in);
    Integer menu_input;
    // ========================================= M A C H I N E
    // =========================================
    LinkedList<Machine> machine_Data = new LinkedList<Machine>();

    class Machine {
        String machine_ID;
        String machine_City;
        String machine_Location;
        String machine_Account;
        int machine_Balance;

        Machine(String machine_ID, String machine_City, String machine_Location, String machine_Account,
                int machine_Balance) {
            this.machine_ID = machine_ID;
            this.machine_City = machine_City;
            this.machine_Location = machine_Location;
            this.machine_Account = machine_Account;
            this.machine_Balance = machine_Balance;
        }

        public String getMachine_Account() {
            return machine_Account;
        }

        public int getMachine_Balance() {
            return machine_Balance;
        }

        public String getMachine_City() {
            return machine_City;
        }

        public String getMachine_ID() {
            return machine_ID;
        }

        public String getMachine_Location() {
            return machine_Location;
        }
    }

    void addMachine_Data() throws FileNotFoundException {
        try (Scanner machineData_input = new Scanner(machineFile_data)) {
            String string_temp;
            while (machineData_input.hasNext()) {
                string_temp = machineData_input.nextLine();
                String[] data = string_temp.split("\t");
                String temp = data[4].substring(1);
                temp = temp.substring(0, 1) + temp.substring(2);
                int tempint = Integer.valueOf(temp);
                machine_Data.add(new Machine(data[0], data[1], data[2], data[3], tempint));
            }
        }
    }

    void printMachineData() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID\t\tCity\t\tPosition\t\tShippingType\t\tAccount Number\t\t\tBalance($)");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        String shippingType = "";
        for (Machine element : machine_Data) {
            String latitude_position, longtitude_position;
            {// position split
                String tempMachine_position = element.getMachine_Location();
                String[] position_split = tempMachine_position.split(" ");
                latitude_position = position_split[0].substring(0, 10);
                longtitude_position = position_split[1].substring(0, 10);
            }
            String accountNumber = element.getMachine_Account();
            String balance = String.valueOf(element.getMachine_Balance());
            {// shipping type process
                if (Double.valueOf(latitude_position) < 0 && Double.valueOf(longtitude_position) < 0) {
                    shippingType = "Ships";
                } else if (Double.valueOf(latitude_position) > 0
                        && Double.valueOf(longtitude_position) > 0) {
                    shippingType = "Planes";
                } else {
                    shippingType = "Trucks";
                }
            }
            System.out.printf("%-6s\t%-15s\t%.2f, %.2f\t\t%s\t\t\t%s\t\t%s\n", element.getMachine_ID(),
                    element.getMachine_City(),
                    Double.valueOf(latitude_position), Double.valueOf(longtitude_position), shippingType,
                    accountNumber.replace(accountNumber.substring(12), "xxxx"),
                    balance.replace(balance.substring(1), ",xxx"));
        }
    }

    void printMachineData_SortBalance() {
        Collections.sort(machine_Data, (o1, o2) -> o2.getMachine_Balance() - o1.getMachine_Balance());
        printMachineData();
    }

    void printMachineData_SortCity() {
        Collections.sort(machine_Data, (o1, o2) -> (o1.getMachine_City().compareTo(o2.getMachine_City())));
        printMachineData();
    }

    void call_machineFunction() {
        do {
            show_sort_menu();
            menu_input = keyboard_input.nextInt();
            if (menu_input == 1) {
                printMachineData_SortBalance();
            } else if (menu_input == 2) {
                printMachineData_SortCity();
            } else if (menu_input == 3) {
                return;
            }
        } while (menu_input != 3);
    }

    // ========================================= U S E R
    // =========================================
    LinkedList<User> user_Data = new LinkedList<User>();
    boolean password_check = false;
    boolean expire_check = true;

    class User {
        String user_ID;
        String user_Name;
        String user_Email;
        String user_Password;
        String user_Telephone;
        String user_expiredDate;

        User(String user_ID, String user_Name, String user_Email, String user_Password, String user_Telephone) {
            this.user_ID = user_ID;
            this.user_Name = user_Name;
            this.user_Email = user_Email;
            this.user_Password = user_Password;
            this.user_Telephone = user_Telephone;
        }

        public String getUser_Email() {
            return user_Email;
        }

        public String getUser_ID() {
            return user_ID;
        }

        public String getUser_Name() {
            return user_Name;
        }

        public String getUser_Password() {
            return user_Password;
        }

        public String getUser_Telephone() {
            return user_Telephone;
        }

        public String getUser_expiredDate() {
            return getUser_Password().substring(8, 9);
        }

    }

    void addUser_Data() throws FileNotFoundException {
        try (Scanner userData_input = new Scanner(userFile_data)) {
            String string_temp;
            while (userData_input.hasNext()) {
                string_temp = userData_input.nextLine();
                String[] data = string_temp.split("\t");
                user_Data.add(new User(data[0], data[1], data[2], data[3], data[4]));
            }
        }
    }

    void printUser_format(String password, String expireDate) {
        for (User element : user_Data) {
            String full_password = element.getUser_Password();
            String passwordCheck = String.join("", full_password.substring(3, 5), full_password.substring(11, 15));
            if (password.equals(passwordCheck)) {
                String[] nameSplit = element.getUser_Name().split(" ");
                String name = String.join(". ", nameSplit[0].substring(0, 1), nameSplit[1]);
                String usertelephone = element.getUser_Telephone().replace(element.getUser_Telephone().substring(8),
                        "xxxx");
                String email = element.getUser_Email();

                System.out.printf("Welcome : %s\nEmail : %s\nTel : %s\n", name, email, usertelephone);
            }
        }
    }

    void addMore_user() throws FileNotFoundException {
        int id = 0;
        System.out.print("Enter Name : ");
        String first_name = keyboard_input.next();
        System.out.print("Enter Last Name : ");
        String last_name = keyboard_input.next();
        System.out.print("Enter Email : ");
        String email = keyboard_input.next();
        System.out.print("Enter Password : ");
        String password = keyboard_input.next();
        System.out.print("Confirm Password : ");
        String confirm_password = keyboard_input.next();
        System.out.print("Telephone number : ");
        String telephonee = keyboard_input.next();

        Scanner userData_input = new Scanner(userFile_data);

        if (first_name.length() >= 2 && last_name.length() >= 2 && password.length() == 6
                && password.equals(confirm_password) && email.contains("@") == true && telephonee.length() == 10) {
            while (userData_input.hasNext()) {
                String line = userData_input.nextLine();
                String[] temp = line.split("\t");
                id = Integer.valueOf(temp[0]);
                id++;
            }
            String sbTelephone;
            sbTelephone = (telephonee.substring(0, 3) + "-" + telephonee.substring(3, 6) + "-"
                    + telephonee.substring(6));
            telephonee = sbTelephone;
            String CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder(18);
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < 18; i++) {
                if (i == 3 || i == 4 || i == 8 || i == 11 || i == 12 || i == 13 || i == 14) {
                    if (i == 3) {
                        sb.append(password.charAt(0));
                    }
                    if (i == 4) {
                        sb.append(password.charAt(1));
                    }
                    if (i == 11) {
                        sb.append(password.charAt(2));
                    }
                    if (i == 12) {
                        sb.append(password.charAt(3));
                    }
                    if (i == 13) {
                        sb.append(password.charAt(4));
                    }
                    if (i == 14) {
                        sb.append(password.charAt(5));
                    }
                    if (i == 8) {
                        sb.append('1');
                    }

                } else {
                    int randomIndex = random.nextInt(CHARACTER.length());
                    char randomChar = CHARACTER.charAt(randomIndex);
                    sb.append(randomChar);
                }
            }
            password = sb.toString();
            try {
                FileWriter userFileWriter = new FileWriter(
                        "C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Login.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(userFileWriter);
                bufferedWriter.append("\n");
                bufferedWriter.append(
                        id + "\t" + first_name + " " + last_name + "\t" + email + "\t" + password + "\t" + telephonee);
                bufferedWriter.close();
                System.out.println("------------------------");
                System.out.print("The user added sucessfully\n");
                addUser_Data();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        userData_input.close();
    }

    void editUser() throws FileNotFoundException {
        int menu_input;
        System.out.println("-------------------");
        System.out.print("Enter member ID : ");
        String id_input = keyboard_input.next();
        String filePath = "C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Login.txt";

        for (User element : user_Data) {
            String temp = element.getUser_Password();
            if (element.getUser_ID().equals(id_input)) {
                System.out.println("Valid Member ID.");
                System.out.println("-------------------");
                editUserMenuPrint();
                menu_input = keyboard_input.nextInt();
                System.out.println("-------------------");
                if (menu_input == 1) {
                    printEditUserStatus();
                    menu_input = keyboard_input.nextInt();
                    if (menu_input == 1) {
                        String tempActive = element.getUser_Password();
                        tempActive = tempActive.substring(0, 8) + "1" + tempActive.substring(9);
                        fileEditer(filePath, temp, tempActive);
                    } else {
                        String tempActive = element.getUser_Password();
                        tempActive = tempActive.substring(0, 8) + "0" + tempActive.substring(9);
                        fileEditer(filePath, temp, tempActive);
                    }
                } else if (menu_input == 2) {
                    char reset_confirm;
                    printResetUserPassword();
                    reset_confirm = keyboard_input.next().charAt(0);
                    if (reset_confirm == 'Y') {
                        String random_number = "0123456789";
                        StringBuilder sb = new StringBuilder();
                        Random random = new Random();
                        int length = 6;
                        for (int i = 0; i < length; i++) {
                            int index = random.nextInt(random_number.length());
                            char randomChar = random_number.charAt(index);
                            sb.append(randomChar);
                        }
                        String random_password = temp;
                        String new_randompassword;
                        new_randompassword = random_password.substring(0, 3) + sb.substring(0, 2)
                                + random_password.substring(5, 11) + sb.substring(2, 6) + random_password.substring(15);
                        fileEditer(filePath, temp, new_randompassword);
                        System.out.printf("This password has been successfully reset. =>  New password is \"%s\"\n",
                                sb);
                        return;
                    } else if (reset_confirm == 'N') {
                        return;
                    }
                } else if (menu_input == 3) {
                    return;
                }
            }
        }
    }

    void userPassword_Check(String user, String password) {
        for (User element : user_Data) {
            String temp_password = element.getUser_Password();
            temp_password = String.join("", temp_password.substring(3, 5), temp_password.substring(11, 15));
            if (element.getUser_Email().equals(user) && password.equals(temp_password)
                    && element.getUser_expiredDate().equals("1")) {
                password_check = true;
                expire_check = false;
            } else if (element.getUser_Email().equals(user) && password.equals(temp_password)
                    && element.getUser_expiredDate().equals("0")) {
                password_check = true;
                expire_check = true;
            }
        }
    }

    // ========================================= D R I N K S
    // =========================================
    LinkedList<Drink> drinks_data = new LinkedList<Drink>();

    class Drink {
        String drink_ID;
        String drink_name;
        String drink_price;
        String drink_age;
        String drink_gender;
        String drink_type;
        String drink_keyword;

        Drink(String id, String name, String price, String age, String gender, String drink_type, String keyword) {
            this.drink_ID = id;
            this.drink_name = name;
            this.drink_price = price;
            this.drink_age = age;
            this.drink_gender = gender;
            this.drink_type = drink_type;
            this.drink_keyword = keyword;
        }

        public String getDrink_ID() {
            return drink_ID;
        }

        public String getDrink_age() {
            return drink_age;
        }

        public String getDrink_gender() {
            return drink_gender;
        }

        public String getDrink_keyword() {
            return drink_keyword;
        }

        public String getDrink_name() {
            return drink_name;
        }

        public String getDrink_price() {
            return drink_price;
        }

        public String getDrink_type() {
            return drink_type;
        }
    }

    void add_DrinksData() throws FileNotFoundException {
        try (Scanner DrinksData_input = new Scanner(drinks_File)) {
            String string_temp;
            while (DrinksData_input.hasNext()) {
                string_temp = DrinksData_input.nextLine();
                String part[] = string_temp.split("\t");
                drinks_data.add(new Drink(part[0], part[1], part[2], part[3], part[4], part[5], part[6]));
            }
        }
    }

    void printDrinksData() {
        System.out.print("|-----------------------------------------|\n" + //
                "|  ID\t  Menu\t\t\tPrice\t  |\n" + //
                "|-----------------------------------------|\n" + //
                "");
        Collections.sort(drinks_data,
                (o1, o2) -> Integer.valueOf(o1.getDrink_price()) - Integer.valueOf(o2.getDrink_price()));

        for (Drink element : drinks_data) {
            String id = element.getDrink_ID();
            String name = element.getDrink_name();
            String price = element.getDrink_price();
            System.out.printf("|  %s\t  %-16s\t%s\t  |\n", id, name, price);
        }
        System.out.print("|-----------------------------------------|\n");
    }

    void orderDrink() {
        String runID = order_data.get(order_data.size() - 1).getOrder_id();
        printDrinksData();
        String input_ID = "";
        System.out.print("Enter Menu ID : ");
        input_ID = keyboard_input.next();
        boolean check = false;
        for (Drink element : drinks_data) {
            if (input_ID.equals(element.getDrink_ID())) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.print("--------------------------------------\n" + //
                    "Enter Tel. : ");

            String input_tel = keyboard_input.next();
            String sbTelephone;
            sbTelephone = (input_tel.substring(0, 3) + "-" + input_tel.substring(3, 6) + "-"
                    + input_tel.substring(6));
            input_tel = sbTelephone;
            String pin;
            String NUMBER = "0123467890";
            String CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder(6);
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < 6; i++) {
                if (i < 4) {
                    int randomIndex = random.nextInt(NUMBER.length());
                    char randomChar = NUMBER.charAt(randomIndex);
                    sb.append(randomChar);

                } else {
                    int randomIndex = random.nextInt(CHARACTER.length());
                    char randomChar = CHARACTER.charAt(randomIndex);
                    sb.append(randomChar);
                }
            }
            pin = sb.toString();
            System.out.printf("--------------------------------------\n" + "Your PIN is : %s\n", pin);
            try {
                FileWriter userFileWriter = new FileWriter("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Order.txt",
                        true);
                BufferedWriter bufferedWriter = new BufferedWriter(userFileWriter);
                bufferedWriter.append("\n");
                bufferedWriter.append(Integer.valueOf(runID) + 1 + "\t" + input_ID + "\t-\t" + input_tel + "\t" + pin
                        + "\t0" + "\t-");
                order_data.add(
                        new Order(String.valueOf(Integer.valueOf(runID) + 1), input_ID, "-", input_tel, pin, "0", "-"));
                bufferedWriter.close();
                System.out.println("------------------------");
                System.out.println("Your order has been successfully ordered.");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    void orderPIN_check() throws FileNotFoundException {
        add_DrinksData();
        System.out.print("-------------------\n" + "     PIN Check\n" + "-------------------\n" + "Enter PIN : ");
        String enter_pin = keyboard_input.next();
        boolean printcheck = false;
        boolean validPinCheck = false;
        for (Order element : order_data) {
            if (enter_pin.equals(element.getOrder_pin())) {
                String menu_name = "";
                for (Drink drink_element : drinks_data) {
                    if (drink_element.getDrink_ID().equals(element.getOrder_menu_ID())) {
                        menu_name = drink_element.getDrink_name();
                        break;
                    }
                }
                System.out.println("-------------------");
                System.out.printf("Menu : %s\n", menu_name);
                if (element.getOrder_status().equals("0")) {
                    System.out.println("Status : Not yet used");
                    System.out.println("-------------------");
                    printcheck = true;
                    break;
                }
            } else {
                validPinCheck = true;
            }
        }
        if (!validPinCheck) {
            System.out.println("-------------------\n" + //
                    "Invalid PIN.\n" + //
                    "-------------------");
        }
        if (!printcheck && !validPinCheck) {
            System.out.println("Status : Used");
            System.out.println("-------------------");
        }

    }

    LinkedList<PopularDrink> popularDrinksMen_data = new LinkedList<PopularDrink>();
    LinkedList<PopularDrink> popularDrinksMale_data = new LinkedList<PopularDrink>();
    LinkedList<PopularDrink> popularDrinksAll_data = new LinkedList<PopularDrink>();

    class PopularDrink {
        String id;
        String name;
        int count;

        PopularDrink(String id, String name, int count) {
            this.name = name;
            this.count = count;
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

    void printMostPopularDrink_men() {
        int count = 0;
        for (Drink drink_element : drinks_data) {
            if (drink_element.getDrink_gender().equals("M")) {
                for (Order order_element : order_data) {
                    if (order_element.getOrder_menu_ID().equals(drink_element.getDrink_ID())) {
                        count++;
                    }
                }
                popularDrinksMen_data.add(new PopularDrink(drink_element.getDrink_ID(), drink_element.getDrink_name(), count));
                count=0;
            }
        }
        Collections.sort(popularDrinksMen_data, (o1, o2) -> o2.getCount() - o1.getCount());
        System.out.println("--------------------\n" + //
                "Top 3 For-Men");
        System.out.println("--------------------");
        int loop = 1;
        try {
            for (int index = 0; index < 3; index++) {
                System.out.printf("# %d : %s %s\n", loop++, popularDrinksMen_data.get(index).getId(),
                        popularDrinksMen_data.get(index).getName());
            }
            System.out.println("--------------------");
        } catch (Exception e) {
            System.out.println("No more Popular Drinks");
        }

    }

    void printMostPopularDrink_male() {
        int count = 0;
        for (Drink drink_element : drinks_data) {
            if (drink_element.getDrink_gender().equals("F")) {
                for (Order order_element : order_data) {
                    if (order_element.getOrder_menu_ID().equals(drink_element.getDrink_ID())) {
                        count++;
                    }
                }
                popularDrinksMale_data.add(new PopularDrink(drink_element.getDrink_ID(), drink_element.getDrink_name(), count));
                count=0;
            }
        }
        Collections.sort(popularDrinksMale_data, (o1, o2) -> o2.getCount() - o1.getCount());
        System.out.println("--------------------\n" + //
                "Top 3 For-Women");
        System.out.println("--------------------");
        int loop = 1;
        try {
            for (int index = 0; index < 3; index++) {
                System.out.printf("# %d : %s %s\n", loop++, popularDrinksMale_data.get(index).getId(),
                        popularDrinksMale_data.get(index).getName());
            }
            System.out.println("--------------------");
        } catch (Exception e) {
            System.out.println("No more Popular Drinks");
        }

    }

    void printMostPopularDrink_all() {
        int count = 0;
        for (Drink drink_element : drinks_data) {
            if (drink_element.getDrink_gender().equals("A")) {
                for (Order order_element : order_data) {
                    if (order_element.getOrder_menu_ID().equals(drink_element.getDrink_ID())) {
                        count++;
                    }
                }
                popularDrinksAll_data.add(new PopularDrink(drink_element.getDrink_ID(), drink_element.getDrink_name(), count));
                count=0;
            }
        }

        Collections.sort(popularDrinksAll_data, (o1, o2) -> o2.getCount() - o1.getCount());
        System.out.println("--------------------\n" + //
                "Top 3 For-All");
        System.out.println("--------------------");
        int loop = 1;
        try {
            for (int index = 0; index < 3; index++) {
                System.out.printf("# %d : %s %s\n", loop++, popularDrinksAll_data.get(index).getId(),
                        popularDrinksAll_data.get(index).getName());
            }
        } catch (Exception e) {
            System.out.println("No more Popular Drinks");
        }

        System.out.println("--------------------");
    }

    // ========================================= O R D E R
    // =========================================
    LinkedList<Order> order_data = new LinkedList<Order>();

    class Order {
        String order_id;
        String order_menu_ID;
        String order_machine_ID;
        String order_telephone;
        String order_pin;
        String order_status;
        String order_date;

        Order(String id, String menu_ID, String machine_ID, String tel, String pin, String status, String date) {
            this.order_id = id;
            this.order_menu_ID = menu_ID;
            this.order_machine_ID = machine_ID;
            this.order_telephone = tel;
            this.order_pin = pin;
            this.order_status = status;
            this.order_date = date;
        }

        public String getOrder_date() {
            return order_date;
        }

        public String getOrder_id() {
            return order_id;
        }

        public String getOrder_machine_ID() {
            return order_machine_ID;
        }

        public String getOrder_menu_ID() {
            return order_menu_ID;
        }

        public String getOrder_pin() {
            return order_pin;
        }

        public String getOrder_status() {
            return order_status;
        }

        public String getOrder_telephone() {
            return order_telephone;
        }
    }

    void add_OrderData() throws FileNotFoundException {
        try (Scanner OrderData_input = new Scanner(order_File)) {
            String string_temp;
            while (OrderData_input.hasNext()) {
                string_temp = OrderData_input.nextLine();
                String part[] = string_temp.split("\t");
                order_data.add(new Order(part[0], part[1], part[2], part[3], part[4], part[5], part[6]));
            }
        }
    }

    // ========================================= M E N U
    // =========================================
    void show_menu() {
        System.out.println("------------------------");
        System.out.println("          menu          ");
        System.out.println("------------------------");
        System.out.println("1. Machine details");
        System.out.println("2. Add User");
        System.out.println("3. Edit user");
        System.out.println("4. Exit");
        System.out.println("------------------------");
        System.out.print("Enter number : ");
    }

    void show_sort_menu() {
        System.out.print("-------------------\n" + //
                "        Menu\n" + //
                "-------------------\n" + //
                "1. Sorting by balance (DESC)\n" + //
                "2. Sorting by city (ASC)\n" + //
                "3. Return to main menu\n" + //
                "-------------------\n" + //
                "Enter Number (1-3) : ");
    }

    void editUserMenuPrint() {
        System.out.println("1. Edit user status");
        System.out.println("2. Reset password");
        System.out.println("3. Return to main menu");
        System.out.print("-------------------\nEnter Number (1-3) : ");
    }

    void printEditUserStatus() {
        System.out.println("3.1 Edit user status");
        System.out.println("-------------------");
        System.out.println("1. Active");
        System.out.println("2. Non-active");
        System.out.println("-------------------");
        System.out.print("Enter Number (1-2) : ");
    }

    void printResetUserPassword() {
        System.out.println("3.2 Reset password");
        System.out.println("-------------------\nAre you sure about resetting this password?\n-------------------");
        System.out.print("Enter Character (Y/N) : ");
    }

    void show_loginMenu() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("------------------------");
            System.out.println("         Login       ");
            System.out.println("------------------------");
            System.out.print("Username : ");
            String user_input = keyboard_input.next();
            System.out.print("Password : ");
            String password_input = keyboard_input.next();
            System.out.println("------------------------");
            userPassword_Check(user_input, password_input);
            if (expire_check && password_check) {
                System.out.println("The users account has expired");
                break;
            } else if (password_check && !expire_check) {
                for (User element : user_Data) {
                    if (element.getUser_Email().equals(user_input)) {
                        String[] nameSplit = element.getUser_Name().split(" ");
                        String name = String.join(". ", nameSplit[0].substring(0, 1), nameSplit[1]);
                        String usertelephone = element.getUser_Telephone()
                                .replace(element.getUser_Telephone().substring(8), "xxxx");
                        String email = element.getUser_Email();
                        System.out.printf("Welcome : %s\nEmail : %s\nTel : %s\n", name, email, usertelephone);
                        break;
                    }
                }
                break;
            } else {
                System.out.printf("The username or password is incorrect. (%d)\n", i);
                if (i == 3) {
                    return;
                }
            }
        }
    }

    void show_mainMenu() {
        System.out.print("-------------------\n" + //
                "   SE BUU Drink\n" + //
                "-------------------\n" + //
                "1. Ordering your drink\n" + //
                "2. PIN Check\n" + //
                "3. Most popular drink\n" + //
                "4. Login\n" + //
                "5. Exit\n" + //
                "-------------------\n" + //
                "Enter Number : ");
    }

    void show_populardrinkMenu(){
        System.out.println("--------------------");
        System.out.print("Most popular drink\n" + //
                "-------------------\n" + //
                "1. For-Men\n" + //
                "2. For-Women\n" + //
                "3. For-All\n" + //
                "4. Exit\n" + //
                "--------------------\n");
        System.out.print("Enter Number : ");
    }
    // ========================================= F I L E E D I T O R
    // =========================================
    void fileEditer(String filePath, String oldString, String newString) {

        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line;
                line = reader.readLine();
                if (line != null) {
                    oldContent = oldContent + System.lineSeparator();
                }
            }

            String newContent = oldContent.replaceAll(oldString, newString);

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
