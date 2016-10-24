package sample;

public class Model {
    public String format(String buffer, String answer, String operator){
        switch (operator){
            case "AC":
                return "";
            case "Del":
                if (!buffer.isEmpty()){
                    return buffer.substring(0, buffer.length() - 1);
                }
                else
                    return "";
            case "Ans":
                return answer;
        }
        return "";
    }

    public String convert(String operator){
        switch (operator){
            case "!":
                return "factorial";
            case "abs":
                return "fabs";
            case "ctg":
                return "";
            case "arcctg":
                return "";
            case "ctgh":
                return "";
            case "^":
                return "pow";
            case "f'":
                return "integrate";
        }
        return "";
    }
}
