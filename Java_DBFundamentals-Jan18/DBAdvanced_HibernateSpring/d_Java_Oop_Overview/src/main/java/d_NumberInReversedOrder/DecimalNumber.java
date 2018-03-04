package d_NumberInReversedOrder;

import java.math.BigDecimal;
import java.util.Arrays;

public class DecimalNumber {

    private BigDecimal num;

    public DecimalNumber(){
        this.num = new BigDecimal("0");
    }
    
    public DecimalNumber(String numStr) {
        this.setNum(numStr);
    }

    public BigDecimal getNum() {
        return this.num;
    }

    public void setNum(String numStr) {
        this.num = new BigDecimal(numStr);
    }

    public String reverseDigits(){
        char[] reversed = this.num.toString().toCharArray();
        StringBuilder output = new StringBuilder();
        for (int i = reversed.length - 1; i > -1; i--) {
             output.append(reversed[i]);
        }

        return output.toString();
    }
}
