import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HashTable {
    public static HashTableEntry[] table;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Prompt for file path
        String A1 = "src/TwelfthNightActOne.txt";
        String FULL = "src/TwelfthNightFULL.txt";
        System.out.print("Enter the file path: ");
        String filePath = sc.nextLine();

        // Prompt for hash table size
        // 1499
        // 4000 for full text
        System.out.print("Enter the hash table size: ");
        int tableSize = sc.nextInt();
        sc.close();

        // Reading the file
        Scanner fileScanner = new Scanner(new File(filePath));
        fileScanner.useDelimiter("\\Z");
        String text = fileScanner.next();
        fileScanner.close();

        // Splitting the text into unique words
        String[] words = text.replace("\n", " ").replace("\r", " ").replaceAll("[^A-Za-z\\s\\-'']", "").split(" ");
        ArrayList<String> uniqueWords = new ArrayList<String>();
        ArrayList<String> seenWords = new ArrayList<String>();
        for (String word : words) {
            if (!seenWords.contains(word)) {
                seenWords.add(word);
                uniqueWords.add(word);
            }
        }

        // Creating the hash table
        table = new HashTableEntry[tableSize];
        for (String word : uniqueWords) {
            int hashValue = hashFunction(word, tableSize);
            int index = hashValue;
            int i = 0;
            while (table[(index + i)] != null && (index + i) < tableSize - 1) {
                i++;
            }
            if ((index + i) < tableSize) {
                table[(index + i)] = new HashTableEntry((index + i), word, hashValue);
            } else {
                while (table[(index + i) % tableSize] != null) {
                    i++;
                }
                table[(index + i) % tableSize] = new HashTableEntry((index + i) % tableSize, word, hashValue);
            }
        }

        // Printing the hash table, filling empty slots with -1
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null) {
                System.out.println(table[i].index + " " + table[i].word + " " + table[i].hashValue);
            } else {
                table[i] = new HashTableEntry(i, null, -1);
                System.out.println(i + "  " + -1);
            }
        }
        loadFactor();
        longestEmptyArea();
        longestCluster();
        mostCommonHashValue();
        wordFarthestFromHash();

    }

    // Hash function
    public static int hashFunction(String s, int tableSize) {
        int hashValue = 0;
        for (int i = 0; i < s.length(); i++) {
            hashValue = (hashValue * 123 + (int) s.charAt(i)) % tableSize;
        }
        return hashValue;
    }

    // a. Calculate non-empty addresses and load factor
    public static void loadFactor() {
        int nonEmptyCount = 0;
        for (HashTableEntry hte : table) {
            if (hte.hashValue != -1)
                nonEmptyCount++;
        }
        double lf = (double) nonEmptyCount / table.length;
        System.out.println("Non-empty addresses: " + nonEmptyCount + ", Load factor: " + lf);
    }

    // b. Find the longest empty area in the table
    public static void longestEmptyArea() {
        int maxLength = 0;
        int currentLength = 0;
        int startIdx = -1;
        int bestStartIdx = -1;

        for (int i = 0; i < table.length; i++) {
            if (table[i].hashValue == -1) {
                currentLength++;
                if (startIdx == -1)
                    startIdx = i;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    bestStartIdx = startIdx;
                }
                currentLength = 0;
                startIdx = -1;
            }
        }

        // Check if the empty area wraps around the end of the array
        if (currentLength > 0 && table[0].hashValue == -1) {
            int wrapCount = 0;
            for (int i = 0; i < table.length && table[i].hashValue == -1; i++) {
                wrapCount++;
            }
            if (currentLength + wrapCount > maxLength) {
                maxLength = currentLength + wrapCount;
                bestStartIdx = startIdx;
            }
        }

        System.out.println("Longest empty area length: " + maxLength + ", Starting at index: " + bestStartIdx);
    }

    // c. Find the longest cluster in the table
    public static void longestCluster() {
        int maxLength = 0;
        int currentLength = 0;
        int startIdx = -1;
        int bestStartIdx = -1;

        for (int i = 0; i < table.length; i++) {
            if (table[i].hashValue != -1) {
                currentLength++;
                if (startIdx == -1)
                    startIdx = i;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    bestStartIdx = startIdx;
                }
                currentLength = 0;
                startIdx = -1;
            }
        }

        // Check for wrapping at the end
        if (currentLength > 0 && table[0].hashValue != -1) {
            int wrapCount = 0;
            for (int i = 0; i < table.length && table[i].hashValue != -1; i++) {
                wrapCount++;
            }
            if (currentLength + wrapCount > maxLength) {
                maxLength = currentLength + wrapCount;
                bestStartIdx = startIdx;
            }
        }

        System.out.println("Longest cluster length: " + maxLength + ", Starting at index: " + bestStartIdx);
    }

    // d. Hash value with the greatest number of distinct words
    public static void mostCommonHashValue() {
        HashMap<Integer, Set<String>> hashMap = new HashMap<>();

        // Populate the HashMap with each hash value pointing to a set of distinct words
        for (HashTableEntry hte : table) {
            if (hte.hashValue != -1) {
                hashMap.computeIfAbsent(hte.hashValue, k -> new HashSet<>()).add(hte.word);
            }
        }

        // Find the hash value with the greatest number of distinct words
        int mostCommonHashValue = Collections.max(hashMap.entrySet(),
                Map.Entry.comparingByValue((set1, set2) -> Integer.compare(set1.size(), set2.size())))
                .getKey();
        int numberOfDistinctWords = hashMap.get(mostCommonHashValue).size();

        System.out.println("Hash value with most distinct words: " + mostCommonHashValue +
                ", Number of words: " + numberOfDistinctWords);
    }

    // e. Word farthest from its actual hash value
    public static void wordFarthestFromHash() {
        String farthestWord = null;
        int maxDistance = -1;
        int originalHash;

        for (HashTableEntry hte : table) {
            if (hte.hashValue != -1) {
                originalHash = hte.hashValue;
                int distance = Math.abs(hte.index - originalHash);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    farthestWord = hte.word;
                }
            }
        }

        System.out.println("Word farthest from hash value: " + farthestWord + ", Distance: " + maxDistance);
    }
}

class HashTableEntry {
    int index;
    String word;
    int hashValue;

    public HashTableEntry(int index, String word, int hashValue) {
        this.index = index;
        this.word = word;
        this.hashValue = hashValue;
    }
}
