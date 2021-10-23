package com.pashin.network;

import java.util.ArrayList;

public abstract class Layer {

    protected ArrayList<Neuron> listOfNeurons;

    protected int numberOfNeuronsInLayer;

    protected Layer(ArrayList<Neuron> listOfNeurons, int numberOfNeuronsInLayer) {
        this.listOfNeurons = listOfNeurons;
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
    }

    protected ArrayList<Neuron> getListOfNeurons() {
        return listOfNeurons;
    }

    protected void setListOfNeurons(ArrayList<Neuron> listOfNeurons) {
        this.listOfNeurons = listOfNeurons;
    }

    protected int getNumberOfNeuronsInLayer() {
        return numberOfNeuronsInLayer;
    }

    protected void setNumberOfNeuronsInLayer(int numberOfNeuronsInLayer) {
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
    }
}
