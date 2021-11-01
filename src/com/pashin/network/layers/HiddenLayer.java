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

    public void calculateValues(ArrayList<InputNeuron> inputNeurons) {
        double sum;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            sum = 0.0;
            for (InputNeuron inputNeuron : inputNeurons) {
                sum += inputNeuron.getOutputValue() * inputNeuron.getWeights().get(i);
            }
            listOfNeurons.get(i).calculateValue(sum);
        }
    }

    public void calculateErrorsOfClassification(ArrayList<OutputNeuron> outputNeurons) {
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

    public void correctWeights(ArrayList<OutputNeuron> outputNeurons, double trainCoefficient) {
        ArrayList<ArrayList<Double>> weightsList = new ArrayList<>();
        ArrayList<Double> weights;
        double gradient;
        OutputNeuron outputNeuron;
        HiddenNeuron hiddenNeuron;
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            hiddenNeuron = listOfNeurons.get(i);
            weights = new ArrayList<>();
            for (int j = 0; j < outputNeurons.size(); j++) {
                outputNeuron = outputNeurons.get(j);
                gradient = -1.0 * outputNeuron.getError() * hiddenNeuron.getOutputValue() * derivativeGauss(outputNeuron.getInputValue());
                weights.add(hiddenNeuron.getWeights().get(j) - trainCoefficient * gradient);
            }
            weightsList.add(weights);
        }
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            listOfNeurons.get(i).setWeights(weightsList.get(i));
        }
    }

    @Override
    public String toString() {
        return "HiddenLayer{" +
                "listOfNeurons=" + listOfNeurons +
                ", numberOfNeuronsInLayer=" + numberOfNeuronsInLayer +
                '}';
    }
}
