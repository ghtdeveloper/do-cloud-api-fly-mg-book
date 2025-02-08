package com.vortechgroup.queen.skies.utils;

public class RegexPattern {
    public static final String REGEX_ONLY_LETTERS = "^([a-zA-Z]+)?$";
    public static final String REGEX_ONLY_LETTERS_AND_SPACE = "^([a-zA-Z ]+)?$";
    public static final String REGEX_PREFIX_ONLY_NUMBER = "^(0|[1-9][0-9]*)$";
    public static final String REGEX_PREFIX_NUMBER = "^[0-9]*$";
    public static final String REGEX_PREFIX_VALID_PHONE_NUMBER = "^(809|829|849)\\d{7}$";
    public static final String REGEX_PREFIX_VALID_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String REGEX_ONLY_ONE_CHARACTER_UPPERCASE = "^[A-Z]{1}$";
    public static final String REGEX_ALPHANUMERIC = "^([a-zA-Z0-9]+)?$";
    public static final String REGEX_ALPHANUMERIC_AND_SPACE = "^([a-zA-Z0-9 ]+)?$";
    public static final String REGEX_ALPHANUMERIC_UPDT = "^(?=.*[a-zA-Z])[a-zA-Z\\d\\.\\_\\,\\&\\/ ]{1,100}$";
    public static final String REGEX_TEST = "\\b(\\d)\\1{6,}\\b";
    public static final String REGEX_ONLY_CHARACTER = "^[A-Z]";
    public static final String REGEX_ADDRESS_ZIP_CODE = "^\\d{1,11}$";
}
