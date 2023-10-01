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
    Scanner keyboard_input = new Scanner(System.in);
    Integer menu_input;
    // ========================================= M A C H I N E
    // =========================================//
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

    void addMachine_Data(File filepath) throws FileNotFoundException {
        try (Scanner machineData_input = new Scanner(filepath)) {
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
        Collections.sort(machine_Data, (o1, o2) -> o1.getMachine_Balance() - o2.getMachine_Balance());
        
        printMachineData();
    }

    void printMachineData_SortCity() {
        Collections.sort(machine_Data, (o1, o2) -> (o1.getMachine_City().compareTo(o2.getMachine_City())));
        
        printMachineData();
    }

    // ========================================= U S E R
    // =========================================//
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

    void addUser_Data(File filepath) throws FileNotFoundException {
        try (Scanner userData_input = new Scanner(filepath)) {
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

        File userData = new File("C:\\Code\\PSP T_Apisit\\Obtimized_SeDrink\\Login.txt");
        Scanner userData_input = new Scanner(userData);

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
                        "C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(userFileWriter);
                bufferedWriter.append("\n");
                bufferedWriter.append(
                        id + "\t" + first_name + " " + last_name + "\t" + email + "\t" + password + "\t" + telephonee);
                bufferedWriter.close();
                System.out.println("------------------------");
                System.out.print("The user added sucessfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    void editUser() throws FileNotFoundException {
        int menu_input;
        System.out.println("-------------------");
        System.out.print("Enter member ID : ");
        String id_input = keyboard_input.next();
        String filePath = "C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt";

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
                        System.out.printf("This password has been successfully reset. =>  New password is \"%s\"", sb);
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
            if (element.getUser_Email().equals(user) && password.equals(temp_password) && element.getUser_expiredDate().equals("1")) {
                password_check = true;
                expire_check = false;
            }
            else if (element.getUser_Email().equals(user) && password.equals(temp_password) && element.getUser_expiredDate().equals("0")) {
                password_check = true;
                expire_check = true;
            }
        }
    }

    // ========================================= M E N U
    // =========================================//
    void show_menu() {
        System.out.println();
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
            }
            else if (password_check && !expire_check) {
                for (User element : user_Data) {
                    if (element.getUser_Email().equals(user_input)) {
                        String[] nameSplit = element.getUser_Name().split(" ");
                        String name = String.join(". ", nameSplit[0].substring(0, 1), nameSplit[1]);
                        String usertelephone = element.getUser_Telephone().replace(element.getUser_Telephone().substring(8),"xxxx");
                        String email = element.getUser_Email();
                        System.out.printf("Welcome : %s\nEmail : %s\nTel : %s\n", name, email, usertelephone);
                        break;
                    }
                }
                break;
            }
            else {
                System.out.printf("The username or password is incorrect. (%d)\n",i);
                if (i==3) {
                    return;
                }
            }
        }
    }

    // ========================================= F I L E E D I T O R
    // =========================================//
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
