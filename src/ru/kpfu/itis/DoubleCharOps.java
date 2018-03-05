package ru.kpfu.itis;

import javafx.util.Pair;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 4.3.18.
 * Класс для вычисления условной вероятности символа и для вычисления условной энтропии
 */
public class DoubleCharOps {
    private final double log2 = Math.log(2); //логарифм по основанию 10 от 2
    //private Map<Integer, Integer> pairs; //словарь: числовое значение символа i и слеудующего за ним i+1
    private Map<MyPair<Integer, Integer>, Integer> pairAmounts; //словарь: пара символов и их количество в тексте
    private Integer amountOfPairs; //общее количество пар в тексте
    private Map<MyPair<Integer, Integer>, Double> pairProbabilities; //словарь: пара символов и их совместные вероятности в тексте
    private Map<MyPair<Integer, Integer>, Double> pairConditionalProbabilities; //словарь: пара символов и их условная вероятнсть
    private Double conditionalEntropy = 0.0; //условная энтропия

    DoubleCharOps(String textToDo) {
        SingleCharOpsForDoubles sco = new SingleCharOpsForDoubles(textToDo);

        pairAmounts = new TreeMap<>();

        for (int i = 0; i < textToDo.length() - 1; i++) {
            MyPair<Integer, Integer> pair = new MyPair<>((int) textToDo.charAt(i), (int) textToDo.charAt(i + 1)); //пара последовательных символов
            pairAmounts.merge(pair, 1, Integer::sum);
        }

        //количество пар символов в тексте
        amountOfPairs = 0;
        for (Map.Entry<MyPair<Integer, Integer>, Integer> entry :
                pairAmounts.entrySet()) {
            amountOfPairs += entry.getValue();
        }

        pairProbabilities = new TreeMap<>();
        pairConditionalProbabilities = new TreeMap<>();
        for (Map.Entry<MyPair<Integer, Integer>, Integer> entry :
                pairAmounts.entrySet()) {
            double jointProbability = entry.getValue() / (double) amountOfPairs; //вычисляем вероятность пары, поделив количество данных пар на общее количество пар
            pairProbabilities.put(entry.getKey(), jointProbability); //сохраняем вероятность в словарь
            double conditionalProbability = jointProbability / (sco.getCharProbabilities().get(entry.getKey().getKey())); //вычисляем условную вероятность пары, поделив совместную вероятность на вероятность первого символа пары
            pairConditionalProbabilities.put(entry.getKey(), conditionalProbability); //сохраняем вероятность в словарь

            double pijlogpij = jointProbability * log2(conditionalProbability);
            conditionalEntropy += pijlogpij;
        }
        conditionalEntropy *= (-1);
    }

    // метод для вычисления логарифма по основанию 2 от x
    private double log2(double x) {
        return Math.log(x) / log2;
    }

    //методы доступа к полям класса
    public Map<MyPair<Integer, Integer>, Integer> getPairAmounts() {
        return pairAmounts;
    }

    public Integer getAmountOfPairs() {
        return amountOfPairs;
    }

    public Map<MyPair<Integer, Integer>, Double> getPairProbabilities() {
        return pairProbabilities;
    }

    public Map<MyPair<Integer, Integer>, Double> getPairConditionalProbabilities() {
        return pairConditionalProbabilities;
    }

    public Double getConditionalEntropy() {
        return conditionalEntropy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleCharOps that = (DoubleCharOps) o;

        return pairAmounts.equals(that.pairAmounts);
    }

    @Override
    public int hashCode() {
        return pairAmounts.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Условная энтропия данного текста по паре символов:\n");
        for (Map.Entry<MyPair<Integer, Integer>, Integer> entry :
                pairAmounts.entrySet()) {
            sb.append("Код Unicode: \"")
                    .append(entry.getKey().getKey())
                    .append(" ").append(entry.getKey().getValue())
                    .append("\", ");
            sb.append("символы: \"")
                    .append(Character.getName((int) entry.getKey().getKey()))
                    .append(" ")
                    .append(Character.getName((int) entry.getKey().getValue()))
                    .append("\", "); //название символа
            sb.append("количество: \"").append(entry.getValue()).append("\"");
            sb.append("\n");
            sb.append("вероятность пары символов: \"").append(pairProbabilities.get(entry.getKey())).append("\", ");
            sb.append("\n");
        }
        sb.append("Условная энтропия текста: \"").append(conditionalEntropy).append("\" бит");
        return sb.toString();
    }
}
