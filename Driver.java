package SystemDesign.SankesAndLadderLLD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) {

        //create Player
        Player playerA = new Player("A1", "Rishabh");
        Player playerB = new Player("A2", "Gaurav");
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(playerA);
        playerList.add(playerB);

        //Create Dice
        Dice dice = new Dice(1, 6);

        //Initialize game board
        Map<Integer, Integer> ladderPositionMap = new HashMap<>();
        ladderPositionMap.put(67, 100);
        ladderPositionMap.put(73, 91);
        ladderPositionMap.put(19, 66);
        ladderPositionMap.put(32, 53);

        Map<Integer, Integer> snakedPositionMap = new HashMap<>();
        snakedPositionMap.put(25, 6);
        snakedPositionMap.put(46, 12);
        snakedPositionMap.put(74, 52);
        snakedPositionMap.put(88, 76);

        GameBoard board = new GameBoard(100, ladderPositionMap, snakedPositionMap);
        board.setPlayers(playerList);
        board.startGame(dice);
    }


}
