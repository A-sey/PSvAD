package com.company;

import java.util.List;

public class Main {
    private static final String delimiter = ";";

    public static void main(String[] args) {
        Generate generate;
        if (args.length > 0) {
            try {
                int seed = Integer.parseInt(args[0]);
                generate = new Generate(seed);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка входного параметра, seed выбран случайно\n\n");
                generate = new Generate();
            }
        } else {
            generate = new Generate();
        }
        System.out.println(partOne(generate));
        System.out.println(partTwo(generate));

    }

    private static String partOne(Generate generate) {
        StringBuilder text = new StringBuilder("X").append(delimiter).append("Модификация").append(delimiter).append(delimiter).append("Y\n");
        List<Double> X = generate.genX(1, 10);
        List<Double> Y = generate.genY();
        List<String> F1 = generate.getF1();
        for (int i = 0; i < X.size(); i++) {
            text.append(X.get(i)).append(delimiter).append(F1.get(i)).append(delimiter).append("=").append(delimiter).append(Y.get(i)).append("\n");
        }
        text.append("\nДля X:\n");
        text.append(printResearch(false, X));
        text.append("\nДля Y:\n");
        text.append(printResearch(false, Y));
        return text.toString();
    }

    private static String partTwo(Generate generate) {
        StringBuilder text = new StringBuilder("R").append(delimiter).append("Модификация").append(delimiter).append(delimiter).append("G\n");
        List<Double> R = generate.genR(1, 10);
        List<Double> G = generate.genG();
        List<String> F2 = generate.getF2();
        for (int i = 0; i < R.size(); i++) {
            text.append(R.get(i)).append(delimiter).append(F2.get(i)).append(delimiter).append("=").append(delimiter).append(G.get(i)).append("\n");
        }
        text.append("\nДля R:\n");
        text.append(printResearch(true, R));
        text.append("\nДля G:\n");
        text.append(printResearch(true, G));
        return text.toString();
    }

    private static String printResearch(boolean printZ, List<Double> list) {
        StringBuilder text = new StringBuilder();
        Research rR = new Research(list);
        text.append("Математическое ожидание:").append(delimiter).append(rR.getMatExp()).append("\n");
        text.append("Медиана:").append(delimiter).append(rR.getM()).append("\n");
        if (printZ) {
            for (Double z : rR.getZ()) {
                text.append("Мода:").append(delimiter).append(z).append("\n");
            }
        }
        text.append("Среднеквадратичное отклонение:").append(delimiter).append(rR.getS()).append("\n");
        text.append("Дисперсия:").append(delimiter).append(rR.getS2()).append("\n");
        text.append("Размах:").append(delimiter).append(rR.getR()).append("\n");
        return text.toString();
    }
}
