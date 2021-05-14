package repository;

import models.Game;

import java.util.HashMap;

public class GameRepository {
    private static HashMap<String, Game> gameHashMap = new HashMap<>();

    public static Game get(String id) {
        return gameHashMap.get(id);
    }

    public static void add(Game game) {
        gameHashMap.putIfAbsent(game.getId(), game);
    }
}
