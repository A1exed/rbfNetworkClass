package com.pashin.network;

import java.util.ArrayList;

public class Neuron {

    private ArrayList<Double> listOfWeightIn;

    private ArrayList<Double> listOfWeightOut;

    public Double initNeuron() {
        return 0.0;
    }

    public ArrayList<Double> getListOfWeightIn() {
        return listOfWeightIn;
    }

    public void setListOfWeightIn(ArrayList<Double> listOfWeightIn) {
        this.listOfWeightIn = listOfWeightIn;
    }

    public ArrayList<Double> getListOfWeightOut() {
        return listOfWeightOut;
    }

    public void setListOfWeightOut(ArrayList<Double> listOfWeightOut) {
        this.listOfWeightOut = listOfWeightOut;
    }
}
