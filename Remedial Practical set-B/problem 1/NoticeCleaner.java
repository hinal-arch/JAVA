import java.util.Scanner;

public class NoticeCleaner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String keyword = sc.nextLine();

        String[] words = line.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(words[i].toLowerCase());
        }

        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

        String shortest = words[0].toLowerCase();
        int keywordCount = 0;
        int vowels = 0;

        for (String word : words) {
            String w = word.toLowerCase();

            if (w.length() < shortest.length()) {
                shortest = w;
            }

            if (w.equalsIgnoreCase(keyword)) {
                keywordCount++;
            }
        }

        for (int i = 0; i < line.length(); i++) {
            char ch = Character.toLowerCase(line.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                vowels++;
            }
        }

        System.out.println("Sentence: " + sb);
        System.out.println("Words: " + words.length);
        System.out.println("Shortest word: " + shortest);
        System.out.println("Keyword count: " + keywordCount);
        System.out.println("Vowels: " + vowels);

        sc.close();
    }
}
