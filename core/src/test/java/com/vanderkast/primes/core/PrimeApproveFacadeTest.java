package com.vanderkast.primes.core;

import com.vanderkast.primes.PrimeApproveFacade;
import com.vanderkast.primes.cache.PrimeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings({"unchecked", "ConstantConditions"})
class PrimeApproveFacadeTest {
    private final PrimeApprove<Long> longs = mock(PrimeApprove.class);
    private final PrimeApprove<BigInteger> bigInts = mock(PrimeApprove.class);
    private final PrimeCache cache = mock(PrimeCache.class);

    private final PrimeApproveFacade facade = new PrimeApproveFacade(
            longs,
            bigInts,
            cache);

    @BeforeEach
    void setUpEach() {
        doReturn(Optional.empty()).when(cache).isPrime(any());
    }

    @Test
    void incorrectNumber() {
        assertThrows(NumberFormatException.class,
                () -> facade.isPrime("lol man it's illegal"));
    }

    @Test
    void bigInt() {
        // given
        var number = BigInteger.valueOf(Long.MAX_VALUE);
        var prime = false;
        doReturn(prime).when(bigInts).isPrime(number);

        // when
        facade.isPrime(number.toString());

        // then
        verify(cache).put(number, prime);
        verify(bigInts).isPrime(number);
        verifyZeroInteractions(longs);
    }

    @Test
    void longs() {
        // given
        long number = Long.MAX_VALUE - 10;
        var prime = false;
        doReturn(prime).when(longs).isPrime(number);

        // when
        facade.isPrime(String.valueOf(number));

        // then
        verify(cache).put(BigInteger.valueOf(number), prime);
        verify(longs).isPrime(number);
        verifyZeroInteractions(bigInts);
    }

    @Test
    void getFromCache() {
        // given
        long number = Long.MAX_VALUE - 10;
        var prime = false;
        var big = BigInteger.valueOf(number);
        doReturn(Optional.of(prime)).when(cache).isPrime(big);

        // when
        var actual = facade.isPrime(String.valueOf(number));

        // then
        assertEquals(prime, actual);
        verify(cache).isPrime(big);
        verifyZeroInteractions(bigInts);
        verifyZeroInteractions(longs);
    }
}
