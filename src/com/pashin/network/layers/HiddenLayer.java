package com.pashin.network.layers;

import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.InputNeuron;
import com.pashin.network.neurons.OutputNeuron;

import java.util.ArrayList;

public class HiddenLayer extends Layer<HiddenNeuron> {

    public HiddenLayer(int numberOfNeuronsInLayer, int numberOfNeuronsInNextLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new HiddenNeuron(numberOfNeuronsInNextLayer));
        }
    }

    public void classify(ArrayList<InputNeuron> inputNeurons) {
        HiddenNeuron neuron;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            for (InputNeuron inputNeuron : inputNeurons) {
                neuron = listOfNeurons.get(i);
                neuron.setInputValue(neuron.getInputValue() + inputNeuron.getOutputValue() * inputNeuron.getWeights().get(i));
                neuron.calculate();
            }
        }
    }

    public void calculateErrors(ArrayList<OutputNeuron> outputNeurons) {
        HiddenNeuron hiddenNeuron;
        double error;
        double mul;
        double sum;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            hiddenNeuron = listOfNeurons.get(i);
            error = 0.0;
            for (int j = 0; j < outputNeurons.size(); j++) {
                mul = outputNeurons.get(j).getError() * hiddenNeuron.getWeights().get(j);
                sum = 0.0;
                for (int k = 0; k < numberOfNeuronsInLayer; k++) {
                    sum += listOfNeurons.get(k).getWeights().get(j);
                }
                error += mul / sum;
            }
            hiddenNeuron.setError(error);
        }
    }

    public void correctWeights() {

    }

    public void train(ArrayList<InputNeuron> inputNeurons) {

    }

}
