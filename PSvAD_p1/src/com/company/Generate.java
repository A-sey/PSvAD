package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {
    private final List<Double> X = new ArrayList<>();
    private final List<Double> Y = new ArrayList<>();
    private final List<Double> R = new ArrayList<>();
    private final List<Double> G = new ArrayList<>();
    private final List<String> F1 = new ArrayList<>();
    private final List<String> F2 = new ArrayList<>();
    private final Random random;

    public Generate() {
        random = new Random();
    }

    public Generate(int seed) {
        random = new Random(seed);
    }

    public List<Double> genX(int min, int max) {
        for (int i = 0; i < 40; i++) {
            double x = random.nextDouble() * (max - min) + min;
            X.add(x);
        }
        return X;
    }

    public List<Double> genY() {
        Y.clear();
        F1.clear();
        for (Double x : X) {
            double y = f(x, F1);
            Y.add(y);
        }
        return Y;
    }

    public List<Double> genR(int min, int max) {
        for (int i = 0; i < 40; i++) {
            double r = random.nextInt(max + 1 - min) + min;
            R.add(r);
        }
        return R;
    }

    public List<Double> genG() {
        G.clear();
        F2.clear();
        for (Double r : R) {
            double g = f(r, F2);
            G.add(g);
        }
        return G;
    }

    private double f(double x, List<String> l) {
        int mode = random.nextInt(3);
        if (mode == 0) {
            int a = random.nextInt(15) + 1;
            l.add("'+" + a);
            return x + a;
        } else if (mode == 1) {
            int a = random.nextInt(9) + 2;
            l.add("'*" + a);
            return x * a;
        } else {
            int a = random.nextInt(2) + 2;
            l.add("'^" + a);
            return Math.pow(x, a);
        }
    }

    public List<Double> getX() {
        return X;
    }

    public List<Double> getY() {
        return Y;
    }

    public List<Double> getR() {
        return R;
    }

    public List<Double> getG() {
        return G;
    }

    public List<String> getF1() {
        return F1;
    }

    public List<String> getF2() {
        return F2;
    }
}
