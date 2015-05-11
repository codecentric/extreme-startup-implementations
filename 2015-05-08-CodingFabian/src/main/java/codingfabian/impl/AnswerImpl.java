package codingfabian.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerImpl {

  private static final String LARGEST = "which of the following numbers is the largest: ";
  private static final String SQUARE_CUBE = "which of the following numbers is both a square and a cube: ";
  private static final String BASE_MATH = "what is ";
  private static final String PRIME = "which of the following numbers are primes: ";
  private static final String SCRABBLE = "what is the english scrabble score of ";
  private static final String ANAGRAM = "which of the following is an anagram of ";

  private static final Pattern FIBONACCI = Pattern
      .compile("what is the (\\d+)th number in the Fibonacci sequence");

  private static final int[] SCRABBLE_SCORE = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8,
      5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

  public String forQuestion(String parameter) {
    String question = parameter.substring(10);

    Matcher matcher = FIBONACCI.matcher(question);
    if (matcher.matches()) {
      return returnFibbonaci(matcher.group(1));
    }

    if (question.startsWith(ANAGRAM)) {
      return returnAnagram(question);
    }
    if (question.startsWith(SCRABBLE)) {
      return returnScrabble(question);
    }
    if (question.startsWith(LARGEST)) {
      return returnLargestQuestion(question);
    }
    if (question.startsWith(SQUARE_CUBE)) {
      return returnSquareQube(question);
    }
    if (question.startsWith(BASE_MATH)) {
      return returnBaseMath(question);
    }
    if (question.startsWith(PRIME)) {
      return returnPrime(question);
    }
    return returnTrivia(question);
  }

  private String returnTrivia(String question) {
    if ("what colour is a banana".equals(question)) {
      return "yellow";
    }
    if ("who played James Bond in the film Dr No".equals(question)) {
      return "Sean Connery";
    }
    if ("who is the Prime Minister of Great Britain".equals(question)) {
      return "David Cameron";
    }
    if ("which city is the Eiffel tower in".equals(question)) {
      return "Paris";
    }
    if ("what currency did Spain use before the Euro".equals(question)) {
      return "Peseta";
    }
    return "Fabian (OSGi Hero)";
  }

  private String returnAnagram(String question) {
    // TODO count chars of input and check with candidates
    return "";
  }

  private String returnScrabble(String question) {
    String substring = question.substring(SCRABBLE.length());
    String word = substring.toUpperCase();

    int score = 0;
    for (byte Letter : word.getBytes()) {
      if (Letter >= 'A' && Letter <= 'Z') {
        score += SCRABBLE_SCORE[Letter - 'A'];
      }
    }
    return String.valueOf(score);
  }

  private String returnFibbonaci(String group) {
    int fibCount = Integer.parseInt(group) + 1;
    int[] fibs = new int[fibCount];
    fibs[0] = 0;
    fibs[1] = 1;
    for (int i = 2; i <= fibCount; i++) {
      fibs[i] = fibs[i - 1] + fibs[i - 2];
    }
    return String.valueOf(fibs[fibCount - 1]);
  }

  public String returnBaseMath(String question) {
    String substring = question.substring(BASE_MATH.length());
    String[] split = substring.split(" ");
    if ("multiplied".equals(split[1])) {
      return String
          .valueOf(Long.parseLong(split[0]) * Long.parseLong(split[3]));
    }
    if ("plus".equals(split[1])) {
      return String
          .valueOf(Long.parseLong(split[0]) + Long.parseLong(split[2]));
    }
    if ("minus".equals(split[1])) {
      return String
          .valueOf(Long.parseLong(split[0]) - Long.parseLong(split[2]));
    }
    if ("to".equals(split[1])) {
      return String.valueOf((long) Math.pow(Long.parseLong(split[0]),
          Long.parseLong(split[5])));
    }
    return "";
  }

  private String returnLargestQuestion(String question) {
    String param = question.substring(LARGEST.length());
    String[] split = param.split(",");
    List<Long> l = new ArrayList<>();
    for (String string : split) {
      l.add(toLong(string));
    }
    Collections.sort(l);
    return String.valueOf(l.get(l.size() - 1));
  }

  public String returnSquareQube(String question) {
    String param = question.substring(SQUARE_CUBE.length());
    String[] split = param.split(",");
    for (String string : split) {
      if (isCube(string) && isSquare(string)) {
        return string.trim();
      }
    }
    return "";
  }

  private boolean isCube(String string) {
    Long valueOf = toLong(string);
    double sqrt = Math.cbrt(valueOf);
    if ((long) sqrt > 1 && sqrt == (long) sqrt) {
      return true;
    }
    return false;
  }

  private boolean isSquare(String string) {
    Long valueOf = toLong(string);
    double sqrt = Math.sqrt(valueOf);
    if ((long) sqrt > 1 && sqrt == (long) sqrt) {
      return true;
    }
    return false;
  }

  public String returnPrime(String question) {
    String param = question.substring(PRIME.length());
    String[] split = param.split(",");
    ArrayList<String> primes = new ArrayList<>();
    for (String string : split) {
      String trim = string.trim();
      if (isPrime(Integer.parseInt(trim))) {
        primes.add(trim);
      }
    }
    return String.join(", ", primes);
  }

  protected boolean isPrime(int number) {
    if (number < 2) {
      return false;
    } else {
      // iterate over all integers up to sqrt(number)
      for (int i = 2, limit = (int) Math.sqrt(number); i <= limit; i++) {
        if (number % i == 0) {
          return false;
        }
      }
      return true;
    }
  }

  private Long toLong(String string) {
    return Long.valueOf(string.trim());
  }
}
