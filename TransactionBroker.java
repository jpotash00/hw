import java.text.DecimalFormat;
import java.util.*;
import java.lang.*;

public class TransactionBroker{
    private static double balance = 500d;
    private static int penaltyCounter = 0;
    private static double balancePenalty = 20;
    private static DecimalFormat x = new DecimalFormat("$###,###,###.0#");

    public static boolean isValidDouble(String token){
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException s){
            return false;
        }
    }
    public static boolean isValidOperator(String operator){
        if (operator.equalsIgnoreCase("add") 
            || operator.equalsIgnoreCase("sub") 
            || operator.equalsIgnoreCase("mul") 
            || operator.equalsIgnoreCase("div") 
            || operator.equalsIgnoreCase("pow") 
            || operator.equalsIgnoreCase("mod"))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public static double operation(double op1, double op2, String operator){
        if (isValidOperator(operator)){
            if (operator.equalsIgnoreCase("add")){
                return (op1 + op2);
            } 
            else if (operator.equalsIgnoreCase("sub")){
                return (op1 - op2); 
            }
            else if (operator.equalsIgnoreCase("mul")){
                return (op1 * op2); 
            }
            else if (operator.equalsIgnoreCase("div")){
                return (op1 / op2);
            }
            else if (operator.equalsIgnoreCase("mod")){
                return (op1 % op2);
            }
            else if (operator.equalsIgnoreCase("pow")){
                return exponentiate(op1, op2);
            }
        } 
            return 0;      
    }  
    public static double exponentiate(double base, double exponent) {
                return (Math.pow(base,exponent));  
        }
    private static double parseToken(String token) {
        if (isValidDouble(token)){
            return Double.parseDouble(token);
    }
            return 0;       
    }
    public static void main(String[]args){
        for (int i = 0; i < args.length; i++){
            String token = args[i];
            String nextToken = "";
            String nextNextToken = "";
            if ((i+1 < args.length-1) && (i+2 < args.length-2)){
                nextToken = args[i+1];
                nextNextToken = args[i+2];
            }
            double tokenTransaction = parseToken(token);
            double nextNextTokenTransaction = parseToken(nextNextToken);
            if (!isValidDouble(token) && isValidOperator(token)){
                error(); 
            }
            else if (!isValidDouble(token) && !isValidOperator(token)){
                opError();
            }
            if (isValidDouble(token) && isValidOperator(nextToken) && isValidDouble(nextNextToken)){
                tokenTransaction = operation(tokenTransaction, nextNextTokenTransaction, nextToken);
                i+=2; 
            }
            else if (isValidDouble(token) && isValidOperator(nextToken) && !isValidDouble(nextNextToken)){
                error();
            }
            if ((balance >= 500) && (balance + tokenTransaction < 500)){
                balance += tokenTransaction;
                System.out.println("Your balance: " + x.format(balance));
                System.out.println("Your last transaction lowered your balance to " + x.format(balance));
                penalty();
                System.out.println("Your balance: " + x.format(balance));
            }
            else {
                balance += tokenTransaction;
                System.out.println("Your balance: " + x.format(balance));
            }
        }
        finalBalance();
    }
    public static void penalty() {
        balance -= balancePenalty;
        penaltyCounter++;
        System.out.println("You have been charged a low-balance penalty of " + x.format(balancePenalty));
      //  System.out.println(penaltyCounter);
    }  
    public static void finalBalance(){
        double penaltySum = penaltyCounter * balancePenalty;
        System.out.println("********************");
        System.out.println("Your final balance: " + x.format(balance));
        System.out.println("The total you were charged in penalties: " + x.format(penaltySum));
    }
    public static void error(){
        System.out.println("The mod operator must be preceded by, and followed by, numeric operands");
        System.exit(0);
    }
    public static void opError(){
        System.out.println("That is not a valid operator and the mod operator must be preceded by, and followed by, numeric operands");
        System.exit(0);
    }
}
