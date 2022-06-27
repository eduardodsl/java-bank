package utils;

/**
 * Calc contains helper functions aimed to do number conversions.
 * @author Eduardo Augsuto da Silva Leite
 */
public class Calc {
    
    /**
     * Converts cents to money in double format
     * @param value money ammount in cents
     * @return double
     */
    public static double convertToReal(int value){
        return (double)value/100;
    }

    /**
     * Converts a string representation of money (10.99) to cents
     * @param value
     * @return int
     * @throws NumberFormatException
     */
    public static int convertToCents(String value) throws NumberFormatException{
        
        if(value.contains(",") || value.contains(".")){
            
            value = value.replace(",", ".");
            String[] values = value.split("\\.");
            
            if(values.length < 2){
                throw new NumberFormatException("value must contain a decimal value");
            }

            try {
                int money = Integer.parseInt(values[0]);
                if (values[1].length() < 2){
                    values[1] += 0;
                }
                if(values[1].length() > 2){
                    return -1;
                }
                int cents = Integer.parseInt(values[1]);
                return (money*100) + cents;
            }catch(NumberFormatException e){
                throw e;
            }

        }

        try {
            int money = Integer.parseInt(value);
            if(money == 0){
                return -1;
            }
            return money * 100;
        }catch(NumberFormatException e){
            throw e;
        }
        
    }

}
