package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.round.Planning;

public class ActivateVarusAbilityMove extends Move {

    public ActivateVarusAbilityMove(Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.MoveID = 26;
    }

    @Override
    public void PerformMove() {
        int inc = 400;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.CurrentChampion.CA.VarusNextRound = 4;
        this.CurrentChampion.CA.CriticalStrikeDamage = inc;
    }

}
