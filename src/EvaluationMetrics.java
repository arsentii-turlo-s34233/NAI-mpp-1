import java.util.List;

public class EvaluationMetrics {
    // Returns accuracy as a ratio of correct predictions to total predictions
    public static double measureAccuracy(List<String> realClasses, List<String> predictedClasses){
        int matches = 0;
        for (int i = 0; i < realClasses.size(); i++){
            if(realClasses.get(i).equals(predictedClasses.get(i))){
                matches++;
            }
        }
        return (double) matches / realClasses.size();
    }
}
