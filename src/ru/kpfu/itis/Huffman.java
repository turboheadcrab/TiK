package ru.kpfu.itis;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 6.3.18.
 */
public class Huffman {
    private Map<Integer, String> charCodes;
    private String encodedText;
    private Double calculatingSpeed;

    Huffman(String textToDo) {
        SingleCharOps sco1 = new SingleCharOps(textToDo);
        PriorityQueue<HuffmanNode<Integer>> queue = new PriorityQueue<>(sco1.getAmountOfChars().intValue());
        for (Map.Entry<Integer, Double> entry :
                sco1.getCharProbabilities().entrySet()) {
            HuffmanNode<Integer> node = new HuffmanNode<>(entry.getValue(), entry.getKey(), null, null);
            queue.add(node);
        }
        HuffmanNode<Integer> root = null;

        charCodes = new TreeMap<>();

        while (queue.size() > 1) {

            HuffmanNode<Integer> x = queue.peek();
            queue.poll();

            HuffmanNode<Integer> y = queue.peek();
            queue.poll();

            HuffmanNode<Integer> f = new HuffmanNode<>(x.getProbability() + y.getProbability(), 0, x, y);
            root = f;
            queue.add(f);
        }
        printCode(root, "", charCodes);

        StringBuilder sb = new StringBuilder();
        textToDo.chars().forEach(i -> sb.append(charCodes.get(i)));
        encodedText = sb.toString();

        calculatingSpeed = encodedText.length() / (double) textToDo.length();

    }

    private static void printCode(HuffmanNode<Integer> root, String s, Map<Integer, String> charCodes) {
        if (root.getLeft() == null && root.getRight() == null && root.getCharacter() > 0) {
            charCodes.put(root.getCharacter(), s);
            return;
        }
        printCode(root.getLeft(), s + "0", charCodes);
        printCode(root.getRight(), s + "1", charCodes);
    }

    public Map<Integer, String> getCharCodes() {
        return charCodes;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public Double getCalculatingSpeed() {
        return calculatingSpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Кодирование Хаффмена с учётом вероятности отдельных букв:\n");
        for (Map.Entry<Integer, String> entry :
                charCodes.entrySet()) {
            sb.append("Символ: \"")
                    .append(Character.getName(entry.getKey()))
                    .append("\", код: \"")
                    .append(entry.getValue())
                    .append("\"\n");
        }
        sb.append("Скорость кодирования: ").append(calculatingSpeed).append(" бит/символ");
        return sb.toString();
    }
}
