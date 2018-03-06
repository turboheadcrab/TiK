package ru.kpfu.itis;

/**
 * Created by Apraxin Vladimir on 6.3.18.
 */
public class HuffmanNode<T> implements Comparable<HuffmanNode> {
    private Double probability;
    private T character;
    private HuffmanNode<T> left;
    private HuffmanNode<T> right;

    HuffmanNode(Double probability, T character, HuffmanNode<T> left, HuffmanNode<T> right) {
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

    public T getCharacter() {
        return character;
    }

    public HuffmanNode<T> getLeft() {
        return left;
    }

    public HuffmanNode<T> getRight() {
        return right;
    }
}