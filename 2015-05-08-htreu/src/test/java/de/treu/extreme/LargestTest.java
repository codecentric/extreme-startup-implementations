package de.treu.extreme;

import de.britter.extremestartup.Largest;
import de.britter.extremestartup.Multiply;
import junit.framework.TestCase;

/**
 * Created by treu on 08/05/15.
 */
public class LargestTest extends TestCase {

    public void testLargest() {
        assertEquals("100", Largest.largest("596a6bb0: which of the following numbers is the largest: 90, 16, 5, 68, 100"));
    }
}
