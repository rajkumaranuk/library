package com.sky.library;

import java.text.BreakIterator;

public class StringUtil {

    public static String truncate(final String text, final int maxWords) {
        if (text == null) {
            return null;
        }
        final BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int position = 0;
        for (int i = 0; i < maxWords && position != BreakIterator.DONE && position < text.length(); ) {
            if (Character.isLetter(text.codePointAt(position))) {
                i++;
            }
            position = breakIterator.next();
        }
        if (position == BreakIterator.DONE || position >= text.length()) {
            return text;
        }
        return text.substring(0, position) + "...";
    }
}
