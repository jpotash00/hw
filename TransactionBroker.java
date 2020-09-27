import java.text.DecimalFormat;
import java.util.*;

public class TransactionBroker{
    private static double balance = 500d;
    private static int penaltyCounter = 0;
    private static DecimalFormat x = new DecimalFormat("$###,###,###.00");

    public static boolean isValidDouble(String str){
        try {
            Double.parseDouble(str);
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
                return 0;//exponentiate(op1, op2);
            }
        } 
            return 0;      
    } 
       // public static operationFormat(double op1, String operator, double op2){
        
        // public static double exponentiate(double base, double exponent) {
        //     if (operator.equalsIgnoreCase("pow")){
        //         return(Math.pow(op1,op2));  
        //     }
        // }
    private static double parseToken(String token) {
        if (isValidDouble(token)){
            return Double.parseDouble(token);
        }
            // else if (isValidOperator(token)){
            //     return token;
            // }
        return 0;        
    }
    public static void main(String[]args){
        for (int i = 0; i < args.length; i++){
            String token = args[i];
            double tokenTransaction = parseToken(token);
            if ((balance >= 500) && (balance + tokenTransaction < 500)){
                balance += tokenTransaction;
                System.out.println("Your last transaction lowered your balance to " + x.format(balance));
                penalty(tokenTransaction, penaltyCounter);
                System.out.println("Your balance: " + x.format(balance));
            }
            else {
                balance += tokenTransaction;
                System.out.println("Your balance: " + x.format(balance));
            }
        }
    }
    public static void penalty(double moneyTransaction, int penaltyCounter) {
        double balancePenalty = 20;
        balance -= balancePenalty;
        penaltyCounter++;
        System.out.println("You have been charge a low balance penalty of " + x.format(balancePenalty)); 
    }    
}