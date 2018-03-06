package ru.kpfu.itis;

/**
 * Created by Apraxin Vladimir on 6.3.18.
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private Double probability;
    private int character;
    private HuffmanNode left;
    private HuffmanNode right;

    HuffmanNode(Double probability, int character, HuffmanNode left, HuffmanNode right) {
        this.probability = probability;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode anotherHuffmanNode) {
        if (this.probability > anotherHuffmanNode.probability) {
            return 1;
        } else if (this.probability < anotherHuffmanNode.probability) {
            return -1;
        } else {
            return 0;
        }
    }

    public Double getProbability() {
        return probability;
    }

    public int getCharacter() {
        return character;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }
}