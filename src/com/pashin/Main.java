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
        NeuralNetwork network = new NeuralNetwork(4, 100, 3);
        network.train(dataset, 1000, 0.1);
        Data data = new Data();
        ArrayList<Double> params = new ArrayList<>();
        // Первый из датасета
        params.add(5.1 / 7.9);
        params.add(3.5 / 7.9);
        params.add(1.4 / 7.9);
        params.add(0.2 / 7.9);
        data.setParams(params);
        network.classify(data);
    }
}
