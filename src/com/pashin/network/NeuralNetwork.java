package com.pashin.network;

import com.pashin.Data;
import com.pashin.Dataset;
import com.pashin.network.layers.*;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.InputNeuron;
import com.pashin.network.neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.Collections;

public class NeuralNetwork {

    private Dataset dataset;

    private InputLayer inputLayer;

    private HiddenLayer hiddenLayer;

    private OutputLayer outputLayer;

    public NeuralNetwork(Dataset dataset, int numberOfParams, int numberOfHiddenNeurons, int numberOfClasses) {
        this.dataset = dataset;
        inputLayer = new InputLayer(numberOfParams, numberOfHiddenNeurons);
        hiddenLayer = new HiddenLayer(numberOfHiddenNeurons, numberOfClasses);
        outputLayer = new OutputLayer(numberOfClasses);
    }

    public void train() {
        Collections.shuffle(dataset.getData());

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
