package sample;

import java.util.Arrays;
import java.util.Stack;


public class ReversePolishNotation {

    private String str;

    public ReversePolishNotation(String str) {
        this.str = str;
    }

    private int priority(char currSymbol) {
        if (currSymbol == ' ') {
            return 5;
        } else if (currSymbol >= '0' && currSymbol <= '9' || currSymbol == '.') {
            return 4;
        } else if (currSymbol == '/' || currSymbol == '*') {
            return 3;
        } else if (currSymbol == '+' || currSymbol == '-') {
            return 2;
        } else if (currSymbol == '(') {
            return 1;
        }
        return 0;
    }

    private double charArrayToInt(char[] arr) {
        double ans = Double.parseDouble(new String(arr)); // Медленно?
        return ans;
    }

    private char[] rpn(String currStatement) {
        char[] road = currStatement.toCharArray();
        char[] currRPN = new char[2*road.length];
        int inx = 0;
        StackArray stack = new StackArray(road.length);
        for (int i = 0; i < road.length; i++) {
            int currMove = priority(road[i]);
            if (currMove == 5) {
                continue;
            }else if (currMove == 4) {
                currRPN[inx++] = road[i];
            } else if (currMove == 3 || currMove == 2) {
                currRPN[inx++] = ' ';
                if (stack.getSize() == 0 || priority(stack.peek()) < currMove) {
                    stack.push(road[i]);
                } else {
                    while (stack.getSize() != 0 && priority(stack.peek()) >= currMove) {
                        currRPN[inx++] = stack.pop();
                    }
                    stack.push(road[i]);
                }
            } else if (currMove == 1) {
                currRPN[inx++] = ' ';
                stack.push(road[i]);
            } else if (currMove == 0) {
                while (stack.peek() != '(') {
                    currRPN[inx++] = ' ';
                    currRPN[inx++] = stack.pop();
                }
                stack.pop();
            }
        }
        while (stack.getSize() != 0) {
            currRPN[inx++] = stack.pop();
        }
        return currRPN;
    }

    public String rpnResult () {
        char[] rpn = rpn(str);
        Stack<Double> stack = new Stack<>();
        char[] temp = new char[rpn.length];
        int tempInx = 0;
        double currDigit;
        int firstInx = (rpn[0] == ' ') ? 1 : 0;
        for (int i = firstInx; i < rpn.length; i++) {
            if (rpn[i] != '\u0000') {
                if(rpn[i] >= '0' && rpn[i] <= '9' || rpn[i] == '.') {
                    temp[tempInx++] = rpn[i];
                } else {
                    if (tempInx != 0) {
                        currDigit = charArrayToInt(temp);
                        stack.push(currDigit);
                        Arrays.fill(temp, '\u0000');
                        tempInx = 0;
                    }
                    if (rpn[i] == ' ') {
                        continue;
                    }
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    double res = -1;
                    switch (rpn[i]) {
                        case '+':
                            res = num1 + num2;
                            break;
                        case '-':
                            res = num1 - num2;
                            break;
                        case '*':
                            res = num1 * num2;
                            break;
                        case '/':
                            res = num1 / num2;
                            break;
                    }
                    stack.push(res);
                }
            }
        }
        double ans = stack.pop();
        int res = (int)ans;
        double res2 = ans - res;
        if (res2 == 0) {
            return Integer.toString(res);
        }
        return Double.toString(ans);
    }
}
