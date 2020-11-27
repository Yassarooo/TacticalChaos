package tactical_chaos.player;

import java.util.*;
import tactical_chaos.champion.Champion;
import static tactical_chaos.champion.Champion.GetChance;
import tactical_chaos.move.*;
import static tactical_chaos.move.MoveFactory.GetMoves;
import static tactical_chaos.round.Game.GetID;
import static tactical_chaos.round.Game.PlayerNumber;
import tactical_chaos.round.Planning;
import static tactical_chaos.round.Planning.GetTemporalList;

public class AutoPlayer extends Player {

    public AutoPlayer() {
        PlayerPlans = new ArrayList<>();
        ChampionsID = new ArrayList<>();
        this.currentChampions = new ArrayList<>();
        this.Bench = new ArrayList<>();
        this.PlayerID = 2;
        this.coins = 2;
        Name = "Auto Player " + PlayerNumber++;
    }

    @Override
    public void Play(Planning r) {
        this.coins += 2;
        int c;
        Random rand = new Random();
        Champion temp;

        if (r.NumberOfRound <= 3) {
            Move buy = new BuyMove(this, r.newStore, GetTemporalList(r.newStore), GetID());
            buy.PerformMove();
            Move P = new PutOnMapMove(this, r.Map);
            P.PerformMove();
        } else if (r.NumberOfRound > 3) {
            boolean ChanceToBuy;
            ChanceToBuy = GetChance(50);
            if (ChanceToBuy) {
                Move buy = new BuyMove(this, r.newStore, GetTemporalList(r.newStore), GetID());
                buy.PerformMove();
            }
            if (r.NumberOfRound > 9) {
                c = rand.nextInt(this.currentChampions.size());
                temp = this.currentChampions.get(c);
                GetMoves(this, temp, r.Map);
                Move m;
                if (temp.Moves.size() > 0) {

                    c = rand.nextInt(temp.Moves.size());
                    m = temp.Moves.get(c);
                    m.PerformMove();
                    if (m.MoveID == 32) {
                        if (m.CurrentChampion.CA.Number == 30 || m.CurrentChampion.CA.Number == 31) {
                            if (m.CurrentChampion.MimicChampions.size() > 0) {
                                for (Champion cc : m.CurrentChampion.MimicChampions) {
                                    GetMoves(this, cc, r.Map);
                                    for (Move mo : cc.Moves) {
                                        if (mo.MoveID == m.MoveID) {
                                            mo.PerformMove();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Move s;
            s = new SwapMove(this, r.Map);
            s.PerformMove();
        }
    }

    @Override
    public Champion ChooseChampionFromBench() {
        return null;
    }

    @Override
    public Champion ChooseChampionFromMap() {
        return null;
    }
}
