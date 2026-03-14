import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<String[]> load (String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(filePath)
            );
            List<String[]> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null){
                if (!(line.isBlank())){
                    rows.add(line.split(","));
                }
            }
            return rows;
        }
        catch (FileNotFoundException e){
            System.err.println("File with this name is not found");
        }
        return new ArrayList<>();
    }

    public static List<String> extractLabels(List<String[]> rows){
        List<String> labels = new ArrayList<>();
        for (String[] row : rows){
            labels.add(row[row.length-1]);
        }
        return labels;
    }
    public static List<double[]> extractFeatures(List<String[]> rows){
        List<double[]> features = new ArrayList<>();
        for(String[] row : rows){
            double[] featureVector = new double[row.length-1];
            int j = 0;
            for (int i = 0; i < row.length; i++){
                try{
                    featureVector[j] = Double.parseDouble(row[i]);
                    j++;
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            features.add(featureVector);
        }
        return features;
    }


}
