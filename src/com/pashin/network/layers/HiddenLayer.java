package com.pashin.network.layers;

import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.InputNeuron;

import java.util.ArrayList;

public class HiddenLayer extends Layer<HiddenNeuron> {

    public HiddenLayer(int numberOfNeuronsInLayer, int numberOfNeuronsInNextLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new HiddenNeuron(numberOfNeuronsInNextLayer));
        }
    }

    public void classify(ArrayList<InputNeuron> inputNeurons) {
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            for (InputNeuron inputNeuron : inputNeurons) {
                listOfNeurons.get(i).setInputValue(listOfNeurons.get(i).getInputValue() + inputNeuron.getOutputValue() * inputNeuron.getWeights().get(i));
                listOfNeurons.get(i).calculate();
            }
        }
    }

    public void train(ArrayList<InputNeuron> inputNeurons) {

    }

}
