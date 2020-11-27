package tactical_chaos.champion;

import tactical_chaos.move.Move;
import java.util.*;
import tactical_chaos.damage.*;
import tactical_chaos.champion.ChampionsEnum.*;

public class Champion implements Damage {

    public List<Move> Moves;
    public List<ChampionClass> ActiveClasses;
    public List<DamageCalculator> ClassBuffs;
    public ChampionAttributes CA;
    public List<Champion> MimicChampions;
    public List<ChampionItems> Items;

    public Champion() {
        ActiveClasses = new ArrayList<>();
        Moves = new ArrayList<>();
        CA = new ChampionAttributes();
        ClassBuffs = new ArrayList<>();
        MimicChampions = new ArrayList<>();
        Items = new ArrayList<>();
    }

    public class ChampionAttributes {

        public String Name, Abillity, Exist;
        public int Armor, MagicResist, VisionRange, AttackRange, AttackDamage, Cost, Number;
        public int Speed, CriticalStrikeDamage, ManaStart, ManaCost, CriticalStrikeChance, Level, Copies;
        public int DemonManaBurn, ID, GunslingerBuff2,SorcererBuff,BrawlerExtra,NextRound,TalonNextRound,VarusNextRound,BladeMasterBuff2;
        public boolean IsDead = false, IsStunned = false, DragonBuff = false, GlacialBuff = false,ImperialBuff=false,BladeMasterBuff=false;
        public boolean NobleBuff = false, DemonBuff = false, YordleBuff = false, GunslingerBuff = false,VoidBuff=false,BrawlerBuff=false;
        public boolean FioraAbility=false , TalonAbility=false , VarusAbility=false,AsheAbility=false ,MagicHat=false , GetMana=true;
        public float Health, newHealth;

        public ChampionAttributes CopyCA(ChampionAttributes ca) {
            Health = ca.Health;
            newHealth = ca.newHealth;
            Speed = ca.Speed;
            CriticalStrikeDamage = ca.CriticalStrikeDamage;
            ManaStart = ca.ManaStart;
            ManaCost = ca.ManaCost;
            CriticalStrikeChance = ca.CriticalStrikeChance;
            Level = ca.Level;
            Copies = ca.Copies;
            IsDead = ca.IsDead;
            Armor = ca.Armor;
            MagicResist = ca.MagicResist;
            VisionRange = ca.VisionRange;
            AttackRange = ca.AttackRange;
            AttackDamage = ca.AttackDamage;
            Cost = ca.Cost;
            Number = ca.Number;
            Name = ca.Name;
            Abillity = ca.Abillity;
            Exist = ca.Exist;
            DragonBuff = ca.DragonBuff;
            GlacialBuff = ca.GlacialBuff;
            NobleBuff = ca.NobleBuff;
            DemonBuff = ca.DemonBuff;
            DemonManaBurn = ca.DemonManaBurn;
            GunslingerBuff=ca.GunslingerBuff;
            GunslingerBuff2=ca.GunslingerBuff2;
            YordleBuff=ca.YordleBuff;
            BrawlerBuff=ca.BrawlerBuff;
            IsStunned=ca.IsStunned;
            VoidBuff=ca.VoidBuff;
            SorcererBuff=ca.SorcererBuff;
            NextRound=ca.NextRound;
            TalonNextRound=ca.TalonNextRound;
            VarusNextRound=ca.VarusNextRound;
            TalonAbility=ca.TalonAbility;
            VarusAbility=ca.VarusAbility ; 
            AsheAbility=ca.AsheAbility;
            GetMana=ca.GetMana;
            IsDead = ca.IsDead;
            ID = ca.ID;
            MagicHat=ca.MagicHat;
            return this;
        }
    }

    @Override
    public void AcceptDamage(DamageCalculator... damage) {
        for (DamageCalculator dam : damage) {
            dam.Calculate(this);
        }
    }

    public static Champion CopyChampion(Champion champ) {
        Champion temp = new Champion();
        temp.ActiveClasses = champ.ActiveClasses;
        temp.CA.CopyCA(champ.CA);
        temp.Moves = champ.Moves;
        temp.ClassBuffs = champ.ClassBuffs;
        return temp;
    }

    public static boolean GetChance(int Chance) {
        boolean[] ChanceArray = new boolean[100];
        Random rand = new Random();
        int ran;
        for (int i = 0; i < 100; i++) {
            ChanceArray[i] = false;
        }
        if (Chance < 50) {
            for (int i = 0; i < Chance; i++) {
                ran = rand.nextInt(ChanceArray.length);
                while (ChanceArray[ran] == true) {
                    ran = rand.nextInt(ChanceArray.length);
                }
                ChanceArray[ran] = true;
            }
        } else {
            if (Chance >= 100) {
                Chance = 100;
            }
            for (int i = 0; i < Chance; i++) {
                ChanceArray[i] = true;
            }
        }
        int r = rand.nextInt(ChanceArray.length);
        return ChanceArray[r];
    }

    public String PrintChampion() {
        return "\nName : " + this.CA.Name + "|" + "Health :" + this.CA.newHealth + " | Level : " + CA.Level;
    }

    public String StoreChampion() {
        return "\nChamp Number : " + CA.Number + " | Name : " + CA.Name + " | Health : " + CA.newHealth + "/" + CA.Health
                + " | Cost : " + CA.Cost + " | Level : " + CA.Level;
    }

    @Override
    public String toString() {
        String mana = "[";
        int num = CA.ManaStart;
        for (int i = 0; i < CA.ManaCost; i++) {
            if (num > 0) {
                mana += "#";
                num--;
            } else {
                mana += " ";
            }
        }
        mana += "]";
        return "\nChamp ID : " + CA.ID + " | Name : " + CA.Name + " | Health : " + CA.newHealth + "/" + CA.Health
                 + " | Level : " + CA.Level + " |\t\t Mana : " + mana;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Champion ch = (Champion) obj;
        return (ch.CA.ID == this.CA.ID);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.Moves);
        hash = 61 * hash + Objects.hashCode(this.ActiveClasses);
        hash = 61 * hash + Objects.hashCode(this.ClassBuffs);
        hash = 61 * hash + Objects.hashCode(this.CA);
        return hash;
    }
}
