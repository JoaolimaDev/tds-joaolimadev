package com.tds.shortener.config;

import java.util.regex.Pattern;

/**
 * inputConfig
 */

public interface inputConfig {

    public default String urlValidation(String url){


        if (!Pattern.compile("https://.*\\.com").matcher(url).find()) {
            return "O input url deve conter https: e .com !";
        }

        return null;
    }
}