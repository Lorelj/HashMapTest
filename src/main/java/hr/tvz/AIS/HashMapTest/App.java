package hr.tvz.AIS.HashMapTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

class App {

	private static final TimeUnit TIME_UNIT = TimeUnit.NANOSECONDS;

	public static void main(String[] args) {
		System.out.println("HashMap test started");
		insertionTests();
		deletionTests();
		fetchingTests();
		System.out.println("HashMap test finished");
	}

	private static void insertionTests() {
		insertionTest(1000);
		insertionTest(2000);
		insertionTest(5000);
		insertionTest(7500);
		insertionTest(10000);
	}

	private static void fetchingTests() {
		fetchingTest(1000);
		fetchingTest(2000);
		fetchingTest(5000);
		fetchingTest(7500);
		fetchingTest(10000);
	}

	private static void deletionTests() {
		deletionTest(1000);
		deletionTest(2000);
		deletionTest(5000);
		deletionTest(7500);
		deletionTest(10000);
	}

	public static HashMap<Integer, Integer> fillHashMap(int numOfElements) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < numOfElements; i++) {
			map.put(i, i);
		}
		return map;
	}

	public static void insertionTest(int numOfElements) {
		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new FileWriter("Insertion"
					+ numOfElements + ".csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 1000; i++) {
			HashMap<Integer, Integer> map = fillHashMap(numOfElements);
			Stopwatch stopwatch = Stopwatch.createStarted();
			map.put(numOfElements + 1, numOfElements + 1);
			stopwatch.stop();
			fileWriter.println(stopwatch.elapsed(TIME_UNIT));
		}
		fileWriter.close();
	}

	public static void deletionTest(int numOfElements) {
		PrintWriter fileWriter = null;

		try {
			fileWriter = new PrintWriter(new FileWriter("Deletion"
					+ numOfElements + ".csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 1000; i++) {
			HashMap<Integer, Integer> map = fillHashMap(numOfElements);
			Stopwatch stopwatch = Stopwatch.createStarted();
			map.remove((int) (numOfElements * 0.8)); // brise se broj koji je na
														// 80% npr. za 1000
														// elemenata obrisat ce
														// se element sa kljucem 800
			stopwatch.stop();
			fileWriter.println(stopwatch.elapsed(TIME_UNIT));
		}
		fileWriter.close();
	}

	public static void fetchingTest(int numOfElements) {
		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new FileWriter("Fetching"
					+ numOfElements + ".csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 1000; i++) {
			HashMap<Integer, Integer> map = fillHashMap(numOfElements);
			Stopwatch stopwatch = Stopwatch.createStarted();
			map.get((int) (numOfElements * 0.8)); // dohvaca se broj koji je na
													// 80% npr. za 1000
													// elemenata dohvatit ce se
													// element sa kljucem 800
			stopwatch.stop();
			fileWriter.println(stopwatch.elapsed(TIME_UNIT));
		}
		fileWriter.close();
	}
}
