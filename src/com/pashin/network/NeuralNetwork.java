package com.pashin.network;

import com.pashin.Data;
import com.pashin.Dataset;
import com.pashin.network.layers.*;
import com.pashin.network.neurons.OutputNeuron;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class NeuralNetwork implements Serializable {

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;

    private boolean isTrained;

    public NeuralNetwork(int numberOfParams, int numberOfHiddenNeurons, int numberOfClasses) {
        inputLayer = new InputLayer(numberOfParams, numberOfHiddenNeurons);
        hiddenLayer = new HiddenLayer(numberOfHiddenNeurons, numberOfClasses);
        outputLayer = new OutputLayer(numberOfClasses);
        isTrained = false;
    }

    public void train(Dataset dataset, int numOfEpoch, double trainCoefficient, int percentOfTestData) {
        // Инициализация центров
        hiddenLayer.initCentresAndRadius(inputLayer.getListOfNeurons(), dataset.getData().get(0));

        Collections.shuffle(dataset.getData());
        System.out.println("-------------------------");
        System.out.println("||   Начало обучения   ||");
        System.out.println("-------------------------");
        int countOfErrors;
        isTrained = true;
        countOfErrors = 1;
        int epoch = 1;
        int numTrainData = dataset.getData().size() * (100 - percentOfTestData) / 100;
        Dataset trainData = new Dataset();
        Dataset testData = new Dataset();
        double trainError;
        double testError;
        while (countOfErrors != 0 && epoch <= numOfEpoch) {
            // Перемешиваем
            trainData.setData(new ArrayList<>());
            testData.setData(new ArrayList<>());
            for (Data data : dataset.getData().subList(0, numTrainData - 1)) {
                trainData.getData().add(data);
            }
            for (Data data : dataset.getData().subList(numTrainData, dataset.getData().size())) {
                testData.getData().add(data);
            }
            System.out.println("---------------------------------------");
            System.out.println("Эпоха #" + epoch);
            for (Data data : trainData.getData()) {
                calculateValues(data);
                calculateErrors(data);
                correctParams(trainCoefficient);
            }
            trainError = 0;
            for (OutputNeuron outputNeuron : outputLayer.getListOfNeurons()) {
                trainError += outputNeuron.getError();
            }
            trainError = Math.sqrt(Math.pow(trainError, 2) / outputLayer.getNumberOfNeuronsInLayer());
            countOfErrors = 0;
            for (Data data : testData.getData()) {
                calculateValues(data);
                for (int j = 0; j < outputLayer.getListOfNeurons().size(); j++) {
                    if (j != data.getClassification() && outputLayer.getListOfNeurons().get(j).getOutputValue() >= outputLayer.getListOfNeurons().get(data.getClassification()).getOutputValue()) {
                        countOfErrors++;
                        j = outputLayer.getListOfNeurons().size();
                    }
                }
            }
            // Ошибка теста
            testError = 0;
            for (OutputNeuron outputNeuron : outputLayer.getListOfNeurons()) {
                testError += outputNeuron.getError();
            }
            testError = Math.abs(testError / outputLayer.getNumberOfNeuronsInLayer());
            System.out.printf("Ошибок классификации %d/%d\n", countOfErrors, testData.getData().size());
            System.out.printf("Погрешность обучения (СКО): %f\n", trainError);
            System.out.printf("Погрешность тестирования (ср. отн. погр.): %f\n", testError);
            epoch++;
        }
        System.out.println("----------------------------");
        System.out.println("||   Обучение завершено   ||");
        System.out.println("----------------------------");
    }

    public void classify(Data data) {
        if (!isTrained) hiddenLayer.initCentresAndRadius(inputLayer.getListOfNeurons(), data);
        calculateValues(data);
        System.out.println("Результат классификации:");
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            switch (i) {
                case 0 -> System.out.printf("(%d) Iris Setosa: %f\n", i, outputLayer.getListOfNeurons().get(i).outputValue);
                case 1 -> System.out.printf("(%d) Iris Versicolour: %f\n", i, outputLayer.getListOfNeurons().get(i).outputValue);
                case 2 -> System.out.printf("(%d) Iris Virginica: %f\n", i, outputLayer.getListOfNeurons().get(i).outputValue);
            }
        }
    }

    public void saveNetwork() {
        File file = new File("src/com/pashin/resources/network-" + new Date().getTime() + ".trainedstate");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NeuralNetwork openNetwork(File file) {
        NeuralNetwork neuralNetwork = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            neuralNetwork = (NeuralNetwork) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return neuralNetwork;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return neuralNetwork;
    }

    private void calculateValues(Data data) {
        inputLayer.calculateValues(data);
        hiddenLayer.calculateValues(inputLayer.getListOfNeurons());
        outputLayer.calculateValues(hiddenLayer.getListOfNeurons());
    }

    private void calculateErrors(Data data) {
        outputLayer.calculateErrors(data.getClassification());
        hiddenLayer.calculateErrors(outputLayer.getListOfNeurons());
    }

    private void correctParams(double trainCoefficient) {
        outputLayer.correctParams(trainCoefficient);
        hiddenLayer.correctParams(inputLayer.getListOfNeurons(), outputLayer.getListOfNeurons(), trainCoefficient);
    }

}
