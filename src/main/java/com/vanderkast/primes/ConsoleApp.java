package com.vanderkast.primes;

import com.vanderkast.primes.domain.BigIntegerPrimeApprove;
import com.vanderkast.primes.domain.LongPrimeApprove;
import com.vanderkast.primes.domain.PrimeApproveFacade;

public class ConsoleApp {
    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("No numbers passed");
            return;
        }
        var facade = new PrimeApproveFacade(new LongPrimeApprove(), new BigIntegerPrimeApprove());
        String prime;
        for (String arg : args) {
            try {
                prime = facade.isPrime(arg) ? "prime" : "not prime";
                System.out.printf("Number %s is %s%n", arg, prime);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number " + arg);
            }
        }
    }
}
