package com.pashin.network;

import java.util.ArrayList;

public abstract class Neuron {

    protected ArrayList<Double> weights;

    protected double inputValue;

    protected double outputValue;

    protected double error;

    protected Neuron(int numberOfRelations) {
        error = 0.0;
        weights = new ArrayList<>();
        for (int i = 0; i < numberOfRelations; i++) {
            weights.add(Math.random());
        }
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
