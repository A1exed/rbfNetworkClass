package com.pashin.network.layers;

import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.OutputNeuron;

import java.util.ArrayList;

public class OutputLayer extends Layer<OutputNeuron> {

    public OutputLayer(int numberOfNeuronsInLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new OutputNeuron());
        }
    }

    public void classify(ArrayList<HiddenNeuron> hiddenNeurons) {
        OutputNeuron neuron;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            for (HiddenNeuron hiddenNeuron : hiddenNeurons) {
                neuron = listOfNeurons.get(i);
                neuron.setInputValue(neuron.getInputValue() + hiddenNeuron.getOutputValue() * hiddenNeuron.getWeights().get(i));
                neuron.calculate();
            }
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

    public void train(ArrayList<HiddenNeuron> hiddenNeurons) {

    }

}
