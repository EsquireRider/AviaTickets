import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    @Test
    void testComparePriceIsHigher() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Краснодар", 3000, 10 * 60, 12 * 60);

        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
    }

    @Test
    void testComparePriceIsLower() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Краснодар", 3000, 10 * 60, 12 * 60);

        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
    }

    @Test
    void testComparePriceIsEqual() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Краснодар", 4000, 10 * 60, 12 * 60);

        Assertions.assertEquals(0, ticket1.compareTo(ticket2));
    }

    @Test
    void testSearchAndSortByPrice() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 12 * 60);
        Ticket ticket3 = new Ticket("Нижний Новгород", "Пенза", 5000, 9 * 60, 18 * 60);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] expected = {ticket2, ticket1};
        Ticket[] actual = aviaSouls.search("Нижний Новгород", "Москва");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testSearchTicketsNoFound() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 12 * 60);
        Ticket ticket3 = new Ticket("Нижний Новгород", "Пенза", 5000, 9 * 60, 18 * 60);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Москва", "Пенза");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    void testCompareTimeIsHigher() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Assertions.assertTrue(comparator.compare(ticket1, ticket2) > 0);
    }

    @Test
    void testCompareTimeIsLower() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 14 * 60);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Assertions.assertTrue(comparator.compare(ticket1, ticket2) < 0);
    }

    @Test
    void testCompareTimeIsEqual() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Assertions.assertEquals(0, comparator.compare(ticket1, ticket2));
    }

    @Test
    void testSearchAndSortByTime() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 12 * 60, 18 * 60);
        Ticket ticket3 = new Ticket("Нижний Новгород", "Пенза", 3500, 9 * 60, 18 * 60);
        Ticket ticket4 = new Ticket("Нижний Новгород", "Москва", 3500, 10 * 60, 14 * 60);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket4, ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Нижний Новгород", "Москва", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testEqualsAndHashCodeTrue() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);

        Assertions.assertEquals(ticket1, ticket2);
        Assertions.assertEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeFalse() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);
        Ticket ticket2 = new Ticket("Москва", "Пенза", 3000, 8 * 60, 14 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
        Assertions.assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeFalsePrice() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 14 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
        Assertions.assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeFalseTimeFrom() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 8 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 14 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
        Assertions.assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeFalseTimeTo() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 16 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
    }

    @Test
    void testEqualsFalseFrom() {
        Ticket ticket1 = new Ticket("Пенза", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 14 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
        Assertions.assertNotEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    void testEqualsFalseTo() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Пенза", 3000, 10 * 60, 14 * 60);

        Assertions.assertNotEquals(ticket1, ticket2);
    }

    @Test
    void testEqualsAndHashCodeFalseNull() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = null;

        Assertions.assertNotEquals(ticket1, ticket2);
    }

    @Test
    void testEqualsAndHashCodeFalseClass() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        String ticket2 = "Рейс";

        Assertions.assertNotEquals(ticket1, ticket2);
    }

    @Test
    void testGetPrice() {
        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 12 * 60);

        Assertions.assertEquals(4000, ticket1.getPrice());
    }

    @Test
    void testGetFrom() {
        Ticket ticket = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Assertions.assertEquals("Нижний Новгород", ticket.getFrom());
    }

    @Test
    void testGetTo() {
        Ticket ticket = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Assertions.assertEquals("Москва", ticket.getTo());
    }

    @Test
    void testGetTimeFrom() {
        Ticket ticket = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Assertions.assertEquals(10 * 60, ticket.getTimeFrom());
    }

    @Test
    void testGetTimeTo() {
        Ticket ticket = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Assertions.assertEquals(14 * 60, ticket.getTimeTo());
    }

    @Test
    void testFindAllTickets() {
        AviaSouls aviaSouls = new AviaSouls();

        Ticket ticket1 = new Ticket("Нижний Новгород", "Москва", 4000, 10 * 60, 14 * 60);
        Ticket ticket2 = new Ticket("Нижний Новгород", "Москва", 3000, 10 * 60, 12 * 60);
        Ticket ticket3 = new Ticket("Нижний Новгород", "Пенза", 5000, 9 * 60, 18 * 60);

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] expected = {ticket1, ticket2, ticket3};
        Ticket[] actual = aviaSouls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}