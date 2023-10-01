package Obtimized_SeDrink;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class A_Machine extends A_Function {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard_input = new Scanner(System.in);
        int menu_input;
        A_Function machine = new A_Function();
        machine.addMachine_Data();
        machine.addUser_Data();
        machine.add_DrinksData();
        machine.add_OrderData();
        do {
            machine.show_mainMenu();
            menu_input = keyboard_input.nextInt();
            if (menu_input==1) {
                machine.orderDrink();
            }else if (menu_input==2) {
                machine.orderPIN_check();
            }else if (menu_input==3) {
                machine.show_loginMenu();
                do {
                    machine.show_menu();
                    menu_input = keyboard_input.nextInt();
                    if (menu_input == 1) {
                        machine.call_machineFunction();
                    } else if (menu_input == 2) {
                        machine.addMore_user();;
                        machine.addUser_Data();
                    } else if (menu_input == 3) {
                        machine.editUser();
                    }
                } while (menu_input != 4);
            }
        } while (menu_input!=4);
        keyboard_input.close();
    }
}
