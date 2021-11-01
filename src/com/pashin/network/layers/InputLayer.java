package com.pashin.network.layers;

import com.pashin.Data;
import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.InputNeuron;

import java.util.ArrayList;

public class InputLayer extends Layer<InputNeuron> {

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

    public void correctWeights(ArrayList<HiddenNeuron> hiddenNeurons, double trainCoefficient) {
        ArrayList<ArrayList<Double>> weightsList = new ArrayList<>();
        ArrayList<Double> weights;
        double gradient;
        HiddenNeuron hiddenNeuron;
        InputNeuron inputNeuron;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            inputNeuron = listOfNeurons.get(i);
            weights = new ArrayList<>();
            for (int j = 0; j < hiddenNeurons.size(); j++) {
                hiddenNeuron = hiddenNeurons.get(i);
                gradient = -1.0 * hiddenNeuron.getError() * inputNeuron.getOutputValue() * derivativeGauss(hiddenNeuron.getInputValue());
                weights.add(inputNeuron.getWeights().get(j) - trainCoefficient * gradient);
            }
            weightsList.add(weights);
        }
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            listOfNeurons.get(i).setWeights(weightsList.get(i));
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
