package de.treu.extreme;

import de.britter.extremestartup.Plus;
import junit.framework.TestCase;

/**
 * Created by treu on 08/05/15.
 */
public class PlusTest extends TestCase {

    public void testPlus() {
        assertEquals("11", Plus.plus("7b8d0d40: what is 4 multiply 7"));
        assertEquals("10", Plus.plus("7b8d0d40: what is 5 multiply 5"));
        assertEquals("30", Plus.plus("7b8d0d40: what is 10 multiply 20"));
    }
}
