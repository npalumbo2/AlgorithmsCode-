import java.util.Scanner;
public class PalindromeCheck {
    public static void main(String[] args) {
        String proccessed = "";
        System.out.print("Please enter your desired string:");
        String input = new Scanner(System.in).nextLine();
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (((int)input.charAt(i)) <= 122 && ((int)input.charAt(i)) >= 97) {
                proccessed = proccessed + input.substring(i, i + 1);
            }
        }
        System.out.println("Proccessed string: " + proccessed);
        if (isPalindrome(proccessed)) {
            System.out.println("The word is a palindrome.");
        } else {
            System.out.println("The word is not a palindrome.");
        }
    }
    public static boolean isPalindrome(String s) {
        int i = 0; 
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
