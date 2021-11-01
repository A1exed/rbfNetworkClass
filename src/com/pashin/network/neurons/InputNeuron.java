package com.pashin.network.neurons;

import com.pashin.network.Neuron;

public class InputNeuron extends Neuron {

    public InputNeuron(int numberOfRelations) {
        super(numberOfRelations);
    }

    public void calculateValue(double inputValue) {
        setInputValue(inputValue);
        outputValue = inputValue;
    }

    @Override
    public String toString() {
        return "InputNeuron{" +
                "weights=" + weights +
                ", inputValue=" + inputValue +
                ", outputValue=" + outputValue +
                ", error=" + error +
                '}';
    }
}
