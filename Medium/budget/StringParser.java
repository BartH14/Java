package budget;

public class StringParser {

    public float getDollarAmount(String string) {
        int tokenIndex = string.lastIndexOf('$');
        String dollarString = string.substring(tokenIndex+1);
        return Float.parseFloat(dollarString);
    }

    public String getProductName(String string) {
        int tokenIndex = string.lastIndexOf('$');
        return string.substring(0, tokenIndex - 1);
    }
}
