package availty;

import java.util.Scanner;

public class CheckParentheses {

    public static boolean validParentheses(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch =='(') {
                ++count;
            } else if (ch == ')') {
                --count;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter string:");
        String input = scan.next();
        Boolean valid = validParentheses(input);
        System.out.println(valid);
    }
}
