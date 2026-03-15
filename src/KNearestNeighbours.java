import java.util.List;

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
            throw new IllegalArgumentException("Length of two cetors should be the same");
        }
        double result = 0.0;
        for ( int i = 0; i < x.length; i++){
            result += Math.pow((x[i]-y[i]), 2);
        }
        result = Math.sqrt(result);
        return result;
    }
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
}
