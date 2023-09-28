package New_SEDrinks_copy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class Machine_Functions {
    Scanner keyboard_input = new Scanner(System.in);
    LinkedList<String[]> userPassword_data = new LinkedList<>();
    LinkedList<String[]> machine_data = new LinkedList<>();
    LinkedList<String> name = new LinkedList<>();
    LinkedList<String> usertelephone = new LinkedList<>();
    LinkedList<String> user = new LinkedList<>();
    LinkedList<String> password = new LinkedList<>();
    LinkedList<String> expiredate = new LinkedList<>();
    String[] name_split;
    String full_password, real_password, expire_date;
    boolean userPassword_Check = false;
    boolean expire_check = true;
    int user_position = 0;
    int attemp = 0;

    LinkedList<Machine> new_MachineData = new LinkedList<Machine>();
    LinkedList<Drinks> new_drinksData = new LinkedList<Drinks>();
    
    class Drinks{
        String drink_name;
        int drink_price;

        Drinks(String name,int price){
            this.drink_name = name;
            this.drink_price = price;
        }

        public String getDrink_name() {
            return drink_name;
        }

        public int getDrink_price() {
            return drink_price;
        }
    }

    void add_DrinksData(File datafile) throws FileNotFoundException {
        try (Scanner DrinksData_input = new Scanner(datafile)) {
            //String string_temp;
            while (DrinksData_input.hasNext()) {
                
            }
        }
    }

    void show_drinks_menu(){

    }

    void printDrinksData(){

    }
    
    class Machine{
        String machine_id;
        String machine_city;
        String machine_position;
        String machine_accountnNumber;
        int machine_balance;

        Machine(String id, String city, String position, String accountnumber, int balance) {
            this.machine_id = id;
            this.machine_city = city;
            this.machine_position = position;
            this.machine_accountnNumber = accountnumber;
            this.machine_balance = balance;
        }

        public int getMachine_balance() {
            return machine_balance;
        }

        public String getMachine_accountnNumber() {
            return machine_accountnNumber;
        }

        public String getMachine_city() {
            return machine_city;
        }

        public String getMachine_id() {
            return machine_id;
        }

        public String getMachine_position() {
            return machine_position;
        }
    }

    void add_newMachineData(File datafile) throws FileNotFoundException {
        try (Scanner machineData_input = new Scanner(datafile)) {
            String string_temp;
            while (machineData_input.hasNext()) {
                string_temp = machineData_input.nextLine();
                String[] data = string_temp.split("\t");
                String temp = data[4].substring(1);
                temp = temp.substring(0, 1) + temp.substring(2);
                int tempint = Integer.valueOf(temp);
                new_MachineData.add(new Machine(data[0], data[1], data[2], data[3], tempint));
            }
        }
    }

    void printMachineData_sortBlance() {
        Collections.sort(new_MachineData, (o1, o2) -> o1.getMachine_balance() - o2.getMachine_balance());
        Collections.sort(new_MachineData, Collections.reverseOrder());
        show_info();
        String shippingType = "";
        for (Machine element : new_MachineData) {
            String latitude_position, longtitude_position;
            {// position split
                String tempMachine_position = element.getMachine_position();
                String[] position_split = tempMachine_position.split(" ");
                latitude_position = position_split[0].substring(0, 10);
                longtitude_position = position_split[1].substring(0, 10);
            }
            String accountNumber = element.getMachine_accountnNumber();
            String balance = String.valueOf(element.getMachine_balance());
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
            System.out.printf("%-6s\t%-15s\t%.2f, %.2f\t\t%s\t\t\t%s\t\t%s\n", element.getMachine_id(),
                    element.getMachine_city(),
                    Double.valueOf(latitude_position), Double.valueOf(longtitude_position), shippingType,
                    accountNumber.replace(accountNumber.substring(12), "xxxx"),
                    balance.replace(balance.substring(1), ",xxx"));
        }
    }

    void printMachineData_sortCity() {
        Collections.sort(new_MachineData, (o1, o2) -> (o1.getMachine_city().compareTo(o2.getMachine_city())));
        show_info();
        String shippingType = "";
        for (Machine element : new_MachineData) {
            String latitude_position, longtitude_position;
            {// position split
                String tempMachine_position = element.getMachine_position();
                String[] position_split = tempMachine_position.split(" ");
                latitude_position = position_split[0].substring(0, 10);
                longtitude_position = position_split[1].substring(0, 10);
            }
            String accountNumber = element.getMachine_accountnNumber();
            String balance = String.valueOf(element.getMachine_balance());
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
            System.out.printf("%-6s\t%-15s\t%.2f, %.2f\t\t%s\t\t\t%s\t\t%s\n", element.getMachine_id(),
                    element.getMachine_city(),
                    Double.valueOf(latitude_position), Double.valueOf(longtitude_position), shippingType,
                    accountNumber.replace(accountNumber.substring(12), "xxxx"),
                    balance.replace(balance.substring(1), ",xxx"));
        }
    }

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

    void show_info() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID\t\tCity\t\tPosition\t\tShippingType\t\tAccount Number\t\t\tBalance($)");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
    }

    boolean login_status() {
        return userPassword_Check;
    }

    boolean expire_status() {
        return expire_check;
    }

    void show_loginMenu() {
        format_userData();
        System.out.println("------------------------");
        System.out.println("         Login       ");
        System.out.println("------------------------");
        System.out.print("Username : ");
        String user_input = keyboard_input.next();
        System.out.print("Password : ");
        String password_input = keyboard_input.next();
        System.out.println("------------------------");
        userPassword_Check(user_input, password_input);
        if (expire_check && userPassword_Check) {
            System.out.println("The users account has expired");
        }
        if (userPassword_Check && !expire_check) {
            System.out.printf("Welcome : %s\n", name.get(user_position));
            System.out.printf("Email : %s\n", user.get(user_position));
            System.out.printf("Tel : %s", usertelephone.get(user_position));
            this.userPassword_Check = true;
        }
    }

    void userPassword_Check(String userr, String passwordd) {
        for (int index = 0; index < user.size(); index++) {
            if (userr.equals(user.get(index)) && passwordd.equals(password.get(index))
                    && expiredate.get(index).equals("1")) {
                this.userPassword_Check = true;
                this.expire_check = false;
                this.user_position = index;
                break;
            } else if (userr.equals(user.get(index)) && passwordd.equals(password.get(index))) {
                this.userPassword_Check = true;
                break;
            } else if (userr.equals(user.get(index)) && passwordd.equals(password.get(index))
                    && expiredate.get(index).equals("0")) {
                this.userPassword_Check = true;
                this.expire_check = true;
            }
        }
    }

    void addMachineData(File datafile) throws FileNotFoundException {
        try (Scanner machineData_input = new Scanner(datafile)) {
            String string_temp;
            while (machineData_input.hasNext()) {
                string_temp = machineData_input.nextLine();
                String[] data = string_temp.split("\t");
                machine_data.add(data);
            }
        }
    }

    void addUserData(File datafile) throws FileNotFoundException {
        Scanner userPassword_input = new Scanner(datafile);
        String string_temp;
        while (userPassword_input.hasNext()) {
            string_temp = userPassword_input.nextLine();
            String[] data = string_temp.split("\t");
            userPassword_data.add(data);
        }
        userPassword_input.close();
    }

    void addMoreUser() throws FileNotFoundException {
        int id = 0;
        System.out.print("Enter Name : ");
        String first_name = keyboard_input.next();
        System.out.print("Enter Last Name : ");
        String last_name = keyboard_input.next();
        System.out.print("Enter Email : ");
        String email = keyboard_input.next();
        System.out.print("Enter Password : ");
        String passwordd = keyboard_input.next();
        System.out.print("Confirm Password : ");
        String confirm_password = keyboard_input.next();
        System.out.print("Telephone number : ");
        String telephonee = keyboard_input.next();
        File line_input_ID = new File("C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt");
        Scanner input_ID = new Scanner(line_input_ID);

        if (first_name.length() >= 2 && last_name.length() >= 2 && passwordd.length() == 6
                && passwordd.equals(confirm_password) && email.contains("@") == true
                && telephonee.length() == 10) {
            // id
            while (input_ID.hasNext()) {
                String line = input_ID.nextLine();
                String[] temp = line.split("\t");
                id = Integer.valueOf(temp[0]);
                id++;
            }
            // tel
            String sbTelephone;
            sbTelephone = (telephonee.substring(0, 3) + "-" + telephonee.substring(3, 6) + "-"
                    + telephonee.substring(6));
            telephonee = sbTelephone;
            // pass
            String CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder(18);
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < 18; i++) {
                if (i == 3 || i == 4 || i == 8 || i == 11 || i == 12 || i == 13 || i == 14) {
                    if (i == 3) {
                        sb.append(passwordd.charAt(0));
                    }
                    if (i == 4) {
                        sb.append(passwordd.charAt(1));
                    }
                    if (i == 11) {
                        sb.append(passwordd.charAt(2));
                    }
                    if (i == 12) {
                        sb.append(passwordd.charAt(3));
                    }
                    if (i == 13) {
                        sb.append(passwordd.charAt(4));
                    }
                    if (i == 14) {
                        sb.append(passwordd.charAt(5));
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

            passwordd = sb.toString();
            try {
                FileWriter userFileWriter = new FileWriter(
                        "C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(userFileWriter);
                bufferedWriter.append("\n");
                bufferedWriter.append(id + "\t" + first_name + " " + last_name + "\t" + email + "\t"
                        + passwordd + "\t" + telephonee);
                bufferedWriter.close();
                System.out.println("------------------------");
                System.out.print("The user added sucessfully");
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        else {
            System.out.println("------------------------");
            System.out.print("The user additonal failed");
        }

        input_ID.close();
    }

    void format_userData() {
        for (String[] data : userPassword_data) {
            name_split = data[1].split(" ");
            name.add(String.join(". ", name_split[0].substring(0, 1), name_split[1]));
            full_password = data[3];
            usertelephone.add(data[4].replace(data[4].substring(8), "xxxx"));
            user.add(data[2]);
            password.add(
                    real_password = String.join("", full_password.substring(3, 5), full_password.substring(11, 15)));
            expiredate.add(expire_date = full_password.substring(8, 9));
        }
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
        System.out.println(
                "-------------------\nAre you sure about resetting this password?\n-------------------");
        System.out.print("Enter Character (Y/N) : ");
    }

    void editUser() throws FileNotFoundException {
        int menu_input;
        System.out.println("-------------------");
        System.out.print("Enter member ID : ");
        String temp_id = keyboard_input.next();
        String filePath = "C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt";
        // correct id
        for (String[] data : userPassword_data) {
            String temp = data[3];
            if (data[0].equals(temp_id)) {
                System.out.println("Valid Member ID.");
                System.out.println("-------------------");
                editUserMenuPrint();
                menu_input = keyboard_input.nextInt();
                System.out.println("-------------------");
                if (menu_input == 1) {
                    printEditUserStatus();
                    menu_input = keyboard_input.nextInt();
                    if (menu_input == 1) {
                        data[3] = data[3].substring(0, 8) + "1" + data[3].substring(9);
                        fileEditer(filePath, temp, data[3]);
                    } else if (menu_input == 2) {
                        data[3] = data[3].substring(0, 8) + "0" + data[3].substring(9);
                        fileEditer(filePath, temp, data[3]);
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
                }

                else if (menu_input == 3) {
                    return;
                }

            }

        }

    }

    void editUserMenuPrint() {
        System.out.println("1. Edit user status");
        System.out.println("2. Reset password");
        System.out.println("3. Return to main menu");
        System.out.print("-------------------\nEnter Number (1-3) : ");
    }

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