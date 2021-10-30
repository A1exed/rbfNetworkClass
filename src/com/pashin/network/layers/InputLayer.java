package com.pashin.network.layers;

import com.pashin.Data;
import com.pashin.network.Layer;
import com.pashin.network.neurons.InputNeuron;

public class InputLayer extends Layer<InputNeuron> {

    public InputLayer(int numberOfNeuronsInLayer, int numberOfNeuronsInNextLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new InputNeuron(numberOfNeuronsInNextLayer));
        }
    }

    public void classify(Data data) {
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            listOfNeurons.get(i).setInputValue(data.getParams().get(i));
            listOfNeurons.get(i).classify();
        }
    }

    public void train() {

    }

}
