package de.britter.extremestartup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by treu on 08/05/15.
 */
public class Largest {

    public static String largest(String q) {
        String s = "596a6bb0: which of the following numbers is the largest: 68, 16";
        Pattern p = Pattern.compile(".*?which of the following numbers is the largest: (\\d*), (\\d*),?\\s?(\\d*)?,?\\s?(\\d*)?,?(\\d*)?");
        Matcher m = p.matcher(q);
        List<Integer> result = new ArrayList<Integer>();
        if (m.matches()) {
            for (int i = 1; i < (m.groupCount()); i++) {
                String digit = m.group(i).replace(",", "").replace(" ", "");
                try {
                    result.add(Integer.parseInt(digit));
                } catch (Exception e) {
                    //
                }
            }
        }
        Collections.sort(result);
        return result.isEmpty() ? null : ("" + result.get(result.size() - 1));
    }
}
