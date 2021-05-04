package com.vanderkast.primes;

import com.vanderkast.primes.domain.BigIntegerPrimeApprove;
import com.vanderkast.primes.domain.LongPrimeApprove;
import com.vanderkast.primes.domain.PrimeApproveFacade;

public class StartPoint {
    public static void main(String[] args) {
        var facade = new PrimeApproveFacade(new LongPrimeApprove(), new BigIntegerPrimeApprove());
        String prime;
        for (String arg : args) {
            try {
                prime = facade.isPrime(arg)? "" : "not";
                System.out.printf("Number %s is %s prime%n", arg, prime);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number " + arg);
            }
        }
    }
}
