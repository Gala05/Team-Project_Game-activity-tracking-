package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {
    Player player1 = new Player("Galina");
    Player player2 = new Player("Anna");
    GameStore store = new GameStore();
    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }
    @Test
    public void shouldGetMostPlayerIfFirstWin() {

        store.addPlayTime(player1.getName(), 7);
        store.addPlayTime(player2.getName(),6);

        String expected = "Galina";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }
    @Test
    public void shouldGetMostPlayerIfSecondWin() {

        store.addPlayTime(player1.getName(), 6);
        store.addPlayTime(player2.getName(),7);

        String expected = "Anna";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldGetMostPlayerIfZero() {

        store.addPlayTime(player1.getName(), 0);
        store.addPlayTime(player2.getName(),1);

        String expected = "Anna";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldGetSumPlayerTime() {

        store.addPlayTime(player1.getName(), 5);
        store.addPlayTime(player1.getName(), 2);
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 3);

        int expected = 11;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(actual, expected);
    }
}