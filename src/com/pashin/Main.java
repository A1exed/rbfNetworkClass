package com.pashin;

import com.pashin.network.NeuralNetwork;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        0 - Iris Setosa
//        1 - Iris Versicolour
//        2 - Iris Virginica
        Dataset dataset = new Dataset(new File("src/com/pashin/resources/iris.data"), 4);
        NeuralNetwork network = new NeuralNetwork(dataset, 4, 4, 3, 1);
        network.train();
//        Data data = new Data();
//        ArrayList<Double> params = new ArrayList<>();
//        params.add(1 / 5.2);
//        params.add(1 / 3.1);
//        params.add(1 / 1.8);
//        params.add(1 / 2.7);
//        data.setParams(params);
//        network.classify(data);
    }
}
