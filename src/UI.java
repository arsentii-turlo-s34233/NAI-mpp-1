import java.util.Scanner;

public class UI {
    public static void run(KNearestNeighbours model, int featureCount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write into one line " + featureCount + " comma-separated values of double type (e.g. 3.14, 1.23...)");
        System.out.println("To finish program just type 'exit'");
        while(true){
            String line = sc.nextLine();
            if (line.equals("exit")){
                break;
            }
            else{
                String[] userFeatures = line.split(",");
                double[] userDoubleFeatures = new double[userFeatures.length];
                for (int i = 0; i < userFeatures.length; i++) {
                    userDoubleFeatures[i] = Double.parseDouble(userFeatures[i]);
                }
                System.out.println(model.predict(userDoubleFeatures));
            }
        }
    }
}
