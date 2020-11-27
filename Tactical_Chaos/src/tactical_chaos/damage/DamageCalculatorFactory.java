package tactical_chaos.damage;

import java.util.*;
import tactical_chaos.champion.*;
import static tactical_chaos.champion.Champion.CopyChampion;
import static tactical_chaos.champion.Champion.GetChance;
import tactical_chaos.champion.ChampionsEnum.ChampionClass;
import tactical_chaos.player.Player;

public class DamageCalculatorFactory {

    public static boolean IsDragon(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Dragon)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsImperial(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Imperial)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsRanger(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Ranger)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsGlacial(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Glacial)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsKnight(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Knight)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsNoble(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Noble)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsNinja(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Ninja)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsDemon(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Demon)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsAssassin(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Assassin)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsBrawler(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Brawler)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsVoid(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Void)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsElementalist(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Elementalist)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsBladeMaster(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.BladeMaster)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsPirate(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Pirate)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsYordle(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Yordle)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsGunslinger(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Gunslinger)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsSorcerer(Champion c) {
        for (ChampionClass cc : c.ActiveClasses) {
            if (cc.equals(ChampionClass.Sorcerer)) {
                return true;
            }
        }
        return false;
    }

    public static List<Champion> CalculateClasses(Player p) {
        List<Champion> temp = new ArrayList<>();
        Champion tempChamp;
        for (Champion c : p.currentChampions) {
            tempChamp = CopyChampion(c);
            temp.add(tempChamp);
        }
        int Dragon = 0, Imperial = 0, Ranger = 0, Glacial = 0, Knight = 0, Noble = 0, Ninja = 0, Demon = 0, Assassin = 0, Brawler = 0;
        int Pirate = 0, Yordle = 0, Gunslinger = 0, Sorcerer = 0, Void = 0, Elementalist = 0, BladeMaster = 0;
        for (int i = 0; i < p.currentChampions.size(); i++) {
            if (IsDragon(p.currentChampions.get(i))) {
                Dragon++;
            }
            if (IsImperial(p.currentChampions.get(i))) {
                Imperial++;
            }
            if (IsRanger(p.currentChampions.get(i))) {
                Ranger++;
            }
            if (IsGlacial(p.currentChampions.get(i))) {
                Glacial++;
            }
            if (IsKnight(p.currentChampions.get(i))) {
                Knight++;
            }
            if (IsNoble(p.currentChampions.get(i))) {
                Noble++;
            }
            if (IsNinja(p.currentChampions.get(i))) {
                Ninja++;
            }
            if (IsDemon(p.currentChampions.get(i))) {
                Demon++;
            }
            if (IsAssassin(p.currentChampions.get(i))) {
                Assassin++;
            }
            if (IsBrawler(p.currentChampions.get(i))) {
                Brawler++;
            }
            if (IsVoid(p.currentChampions.get(i))) {
                Void++;
            }
            if (IsElementalist(p.currentChampions.get(i))) {
                Elementalist++;
            }
            if (IsBladeMaster(p.currentChampions.get(i))) {
                BladeMaster++;
            }
            if (IsPirate(p.currentChampions.get(i))) {
                Pirate++;
            }
            if (IsYordle(p.currentChampions.get(i))) {
                Yordle++;
            }
            if (IsGunslinger(p.currentChampions.get(i))) {
                Gunslinger++;
            }
            if (IsSorcerer(p.currentChampions.get(i))) {
                Sorcerer++;
            }
        }
        if (Dragon >= 2) {
            for (Champion c : p.currentChampions) {
                if (IsDragon(c)) {
                    c.CA.DragonBuff = true;  //champion is immune from ability attacks
                }
            }
        }
        if (Imperial >= 2) {
            for (Champion c : p.currentChampions) {
                if (IsImperial(c)) {
                    c.CA.ImperialBuff = true;
                    c.CA.AttackDamage *= 2;  //double attack damage
                }
            }
        }

        if (Ranger >= 3) {
            for (Champion c : p.currentChampions) {
                if (IsRanger(c)) {
                    c.CA.AttackRange *= 2; //double attack range
                    c.CA.VisionRange *= 2;  //double vision range
                }
            }
        }
        if (Glacial >= 2) {
            boolean chance;
            if (Glacial < 4) {
                chance = GetChance(20);
            } else if (Glacial < 6) {
                chance = GetChance(40);
            } else {
                chance = GetChance(60);
            }
            for (Champion c : p.currentChampions) {
                if (IsGlacial(c)) {
                    c.CA.GlacialBuff = chance;   //Can stun enemy in basic attack
                }
            }
        }
        if (Knight >= 2) {
            int ExtraShield;
            if (Knight < 4) {
                ExtraShield = 5;
            } else if (Knight < 6) {
                ExtraShield = 8;
            } else {
                ExtraShield = 12;
            }
            for (Champion c : p.currentChampions) {
                if (IsKnight(c)) {
                    c.CA.MagicResist += ExtraShield;  //gains extra magic resist and armor 
                    c.CA.Armor += ExtraShield;
                }
            }
        }
        if (Noble >= 3) {
            if (Noble < 6) {
                Random rand = new Random();
                int idx = rand.nextInt(p.currentChampions.size());
                p.currentChampions.get(idx).CA.NobleBuff = true;
                p.currentChampions.get(idx).CA.Armor += 20;
            } else {
                for (Champion c : p.currentChampions) {
                    c.CA.NobleBuff = true;
                    c.CA.Armor += 20;
                }
            }
        }
        if (Ninja >= 1) {
            if (Ninja == 1) {
                for (Champion c : p.currentChampions) {
                    if (IsNinja(c)) {
                        c.CA.CriticalStrikeChance += c.CA.CriticalStrikeChance * 50 / 100;
                        c.CA.CriticalStrikeDamage += c.CA.CriticalStrikeDamage * 200 / 100;
                    }
                }
            } else {
                for (Champion c : p.currentChampions) {
                    if (IsNinja(c)) {
                        c.CA.CriticalStrikeChance += c.CA.CriticalStrikeChance * 75 / 100;
                        c.CA.CriticalStrikeDamage += c.CA.CriticalStrikeDamage * 300 / 100;
                    }
                }
            }
        }
        if (Void >= 2) {

            if (Void < 4) {
                Random rand = new Random();
                int idx = rand.nextInt(p.currentChampions.size());
                p.currentChampions.get(idx).CA.VoidBuff = true;
            } else {
                for (Champion c : p.currentChampions) {
                    if (IsVoid(c)) {
                        c.CA.VoidBuff = true;
                    }

                }
            }

        }
        if (Elementalist >= 2) {
            if (Elementalist < 4) {
                for (Champion c : p.currentChampions) {
                    if (IsElementalist(c)) {
                        c.CA.ManaStart *= 2;
                        if (c.CA.ManaStart > c.CA.ManaCost) {
                            c.CA.ManaStart = c.CA.ManaCost;
                        }
                    }
                }
            } else {
                //grant the team an extra place for 10th champion
                for (Champion c : p.currentChampions) {
                    if (IsElementalist(c)) {
                        c.CA.ManaStart *= 2;
                        p.ChampionsLimit = 10;
                        if (c.CA.ManaStart > c.CA.ManaCost) {
                            c.CA.ManaStart = c.CA.ManaCost;
                        }
                    }
                }
            }
        }

        if (BladeMaster >= 3) {
            boolean chance = GetChance(40);
            int extraAttack;
            if (BladeMaster < 6) {
                for (Champion c : p.currentChampions) {
                    if (IsBladeMaster(c)) {
                        extraAttack = 1;
                    }
                }
            } else if (BladeMaster < 9) {
                for (Champion c : p.currentChampions) {
                    if (IsBladeMaster(c)) {
                        extraAttack = 2;
                    }
                }
            } else {
                extraAttack = 4;
                for (Champion c : p.currentChampions) {
                    if (IsBladeMaster(c)) {
                        c.CA.BladeMasterBuff = chance;
                        c.CA.BladeMasterBuff2 = extraAttack;
                    }
                }
            }
        }
        if (Demon >= 2) {
            boolean chance;
            if (Demon < 4) {
                chance = GetChance(30);
            } else {
                chance = GetChance(60);
            }
            for (Champion c : p.currentChampions) {
                if (IsDemon(c)) {
                    c.CA.DemonBuff = chance;
                    if (chance) {
                        if (Demon < 4) {
                            c.CA.DemonManaBurn = 20;
                        } else {
                            c.CA.DemonManaBurn = 40;
                        }
                    }
                }
            }
        }

        if (Assassin >= 2) {

        }
        if (Brawler >= 2) {
            int extraHealth;
            if (Brawler < 4) {
                extraHealth = 250;
            } else if (Brawler < 6) {
                extraHealth = 500;
            } else {
                extraHealth = 1000;
            }
            for (Champion c : p.currentChampions) {
                if (IsBrawler(c)) {
                    if (c.CA.BrawlerBuff) {
                        c.CA.newHealth -= c.CA.BrawlerExtra;
                        c.CA.Health -= c.CA.BrawlerExtra;
                    } else {
                        c.CA.BrawlerBuff = true;
                        c.CA.BrawlerExtra = extraHealth;
                    }

                    c.CA.newHealth += extraHealth;
                    c.CA.Health += extraHealth;
                }
            }
        }
        else{
            for (Champion c : p.currentChampions) {
                if (IsBrawler(c)) {
                    if (c.CA.BrawlerBuff) {
                        c.CA.newHealth -= c.CA.BrawlerExtra;
                        c.CA.Health -= c.CA.BrawlerExtra;
                        c.CA.BrawlerBuff = false;
                        c.CA.BrawlerExtra = 0;
                    }
                }
            }
        }
        if (Pirate >= 3) {
            p.coins += 2;
        }

        if (Yordle >= 2) {
            boolean chance;
            if (Yordle < 4) {
                chance = GetChance(15);
            } else if (Yordle < 6) {
                chance = GetChance(30);
            } else {
                chance = GetChance(50);
            }
            for (Champion c : p.currentChampions) {
                if (IsYordle(c)) {
                    c.CA.YordleBuff = chance;
                }
            }
        }
        if (Gunslinger >= 2) {
            boolean chance = GetChance(40);
            int extra;
            if (Gunslinger < 4) {
                extra = 1;
            } else if (Gunslinger < 6) {
                extra = 2;
            } else {
                extra = 4;
            }
            for (Champion c : p.currentChampions) {
                if (IsGunslinger(c)) {
                    c.CA.GunslingerBuff = chance;
                    c.CA.GunslingerBuff2 = extra;
                }
            }
        }

        if (Sorcerer >= 3) {
            int percent;
            if (Sorcerer < 6) {
                percent = 10;
            } else if (Sorcerer < 9) {
                percent = 40;
            } else {
                percent = 70;
            }
            for (Champion c : p.currentChampions) {
                c.CA.SorcererBuff = percent;
            }
        }

        return temp;
    }
}
