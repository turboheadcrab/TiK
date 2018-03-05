package ru.kpfu.itis;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 5.3.18.
 */
public abstract class SingleCharAbstract {
    Map<Integer, Integer> charAmounts; //словарь: числовое значение символа и его количество в тексте
    Long amountOfChars; //общее количество символов в тексте
    Map<Integer, Double> charProbabilities; //словарь: числовое значение символа и его вероятности в тексте

    SingleCharAbstract(String textToDo) {
        //добавление символов в словарь количеств
        charAmounts = new TreeMap<>();
        textToDo.chars().forEach(i -> charAmounts.merge(i, 1, Integer::sum));

        amountOfChars = textToDo.chars().count(); //количество символов в тексте
    }

    //методы доступа к полям класса
    public Map<Integer, Integer> getCharAmounts() {
        return charAmounts;
    }

    public Long getAmountOfChars() {
        return amountOfChars;
    }

    public Map<Integer, Double> getCharProbabilities() {
        return charProbabilities;
    }

    //метод сравнения двух экземпляров класса
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleCharOps that = (SingleCharOps) o;

        return charAmounts.equals(that.charAmounts);
    }

    //вычисление хэш-кода
    @Override
    public int hashCode() {
        return charAmounts.hashCode();
    }
}
