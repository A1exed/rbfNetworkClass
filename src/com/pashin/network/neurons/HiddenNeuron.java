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

    public void calculateValue(double inputValue) {
        setInputValue(inputValue);
//        outputValue = Math.pow(2.718, -(Math.pow(inputValue - centre, 2) / (2 * Math.pow(radius, 2))));
        outputValue = (1 / (1 + Math.pow(2.718, -inputValue)));
    }

    public void correctCentreAndRadius() {
        // TODO: корректировка весов
    }

    @Override
    public String toString() {
        return "HiddenNeuron{" +
                "weights=" + weights +
                ", inputValue=" + inputValue +
                ", outputValue=" + outputValue +
                ", error=" + error +
                ", centre=" + centre +
                ", radius=" + radius +
                '}';
    }
}
