package sample;

public class StackArray  {
    private int top;
    private char[] stackArray;
    private int currentSize;

    public StackArray (int size) {
        stackArray = new char[size];
        top = -1;
        currentSize = 0;
    }

    public void push (char data) {
        stackArray[++top] = data;
        currentSize++;
    }

    public char pop () {
        currentSize--;
        return stackArray[top--];
    }

    public char peek () {
        return stackArray[top];
    }

    public int getSize() {
        return currentSize;
    }
}
