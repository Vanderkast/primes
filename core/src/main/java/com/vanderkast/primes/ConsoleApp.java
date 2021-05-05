package com.vanderkast.primes;

import com.vanderkast.primes.cache.SimpleInMemoryPrimeCache;
import com.vanderkast.primes.core.BigIntegerPrimeApprove;
import com.vanderkast.primes.core.LongPrimeApprove;

import java.util.Arrays;
import java.util.List;

public class ConsoleApp {
    private final PrimeApproveFacade facade = new PrimeApproveFacade(
            new LongPrimeApprove(),
            new BigIntegerPrimeApprove(),
            new SimpleInMemoryPrimeCache());

    /**
     * <p>Reads passed arguments and test on primality all arguments after -n argument or --numbers=[comma divided values]</p>
     * <p>Arguments examples: </p>
     * <p>-n 123 43 14 545</p>
     * <p>--numbers=123,43,14,545</p>
     * @param args aka program arguments
     */
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("No arguments passed");
            return;
        }
        var app = new ConsoleApp();
        int i = 0;
        for (; i < args.length; i++) {
            if (args[i].equals("-n")) {
                app.handle(List.of(Arrays.copyOfRange(args, i + 1, args.length)));
                return;
            }
            if (args[i].startsWith("--numbers=")) {
                String numbers = args[i].split("=", 2)[1];
                app.handle(List.of(numbers.split(",")));
                return;
            }
        }
        if (i == args.length)
            System.out.println("No numbers passed");
    }

    /**
     * Tests passed numbers on primality and prints result and process time for each.
     * @param numbers to handle.
     */
    public void handle(Iterable<String> numbers) {
        String prime;
        long start, time;
        for (String number : numbers) {
            try {
                start = System.nanoTime();
                prime = facade.isPrime(number) ? "prime" : "not prime";
                time = System.nanoTime() - start;
                System.out.printf("Number %s is %s. Process time nanos: %s%n", number, prime, time);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number " + number);
            }
        }
    }
}
