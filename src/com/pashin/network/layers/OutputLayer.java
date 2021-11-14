package com.pashin.network.layers;

import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.OutputNeuron;

import java.io.Serializable;
import java.util.ArrayList;

public class OutputLayer extends Layer<OutputNeuron> implements Serializable {

    public OutputLayer(int numberOfNeuronsInLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new OutputNeuron());
        }
    }

    public void calculateValues(ArrayList<HiddenNeuron> hiddenNeurons) {
        double sum;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            sum = 0.0;
            for (HiddenNeuron hiddenNeuron : hiddenNeurons) {
                sum += hiddenNeuron.getOutputValue() * hiddenNeuron.getWeights().get(i);
            }
            listOfNeurons.get(i).calculateValue(sum);
        }
    }

    public void calculateErrors(int classification) {
        OutputNeuron neuron;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            neuron = listOfNeurons.get(i);
            if (i == classification) {
                neuron.setError(1.0 - neuron.getOutputValue());
            } else {
                neuron.setError(0.0 - neuron.getOutputValue());
            }
        }
    }

    @Override
    public String toString() {
        return "OutputLayer{" +
                "listOfNeurons=" + listOfNeurons +
                ", numberOfNeuronsInLayer=" + numberOfNeuronsInLayer +
                '}';
    }
}
