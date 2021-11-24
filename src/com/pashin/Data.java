package com.pashin;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    private ArrayList<Double> params;

    private double result;

    public Data() {

    }

    public ArrayList<Double> getParams() {
        return params;
    }

    public void setParams(ArrayList<Double> params) {
        this.params = params;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "params=" + params +
                ", result=" + result +
                '}';
    }
}
