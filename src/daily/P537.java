package daily;

public class P537 {
    public String complexNumberMultiply(String num1, String num2) {
        String num1Real = num1.substring(0,num1.indexOf("+"));
        String num2Real = num2.substring(0,num2.indexOf("+"));

        String num1Imaginary = num1.substring(num1.indexOf("+")+1,num1.indexOf("i"));
        String num2Imaginary = num2.substring(num2.indexOf("+")+1,num2.indexOf("i"));
        System.out.println(num1Real + " " + num1Imaginary );
        System.out.println(num2Real + " " + num2Imaginary );

        StringBuilder sb = new StringBuilder();
        int resReal = Integer.valueOf(num1Real) * Integer.valueOf(num2Real) - Integer.valueOf(num1Imaginary) * Integer.valueOf(num2Imaginary);
        int resImaginary = Integer.valueOf(num1Real) * Integer.valueOf(num2Imaginary) + Integer.valueOf(num2Real) * Integer.valueOf(num1Imaginary);
        sb.append(String.valueOf(resReal)).append("+").append(String.valueOf(resImaginary)).append("i");
        return sb.toString();

    }

    public static void main(String[] args) {
        String res = new P537().complexNumberMultiply("-1+-1i","1+-1i");
        System.out.println(res);
    }
}
