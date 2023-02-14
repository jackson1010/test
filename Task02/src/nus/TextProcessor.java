package nus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class TextProcessor {

    public static void main(String[] args) throws IOException {

        Path p = Paths.get(args[0]);
        File f = p.toFile();

        System.out.println(p.toString());

        Map<String, Map<String, Integer>> bigMap = new HashMap<>();
        Map<String, Integer> wordCount = new HashMap<>();

        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            String newLine = line.replaceAll("\\p{Punct}", "");
            System.out.printf("> %s\n", newLine.toLowerCase());
            String[] words = newLine.toLowerCase().split(" ");

            for (int i = 0; i < words.length - 1; i++) {
                String currentWord = words[i];
                String NextWord = words[i + 1];
                wordCount = bigMap.getOrDefault(currentWord, new HashMap<>());
                wordCount.put(NextWord, wordCount.getOrDefault(NextWord, 0) + 1);
                bigMap.put(currentWord, wordCount);
            }
        }
        double probability=0;
        double frequency =0;
        double allNextWords=0;
        for (String word : bigMap.keySet()) {
            wordCount = bigMap.get(word);
            System.out.print(word);
            System.out.println();
            for (String nextWord : wordCount.keySet()) {
                int count = wordCount.get(nextWord);
                System.out.println( "    "+nextWord + " " + count);
            }
        }
            br.close();
            isr.close();
            is.close();
        
    }
}