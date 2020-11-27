package tactical_chaos.player;

import tactical_chaos.champion.Champion;
import java.util.*;
import tactical_chaos.move.Move;
import tactical_chaos.round.Planning;

public abstract class Player {

    public List<Move> PlayerPlans;
    public int coins = 0, PlayerID;
    public List<Champion> currentChampions, Bench;
    public List<Integer> ChampionsID;
    public String Name;
    public int ChampionsLimit = 9;

    public abstract Champion ChooseChampionFromBench();

    public abstract Champion ChooseChampionFromMap();

    public void PrintChampions() {

    }

    public abstract void Play(Planning r);
}
