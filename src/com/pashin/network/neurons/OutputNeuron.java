package com.pashin.network.neurons;

import com.pashin.network.Neuron;

public class OutputNeuron extends Neuron {

    private double centre;

    private double radius;

    public OutputNeuron() {
        super(0);
        centre = 0;
        radius = 1;
        // TODO: инициализация
    }

    public void calculateValue(double inputValue) {
        setInputValue(inputValue);
//        outputValue = inputValue;
        outputValue = (1 / (1 + Math.pow(2.718, -inputValue)));
    }

    public void correctCentreAndRadius() {
        // TODO: корректировка весов
    }

    @Override
    public String toString() {
        return "OutputNeuron{" +
                "weights=" + weights +
                ", inputValue=" + inputValue +
                ", outputValue=" + outputValue +
                ", error=" + error +
                ", centre=" + centre +
                ", radius=" + radius +
                '}';
    }
}
