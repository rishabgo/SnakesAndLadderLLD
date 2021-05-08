package SystemDesign.SankesAndLadderLLD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Data
@Getter
public class Dice {

    private int minValue;
    private int maxValue;
    private int currentValue;

    public Dice(int minValue,int maxValue){
        this.maxValue = minValue;
        this.maxValue = maxValue;
    }

    public Integer rollDice(){
        currentValue = ThreadLocalRandom.current().nextInt(minValue,maxValue)+1;
        return currentValue;
    }
}
