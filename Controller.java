package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller{
    @FXML
    private TextArea output;

    private boolean start = true;

    private String operator = "";
    private String buffer = "";
    private String bufferMatrix = "";
    private String answer = "input";
    private Model model = new Model();

    @FXML
    private void print(ActionEvent event){
        if (start){
            output.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
        buffer = output.getText();
    }
    @FXML
    private void functions(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        operator = value;
        output.setText(output.getText() + model.convert(operator));
        buffer = output.getText();
        operator = "";
    }
    @FXML
    private void format(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (!operator.isEmpty()) return;
        operator = value;
        output.setText(model.format(buffer, answer, operator));
        buffer = output.getText();
        operator = "";
    }
    @FXML
    private void equals(ActionEvent event) throws FileNotFoundException{
        if (!operator.isEmpty()) return;
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        out.println(buffer);
        answer = sc.next();
        output.setText(answer);
        buffer = output.getText();
        operator = "";
        start = true;
        out.close();
    }

    @FXML
    private void matrixOut(ActionEvent event) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        out.print(bufferMatrix);
        output.setText(sc.next());
        bufferMatrix = "";
        out.close();
    }

    @FXML
    private void matrixColumnsRows(ActionEvent event){
        bufferMatrix += output.getText() + "\n";
        output.setText("");
    }

    @FXML
    private void matrixNext(ActionEvent event){
        bufferMatrix += output.getText() + "\n";
        output.setText("");
    }

    @FXML
    private void matrixMultiply(ActionEvent event){
        bufferMatrix += "*" + "\n";
        output.setText("*");
    }

    @FXML
    private void matrixSum(ActionEvent event){
        bufferMatrix += "+" + "\n";
        output.setText("+");
    }
}
