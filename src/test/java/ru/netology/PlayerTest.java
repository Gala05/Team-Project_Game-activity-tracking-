package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfFewSameGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game1, 2);

        int expected = 5;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfNoGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game1, 2);


        String actual = String.valueOf(player.mostPlayerByGenre("Головоломка"));
        assertEquals(null, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfOneGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game1, 2);

        String expected = "Нетология Баттл Онлайн";
        String actual = String.valueOf(player.mostPlayerByGenre("Аркада"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfTwoGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Три в ряд", "Головомка");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game1, 2);

        String expected = "Нетология Баттл Онлайн";
        String actual = String.valueOf(player.mostPlayerByGenre("Аркада"));
        assertEquals(expected, actual);
    }
}
