package com.example.beex.utils.mask.tox;

/**
 * Created by Tox on 12/8/15.
 */
public class SimpleMaskFormatter extends MaskFormatter {

    public static class SimpleMaskCharacter {
        static final String NUMBER = "N";
        static final String LETTER = "L";
        static final String ALPHANUMERIC = "A";
        static final String LOWERCASE = "l";
        static final String UPPERCASE = "U";
    }

    public SimpleMaskFormatter(String mask) {
        super(mask);

        registerPattern(SimpleMaskCharacter.NUMBER, new MaskPattern("\\p{Digit}"));
        registerPattern(SimpleMaskCharacter.LETTER, new MaskPattern("\\p{Alpha}"));
        registerPattern(SimpleMaskCharacter.ALPHANUMERIC, new MaskPattern("\\p{Alnum}"));
        registerPattern(SimpleMaskCharacter.LOWERCASE, new MaskPattern("\\p{Lower}"));
        registerPattern(SimpleMaskCharacter.UPPERCASE, new MaskPattern("\\p{Upper}"));
    }
}
