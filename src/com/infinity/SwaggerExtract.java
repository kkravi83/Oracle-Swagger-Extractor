package com.infinity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SwaggerExtract {

    private static final String inFile = "c:\\temp\\swagger\\hugeswagger.json";
    private static final String outFile = "c:\\temp\\swagger\\out.json";

    private static final String inProgressFile = "c:\\temp\\swagger\\inProgressFile.json";

    public static void main(final String[] args) throws IOException {

        new SwaggerExtract().startProcess();
    }

    private void startProcess() throws IOException {
        final BufferedReader fis = new BufferedReader(new FileReader(new File(inProgressFile)));
        final BufferedWriter fos = new BufferedWriter(new FileWriter(new File(outFile)));

        final Set<String> refs = fis.lines().filter(inLine -> inLine.contains("/definitions/")).map(String::trim)
                .collect(Collectors.toSet());
        final List<String> defs = new ArrayList<String>();
        refs.stream().forEach(ref -> {

            final String tow = "\"" + ref.substring(ref.lastIndexOf("/") + 1);

            defs.add(tow);

        });

        fis.close();
        final BufferedReader fis2 = new BufferedReader(new FileReader(new File(inFile)));
        final List<String> swagString = fis2.lines().collect(Collectors.toList());

        for (final String def : defs) {
            boolean matchFound = false;
            int stackCount = 0;
            for (final String swagLine : swagString) {

                if (swagLine.contains(def)) {
                    matchFound = true;
                }

                if (matchFound) {
                    stackCount += count(swagLine, '{');
                    stackCount -= count(swagLine, '}');
                    fos.write(swagLine);
                    fos.write("\r\n");
                }
                if (matchFound && stackCount == 0) {
                    break;
                }
            }
        }

        fis2.close();
        fos.close();

    }

    private int count(final String str, final char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}
