package com.pashin.network.neurons;

import com.pashin.network.Neuron;

public class InputNeuron extends Neuron {

    public InputNeuron(int numberOfRelations) {
        super(numberOfRelations);
    }

    public void calculate() {
        outputValue = inputValue;
    }

    public void classify() {
        calculate();
    }

    public void train() {
        calculate();
        correctWeight();
    }

}
