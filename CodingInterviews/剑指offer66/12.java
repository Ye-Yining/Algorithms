import java.lang.*;
public class Solution {
    public double Power(double base, int exponent) {
        //return Math.pow(base, exponnet);
        
        //when base is 0.0,if exponent < 0. then the result is meanfulless.
        if (Double.compare(base, 0.0) == 0)
            return 0.0;
        int absExponent = 0;
        if (exponent > 0){
            absExponent = exponent;
            return PowerWithAbseExponent(base, absExponent);
        }
        //when exponnet < 0, then the result would be - Power(base, -exponent), which is negative
        else{
            absExponent = -exponent;
            return 1 / PowerWithAbseExponent(base, absExponent);
        }
  }
    public static double PowerWithAbseExponent(double base, int exponent){
        double result = 1.0;
        for (int i = 0; i < exponent; i++){
            result = base * result;
        }
        return result;
    }
}