package New_SEDrinks_copy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestSE_Drinks extends Machine_Functions {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard_input = new Scanner(System.in);

        File machineFile_data = new File("C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Machine.txt");
        File userFile_data = new File("C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Login.txt");

        Machine_Functions machine = new Machine_Functions();
        machine.addMachineData(machineFile_data);
        machine.addUserData(userFile_data);
        machine.add_newMachineData(machineFile_data);

        int menu_input;

        for (int i = 1; i <= 3; i++) {

            machine.show_loginMenu();

            if (machine.login_status() && !machine.expire_status()) {
                do {
                    machine.show_menu();
                    menu_input = keyboard_input.nextInt();
                    if (menu_input == 1) {
                        machine.show_sort_menu();
                        menu_input = keyboard_input.nextInt();
                        if (menu_input==1) {
                            machine.printMachineData_sortBlance();
                        } else if (menu_input==2) {
                            machine.printMachineData_sortCity();
                        } else if (menu_input==3){
                        }
                    } else if (menu_input == 2) {
                        machine.addMoreUser();
                        machine.addUserData(userFile_data);
                    } else if (menu_input == 3) {
                        machine.editUser();
                    }
                } while (menu_input != 4);
                break;
            }

            if (machine.expire_status() && machine.login_status()) {
                break;
            }

            if (!machine.login_status()) {
                System.out.printf("The username or password is incorrect. (%d)\n", i);
            }

        }
        System.out.println("Thank you for using the service.");

        keyboard_input.close();
    }
}
