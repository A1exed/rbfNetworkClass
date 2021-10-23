package com.pashin.network.layers;

import com.pashin.network.Layer;
import com.pashin.network.Neuron;

import java.util.ArrayList;

public class HiddenLayer extends Layer {

    protected HiddenLayer(ArrayList<Neuron> listOfNeurons, int numberOfNeuronsInLayer) {
        super(listOfNeurons, numberOfNeuronsInLayer);
    }

    public ArrayList<HiddenLayer> initLayer(HiddenLayer hiddenLayer, ArrayList<HiddenLayer> listOfHiddenLayers, InputLayer inputLayer, OutputLayer outputLayer) {
        return null;
    }

    public void printLayer(ArrayList<HiddenLayer> listOfHiddenLayers) {

    }
}
