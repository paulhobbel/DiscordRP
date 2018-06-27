package me.paulhobbel.discordrp.common.util;

public final class StringUtils {
    private StringUtils() {}

    public static String join(CharSequence delimiter, Object... elements) {
        StringBuilder builder = new StringBuilder();

        for(Object element : elements) {
            if(builder.length() != 0) {
                builder.append(delimiter);
            }
            builder.append(element);
        }

        return builder.toString();
    }
}
