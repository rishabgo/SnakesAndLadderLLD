package SystemDesign.SankesAndLadderLLD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import static java.lang.System.out;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameBoard {

    private int boardSize;
    private int totalPlayers;
    private Queue<Player> nextTurn;
    private Map<Integer, Integer> ladderPositionMap;
    private Map<Integer, Integer> snakePositionMap;
    private Map<String, Integer> playerMap;

    public GameBoard(int boardSize, Map<Integer, Integer> ladderPositionMap, Map<Integer, Integer> snakePositionMap) {
        this.boardSize = boardSize;
        nextTurn = new LinkedList<>();
        playerMap = new HashMap<>();
        this.ladderPositionMap = ladderPositionMap;
        this.snakePositionMap = snakePositionMap;
    }

    //setupPlayers
    public void setPlayers(List<Player> playerList) {

        totalPlayers = playerList.size();

        playerList.stream().map(player -> {
            /* 1) Add player in playerMap */
            playerMap.put(player.getPayerId(), 0);

            /* 2) Add player in nextTurn queue */
            nextTurn.offer(player);
            return player;
        }).forEach(player -> out.println("Initialized Player " + player.getPlayerName()));

    }

    //write to logic to check if player wins or not
    public boolean checkIfPlayerWins(Player player) {

        int currentPosition = playerMap.get(player.getPayerId());
        return currentPosition == boardSize;
    }

    public Integer checkForSnakesAndLadder(Integer newPosition) {

        //1) check for snake on new position
        if (snakePositionMap.containsKey(newPosition)) {
            newPosition = snakePositionMap.get(newPosition);
            out.println("Bitten by snake, Moved to position " + newPosition);
        }
        /* 2) check for ladder on new position */
        if (ladderPositionMap.containsKey(newPosition)) {
            newPosition = ladderPositionMap.get(newPosition);
            out.println("Ladder found, Moved to position " + newPosition);
        }
        return newPosition;
    }

    //move player
    public void movePlayer(Player player, Integer position) {

        Integer currentPosition = playerMap.get(player.getPayerId());
        Integer newPosition = currentPosition + position;
        newPosition = checkForSnakesAndLadder(newPosition);
        playerMap.put(player.getPayerId(), newPosition);
        out.println("Player " + player.getPlayerName() + " moved to Position " + newPosition);
    }


    public void startGame(Dice dice) {
        out.println("Game started");
        while (nextTurn.size() > 1) {

            var player = nextTurn.poll();
            int num = dice.rollDice();
            out.println("Player " + player.getPlayerName() + " Rolled dice and got value " + num);
            movePlayer(player, num);
            if (checkIfPlayerWins(player)) {
                out.println("Player " + player.getPlayerName() + " Won Game");
                out.println("Game ends");
            } else {
                nextTurn.offer(player);
            }
        }
    }
}
