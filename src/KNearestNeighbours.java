import java.util.*;

public class KNearestNeighbours {
    private int k;
    private List<double[]> trainFeatures;
    private List<String> trainLabels;
    KNearestNeighbours(int k, List<double[]> trainFeatures, List<String> trainLabels){
    this.k = k;
    this.trainFeatures = trainFeatures;
    this.trainLabels = trainLabels;

    }
    public double calculateEuclideanDistance (double[] x, double[] y){
        if (x.length != y.length){
            throw new IllegalArgumentException("Length of two vectors should be the same");
        }
        double result = 0.0;
        for ( int i = 0; i < x.length; i++){
            result += Math.pow((x[i]-y[i]), 2);
        }
        result = Math.sqrt(result);
        return result;
    }
    //Returns indices of the distances array sorted in ascending order (insertion sort)
    public int[] sortDistances(double[] distances){
        int[] indices = new int[distances.length];
        for (int i = 0; i < distances.length; i++) {
            indices[i] = i;
        }
        for (int i = 1; i < distances.length; i++) {
            int current = indices[i];
            int j = i - 1;
            while (j >= 0 && distances[indices[j]] > distances[current]) {
                indices[j + 1] = indices[j];
                j--;
            }
            indices[j + 1] = current;
        }
        return indices;
    }
    // Returns the majority label among k nearest neighbours; breaks ties randomly
    private String findPredictedClass(String[] kNearestLabels){
        HashMap<String, Integer> labelCounts = new HashMap<>();
        for (String label : kNearestLabels){
            if (labelCounts.containsKey(label)){
                labelCounts.put(label, labelCounts.get(label) + 1);
            }
            else{
                labelCounts.put(label, 1);
            }
        }
        int maxCount = 0;
        for (int value : labelCounts.values()){
            if (value > maxCount){
                maxCount = value;
            }
        }
        List<String> bestLabels = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : labelCounts.entrySet()){
            if (entry.getValue() == maxCount){
                bestLabels.add(entry.getKey());
            }
        }
        if (bestLabels.size() == 1 ){
            return bestLabels.get(0);
        }
        else{
            Random random = new Random();
            return bestLabels.get(random.nextInt(bestLabels.size()));
        }

    }
    // Predicts the class of a single observation using KNN voting
    public String predict(double[] observation){
         double[] distances = new double[trainFeatures.size()];
         for (int i = 0; i < trainFeatures.size(); i++){
             distances[i] = calculateEuclideanDistance(observation, trainFeatures.get(i));
         }
         int [] sortedIndices = sortDistances(distances);
         String [] kNearestLabels = new String [k];
         for (int i = 0; i<k; i++){
             kNearestLabels[i] = trainLabels.get(sortedIndices[i]);
         }
         return findPredictedClass(kNearestLabels);
    }
}
