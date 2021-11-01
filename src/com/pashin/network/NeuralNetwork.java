package com.pashin.network;

import com.pashin.Data;
import com.pashin.Dataset;
import com.pashin.network.layers.*;

import java.util.Collections;

public class NeuralNetwork {

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;

    public NeuralNetwork(int numberOfParams, int numberOfHiddenNeurons, int numberOfClasses) {
        inputLayer = new InputLayer(numberOfParams, numberOfHiddenNeurons);
        hiddenLayer = new HiddenLayer(numberOfHiddenNeurons, numberOfClasses);
        outputLayer = new OutputLayer(numberOfClasses);
    }

    public void train(Dataset dataset, int numberOfEpoch, double trainCoefficient) {
        Collections.shuffle(dataset.getData());
        System.out.println("Начало обучения");
        for (int i = 0; i < numberOfEpoch; i++) {
            System.out.println("Эпоха #" + i);
            for (Data data : dataset.getData()) {
                calculateValues(data);
                calculateErrors(data);
//                System.out.println(inputLayer.toString());
//                System.out.println(hiddenLayer.toString());
//                System.out.println(outputLayer.toString());
                correctWeights(trainCoefficient);
            }
        }
        System.out.println("Обучение завершено");
    }

    public void classify(Data data) {
        calculateValues(data);
        System.out.println("Результат классификации:");
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            switch (i) {
                case 0 -> System.out.println("Iris Setosa: " + outputLayer.getListOfNeurons().get(i).outputValue);
                case 1 -> System.out.println("Iris Versicolour: " + outputLayer.getListOfNeurons().get(i).outputValue);
                case 2 -> System.out.println("Iris Virginica: " + outputLayer.getListOfNeurons().get(i).outputValue);
            }
        }
    }

    private void calculateValues(Data data) {
        inputLayer.calculateValues(data);
        hiddenLayer.calculateValues(inputLayer.getListOfNeurons());
        outputLayer.calculateValues(hiddenLayer.getListOfNeurons());
    }

    private void calculateErrors(Data data) {
        outputLayer.calculateErrorsOfClassification(data.getClassification());
        hiddenLayer.calculateErrorsOfClassification(outputLayer.getListOfNeurons());
    }

    private void correctWeights(double trainCoefficient) {
        hiddenLayer.correctWeights(outputLayer.getListOfNeurons(), trainCoefficient);
        inputLayer.correctWeights(hiddenLayer.getListOfNeurons(), trainCoefficient);
    }

}
