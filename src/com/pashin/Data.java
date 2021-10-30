package com.pashin;

import java.util.ArrayList;

public class Data {

    private ArrayList<Double> params;

    private int classification;

    public Data() {

    }

    public ArrayList<Double> getParams() {
        return params;
    }

    public void setParams(ArrayList<Double> params) {
        this.params = params;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Data{" +
                "params=" + params +
                ", classification=" + classification +
                '}';
    }
}
