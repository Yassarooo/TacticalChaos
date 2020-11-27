package tactical_chaos.move;

import tactical_chaos.champion.Champion;

public class ActivateShyvanaAbilityMove extends Move {

    public ActivateShyvanaAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 24;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        float Hinc = (this.CurrentChampion.CA.Health * 10) / 100;
        float Vinc = 5, Ainc = 5;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            Hinc += Hinc * this.CurrentChampion.CA.SorcererBuff / 100;
            Vinc += Vinc * this.CurrentChampion.CA.SorcererBuff / 100;
            Ainc += Ainc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.CurrentChampion.CA.newHealth += Hinc;
        if(this.CurrentChampion.CA.newHealth > this.CurrentChampion.CA.Health) {
            this.CurrentChampion.CA.newHealth = this.CurrentChampion.CA.Health;
        }
        this.CurrentChampion.CA.VisionRange += Vinc;
        this.CurrentChampion.CA.AttackRange += Ainc;

    }

}
