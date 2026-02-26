package com.taskmanager.core.util;

import java.util.LinkedList;
import java.util.List;

class SplitUtils {

    public static List<String> split(String source) {
        int lastFind = 0;
        int currentFind = 0;
        List<String> result = new LinkedList<>();

        while ((currentFind = source.indexOf(" ", lastFind)) != -1) {
            String token = source.substring(lastFind, currentFind);
            addIfValid(token, result);
            lastFind = currentFind + 1;
        }

        String token = source.substring(lastFind);
        addIfValid(token, result);

        return result;
    }

    private static void addIfValid(String token, List<String> list) {
        if (isTokenValid(token)) {
            list.add(token);
        }
    }

    private static boolean isTokenValid(String token) {
        return !token.isEmpty();
    }
}
