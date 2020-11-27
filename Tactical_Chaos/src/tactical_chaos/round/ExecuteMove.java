package tactical_chaos.round;

import java.io.*;
import tactical_chaos.move.*;
import static tactical_chaos.round.Game.CurrentPlayers;
import java.util.*;
import static tactical_chaos.round.Game.CheckItems;
import static tactical_chaos.round.Game.CheckStunnedChampions;
import static tactical_chaos.round.Game.CheckTalonAbility;
import static tactical_chaos.round.Game.RemoveDeadChampions;
import static tactical_chaos.round.Game.ShowMap;

public class ExecuteMove extends Round {

    public ExecuteMove() throws FileNotFoundException {
    }

    @Override
    public void Execute() {
        List<Integer> PlayersIndexes = new ArrayList<>();
        Random rand = new Random();
        int idx;
        while (PlayersIndexes.size() != CurrentPlayers.size()) {
            idx = rand.nextInt(CurrentPlayers.size());
            while (PlayersIndexes.contains(idx)) {
                idx = rand.nextInt(CurrentPlayers.size());
            }
            PlayersIndexes.add(idx);
            for (Move m : CurrentPlayers.get(idx).PlayerPlans) {
                m.PerformMove();
            }
            CurrentPlayers.get(idx).PlayerPlans.clear();
            CheckStunnedChampions();
            CheckTalonAbility();
            CheckItems();
            RemoveDeadChampions();
        }
    }
}