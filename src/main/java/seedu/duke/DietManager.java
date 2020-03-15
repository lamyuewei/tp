package seedu.duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DietManager {
    private static Person currentUser;
    private static Parser parser;
    private static Storage storage;
    private static UI ui;
    private static PersonList personList;
    private static FoodNutritionInfo foodNutritionInfo;
    private final static String logo =
            "  _____   _        _     __  __                                         \n"
            + " |  __ \\ (_)      | |   |  \\/  |                                        \n"
            + " | |  | | _   ___ | |_  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
            + " | |  | || | / _ \\| __| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
            + " | |__| || ||  __/| |_  | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
            + " |_____/ |_| \\___| \\__| |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
            + "                                                       __/ |            \n"
            + "                                                      |___/             ";;

    public DietManager(String filePath){
        foodNutritionInfo = new FoodNutritionInfo();
        personList = new PersonList();
        ui = new UI();
        storage = new Storage(filePath);
        parser = new Parser();

        try{
            storage.loadFileData(personList);
        } catch (FileNotFoundException e){
            System.out.println("File not found!!! Already create a new one for you!!!");
        }
    }

    public static void main(String[] args) {
        new DietManager("data/user info.txt").run();
        Scanner sc = new Scanner(System.in);
    }

    public static void welcome(){
        System.out.println(logo);
        System.out.println("Welcome to Diet Manager! How may I assist you today?");
    }

    public static void handleCommands(){
        Command command = parser.getCommand();
        while(!command.isExit()){
            command.execute(personList);
            storage.saveToFile(personList);
            ui.showResult();
            command = parser.getCommand();
        }
    }

    public static void exit(){
        System.out.println("Thank you and see you again soon!");
    }

    public void run(){
        welcome();
        handleCommands();
        exit();
    }
}

