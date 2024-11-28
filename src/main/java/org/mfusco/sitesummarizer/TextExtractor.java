package org.mfusco.sitesummarizer;

import java.util.ArrayDeque;
import java.util.Deque;

class TextExtractor {

    private static final String[] SKIP_TAG = new String[] {"style", "script", "span", "label", "footer", "meta"};

    static String extractText(String html) {
        return extractText(html, Integer.MAX_VALUE);
    }

    static String extractText(String html, int limit) {
        String longestDiv = "";
        StringBuilder completeText = new StringBuilder();
        StringBuilder textSection = new StringBuilder();
        Deque<StringBuilder> divContents = new ArrayDeque<>();

        int cursor = 0;
        while (textSection.length() < limit) {
            cursor = skipTag(html, cursor);
            int closeTagPos = html.indexOf(">", cursor);
            if (closeTagPos < 0) {
                break;
            }
            if (html.startsWith("<div", cursor)) {
                divContents.push(textSection);
                textSection = new StringBuilder();
            } else if (html.startsWith("</div>", cursor)) {
                if (textSection.length() > longestDiv.length()) {
                    longestDiv = textSection.toString();
                }
                textSection = divContents.pop();
            }
            cursor = html.indexOf("<", closeTagPos);
            if (cursor < 0) {
                break;
            }
            String block = html.substring(closeTagPos+1, cursor).trim();
            if (block.length() > 2) {
                char firstChar = block.charAt(0);
                if ((Character.isLetter(firstChar) || firstChar == '"')) {
                    textSection.append(block).append(" ");
                    completeText.append(block).append(" ");
                }
            }
        }

        String extractedText = textSection.length() > longestDiv.length() ? textSection.toString() : longestDiv;
        return extractedText.length() < completeText.length() / 4 ? completeText.toString() : extractedText;
    }

    private static int skipTag(String html, int cursor) {
        int nextCursor = cursor;
        for (String tag : SKIP_TAG) {
            if (html.startsWith("<" + tag, cursor)) {
                nextCursor = html.indexOf("</" + tag + ">", cursor);
                if (nextCursor < 0) {
                    return cursor;
                }
                break;
            }
        }
        return nextCursor > cursor ? skipTag(html, nextCursor) : nextCursor;
    }
}
