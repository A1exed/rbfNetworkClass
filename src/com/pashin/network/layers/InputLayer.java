package com.pashin.network.layers;

import com.pashin.Data;
import com.pashin.network.Layer;
import com.pashin.network.neurons.InputNeuron;

import java.io.Serializable;

public class InputLayer extends Layer<InputNeuron> implements Serializable {

    public InputLayer(int numberOfNeuronsInLayer, int numberOfNeuronsInNextLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new InputNeuron(numberOfNeuronsInNextLayer));
        }
    }

    public void calculateValues(Data data) {
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            listOfNeurons.get(i).calculateValue(data.getParams().get(i));
        }
    }

    @Override
    public String toString() {
        return "InputLayer{" +
                "listOfNeurons=" + listOfNeurons +
                ", numberOfNeuronsInLayer=" + numberOfNeuronsInLayer +
                '}';
    }
}
