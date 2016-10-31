package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonCaller {

    public void call() throws IOException {
        // set up the command and parameter
        String pythonScriptPath = "C:\\Users\\vladi\\calc\\test.py";
        String[] cmd = new String[2];
        cmd[0] = "C:\\Users\\vladi\\AppData\\Local\\Programs\\Python\\Python35-32\\python.exe";
        cmd[1] = pythonScriptPath;

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);

        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = bfr.readLine()) != null) {
            System.out.println(line);
        }
    }
}