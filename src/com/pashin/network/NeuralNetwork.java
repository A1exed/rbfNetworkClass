package com.pashin.network;

import com.pashin.Data;
import com.pashin.Dataset;
import com.pashin.network.layers.*;
import com.pashin.network.neurons.OutputNeuron;

import java.util.Collections;

public class NeuralNetwork {

    private Dataset dataset;

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;

    private int numberOfEpoch;

    public NeuralNetwork(Dataset dataset, int numberOfParams, int numberOfHiddenNeurons, int numberOfClasses, int numberOfEpoch) {
        this.numberOfEpoch = numberOfEpoch;
        this.dataset = dataset;
        inputLayer = new InputLayer(numberOfParams, numberOfHiddenNeurons);
        hiddenLayer = new HiddenLayer(numberOfHiddenNeurons, numberOfClasses);
        outputLayer = new OutputLayer(numberOfClasses);
    }

    public void train() {
        Collections.shuffle(dataset.getData());
        System.out.println("Начало обучения");
        for (int i = 0; i < numberOfEpoch; i++) {
            System.out.println("Эпоха #" + i);
            for (Data data : dataset.getData()) {
                classify(data);
                outputLayer.calculateErrors(data.getClassification());
                hiddenLayer.calculateErrors(outputLayer.getListOfNeurons());
                for (OutputNeuron outputNeuron : outputLayer.getListOfNeurons()) {
                    System.out.println(outputNeuron.getOutputValue() + " - " + outputNeuron.getError());
                }
            }
        }

    }

    public void classify(Data data) {
        inputLayer.classify(data);
        hiddenLayer.classify(inputLayer.getListOfNeurons());
        outputLayer.classify(hiddenLayer.getListOfNeurons());
        for (OutputNeuron outputNeuron : outputLayer.getListOfNeurons()) {
            System.out.println(outputNeuron.getOutputValue());
        }
    }

}
