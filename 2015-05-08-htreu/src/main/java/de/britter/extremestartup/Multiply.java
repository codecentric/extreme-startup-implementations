package de.britter.extremestartup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by treu on 08/05/15.
 */
public class Multiply {

    public static String multiply(String q) {
        Pattern p = Pattern.compile(".*?what is (\\d*?) multiplied by (\\d*?)");
        Matcher m = p.matcher(q);
        if (m.matches()) {
            int one = Integer.parseInt(m.group(1));
            int two = Integer.parseInt(m.group(2));

            return "" + (one * two);
        }

        return null;
    }
}

