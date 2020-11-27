package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateGankplankAbilityMove extends Move {

    public ActivateGankplankAbilityMove(Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.MoveID = 9;
    }

    @Override
    public void PerformMove() {
        int Attinc=this.CurrentChampion.CA.AttackRange *2;
        int Visinc=this.CurrentChampion.CA.VisionRange *2;
        
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            Attinc += Attinc * this.CurrentChampion.CA.SorcererBuff / 100;
            Visinc += Visinc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        
        
        this.CurrentChampion.CA.AttackRange =  Attinc;
        this.CurrentChampion.CA.VisionRange =  Visinc;

    }

}
