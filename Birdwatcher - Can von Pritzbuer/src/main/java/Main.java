import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BirdDatabase birdDatabase = new BirdDatabase();


        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("Add")) {
                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Name in Latin: ");
                String latinName = scanner.nextLine();

                birdDatabase.addBird(name, latinName);
            }

            if (command.equals("Observation")) {
                System.out.print("Bird? ");
                String birdName = scanner.nextLine();
                birdDatabase.observeBird(birdName);
            }

            if (command.equals("All")) {
                birdDatabase.printAll();
            }

            if (command.equals("One")) {
                System.out.print("Bird? ");
                String search = scanner.nextLine();
                birdDatabase.printOne(search);
            }

            if (command.equals("Delete")) {
                System.out.print("Bird?");
                String birdName = scanner.nextLine();
                boolean deleted = birdDatabase.deleteBird(birdName);
                if (deleted) {
                    System.out.println("Bird deleted.");
                } else {
                    System.out.println("Bird not found.");
                }
            }

            if (command.equals("Quit")) {
                break;
            }
        }

    }


}
