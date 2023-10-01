package Obtimized_SeDrink;

import java.io.File;
import java.io.FileNotFoundException;

public class A_Machine extends A_Function {
    public static void main(String[] args) throws FileNotFoundException {
        File machineFile_data = new File("C:\\Code\\PSP T_Apisit\\New_SEDrinks_copy\\Machine.txt");
        A_Function machine = new A_Function();
        machine.addMachine_Data(machineFile_data);
        machine.printMachineData_SortBalance();
        machine.printMachineData_SortCity();
    }
}
