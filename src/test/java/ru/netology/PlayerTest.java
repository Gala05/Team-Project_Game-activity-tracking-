package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testIfGameNotInstall() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Стратегия");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            player.play(game1, 5);
        });
    }

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
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 2);

        int expected = 5;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfOneGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 2);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfTwoGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Три в ряд", "Головоломка");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 4);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Головоломка");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfNoGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 2);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Головоломка");

        assertEquals(null, actual);
    }
    @Test
    public void shouldGetPlayerTimeIfOnePlayerOneGame() {
        Player player = new Player("Petya");

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        player.installGame(game);

        player.play(game, 3);
        player.play(game, 2);

        int expected = 5;
        int actual = player.totalPlayingTimeByPlayer();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldGetPlayerTimeIfOnePlayerTwoGame() {
        Player player = new Player("Petya");

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");

        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 2);

        int expected = 5;
        int actual = player.totalPlayingTimeByPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetPlayerTimeIfTwoPlayerOneGame() {
        Player player1 = new Player("Petya");
        Player player2 = new Player("Olya");
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        player1.installGame(game);

        player2.installGame(game);

        player1.play(game, 3);
        player1.play(game, 2);
        player2.play(game, 4);
        player2.play(game, 5);

        int expected = 9;
        int actual = player2.totalPlayingTimeByPlayer();

        assertEquals(expected, actual);
    }
    @Test
    public void shouldGetPlayerTimeIfTwoPlayerTwoGame() {
        Player player1 = new Player("Petya");
        Player player2 = new Player("Olya");
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");
        player1.installGame(game);
        player1.installGame(game1);
        player2.installGame(game);
        player2.installGame(game1);
        player1.play(game, 3);
        player1.play(game1, 2);
        player2.play(game, 4);
        player2.play(game1, 5);

        int expected = 9;
        int actual = player2.totalPlayingTimeByPlayer();

        assertEquals(expected, actual);
    }
}
