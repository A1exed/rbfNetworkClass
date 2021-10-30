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
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            for (HiddenNeuron hiddenNeuron : hiddenNeurons) {
                listOfNeurons.get(i).setInputValue(listOfNeurons.get(i).getInputValue() + hiddenNeuron.getOutputValue() * hiddenNeuron.getWeights().get(i));
                listOfNeurons.get(i).calculate();
            }
        }
    }

    public void train(ArrayList<HiddenNeuron> hiddenNeurons) {

    }

}
