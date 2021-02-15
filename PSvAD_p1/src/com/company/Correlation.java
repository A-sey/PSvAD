package com.company;

import java.util.List;

public class Correlation {
    public static Double getCorrelation(Research rX, Research rY) {
        List<Double> X = rX.getList();
        List<Double> Y = rY.getList();
        if (X.size() != Y.size()) {
            return null;
        }
        int N = X.size();
        Double xx = rX.getMatExp();
        Double yy = rY.getMatExp();
        double mean = xx * yy;
        Double sx = rX.getS();
        Double sy = rY.getS();
        double s = sx * sy;

//        double sum = 0;
//        for (int i = 0; i < N; i++){
//            sum += (X.get(i)-xx)*(Y.get(i)-yy);
//        }
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += X.get(i) * Y.get(i) - mean;
        }
        return sum / N / s;
    }
}
