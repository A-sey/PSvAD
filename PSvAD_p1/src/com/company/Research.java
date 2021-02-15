package com.company;

import java.util.*;

public class Research {
    private final List<Double> X;
    private Double matExp;
    private Double m;
    private final List<Double> z = new ArrayList<>();
    private Double s;
    private Double s2;
    private Double d;
    private Double r;

    public Research(List<Double> X) {
        this.X = X;
    }

    public double getMatExp() {
        if (matExp != null) {
            return matExp;
        }
        double sum = 0;
        for (Double x : X) {
            sum += x;
        }
        matExp = sum / X.size();
        return matExp;
    }

    public double getM() {
        if (m != null) {
            return m;
        }
        int N = X.size();
        if (N % 2 == 1) {
            m = X.get((N + 1) / 2);
        } else {
            m = (X.get(N / 2) + X.get(N / 2 + 1)) / 2;
        }
        return m;
    }

    public List<Double> getZ() {
        if (z.size()>0) {
            return z;
        }
        HashMap<Double, Integer> map = new HashMap<>();
        for (Double x : X) {
            if (!map.containsKey(x)) {
                map.put(x, 1);
            } else {
                int count = map.get(x);
                map.put(x, count + 1);
            }
        }
        int maxVal = 0;
        Set<Map.Entry<Double, Integer>> set = map.entrySet();
        for (Map.Entry<Double, Integer> e : set) {
            if (e.getValue() > maxVal) {
                maxVal = e.getValue();
                z.clear();
                z.add(e.getKey());
            } else if (e.getValue() == maxVal) {
                z.add(e.getKey());
            }
        }
        return z;
    }

    public double getS() {
        if (s != null) {
            return s;
        }
        int N = X.size();
        double sum = 0;
        double xx = getMatExp();
        for (Double x : X) {
            sum += Math.pow((x - xx), 2);
        }
        s = Math.pow(((sum / (N - 1))), 0.5);
        return s;
    }

    public double getS2() {
        if (s2 != null) {
            return s2;
        }
        double s = getS();
        s2 = Math.pow(s, 2);
        return s2;
    }

    public double getD() {
        if (d != null) {
            return d;
        }
        int N = X.size();
        double xx = getMatExp();
        double sum = 0;
        for (Double x : X) {
            sum += Math.abs(x - xx);
        }
        d = sum / N;
        return d;
    }

    public double getR() {
        if(r!=null) {
            return r;
        }
        double max = Collections.max(X);
        double min = Collections.min(X);
        r = max-min;
        return r;
    }

    public List<Double> getList() {
        return X;
    }
}
