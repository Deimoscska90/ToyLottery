import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyLottery {
    private static final String FILE_NAME = "result.txt";
    private static final int NUM_GET_OPERATIONS = 10;

    private PriorityQueue<Toy> toyQueue;

    private class Toy implements Comparable<Toy> {
        String id;
        String name;
        int weight;

        public Toy(String id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        @Override
        public int compareTo(Toy other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public ToyLottery(String[] ids, String[] names, int[] weights) {
        toyQueue = new PriorityQueue<>();
        for (int i = 0; i < ids.length; i++) {
            Toy toy = new Toy(ids[i], names[i], weights[i]);
            toyQueue.add(toy);
        }
    }

    public void writeResultToFile() {
        try {
            File file = new File(FILE_NAME);
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < NUM_GET_OPERATIONS; i++) {
                Toy toy = toyQueue.poll();
                if (toy != null) {
                    writer.write(toy.id + " " + toy.name + "\n");
                }
            }

            writer.close();
            System.out.println("Result written to file " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] ids = {"1", "2", "3"};
        String[] names = {"Lego", "Doll", "Ball"};
        int[] weights = {3, 2, 1};

        ToyLottery toyLottery = new ToyLottery(ids, names, weights);
        toyLottery.writeResultToFile();
    }
}