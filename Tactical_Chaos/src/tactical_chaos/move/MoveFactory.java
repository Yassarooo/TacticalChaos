package tactical_chaos.move;

import java.util.*;
import tactical_chaos.champion.*;
import tactical_chaos.player.*;
import tactical_chaos.arena.*;
import static tactical_chaos.arena.Square.LandType.Grass;
import tactical_chaos.round.Game;

public class MoveFactory {
    public static List<List<Player>> teams =Game.Teams;

    public static void GetMoves( Player player, Champion Attacker, Arena Map) {
        if (Attacker.CA.IsStunned != true) {
            boolean IsChecked = false;
            Attacker.Moves.add(new WalkMove(player, Attacker, Map));
            if (Attacker.CA.Number == 1 || Attacker.CA.Number == 6 || Attacker.CA.Number == 12 || Attacker.CA.Number == 16 || Attacker.CA.Number == 20 || Attacker.CA.Number == 23 || Attacker.CA.Number == 30 || Attacker.CA.Number == 31) {
                IsChecked = true;
                if (Attacker.CA.ManaStart == Attacker.CA.ManaCost) {
                    switch (Attacker.CA.Number) {
                        case 1:
                            Attacker.Moves.add(new ActivateAatroxAbilityMove(Map, Attacker));
                            break;
                        case 6:
                            Attacker.Moves.add(new ActivateDariusAbilityMove(Map, Attacker));
                            break;
                        case 12:
                            Attacker.Moves.add(new ActivateKarthusAbilityMove(Map, player, Attacker));
                            break;
                        case 16:
                            Attacker.Moves.add(new ActivateKindredAbilityMove(Map, player, Attacker));
                            break;
                        case 20:
                            Attacker.Moves.add(new ActivateLuluAbilityMove(Map, player, Attacker));
                            break;
                        case 23:
                            Attacker.Moves.add(new ActivateMorganaAbilityMove(Map, Attacker));
                            break;
                        case 30:
                            Attacker.Moves.add(new ActivateZedAbilityMove(Attacker, Map));
                            break;
                        case 31:
                            Attacker.Moves.add(new ActivateAzirAbilityMove(Attacker, Map));
                            break;
                    }
                }
            }
            for (Champion c : SearchInRange(Attacker, Map, Attacker.CA.AttackRange)) {
                if (!player.ChampionsID.contains(c.CA.ID)&&!IsAlliance(Attacker,c)) {
                    if (Attacker.MimicChampions.contains(c) || c.MimicChampions.contains(Attacker)) {
                        continue;
                    }
                    Attacker.Moves.add(new BasicAttackMove(Attacker, c, Map));
                    if (!IsChecked) {
                        AddAbilityMove(player, Attacker, c, Map);
                    }
                }
            }
        }
    }

    public static void AddAbilityMove(Player player, Champion Attacker, Champion Defender, Arena Map) {
        int num = Attacker.CA.Number;
        if (Attacker.CA.ManaStart == Attacker.CA.ManaCost) {
            switch (num) {
                case 2:
                    Attacker.Moves.add(new ActivateAkaliAbilityMove(Attacker, Defender));
                    break;
                case 3:
                    Attacker.Moves.add(new ActivateAniviaAbilityMove(Attacker, Defender, Map));
                    break;
                case 4:
                    Attacker.Moves.add(new ActivateAsheAbilityMove(Attacker, Defender));
                    break;
                case 5:
                    Attacker.Moves.add(new ActivateChogathAbilityMove(Attacker, Defender));
                    break;
                case 7:
                    Attacker.Moves.add(new ActivateDravenAbilityMove(Attacker, Defender));
                    break;
                case 8:
                    Attacker.Moves.add(new ActivateFioraAbilityMove(Attacker, Defender));
                    break;
                case 9:
                    Attacker.Moves.add(new ActivateGankplankAbilityMove(Defender));
                    break;
                case 10:
                    Attacker.Moves.add(new ActivateGarenAbilityMove(Attacker, Defender));
                    break;
                case 11:
                    Attacker.Moves.add(new ActivateGravesAbilityMove(Attacker, Defender));
                    break;
                case 13:
                    Attacker.Moves.add(new ActivateKassadinAbilityMove(Attacker, Defender));
                    break;
                case 14:
                    Attacker.Moves.add(new ActivateKatarinaAbilityMove(Defender));
                    break;
                case 15:
                    Attacker.Moves.add(new ActivateKennenAbilityMove(Attacker, Defender));
                    break;
                case 17:
                    Attacker.Moves.add(new ActivateLeonaAbilityMove(Defender));
                    break;
                case 18:
                    Attacker.Moves.add(new ActivateLissandraAbilityMove(Attacker, Defender, Map));
                    break;
                case 19:
                    Attacker.Moves.add(new ActivateLucianAbilityMove(Defender));
                    break;
                case 21:
                    Attacker.Moves.add(new ActivateMissFortuneAbilityMove(Attacker, Defender));
                    break;
                case 22:
                    Attacker.Moves.add(new ActivateMordekaiserAbilityMove(Attacker, Defender));
                    break;
                case 24:
                    Attacker.Moves.add(new ActivateShyvanaAbilityMove(Attacker, Defender));
                    break;
                case 25:
                    Attacker.Moves.add(new ActivateTalonAbilityMove(Attacker, Defender));
                    break;
                case 26:
                    Attacker.Moves.add(new ActivateVarusAbilityMove(Defender));
                    break;
                case 27:
                    Attacker.Moves.add(new ActivateVayneAbilityMove(Attacker, Defender));
                    break;
                case 28:
                    Attacker.Moves.add(new ActivateVeigerAbilityMove(Defender));
                    break;
                case 29:
                    Attacker.Moves.add(new ActivateVolibearAbilityMove(Map, player, Attacker, Defender));
                    break;
                default:
                    break;
            }
        }
    }

    public static List<Champion> SearchInRange(Champion Attacker, Arena Map, int range) {
        int idx = Map.SearchForSquare(Attacker);
        if (Map.Squares.get(idx).Type == Grass) {
            range /= 2;
        }
        List<Champion> InVision = new ArrayList<>();
        int posidx = Map.SearchForSquare(Attacker);
        if (posidx != -1) {
            int vision = Attacker.CA.VisionRange;
            int j = 0;
            for (int i = posidx - 100 * Attacker.CA.VisionRange; i <= posidx; i += 100) {

                for (int z = -j; z <= j; z++) {
                    if (i + z < 10000 && i + z >= 1) {
                        for (Champion c : Map.Squares.get(i + z).square) {
                            InVision.add(c);
                        }
                    }
                    if (vision > 0) {
                        if (((i + 100 * 2 * vision) + z) < 10000 && ((i + 100 * 2 * vision) + z) >= 0) {
                            for (Champion c : Map.Squares.get((i + 100 * 2 * vision) + z).square) {
                                InVision.add(c);
                            }
                        }
                    }
                }
                vision--;
                j++;
            }
        }
        return InVision;
    }

    public static boolean IsAlliance(Champion target, Champion friend) {
        int tgteam = -1, frdteam=-2;
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.get(i).size(); j++) {
                for (int z = 0; z < teams.get(i).get(j).currentChampions.size(); z++) {
                    if (teams.get(i).get(j).currentChampions.get(z).CA.ID == target.CA.ID) {
                        tgteam = i;
                    }
                    if (teams.get(i).get(j).currentChampions.get(z).CA.ID == friend.CA.ID) {
                        frdteam = i;
                    }
                }
            }
        }
        if (tgteam == frdteam) {
            return true;
        } else {
            return false;
    
        }
    }
}
