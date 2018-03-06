package ru.kpfu.itis;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Apraxin Vladimir on 6.3.18.
 */
public class HuffmanConditional {
    private Map<MyPair<Integer, Integer>, String> pairCodes;
    private String encodedText;
    private Double calculatingSpeed;

    public HuffmanConditional(String textToDo) {
        DoubleCharOps dco1 = new DoubleCharOps(textToDo);
        PriorityQueue<HuffmanNode<MyPair<Integer, Integer>>> queue = new PriorityQueue<>(dco1.getAmountOfPairs());
        for (Map.Entry<MyPair<Integer, Integer>, Double> entry :
                dco1.getPairProbabilities().entrySet()) {
            HuffmanNode<MyPair<Integer, Integer>> node =
                    new HuffmanNode<>(entry.getValue(), entry.getKey(), null, null);
            queue.add(node);
        }
        HuffmanNode<MyPair<Integer, Integer>> root = null;

        pairCodes = new TreeMap<>();

        while (queue.size() > 1) {

            HuffmanNode<MyPair<Integer, Integer>> x = queue.peek();
            queue.poll();

            HuffmanNode<MyPair<Integer, Integer>> y = queue.peek();
            queue.poll();

            HuffmanNode<MyPair<Integer, Integer>> f =
                    new HuffmanNode<MyPair<Integer, Integer>>(x.getProbability() + y.getProbability(), null, x, y);
            root = f;
            queue.add(f);
        }
        printCode(root, "", pairCodes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textToDo.length() - 1; i++) {
            MyPair<Integer, Integer> pair = new MyPair<>((int) textToDo.charAt(i), (int) textToDo.charAt(i + 1));
            String appendage = pairCodes.get(pair);
            sb.append(appendage);
        }
        encodedText = sb.toString();

        calculatingSpeed = encodedText.length() / (double) textToDo.length();
    }

    private static void printCode(HuffmanNode<MyPair<Integer, Integer>> root,
                                  String s, Map<MyPair<Integer, Integer>, String> pairCodes) {
        if (root.getLeft() == null && root.getRight() == null && root.getCharacter() != null) {
            pairCodes.put(root.getCharacter(), s);
            return;
        }
        printCode(root.getLeft(), s + "0", pairCodes);
        printCode(root.getRight(), s + "1", pairCodes);
    }

    public Map<MyPair<Integer, Integer>, String> getPairCodes() {
        return pairCodes;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public Double getCalculatingSpeed() {
        return calculatingSpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Кодирование Хаффмена с учётом условной вероятности:\n");
        for (Map.Entry<MyPair<Integer, Integer>, String> entry :
                pairCodes.entrySet()) {
            sb.append("Пара символов: \"")
                    .append(Character.getName((int) entry.getKey().getKey()))
                    .append(" ")
                    .append(Character.getName((int) entry.getKey().getValue()))
                    .append("\", код: \"")
                    .append(entry.getValue())
                    .append("\"\n");
        }
        sb.append("Скорость кодирования: ").append(calculatingSpeed).append(" бит/символ");
        return sb.toString();
    }
}
