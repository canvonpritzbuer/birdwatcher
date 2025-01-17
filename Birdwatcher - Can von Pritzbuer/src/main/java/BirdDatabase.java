import java.io.*;
import java.util.ArrayList;

public class BirdDatabase {

    private ArrayList<Bird> birds;
    private final String filePath = "src/main/resources/birds.txt";

    public BirdDatabase() {
        this.birds = new ArrayList<>();
        loadFromFile();
    }

    public void addBird(String name, String latinName) {
        this.birds.add(new Bird(name, latinName));
        saveToFile();
    }

    public boolean observeBird(String name) {
        for (Bird bird : birds) {
            if (bird.getName().equalsIgnoreCase(name)) {
                bird.observe();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public void printAll() {
        for (Bird bird : birds) {
            System.out.println(bird);
        }
    }

    public void printOne(String name) {
        for (Bird bird : birds) {
            if (bird.getName().equalsIgnoreCase(name) || bird.getLatinName().equalsIgnoreCase(name)) {
                System.out.println(bird);
                return;
            }
        }
        System.out.println("Not a bird!");
    }

    public boolean deleteBird(String name) {
        for (Bird bird : birds) {
            if (bird.getName().equalsIgnoreCase(name)) {
                birds.remove(bird);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Bird bird : birds) {
                writer.write(bird.getName() + "," + bird.getLatinName() + "," + bird.getObservations());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String latinName = parts[1];
                    int observations = Integer.parseInt(parts[2]);
                    birds.add(new Bird(name, latinName, observations));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }



}
