package com.company;

import java.util.Calendar;
import java.util.List;

public class Main {
    private static String delimiter;
    private static Integer seed;
    private static String fileName;
    private static int count;

    private static boolean parseArgs(String[] args) {
        boolean fd = false;
        boolean ff = false;
        boolean fc = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    System.out.println("Параметры:");
                    System.out.println("\t-s - Начальный параметр для генератора случайных чисел.");
                    System.out.println("\t\tПри одинаковом значении этого параметра результат будет повторяться.");
                    System.out.println("\t-c - Количество значений в каждой выборке.");
                    System.out.println("\t-d - Разделитель значений.");
                    System.out.println("\t-f - Имя итогового (какое слово странное) файла.");
                    return false;
                case "-d":
                    delimiter = args[i + 1];
                    fd = true;
                    i++;
                    break;
                case "-f":
                    fileName = args[i + 1];
                    ff = true;
                    i++;
                    break;
                case "-s":
                    try {
                        seed = Integer.parseInt(args[i + 1]);
                        i++;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка начального значения генератора, seed выбран случайно\n\n");
                    }
                    break;
                case "-c":
                    try {
                        count = Integer.parseInt(args[i + 1]);
                        fc = true;
                        i++;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка количества значений выборок, установлено значение по умолчанию\n\n");
                    }
                    break;
            }
        }
        if (!fd) {
            delimiter = ";";
        }
        if (!ff) {
            fileName = String.format("%d.txt", Calendar.getInstance().getTime().getTime());
        }
        if (!fc) {
            count = 40;
        }
        return true;
    }

    public static void main(String[] args) {
        if(!parseArgs(args)){
            return;
        }
        Generate generate;
        if (seed != null) {
            generate = new Generate(count, seed);
        } else {
            generate = new Generate(count);
        }
        String text = partOne(generate) + "\n\n" + partTwo(generate);
        System.out.println(text);
        Files.write(fileName, text);
    }

    private static String partOne(Generate generate) {
        StringBuilder text = new StringBuilder("X").append(delimiter).append("Модификация").append(delimiter).append(delimiter).append("Y\n");
        List<Double> X = generate.genX(1, 10);
        List<Double> Y = generate.genY();
        List<String> F1 = generate.getF1();
        for (int i = 0; i < X.size(); i++) {
            text.append(X.get(i)).append(delimiter).append(F1.get(i)).append(delimiter).append("=").append(delimiter).append(Y.get(i)).append("\n");
        }
        Research rX = new Research(X);
        text.append("\nДля X:\n");
        text.append(printResearch(false, rX));
        Research rY = new Research(Y);
        text.append("\nДля Y:\n");
        text.append(printResearch(false, rY));
        text.append("\nКоэффициент корреляции:").append(delimiter).append(Correlation.getCorrelation(rX, rY)).append("\n");
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
        Research rR = new Research(R);
        text.append("\nДля R:\n");
        text.append(printResearch(true, rR));
        Research rG = new Research(G);
        text.append("\nДля G:\n");
        text.append(printResearch(true, rG));
        text.append("\nКоэффициент корреляции:").append(delimiter).append(Correlation.getCorrelation(rR, rG)).append("\n");
        return text.toString();
    }

    private static String printResearch(boolean printZ, Research rR) {
        StringBuilder text = new StringBuilder();
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
