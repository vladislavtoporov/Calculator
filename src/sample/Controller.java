package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller{
    private File in = new File("input.txt");
    private File produce = new File("output.txt");

    @FXML
    private TextArea output;

    @FXML
    private TextArea text;

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
    private void equals() throws FileNotFoundException, java.lang.InterruptedException{
        PrintWriter out = new PrintWriter(produce);
        buffer = output.getText();
        out.println(buffer);
        out.close();

        Scanner scan = new Scanner(produce);
        String str = scan.nextLine();
        scan.close();
        PrintWriter write = new PrintWriter(in);
        try{
            ReversePolishNotation myRPN = new ReversePolishNotation(str);
            write.println(myRPN.rpnResult());
            write.close();
        }
        catch (Exception e){
            write.println("Error");
            write.close();
        }

        while (in.length() == 0 || !in.exists()){
            Thread.sleep(500);
        }
        Scanner sc = new Scanner(in);
        answer = sc.nextLine();
        output.setText(answer);
        buffer = output.getText();
        operator = "";
        start = true;
        sc.close();
        in.delete();
        produce.delete();
    }

    @FXML
    private void functionsEquals() throws java.lang.InterruptedException, IOException{
        PrintWriter out = new PrintWriter(produce);
        buffer = output.getText();
        out.println(buffer);
        out.close();

        PythonCaller pythoncaller = new PythonCaller();
        pythoncaller.call();

        while (in.length() == 0 || !in.exists()){
            Thread.sleep(500);
        }
        Scanner sc = new Scanner(in);
        answer = sc.nextLine();
        output.setText(answer);
        buffer = output.getText();
        operator = "";
        start = true;
        sc.close();
        in.delete();
        produce.delete();
    }

    @FXML
    private void matrixEquals() throws java.lang.InterruptedException, IOException{
        PrintWriter out = new PrintWriter(produce);
        bufferMatrix += output.getText();
        out.print("matrix ");
        out.println(bufferMatrix);
        out.close();

        PythonCaller pythoncaller = new PythonCaller();
        pythoncaller.call();

        while (in.length() == 0 || !in.exists()){
            Thread.sleep(500);
        }
        Scanner sc = new Scanner(in);
        answer = sc.nextLine();
        output.setText(answer);
        sc.close();
        in.delete();
        bufferMatrix = output.getText();
        start = true;
        System.out.println(produce.delete());
    }

    @FXML
    private void matrixMultiply() throws java.lang.InterruptedException{
        bufferMatrix += output.getText() + "\n" + "*" + "\n";
        output.setText("");
    }

    @FXML
    private void matrixSum() throws java.lang.InterruptedException{
        bufferMatrix += output.getText() + "\n" + "+" + "\n";
        output.setText("");
    }

    @FXML
    private void matrixInvertible() throws java.lang.InterruptedException, IOException{
        bufferMatrix += "-1" + "\n";
        output.setText("");
        matrixEquals();
    }

    @FXML
    private void matrixTranspose() throws java.lang.InterruptedException, IOException{
        bufferMatrix += "t" + "\n";
        output.setText("");
        matrixEquals();
    }

    @FXML
    private void matrixSolve() throws java.lang.InterruptedException{
        bufferMatrix += output.getText() + "\n" + "solve" + "\n";
        output.setText("");
    }

    @FXML
    private void helpAbout() throws java.lang.Exception {
        Dialog dialog = new Dialog();
        dialog.about();
    }

    @FXML
    private void helpDoc() throws java.lang.Exception{
        Dialog dialog = new Dialog();
        dialog.doc();
    }
}
