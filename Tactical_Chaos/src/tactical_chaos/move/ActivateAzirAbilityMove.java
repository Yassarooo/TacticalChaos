package tactical_chaos.move;

import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import static tactical_chaos.champion.Champion.CopyChampion;
import static tactical_chaos.round.Game.champID;

public class ActivateAzirAbilityMove extends Move {

    public ActivateAzirAbilityMove(Champion Attacker, Arena Map) {
        this.Map = Map;
        this.CurrentChampion = Attacker;
        this.MoveID=31;
    }

    @Override
    public void PerformMove() {
        int Squareidx = Map.SearchForSquare(this.CurrentChampion);
        if(Squareidx == -1) {
            return;
        }
        int SquareX = Map.Squares.get(Squareidx).SquareX;
        int SquareY = Map.Squares.get(Squareidx).SquareY;
        int OppositeSide = (SquareX * 100) + SquareY + 1;
        Champion temp = CopyChampion(this.CurrentChampion);
        Champion temp2 = CopyChampion(this.CurrentChampion);
        temp.CA.ID = champID++;
        temp2.CA.ID = champID++;
        temp.CA.newHealth = temp.CA.Health;
        temp2.CA.newHealth = temp.CA.Health;
        Map.PutChampionOnMap(OppositeSide, temp);
        Map.PutChampionOnMap(OppositeSide, temp2);
        this.CurrentChampion.MimicChampions.add(temp);
        this.CurrentChampion.MimicChampions.add(temp2);
    }
}