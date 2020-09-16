package MyPackage;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static ArrayList<ToDo> toDos = new ArrayList<>(100000);
    public static ArrayList<Plant> myPlants = new ArrayList<>(100000);
    public static ArrayList<Plant> myPlantsU = new ArrayList<>(100000);
    public static String choice;
    public static String choice1;
    private static Scanner obj6;
    public static String action;
    public static String deadline;
    public static int indexTD;
    public static List<String> plants = new ArrayList<>(100000);
    public static ArrayList<String> myPlants3 = new ArrayList<>(100000);

    public static void overseePlantsLog() {
        showPlants();
       // System.out.println("Plant ID" + "\t" + "|" + " " + "Plant variety" + "\t" + "|" + " " + "Plant location" + "\t" + "|" + " " + "Planting date" + "\t" + "|" + " " + "Action performed" + "\t" + "|" + " " + "Date of action"  + "\t" + "|" + " " + "Action remark");
        for (Plant k : myPlantsU) {
            System.out.println(k.id + "\t" + "|" + " " + k.variety + "\t" + "|" + " " + k.location + "\t" + "|" + " " + k.plantDate + "\t" + "|" + " " + k.actionsPerformed + "\t" + "|" + " " + k.tdCloseDate + "\t" + "|" + " " + k.tdRemark);
        }
    }

    public static void searchPlants() {
        Scanner obj3 = new Scanner(System.in);
        System.out.println("Type the ID of the plant or part of plant name: ");
        String searchPlant = obj3.nextLine();
        searchPlant = searchPlant.toLowerCase();
        showPlants();
        ArrayList<String> myPlantsSearch = new ArrayList<>(100000);
        for (Plant k : myPlantsU) {
            myPlantsSearch.add(k.id + "-" + k.variety + "-" + k.location + "-" + k.plantDate + "-" + k.actionsPerformed + "-" + k.tdDate + "-" + k.tdCloseDate + "-" + k.tdRemark);
        }
        for (String d : myPlantsSearch) {
            String d1 = d.toLowerCase();
            if (d1.contains(searchPlant)) {
                System.out.println(d);
            }
        }
    }

    public static void showToDos() {
        System.out.println("Open ToDos: ");
        try {
            File fileHandle = new File("ToDos.txt");
            Scanner readFromFile = new Scanner(fileHandle);
            while (readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine();
                String line1 = line.replace("[", "");
                String line2 = line1.replace("]", "");
                String[] data = line2.split("-");
                String[] plants2 = data[3].split(",");
                List<String> plants3 = Arrays.asList(plants2);
                ToDo toDo = new ToDo();
                toDo = new ToDo(toDo.action = data[0], toDo.status = true, toDo.deadline = data[2], toDo.plants = plants3);
                if (data[1].equals("true")) {
                    toDos.add(toDo);
                    System.out.println(toDos.indexOf(toDo) + "\t" + toDo.action + "\t" + toDo.deadline + "\t" + toDo.plants);

                }
            }
            readFromFile.close();
            System.out.println("---------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void showPlants() {
        try {
            File fileHandle = new File("myPlants.txt");
            Scanner readFromFile = new Scanner(fileHandle);

            while (readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine();
                String[] data = line.split("-");
                Plant plant = new Plant();
                plant = new Plant(plant.id = data[0], plant.variety = data[1], plant.location = data[2], plant.plantDate = data[3]);
                myPlantsU.add(plant);
            }
            readFromFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void addToDoPt1() {
        obj6 = new Scanner(System.in);
        System.out.println("Enter action to be taken: ");
        action = obj6.nextLine();
        System.out.println("Enter date by which action should be taken: ");
        deadline = obj6.nextLine();
        System.out.println("Enter plants for which action should be taken (enter index, separated by commas): ");
    }

    public static void createPlantsArray() {
        //add plants from file to arraylist
        try {
            File fileHandle = new File("myPlants.txt");
            Scanner readFromFile = new Scanner(fileHandle);

            while (readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine();
                String[] data = line.split("-");
                Plant plant = new Plant();
                plant = new Plant(plant.id = data[0], plant.variety = data[1], plant.location = data[2], plant.plantDate = data[3]);
                myPlants.add(plant);
                myPlants3.add(plant.id);
                System.out.println(myPlants.indexOf(plant) + " " + plant.id + "-" + plant.variety + "-" + plant.location);
            }
            readFromFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void addToDoPt2() {
        Scanner obj2 = new Scanner(System.in);
        String plantsIndex = obj2.nextLine();
        //split user input index string into array
        String[] plants1 = plantsIndex.split(",");
        ArrayList<Integer> plants1Check = new ArrayList<>();
        // add plant names found by index to a List which is a parameter of ToDo
        for (String i : plants1) {
            plants.add(myPlants3.get(Integer.parseInt(i)));
        }

        ToDo toDo1 = new ToDo(action, true, deadline, plants);

        try {
            File fileHandle = new File("ToDos.txt");
            if (fileHandle.createNewFile()) {
                System.out.println("File created: " + fileHandle.getAbsoluteFile());
            } else {
                System.out.println(fileHandle.getAbsoluteFile() + " file already exists.");
            }
            System.out.println("ToDo added successfully!");
            FileWriter writeToFile = new FileWriter(fileHandle, true);
            writeToFile.write(toDo1.action + "-" + toDo1.status + "-" + toDo1.deadline + "-" + toDo1.plants + System.lineSeparator());
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void userInput(){
        Scanner obj4 = new Scanner(System.in);
        System.out.println("Enter the index of ToDo you want to close: ");
        indexTD = Integer.parseInt(obj4.nextLine());
}
    public static void closeToDo(){
         while(true) {
             try {
                 userInput();
                 break;
             } catch (NumberFormatException ex) {
                 System.out.println("Not a valid input, press 'Enter'to try again");
                 Scanner obj = new Scanner(System.in);
                 System.out.println("press 'x' to exit the program");
                 String exit = obj.nextLine();
                 if (exit.equals("x")){
                     exit(0);
                 }
             }
         }
            for (ToDo i : toDos){
                if (toDos.indexOf(i) == indexTD){
                    i.CloseStatus();
                    Scanner obj5 = new Scanner(System.in);
                    System.out.println("Enter date when action was completed: ");
                    i.closeDate = obj5.nextLine();
                    System.out.println("Enter a remark: ");
                    i.remark = obj5.nextLine();
                    //update ToDo file
                    try {
                        //Instantiating the File class
                        String filePath = "ToDos.txt";
                        //Instantiating the Scanner class to read the file
                        Scanner sc = new Scanner(new File(filePath));
                        //instantiating the StringBuffer class
                        StringBuffer buffer = new StringBuffer();
                        //Reading lines of the file and appending them to StringBuffer
                        while (sc.hasNextLine()) {
                            buffer.append(sc.nextLine()+System.lineSeparator());
                        }
                        String fileContents = buffer.toString();
                        //closing the Scanner object
                        sc.close();
                        String oldLine = i.action+"-"+"true"+"-"+ i.deadline;
                        // System.out.println(oldLine);
                        String newLine = i.action+"-"+"false"+"-"+i.deadline+"-"+i.closeDate+"-"+i.remark;
                        //Replacing the old line with new line
                        fileContents = fileContents.replaceAll(oldLine, newLine);
                        //instantiating the FileWriter class
                        FileWriter writer = new FileWriter(filePath);
                        writer.append(fileContents);
                        writer.flush();
                    } catch (IOException e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    //update Plants file
                    String closedPlants = String.join(",", i.plants);
                    String[] closedPlants3 = closedPlants.split(",");
                    showPlants();
                    for (int s = 0; s <closedPlants3.length; s++) {
                        closedPlants3[s] = closedPlants3[s].replaceAll(" ","");
                        for (Plant k: myPlantsU){
                            if (closedPlants3[s].equals(k.id)) {
                    //update myPlants file
                                try {
                                    String filePath = "myPlants.txt";
                                    Scanner sc = new Scanner(new File(filePath));
                                    StringBuffer buffer = new StringBuffer();
                                    while (sc.hasNextLine()) {
                                        buffer.append(sc.nextLine()+System.lineSeparator());
                                    }
                                    String fileContents = buffer.toString();
                                    sc.close();
                                    String oldLine = k.id+"-"+k.variety+"-"+ k.location + "-" + k.plantDate;
                                    k.actionsPerformed = i.action;
                                    k.tdDate=i.deadline;
                                    k.tdRemark=i.remark;
                                    k.tdCloseDate = i.closeDate;
                                    String newLine = k.id+"-"+k.variety+"-"+ k.location + "-" + k.plantDate + "-"+ k.actionsPerformed + "-" + k.tdDate+"-"+k.tdCloseDate+"-"+k.tdRemark;
                                    fileContents = fileContents.replaceAll(oldLine, newLine);
                                    FileWriter writer = new FileWriter(filePath);
                                    writer.append(fileContents);
                                    writer.flush();

                                } catch (IOException e) {
                                    System.out.println("An error occurred: " + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("ToDo status updated successfully!");
    }
    public static void menu(){
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Welcome to Garden Manager!");
        System.out.println("------------------------------------------");
        Scanner obj = new Scanner(System.in);
        System.out.println("To enter a new plant, type '1';" + System.lineSeparator() + "to enter a new ToDo, type '2';" + System.lineSeparator() + "to manage open ToDos, type '3';" + System.lineSeparator() + "to manage plants log, type '4';" + System.lineSeparator() + "to exit, type '5': ");
        System.out.println("------------------------------------------");
        choice = obj.nextLine();

    }
    public static void addPlant(){
            Scanner obj1 = new Scanner(System.in);
            System.out.println("Enter plant ID: ");
            String id = obj1.nextLine();
            System.out.println("Enter plant variety: ");
            String variety = obj1.nextLine();
            System.out.println("Enter plant location: ");
            String location = obj1.nextLine();
            System.out.println("Enter plant planting date: ");
            String plantDate = obj1.nextLine();
            Plant plant = new Plant(id, variety, location, plantDate);
            //adding new plant to file
            try {
                File fileHandle = new File("myPlants.txt");
                if (fileHandle.createNewFile()) {
                    System.out.println("File created: " + fileHandle.getAbsoluteFile());
                } else {
                    System.out.println(fileHandle.getAbsoluteFile() + " file already exists.");
                }
                System.out.println("Plant added successfully!");
                FileWriter writeToFile = new FileWriter(fileHandle, true);
                writeToFile.write(plant.id + "-" + plant.variety + "-" + plant.location+ "-" + plant.plantDate+ "-" +System.lineSeparator());
                writeToFile.close();

            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

    public static void main(String[] args) {
        menu();
        if (choice.equals("1")) {
            do {
            addPlant();
            Scanner obj2 = new Scanner(System.in);
            System.out.println("To enter a new plant, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
            choice1 = obj2.nextLine();
                }
            while (choice1.equals("1"));

            if (choice1.equals("2")){
                menu();
            }
        }
        if (choice.equals("2")) {
            addToDoPt1();
            createPlantsArray();
            addToDoPt2();
            Scanner obj2 = new Scanner(System.in);
            System.out.println("To enter a new ToDo, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
            choice1 = obj2.nextLine();
            if (choice1.equals("2")) {
                menu();
            }
            do {
                addToDoPt1();
                addToDoPt2();
                obj2 = new Scanner(System.in);
                System.out.println("To enter a new ToDo, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
                choice1 = obj2.nextLine();
        }
            while (choice1.equals("1"));
            if (choice1.equals("2")) {
                menu();
            }
        }

        if (choice.equals("3")) {
            showToDos();
            Scanner obj1 = new Scanner(System.in);
            System.out.println("To close a ToDo, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
            choice1 = obj1.nextLine();

            if (choice1.equals("2")){
                menu();
            }
            do {
            closeToDo();
            Scanner obj2 = new Scanner(System.in);
            System.out.println("To close a ToDo, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
            choice1 = obj2.nextLine();
            }
            while (choice1.equals("1"));
            if (choice1.equals("2")){
                menu();
            }
        }
        if (choice.equals("4")) {
            Scanner obj2 = new Scanner(System.in);
            System.out.println("To overview all plants, type '1';" + System.lineSeparator() + "to search log, type '2';" + System.lineSeparator() + "to return to menu, type '3': ");
            String choice1 = obj2.nextLine();
            if (choice1.equals("1")){
                overseePlantsLog();
                Scanner obj3 = new Scanner(System.in);
                System.out.println("To search log, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
                String choice2 = obj3.nextLine();
                if (choice2.equals("1")){
                    searchPlants();
                }
                if (choice2.equals("2")){
                    menu();
                }
            }
            if (choice1.equals("2")){
                searchPlants();
                Scanner obj3 = new Scanner(System.in);
                System.out.println("To do another search, type '1';" + System.lineSeparator() + "to return to menu, type '2': ");
                String choice2 = obj3.nextLine();
                if (choice2.equals("1")){
                    searchPlants();
                }
                if (choice2.equals("2")){
                    menu();
                }
            }
            if (choice1.equals("3")){
                menu();
            }
            }
        if (choice.equals("5")){
            exit(0);
        }
        }
    }


    class Plant{
        public String id;
        public String variety;
        public String location;
        public String plantDate;
        public String actionsPerformed;
        public String tdDate;
        public String tdCloseDate;
        public String tdRemark;

        public Plant(String id, String variety, String location, String plantDate) {
            this.id = id;
            this.variety = variety;
            this.location = location;
            this.plantDate = plantDate;
        }

        public Plant() {
        }
    }

    class ToDo{
        public static String action1;
        public static boolean status1;
        public static String deadline1;
        public static List<String> plants1;
        public String action;
        public boolean status;
        public String deadline;
        public List<String> plants;
        public String remark;
        public String closeDate;

        public ToDo(String action, boolean status, String deadline, List<String> plants) {
            action1 = action;
            status1 = status;
            deadline1 = deadline;
            plants1 = plants;
            this.action = action;
            this.status = status;
            this.deadline = deadline;
            this.plants = plants;
        }

        public ToDo() {
        }

        public ToDo(String closeDate, String remark) {
            this.remark = remark;
            this.closeDate = closeDate;
        }


        public void CloseStatus(){
            this.status = false;

        }
    }


