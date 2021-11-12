package com.pashin.network.neurons;

import com.pashin.network.Neuron;

import java.io.Serializable;
import java.util.ArrayList;

public class HiddenNeuron extends Neuron implements Serializable {

    private ArrayList<Double> weights;

    private ArrayList<Double> centres;

    private ArrayList<Double> x;

    private double radius;

    private double derivative;

    public HiddenNeuron(int numberOfRelations) {
        super(numberOfRelations);
        x = new ArrayList<>();
        centres = new ArrayList<>();
        radius = 1;
        weights = new ArrayList<>();
        for (int i = 0; i < numberOfRelations; i++) {
            weights.add(Math.random());
        }
    }

    public void correctCentreAndRadius() {
        // TODO: корректировка весов
    }

    public double getDerivative() {
        return derivative;
    }

    public void setDerivative(double derivative) {
        this.derivative = derivative;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public void setX(ArrayList<Double> x) {
        this.x = x;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public ArrayList<Double> getCentres() {
        return centres;
    }

    public void setCentres(ArrayList<Double> centres) {
        this.centres = centres;
    }

    @Override
    public String toString() {
        return "HiddenNeuron{" +
                "inputValue=" + inputValue +
                ", outputValue=" + outputValue +
                ", error=" + error +
                ", weights=" + weights +
                ", centres=" + centres +
                ", radius=" + radius +
                '}';
    }
}
