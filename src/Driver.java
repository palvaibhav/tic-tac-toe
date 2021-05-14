import models.Game;
import models.User;
import models.UserSymbol;
import services.GameService;
import services.UserService;

public class Driver {

    public static void main(String[] args) {
        UserService userService = new UserService();
        GameService gameService = new GameService();

        User playerOne = userService.createUser("Vaibhav", UserSymbol.CROSS);
        User playerTwo = userService.createUser("Amit", UserSymbol.CIRCLE);

        Game game = gameService.createGame(playerOne, playerTwo, 3);

        gameService.startGame(game);
    }
}
