import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int count = 0;
        do {
            Scanner kb_login = new Scanner(System.in);
            System.out.println("-----------------------");
            System.out.println("         Login");
            System.out.println("-----------------------");
            System.out.print("Username : ");
            String input_user = kb_login.nextLine();
            System.out.print("Password : ");
            String input_pass = kb_login.nextLine();
            System.out.println("-----------------------");
            Scanner file_login = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Login.txt"));
            while (file_login.hasNextLine()){
                String[] arr_line = new String[6];
                String id = file_login.next();
                arr_line[0] = id;

                String firstname = file_login.next();
                firstname = firstname.substring(0,1)+".";
                arr_line[1] = firstname;

                String lastname = file_login.next();
                arr_line[2] = lastname;

                String email = file_login.next();
                arr_line[3] = email;

                String full_passsword = file_login.next();
                arr_line[4] = full_passsword;
                String password = full_passsword.substring(3,5);
                password += full_passsword.substring(11,15);

                String tel = file_login.nextLine();
                tel = tel.substring(1,9)+"xxxx";
                arr_line[5] = tel;

                if (input_user.equals(email)&&input_pass.equals(password)){
                    if (full_passsword.substring(8,9).equals("1")){
                        //แสดง menu
                        Scanner kb_menu = new Scanner(System.in);
                        int input_num ;
                        do {
                            System.out.println("Welcome : "+firstname+" "+lastname);
                            System.out.println("Email : "+email);
                            System.out.println("Tel. : "+tel);
                            System.out.println("-------------------");
                            System.out.println("        Menu");
                            System.out.println("-------------------");
                            System.out.println("1. Machine details\n" +
                                    "2. Member details\n"+
                                    "3. Add member\n"+
                                    "4. Edit member\n"+
                                    "5. Exit");
                            System.out.println("-------------------");
                            System.out.print("Enter Number : ");
                            input_num = kb_menu.nextInt();
                            System.out.println("-------------------");
                            if (input_num==1){
                                show_machine();
                            }else if (input_num==2){
                                Scanner kb_member = new Scanner(System.in);
                                System.out.print("-------------------\n"+
                                        "        Menu\n"+
                                        "-------------------\n"+
                                        "1. Sorting by ID (DESC)\n"+
                                        "2. Sorting by name (ASC)\n"+
                                        "3. Return to main menu\n"+
                                        "-------------------\n"+
                                        "Enter Number (1-3) :");
                                int num_sort = kb_member.nextInt();
                                if (num_sort!=3){
                                    sort_member(num_sort);
                                }
                                continue;
                            }else if (input_num==3){
                                Scanner kb_add = new Scanner(System.in);
                                System.out.print("Enter First name : ");
                                String firstname_add = kb_add.nextLine();
                                if (firstname_add.length()<2){
                                    System.out.println("Please enter first name more than 2 digits.");
                                    continue;
                                }
                                System.out.print("Enter Last name : ");
                                String lastname_add = kb_add.nextLine();
                                if (lastname_add.length()<2){
                                    System.out.println("Please enter last name more than 2 digits.");
                                    continue;
                                }
                                System.out.print("Enter Telephone number : ");
                                String telephone_add = kb_add.nextLine();
                                if (telephone_add.length()!=10){
                                    System.out.println("Please Enter a valid 10 digits telephone number.");
                                    continue;
                                }else {
                                    System.out.println("The member added successfully!!!");
                                    File file_member = new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt");
                                    FileWriter addfile_member = new FileWriter(file_member,true);
                                    BufferedWriter add_info = new BufferedWriter(addfile_member);
                                    String[] arr = new String[4];

                                    arr[0] = id_newMember()+"\t";
                                    arr[1] = firstname_add+" "+lastname_add+"\t";
                                    arr[2] = telephone_add.substring(0,3)+"-"+telephone_add.substring(3,6)+"-"+telephone_add.substring(6,10)+"\t";
                                    Date date = new Date();
                                    SimpleDateFormat format_date = new SimpleDateFormat("yyyy.MM.dd");
                                    String today = format_date.format(date);
                                    arr[3] = today+"\t";
                                    add_info.newLine();
                                    add_info.write(arr[0]);
                                    add_info.write(arr[1]);
                                    add_info.write(arr[2]);
                                    add_info.write(arr[3]);
                                    add_info.close();
                                }
                            }else if (input_num==4){
                                Scanner kb_edit = new Scanner(System.in);
                                show_member();
                                System.out.print("Enter member ID : ");
                                String id_edit = kb_edit.nextLine();
                                if (!findID(id_edit)){
                                    System.out.println("Invalid Member ID.!!!!");
                                    continue;
                                }
                                System.out.print("Enter First name : ");
                                String firstname_edit = kb_edit.nextLine();
                                if (firstname_edit.length()<2){
                                    System.out.println("Please enter first name more than 2 digits.");
                                    continue;
                                }
                                System.out.print("Enter Last name : ");
                                String lastname_edit = kb_edit.nextLine();
                                if (lastname_edit.length()<2){
                                    System.out.println("Please enter last name more than 2 digits.");
                                    continue;
                                }
                                System.out.print("Enter Telephone number : ");
                                String telephone_edit = kb_edit.nextLine();
                                if (telephone_edit.length()!=10){
                                    System.out.println("Please Enter a valid 10 digits telephone number.");
                                    continue;
                                }else {
                                    System.out.println("The member added successfully!!!");
                                    File file = new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt");
                                    Scanner file_member = new Scanner(file);
                                    ArrayList keep = new ArrayList<>();
                                    String[] arr_edit = new String[4];
                                    arr_edit[0] = id_edit;
                                    arr_edit[1] = firstname_edit+" "+lastname_edit;
                                    arr_edit[2] = telephone_edit.substring(0,3)+"-"+telephone_edit.substring(3,6)+"-"+telephone_edit.substring(6,10);
                                    Date date_edit = new Date();
                                    SimpleDateFormat format_date = new SimpleDateFormat("yyyy.MM.dd");
                                    String today = format_date.format(date_edit);
                                    arr_edit[3] = today;

                                    while (file_member.hasNextLine()){
                                        String info = file_member.nextLine();
                                        String[] arr = info.split("\t");
                                        String id_member = arr[0];
                                        String new_info;
                                        if (id_member.equals(id_edit)){
                                            new_info = arr_edit[0]+"\t"+arr_edit[1]+"\t"+arr_edit[2]+"\t"+arr_edit[3]+"\t\n";
                                        }else {
                                            new_info = arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t\n";
                                        }
                                        keep.add(new_info);

                                    }

                                    FileWriter editfile_member = new FileWriter("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt");
                                    BufferedWriter edit_info = new BufferedWriter(editfile_member);

                                    for (int i = 0; i < keep.size(); i++) {
                                        edit_info.write(String.valueOf(keep.get(i)));
                                    }
                                    edit_info.close();
                                    continue;
                                }
                            }

                        }while (input_num!=5);
                        System.out.println("Thank you for using the service.");
                        return;
                    }else {
                        System.out.println("The users account has expired");
                        return;
                    }
                }
            }
            count++;
            System.out.println("The username or password is incorrect. ("+count+")");
        }while (count!=3);
        System.out.println("Thank you for using the service.");
    }

    public static void show_machine() throws IOException{
        Scanner file_machine = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Machine.txt"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("ID \t\t\t\tCity\t\t\t\t Position\t\t\t\t\t\t\t\t  Account Number\t\t\tBalance\t");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        while (file_machine.hasNextLine()){
            String info = file_machine.nextLine();
            String[] arr = info.split("\t");
            String id_machine = arr[0];
            String city = arr[1];
            String posi = arr[2];
            String acc = arr[3];
            String balance = arr[4];
            String id_member = arr[5];

            System.out.printf("%-15s %-20s %-40s %-25s %-10s",id_machine,city,posi,acc,balance);
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

    public static void show_member() throws IOException{
        Scanner file_member = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt"));
        String id_member = "",name_member,tel_member,date,year,month,day;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("ID\t  Name\t\t\t\t\t Tel.\t\t\t Application Date");
        System.out.println("-------------------------------------------------------------------");
        while (file_member.hasNextLine()){
            String info = file_member.nextLine();
            String[] arr = info.split("\t");
            id_member = arr[0];
            name_member = arr[1];
            tel_member = arr[2];
            date = arr[3];
            year = date.substring(0,4);
            month = date.substring(5,7);
            day = date.substring(8);

            System.out.printf("%-5s %-15s\t\t %-15s %s.%s.%s",id_member,name_member,tel_member,year,month,day);
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public static void sort_member(int num) throws IOException{
            ArrayList<String[]> box = new ArrayList<>();
            Scanner file_member = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt"));
            while (file_member.hasNextLine()){
                String info = file_member.nextLine();
                String[] arr = info.split("\t");
                box.add(arr);
            }
            String[] keep;
            int i,j;
            for (i = 0; i < box.size(); i++) {
                for (j = box.size()-1; j > i; j--) {
                    if (Integer.parseInt(box.get(j)[0])>Integer.parseInt(box.get(j-1)[0])){
                        keep = box.get(j);
                        box.set(j, box.get(j-1));
                        box.set(j-1,keep);
                    }
                }
            }
            System.out.println("-------------------------------------------------------------------");
            System.out.println("ID\t  Name\t\t\t\t\t Tel.\t\t\t Application Date");
            System.out.println("-------------------------------------------------------------------");
            for (int k = 0; k < box.size(); k++) {
                System.out.printf("%-5s %-15s\t\t %-15s %s\n",box.get(k)[0],box.get(k)[1],box.get(k)[2],box.get(k)[3]);
            }
            System.out.println("-------------------------------------------------------------------");
        
    }

    public static String id_newMember() throws FileNotFoundException {
        Scanner file_member = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt"));
        String id_member="";
        while (file_member.hasNextLine()){
            String info = file_member.nextLine();
            String[] arr = info.split("\t");
            id_member = arr[0];
        }
        int id_new = Integer.parseInt(id_member)+1;
        return String.valueOf(id_new);
    }

    public static boolean findID(String id) throws FileNotFoundException{
        Scanner file_member = new Scanner(new File("D:\\Pure\\T.Sit\\SE_Drink_5\\src\\Member.txt"));
        String id_member;
        boolean check = false;
        while (file_member.hasNextLine()){
            String info = file_member.nextLine();
            String[] arr = info.split("\t");
            id_member = arr[0];
            if (id.equals(id_member)){
                check = true;
            }
        }
        return check;
    }
}