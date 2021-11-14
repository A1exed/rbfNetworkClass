package com.pashin.network.layers;

import com.pashin.Data;
import com.pashin.network.Layer;
import com.pashin.network.neurons.HiddenNeuron;
import com.pashin.network.neurons.InputNeuron;
import com.pashin.network.neurons.OutputNeuron;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class HiddenLayer extends Layer<HiddenNeuron> implements Serializable {

    public HiddenLayer(int numberOfNeuronsInLayer, int numberOfNeuronsInNextLayer) {
        super(numberOfNeuronsInLayer);
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            super.getListOfNeurons().add(new HiddenNeuron(numberOfNeuronsInNextLayer));
        }
    }

    public void initCentresAndRadius(ArrayList<InputNeuron> inputNeurons, Data data) {
        for (HiddenNeuron hiddenNeuron1 : listOfNeurons) {
            hiddenNeuron1.setRadius(Math.random() / 10);
            for (InputNeuron ignored : inputNeurons) {
                hiddenNeuron1.getCentres().add(Math.random());
            }
        }
//        HiddenNeuron hiddenNeuron;
//        double rand = 0.0;
//         // Центры
//        if (inputNeurons.size() <= numberOfNeuronsInLayer) {
//            for (int i = 0; i < numberOfNeuronsInLayer; i++) {
//                hiddenNeuron = listOfNeurons.get(i);
//                for (int j = 0; j < inputNeurons.size(); j++) {
//                    hiddenNeuron.getCentres().add(data.getParams().get(j) + rand);
//                }
//                for (int j = 0; j < numberOfNeuronsInLayer - inputNeurons.size() - 1; j++) {
//                    hiddenNeuron.getCentres().add(hiddenNeuron.getCentres().get(j) + rand);
//                }
//                Collections.reverse(hiddenNeuron.getCentres());
//                rand = Math.random() / 100;
//            }
//        } else {
//            for (int i = 0; i < numberOfNeuronsInLayer; i++) {
//                hiddenNeuron = listOfNeurons.get(i);
//                for (int j = 0; j < inputNeurons.size(); j++) {
//                    hiddenNeuron.getCentres().add(data.getParams().get(j) + rand);
//                }
//                Collections.reverse(hiddenNeuron.getCentres());
//                rand = Math.random() / 100;
//            }
//        }
//         // Радиус
//        double tempR;
//        HiddenNeuron tempHidden;
//        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
//            hiddenNeuron = listOfNeurons.get(i);
//            tempR = 0;
//            for (int j = 0; j < numberOfNeuronsInLayer; j++) {
//                tempHidden = listOfNeurons.get(j);
//                if (i != j) {
//                    for (int k = 0; k < inputNeurons.size(); k++) {
//                        tempR += Math.pow(hiddenNeuron.getCentres().get(k) - tempHidden.getCentres().get(k), 2);
//                    }
//                }
//            }
//            hiddenNeuron.setRadius(Math.sqrt(tempR / (numberOfNeuronsInLayer - 1)));
//        }
    }

    public void calculateValues(ArrayList<InputNeuron> inputNeurons) {
        // Определение входного вектора X
        for (HiddenNeuron hiddenNeuron : listOfNeurons) {
            hiddenNeuron.setX(new ArrayList<>());
        }
        for (InputNeuron inputNeuron : inputNeurons) {
            for (HiddenNeuron hiddenNeuron : listOfNeurons) {
                hiddenNeuron.getX().add(inputNeuron.getOutputValue());
            }
        }
        // Расчет
        double sum;
        for (HiddenNeuron hiddenNeuron : listOfNeurons) {
            sum = 0;
            for (int i = 0; i < inputNeurons.size(); i++) {
                sum += Math.pow(hiddenNeuron.getX().get(i) - hiddenNeuron.getCentres().get(i), 2);
            }
            hiddenNeuron.setOutputValue(Math.pow(2.718, -sum / (2 * Math.pow(hiddenNeuron.getRadius(), 2))));
            hiddenNeuron.setDerivative(Math.sqrt(sum) / Math.pow(hiddenNeuron.getRadius(), 2));
        }
    }

    public void calculateErrors(ArrayList<OutputNeuron> outputNeurons) {
//        HiddenNeuron hiddenNeuron;
//        double error;
//        double mul;
//        double sum;
//        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
//            hiddenNeuron = listOfNeurons.get(i);
//            error = 0.0;
//            for (int j = 0; j < outputNeurons.size(); j++) {
//                mul = outputNeurons.get(j).getError() * hiddenNeuron.getWeights().get(j);
//                sum = 0.0;
//                for (int k = 0; k < numberOfNeuronsInLayer; k++) {
//                    sum += listOfNeurons.get(k).getWeights().get(j);
//                }
//                error += mul / sum;
//            }
//            hiddenNeuron.setError(error);
//        }
        double error;
        for (HiddenNeuron hiddenNeuron : listOfNeurons) {
            error = 0.0;
            for (int i = 0; i < outputNeurons.size(); i++) {
                error += hiddenNeuron.getWeights().get(i) * outputNeurons.get(i).getError();
            }
            hiddenNeuron.setError(error);
        }
    }

    public void correctParams(ArrayList<InputNeuron> inputNeurons, ArrayList<OutputNeuron> outputNeurons, double trainCoefficient) {
        ArrayList<ArrayList<Double>> weightsList = new ArrayList<>();
        ArrayList<Double> weights;
        ArrayList<ArrayList<Double>> centresList = new ArrayList<>();
        ArrayList<Double> centres;
        ArrayList<Double> radiuses = new ArrayList<>();
        double gradient;
        OutputNeuron outputNeuron;
        HiddenNeuron hiddenNeuron;
        // Веса
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            hiddenNeuron = listOfNeurons.get(i);
            weights = new ArrayList<>();
            for (int j = 0; j < outputNeurons.size(); j++) {
                outputNeuron = outputNeurons.get(j);
                gradient = -1.0 * outputNeuron.getError() * hiddenNeuron.getOutputValue();
                weights.add(hiddenNeuron.getWeights().get(j) - trainCoefficient * gradient);
            }
            weightsList.add(weights);
        }
        // Центры и радиус
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            hiddenNeuron = listOfNeurons.get(i);
            centres = new ArrayList<>();
            for (int j = 0; j < inputNeurons.size(); j++) {
                gradient = -1.0 * hiddenNeuron.getError() * hiddenNeuron.getOutputValue() * hiddenNeuron.getDerivative();
                centres.add(hiddenNeuron.getCentres().get(j) - trainCoefficient * gradient);
            }
            centresList.add(centres);
            gradient = -1.0 * hiddenNeuron.getError() * hiddenNeuron.getOutputValue() * hiddenNeuron.getDerivative() / hiddenNeuron.getRadius();
            radiuses.add(hiddenNeuron.getRadius() - trainCoefficient * gradient);
        }
        for (int i = 0; i < numberOfNeuronsInLayer; i++) {
            listOfNeurons.get(i).setWeights(weightsList.get(i));
            listOfNeurons.get(i).setCentres(centresList.get(i));
            listOfNeurons.get(i).setRadius(radiuses.get(i));
        }
    }

    @Override
    public String toString() {
        return "HiddenLayer{" +
                "listOfNeurons=" + listOfNeurons +
                ", numberOfNeuronsInLayer=" + numberOfNeuronsInLayer +
                '}';
    }
}
