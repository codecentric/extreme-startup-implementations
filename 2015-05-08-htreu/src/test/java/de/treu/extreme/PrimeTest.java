package de.treu.extreme;

import de.britter.extremestartup.Multiply;
import de.britter.extremestartup.Prime;
import junit.framework.TestCase;

/**
 * Created by treu on 08/05/15.
 */
public class PrimeTest extends TestCase {

    public void testPrime() {
        assertEquals("71", Prime.prime("49af0ef0: which of the following numbers are primes: 730, 71"));
    }
}
