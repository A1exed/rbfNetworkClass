package com.pashin;

import java.io.*;
import java.util.ArrayList;

public class Dataset implements Serializable {

    private ArrayList<Data> data;

    public Dataset() {
    }

    public Dataset(File datasetFile, int numberOfParams) {
        this.data = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(datasetFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] params;
            ArrayList<Double> dataParams;
            Data data;
            String line = bufferedReader.readLine();
            int classification;
            while (line != null) {
                dataParams = new ArrayList<>();
                data = new Data();
                params = line.split(",");
                for (int i = 0; i < numberOfParams; i++) {
                    dataParams.add(Double.parseDouble(params[i]) / 8);
                }
                data.setParams(dataParams);
                classification = switch (params[params.length - 1]) {
                    case "Iris-setosa" -> 0;
                    case "Iris-versicolor" -> 1;
                    case "Iris-virginica" -> 2;
                    default -> 0;
                };
                data.setClassification(classification);
                this.data.add(data);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
