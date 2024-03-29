package com.example.beex.utils.mask.tox;

import java.util.regex.Pattern;

/**
 * Created by Tox on 12/9/15.
 */
public class MaskPattern {

    private Pattern p;
    private String pattern;

    MaskPattern(String pattern) {
        this.p = Pattern.compile(pattern);
        this.pattern = pattern;
    }

    private String getPattern() {
        return pattern;
    }

    boolean isValid(String current, char c) {
        return p.matcher(String.valueOf(c)).matches();
    }

    String transform(char c) {
        return String.valueOf(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof MaskPattern)) return false;

        MaskPattern lhs = (MaskPattern) o;
        return pattern.equals(lhs.getPattern());
    }

    @Override
    public int hashCode() {
        return 33 * pattern.hashCode();
    }
}
