package de.treu.extreme;

import de.britter.extremestartup.Multiply;
import de.britter.extremestartup.Plus;
import junit.framework.TestCase;

/**
 * Created by treu on 08/05/15.
 */
public class MultiplyTest extends TestCase {

    public void testMultiply() {
        assertEquals("28", Multiply.multiply("7b8d0d40: what is 4 multiplied by 7"));
        assertEquals("25", Multiply.multiply("7b8d0d40: what is 5 multiplied by 5"));
        assertEquals("200", Multiply.multiply("7b8d0d40: what is 10 multiplied by 20"));
    }
}
