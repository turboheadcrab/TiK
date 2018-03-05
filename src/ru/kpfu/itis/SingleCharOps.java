package ru.kpfu.itis;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 4.3.18.
 * Класс для вычисления вероятности появления символа в тексте и для вычисления энтропии текста
 */
public class SingleCharOps extends SingleCharAbstract {

    private final double log2 = Math.log(2); //логарифм по основанию 10 от 2
    private Double entropy = 0.0; //энтропия

    SingleCharOps(String textToDo) {
        super(textToDo);

        charProbabilities = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry :
                charAmounts.entrySet()) {
            double probability = entry.getValue() / (double) amountOfChars; //вычисляем вероятность символа, поделив количество данного символа в тексте на общее количество символов
            charProbabilities.put(entry.getKey(), probability); //сохраняем вероятность в словарь
            double pilogpi = probability * log2(probability); //вычисление p(xi)*log(2, p(xi))
            entropy += pilogpi; //добавляем p(xi)*log(2, p(xi)) к переменной энтропии
        }
        entropy *= (-1); //меняем знак энтропии
    }

    // метод для вычисления логарифма по основанию 2 от x
    private double log2(double x) {
        return Math.log(x) / log2;
    }

    public Double getEntropy() {
        return entropy;
    }

    //вывод класса в читабельном виде строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Энтропия данного текста по одному символу:\n");
        for (Map.Entry<Integer, Integer> entry :
                charAmounts.entrySet()) {
            sb.append("Код Unicode: \"").append(entry.getKey()).append("\", ");
            sb.append("символ: \"").append(Character.getName(entry.getKey())).append("\", "); //название символа
            //sb.append("символ: \"" + (char) entry.getKey().intValue() + "\""); //сам символ
            sb.append("количество: \"").append(entry.getValue()).append("\"");
            sb.append("\n");
            sb.append("вероятность символа: \"").append(charProbabilities.get(entry.getKey())).append("\", ");
            sb.append("\n");
        }
        sb.append("Количество символов в тексте: \"").append(amountOfChars).append("\"");
        sb.append("\n");
        sb.append("Энтропия текста: \"").append(entropy).append("\" бит");
        return sb.toString();
    }
}
