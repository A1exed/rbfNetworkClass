package com.pashin.network.neurons;

import com.pashin.network.Neuron;

public class HiddenNeuron extends Neuron {

    private double centre;

    private double radius;

    public HiddenNeuron(int numberOfRelations) {
        super(numberOfRelations);
        centre = 0;
        radius = 1;
        // TODO: инициализация
    }

    public void calculate() {
        outputValue = Math.exp(-(Math.pow(inputValue - centre, 2) / (2 * Math.pow(radius, 2))));
    }

    public void correctCentreAndRadius() {
        // TODO: корректировка весов
    }

}
