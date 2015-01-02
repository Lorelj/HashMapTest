package hr.tvz.AIS.HashMapTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

class App {

  private static final TimeUnit TIME_UNIT = TimeUnit.NANOSECONDS;

  private static final String CSV_DELIMITER = ";";

  public static void main(String[] args) {
    System.out.println("HashMap test started");
    insertionTests();
    deletionTests();
    fetchingTests();
    System.out.println("HashMap test finished");
  }

  private static void insertionTests() {
    PrintWriter fileWriter = null;
    try {
      fileWriter = new PrintWriter(new FileWriter("Insertion.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileWriter.println("Broj elemenata" + CSV_DELIMITER + "Vrijeme (ns)");
    fileWriter.println("1000" + CSV_DELIMITER + insertionTest(1000));
    fileWriter.println("2000" + CSV_DELIMITER + insertionTest(2000));
    fileWriter.println("5000" + CSV_DELIMITER + insertionTest(5000));
    fileWriter.println("7500" + CSV_DELIMITER + insertionTest(7500));
    fileWriter.println("10000" + CSV_DELIMITER + insertionTest(10000));
    fileWriter.close();
  }

  private static void fetchingTests() {
    PrintWriter fileWriter = null;
    try {
      fileWriter = new PrintWriter(new FileWriter("Fetching.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileWriter.println("Broj elemenata" + CSV_DELIMITER + "Vrijeme (ns)");
    fileWriter.println("1000" + CSV_DELIMITER + fetchingTest(1000));
    fileWriter.println("2000" + CSV_DELIMITER + fetchingTest(2000));
    fileWriter.println("5000" + CSV_DELIMITER + fetchingTest(5000));
    fileWriter.println("7500" + CSV_DELIMITER + fetchingTest(7500));
    fileWriter.println("10000" + CSV_DELIMITER + fetchingTest(10000));
    fileWriter.close();
  }

  private static void deletionTests() {
    PrintWriter fileWriter = null;
    try {
      fileWriter = new PrintWriter(new FileWriter("Deletion.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileWriter.println("Broj elemenata" + CSV_DELIMITER + "Vrijeme (ns)");
    fileWriter.println("1000" + CSV_DELIMITER + deletionTest(1000));
    fileWriter.println("2000" + CSV_DELIMITER + deletionTest(2000));
    fileWriter.println("5000" + CSV_DELIMITER + deletionTest(5000));
    fileWriter.println("7500" + CSV_DELIMITER + deletionTest(7500));
    fileWriter.println("10000" + CSV_DELIMITER + deletionTest(10000));
    fileWriter.close();
  }

  public static HashMap<Integer, Integer> fillHashMap(int numOfElements) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < numOfElements; i++) {
      map.put(i, i);
    }
    return map;
  }

  public static long insertionTest(int numOfElements) {
    long sum = 0;
    for (int i = 0; i < 1000; i++) {
      HashMap<Integer, Integer> map = fillHashMap(numOfElements);
      Stopwatch stopwatch = Stopwatch.createStarted();
      map.put(numOfElements + 1, numOfElements + 1);
      stopwatch.stop();
      sum += stopwatch.elapsed(TIME_UNIT);
    }
    return sum / 1000;
  }

  public static long deletionTest(int numOfElements) {
    long sum = 0;
    for (int i = 0; i < 1000; i++) {
      HashMap<Integer, Integer> map = fillHashMap(numOfElements);
      Stopwatch stopwatch = Stopwatch.createStarted();
      map.remove((int) (numOfElements * 0.8)); // brise se broj koji je na
      // 80% npr. za 1000
      // elemenata obrisat ce
      // se element sa kljucem 800
      stopwatch.stop();
      sum += stopwatch.elapsed(TIME_UNIT);
    }
    return sum / 1000;
  }

  public static long fetchingTest(int numOfElements) {
    long sum = 0;
    for (int i = 0; i < 1000; i++) {
      HashMap<Integer, Integer> map = fillHashMap(numOfElements);
      Stopwatch stopwatch = Stopwatch.createStarted();
      map.get((int) (numOfElements * 0.8)); // dohvaca se broj koji je na
      // 80% npr. za 1000
      // elemenata dohvatit ce se
      // element sa kljucem 800
      stopwatch.stop();
      sum += stopwatch.elapsed(TIME_UNIT);
    }
    return sum / 1000;
  }
}
