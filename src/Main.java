import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        String trainFilePath = args[1];
        String testFilePath = args[2];
        List<String[]> trainRows = DataLoader.load(trainFilePath);
        List<String[]> testRows = DataLoader.load(testFilePath);
        List<double[]> trainFeatures = DataLoader.extractFeatures(trainRows);
        List<String> trainLabels = DataLoader.extractLabels(trainRows);
        List<double[]> testFeatures = DataLoader.extractFeatures(testRows);
        List<String> testLabels = DataLoader.extractLabels(testRows);
        KNearestNeighbours model = new KNearestNeighbours(k, trainFeatures, trainLabels);
        List<String> predictedLabels = new ArrayList<>();
        for (double[] testVector : testFeatures) {
            predictedLabels.add(model.predict(testVector));
        }
        double accuracy = EvaluationMetrics.measureAccuracy(testLabels, predictedLabels);
        System.out.println("Accuracy: " + accuracy);
        UI.run(model, testFeatures.get(0).length);
    }
}