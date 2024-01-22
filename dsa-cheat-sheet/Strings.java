public class StringDemo {
    public static void main(String args[]) {
        // LENGTH OF STRING
        String s = new String("123456");
        int n = s.length();

        // CHARACTER AT A PARTICULAR INDEX
        char ch = s.charAt(1);


        StringBuilder sb = new StringBuilder();

        // CONVERT TO CHAR ARRAY
        char[] charArray = str.toCharArray();

        // CONVERT CHAR ARRAY TO STRING
        char[] charArray2 = {'H', 'e', 'l', 'l', 'o'};
        String str2 = String.valueOf(charArray2);
        String str3 = new String(charArray2);

        // SUBSTRING
        String str = "Hello, World!";
        String substring2 = str.substring(7, 12); // Extracts from index 7 to 11 (exclusive)
        System.out.println(substring2); // Output: World

        char ch = 'a';
        sb.append(ch); // Appends the character to the end of the StringBuilder

        String str = "hello";
        sb.append(str); // Appends the string to the end of the StringBuilder

        System.out.println(sb.toString()); // Output: ahello


        sb = new StringBuilder("hello");
        ch = 'a';
        sb.insert(1, ch); // Inserts the character at index 1 in the StringBuilder

        str = "world";
        sb.insert(4, str); // Inserts the string at index 4 in the StringBuilder

        System.out.println(sb.toString()); // Output: haelworldlo
    }
}