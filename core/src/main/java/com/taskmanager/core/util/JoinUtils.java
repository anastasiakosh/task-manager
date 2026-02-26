package com.taskmanager.core.util;

import java.util.List;

class JoinUtils {

    public static String join(List<String> source) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.size(); ++i) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(source.get(i));
        }
        return result.toString();
    }
}
