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
        Map<String, Integer> wordAndCount = new HashMap<>();
        Map<String, Integer> currentCount = new HashMap<>();

        String p = args[0];
        File directory = new File(p);
        File[] f = directory.listFiles();

        for (File file : f) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    br.readLine();
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            String newLine = line.replaceAll("[^a-zA-Z0-9 ]", "");
                            String[] words = newLine.toLowerCase().trim().split(" ");
                            //System.out.println(newLine);

                            for (int i = 0; i < words.length - 1; i++) {
                                String currentWord = words[i];
                                String NextWord = words[i + 1];
                                wordAndCount = bigMap.getOrDefault(currentWord, new HashMap<>());
                                wordAndCount.put(NextWord, wordAndCount.getOrDefault(NextWord, 0) + 1);
                                currentCount.put(currentWord, currentCount.getOrDefault(currentWord, 0) + 1);
                                bigMap.put(currentWord, wordAndCount);
                            }
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
        wordAndCount = bigMap.get(word);
        int currentWordCount = currentCount.get(word);
        if(!word.isEmpty()){
        System.out.print(word);
        System.out.println();
        }
        for (String nextWord : wordAndCount.keySet()) {
        int count = wordAndCount.get(nextWord);
        double probability = (double) count/currentWordCount;
        System.out.printf("    " + nextWord + " %.2f\n", probability);
        }
        }

    }
}