package com.pashin;

import com.pashin.network.NeuralNetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        0 - Iris Setosa
//        1 - Iris Versicolour
//        2 - Iris Virginica
        Dataset dataset = new Dataset(new File("src/com/pashin/resources/iris.data"), 4);

        NeuralNetwork network = new NeuralNetwork(4, 200, 3);

        network.train(dataset, 0.0007, 20);

        Collections.shuffle(dataset.getData());
        for (Data data : dataset.getData()) {
            System.out.println("---------------------------------------");
            network.classify(data);
            System.out.print("Должно быть: ");
            int c = data.getClassification();
            switch (c) {
                case 0 -> System.out.printf("(%d) Iris Setosa\n", c);
                case 1 -> System.out.printf("(%d) Iris Versicolour\n", c);
                case 2 -> System.out.printf("(%d) Iris Virginica\n", c);
            }
        }

//        Scanner in = new Scanner(System.in);
//        System.out.println("Загрузить сеть? (0 - нет, 1 - да)");
//        int answer = in.nextInt();
//        if (answer == 1) {
//            System.out.println("Введите путь до файла сериализованной сети:");
//            String path = in.next();
//
//            NeuralNetwork network = NeuralNetwork.openNetwork(new File(path));
////
//        Collections.shuffle(dataset.getData());
//        for (Data data : dataset.getData()) {
//            System.out.println("---------------------------------------");
//            network.classify(data);
//            System.out.print("Должно быть: ");
//            int c = data.getClassification();
//            switch (c) {
//                case 0 -> System.out.printf("(%d) Iris Setosa\n", c);
//                case 1 -> System.out.printf("(%d) Iris Versicolour\n", c);
//                case 2 -> System.out.printf("(%d) Iris Virginica\n", c);
//            }
//        }
//        } else {
//            Dataset dataset = new Dataset(new File("src/com/pashin/resources/iris.data"), 4);
//
//            NeuralNetwork network = new NeuralNetwork(4, 150, 3);
//
//            network.train(dataset, 1000, 0.1);
//
//        Collections.shuffle(dataset.getData());
//        for (Data data : dataset.getData()) {
//            System.out.println("---------------------------------------");
//            network.classify(data);
//            System.out.print("Должно быть: ");
//            int c = data.getClassification();
//            switch (c) {
//                case 0 -> System.out.printf("(%d) Iris Setosa\n", c);
//                case 1 -> System.out.printf("(%d) Iris Versicolour\n", c);
//                case 2 -> System.out.printf("(%d) Iris Virginica\n", c);
//            }
//        }
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
