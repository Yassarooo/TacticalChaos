package tactical_chaos.move;

import tactical_chaos.champion.Champion;

public class ActivateLucianAbilityMove extends Move {

    public ActivateLucianAbilityMove(Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.MoveID = 19;
    }

    @Override
    public void PerformMove() {

        int Attinc = this.CurrentChampion.CA.AttackRange + 10;
        int Visinc = this.CurrentChampion.CA.VisionRange + 10;
        int Speedinc = this.CurrentChampion.CA.Speed + 10;

        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            Attinc += Attinc * this.CurrentChampion.CA.SorcererBuff / 100;
            Visinc = Visinc * this.CurrentChampion.CA.SorcererBuff / 100;
            Speedinc = Speedinc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.CurrentChampion.CA.AttackRange = Attinc;
        this.CurrentChampion.CA.VisionRange = Visinc;
        this.CurrentChampion.CA.Speed = Speedinc;

    }

}
