package codingfabian.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AnswerTest {

  AnswerImpl answerImpl;

  @Before
  public void setup() {
    answerImpl = new AnswerImpl();
  }

  @Test
  public void testFoo1() {
    String question = "bca6ebe0: which of the following numbers is the largest: 60, 254, 61, 330".substring(10);
    assertEquals(
        "which of the following numbers is the largest: 60, 254, 61, 330",
        question);
    String question2 = "64644450: which of the following numbers is the largest: 17, 346".substring(10);
    assertEquals("which of the following numbers is the largest: 17, 346",
        question2);
  }

  @Test
  public void testFoo2() {
    String question = "7052e6e0: what is 19 plus 4".substring(10);
    assertEquals("what is 19 plus 4", question);
  }

  @Test
  public void testFoo3() {
    String question = "which of the following numbers is both a square and a cube: 9, 64, 762, 970, 625, 256";
    assertEquals("64", answerImpl.returnSquareQube(question));
  }

  @Test
  public void testFoo4() {
    String question = "which of the following numbers is both a square and a cube: 118, 1444";
    assertEquals("", answerImpl.returnSquareQube(question));
  }

  @Test
  public void testFoo5() {
    String question = "what is 4 plus 2";
    assertEquals("6", answerImpl.returnBaseMath(question));
  }

  @Test
  public void testFoo6() {
    String question = "what is 4 multiplied by 2";
    assertEquals("8", answerImpl.returnBaseMath(question));
  }

  @Test
  public void testFoo7() {
    String question = "7052e6e0: what is the 5th number in the Fibonacci sequence";
    assertEquals("5", answerImpl.forQuestion(question));
  }

  @Test
  public void testFoo8() {
    String question = "fd17b460: what is 12 to the power of 6";
    assertEquals("2985984", answerImpl.forQuestion(question));
  }

  @Test
  public void testFoo9() {
    String question = "fd17b460: what is the english scrabble score of cloud";
    assertEquals("8", answerImpl.forQuestion(question));
  }
}
