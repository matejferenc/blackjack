package com.mf.blackjack;

import org.junit.Test;

import static com.mf.blackjack.Card.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class SumsTest {

    @Test
    public void testSoft() {
        assertSoft(ACE, TWO, ACE);
        assertSoft(TEN, ACE);
        assertSoft(ACE, ACE);
        assertSoft(KING, ACE);
        assertSoft(EIGHT, TWO, ACE);
        assertSoft(NINE, ACE, ACE);
        assertSoft(SEVEN, THREE, ACE);
        assertSoft(SIX, FOUR, ACE);
        assertSoft(FIVE, FIVE, ACE);
        assertSoft(FIVE, TWO, THREE, ACE);
        assertNotSoft(KING, TEN, ACE);
        assertNotSoft(KING, TEN, ACE);
        assertNotSoft(SIX, SEVEN, ACE);
        assertNotSoft(SIX, FIVE, ACE);
        assertNotSoft(SEVEN, FOUR, ACE);
        assertNotSoft(EIGHT, THREE, ACE);
        assertNotSoft(NINE, TWO, ACE);
    }

    private void assertSoft(Card... cards) {
        assertTrue(new Sums(asList(cards)).isSoft());
    }

    private void assertNotSoft(Card... cards) {
        assertFalse(new Sums(asList(cards)).isSoft());
    }

    @Test
    public void testMinimum() {
        assertMinimum(10, ACE, NINE);
        assertMinimum(20, TEN, JACK);
        assertMinimum(21, TEN, JACK, ACE);
        assertMinimum(2, ACE, ACE);
    }

    private void assertMinimum(int value, Card... cards) {
        assertEquals(value, new Sums(asList(cards)).getMinimum());
    }

    @Test
    public void testBlackjack() {
        assertBlackjack(ACE, TEN);
        assertBlackjack(ACE, JACK);
        assertBlackjack(QUEEN, ACE);
        assertBlackjack(KING, ACE);
        assertNotBlackjack(ACE, ACE);
        assertNotBlackjack(NINE, ACE, ACE);
    }

    private void assertBlackjack(Card... cards) {
        assertTrue(new Sums(asList(cards)).isBlackjack());
    }

    private void assertNotBlackjack(Card... cards) {
        assertFalse(new Sums(asList(cards)).isBlackjack());
    }

}
