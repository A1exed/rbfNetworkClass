package com.pashin;

import com.pashin.network.NeuralNetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        0 - Iris Setosa
//        1 - Iris Versicolour
//        2 - Iris Virginica
        Dataset dataset = new Dataset(new File("src/com/pashin/resources/iris.data"), 4);

        NeuralNetwork network = new NeuralNetwork(4, 5, 3);

        network.train(dataset, 1000, 0.1, 15);

        Data data = new Data();
        ArrayList<Double> params = new ArrayList<>();
        params.add(5.1 / 7.9);
        params.add(3.5 / 7.9);
        params.add(1.4 / 7.9);
        params.add(0.2 / 7.9);
        data.setParams(params);
        network.classify(data);

//        for (Data data : dataset.getData()) {
//            network.classify(data);
//        }

//        Scanner in = new Scanner(System.in);
//        System.out.println("Загрузить сеть? (0 - нет, 1 - да)");
//        int answer = in.nextInt();
//        if (answer == 1) {
//            System.out.println("Введите путь до файла сериализованной сети:");
//            String path = in.next();
//
//            NeuralNetwork network = NeuralNetwork.openNetwork(new File(path));
//
//            Data data = new Data();
//            ArrayList<Double> params = new ArrayList<>();
//            params.add(7.0 / 7.9);
//            params.add(3.2 / 7.9);
//            params.add(4.7 / 7.9);
//            params.add(1.4 / 7.9);
//            data.setParams(params);
//
//            network.classify(data);
//        } else {
//            Dataset dataset = new Dataset(new File("src/com/pashin/resources/iris.data"), 4);
//
//            NeuralNetwork network = new NeuralNetwork(4, 150, 3);
//
//            network.train(dataset, 1000, 0.1);
//
//            Data data = new Data();
//            ArrayList<Double> params = new ArrayList<>();
//            params.add(5.1 / 7.9);
//            params.add(3.5 / 7.9);
//            params.add(1.4 / 7.9);
//            params.add(0.2 / 7.9);
//            data.setParams(params);
//
//            network.classify(data);
//
//            System.out.println("Сохранить сеть? (0 - нет, 1 - да)");
//            answer = in.nextInt();
//            if (answer == 1) {
//                network.saveNetwork();
//            }
//        }
//        in.close();
    }
}
