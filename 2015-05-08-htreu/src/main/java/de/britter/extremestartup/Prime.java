package de.britter.extremestartup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by treu on 08/05/15.
 */
public class Prime {

    public static String prime(String q) {
        Pattern p = Pattern.compile(".*?which of the following numbers are primes: (\\d*), (\\d*),?\\s?(\\d*)?,?\\s?(\\d*)?,?(\\d*)?\"");
        Matcher m = p.matcher(q);
        if (m.matches()) {
            for (int i = 1; i < (m.groupCount()); i++) {
                String digit = m.group(i).replace(",", "").replace(" ", "");
                try {
                    int prime = Integer.parseInt(digit);
                    if (isPrime(prime)) {
                        return digit;
                    }
                } catch (Exception e) {
                    //
                }
            }
        }
        return null;
    }

    private static boolean isPrime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}

