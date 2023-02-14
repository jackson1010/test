package nus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextProcessor {

    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Integer>> bigMap = new HashMap<>();
        Map<String, Integer> wordCount = new HashMap<>();
        Map<String, Integer> currentCount =new HashMap<>();

        String p = args[0];
        File directory = new File(p);
        File[] f = directory.listFiles();

        for (File file : f) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    //br.readLine();
                    System.out.println(file.getName());

                    while ((line = br.readLine()) != null) {
                        String newLine = line.replaceAll("[^a-zA-Z0-9 ]", "");
                        System.out.printf("> %s\n", newLine.toLowerCase());
                        String[] words = newLine.toLowerCase().split(" ");

                        for (int i = 0; i < words.length - 1; i++) {
                            String currentWord = words[i];
                            String NextWord = words[i + 1];
                            wordCount = bigMap.getOrDefault(currentWord, new HashMap<>());
                            wordCount.put(NextWord, wordCount.getOrDefault(NextWord, 0) + 1);
                            currentCount.put(currentWord,currentCount.getOrDefault(currentWord, 0)+1);
                            bigMap.put(currentWord, wordCount);
                        }
                    }
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        for (String word : bigMap.keySet()) {
            wordCount = bigMap.get(word);
            int currentWordCount = currentCount.get(word);
            System.out.print(word);
            System.out.println();
            for (String nextWord : wordCount.keySet()) {
                int count = wordCount.get(nextWord);
                double probability = (double) count/currentWordCount;
                System.out.println("    " + nextWord + " " + probability);
            }
        }

    }

    private static int getTotalLine(BufferedReader br) throws IOException {
        int count = 0;
        while (br.readLine() != null) {
            count++;
        }
        return count++;
    }
}