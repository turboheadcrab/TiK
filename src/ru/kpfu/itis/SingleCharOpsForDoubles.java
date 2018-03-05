package ru.kpfu.itis;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 5.3.18.
 * Класс для вычисления вероятности символа в тексте для случая условной вероятности
 */
public class SingleCharOpsForDoubles extends SingleCharAbstract {

    SingleCharOpsForDoubles(String textToDo) {
        super(textToDo);

        amountOfChars--; //количество символов в тексте без последнего символа

        charProbabilities = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry :
                charAmounts.entrySet()) {
            double probability = entry.getValue() / (double) amountOfChars; //вычисляем вероятность символа, поделив количество данного символа в тексте на общее количество символов
            charProbabilities.put(entry.getKey(), probability); //сохраняем вероятность в словарь
        }
    }
}
