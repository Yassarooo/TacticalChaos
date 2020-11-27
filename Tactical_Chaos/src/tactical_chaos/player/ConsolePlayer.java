package tactical_chaos.player;

import java.util.*;
import tactical_chaos.champion.*;
import tactical_chaos.move.*;
import static tactical_chaos.move.Move.SearchInList;
import static tactical_chaos.move.MoveFactory.GetMoves;
import static tactical_chaos.round.Game.*;
import tactical_chaos.round.*;
import static tactical_chaos.round.Planning.*;

public class ConsolePlayer extends Player {

    public ConsolePlayer() {
        this.PlayerID = 1;
        this.currentChampions = new ArrayList<>();
        this.Bench = new ArrayList<>();
        Name = "Player " + PlayerNumber++;
        System.out.println("-Enter The Name of " + Name + " : ");
        Scanner sc = new Scanner(System.in);
        Name = sc.nextLine();
        PlayerPlans = new ArrayList<>();
        ChampionsID = new ArrayList<>();
    }

    @Override
    public Champion ChooseChampionFromBench() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-Enter Champion's ID To Choose :");
        System.out.println(Bench.toString());
        System.out.print(" --->> : ");
        int id = sc.nextInt();
        while (!this.Bench.isEmpty() && SearchInList(id, this.Bench) == null) {
            System.out.println("-Champion Not Found !,Please Enter a Valid ID !\n------->>>");
            id = sc.nextInt();
        }
        return SearchInList(id, this.Bench);
    }

    @Override
    public Champion ChooseChampionFromMap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-Enter Champion's ID To Choose :");
        System.out.println(this.currentChampions.toString());
        System.out.print(" --->> : ");
        int id = sc.nextInt();
        while (!this.currentChampions.isEmpty() && SearchInList(id, this.currentChampions) == null) {
            System.out.println("-Champion Not Found !,Please Enter a Valid ID !\n------->>>");
            id = sc.nextInt();
        }
        return SearchInList(id, this.currentChampions);
    }

    @Override
    public void PrintChampions() {
        System.out.println("\n-Your Bench Champions :");
        for (Champion c : this.Bench) {
            System.out.println(c.PrintChampion());
        }
        System.out.println("\n-Your Champions On The Map : ");
        for (Champion c : this.currentChampions) {
            System.out.println(c.PrintChampion());
        }
    }

    public void PrintChampionItems() {
        Scanner s = new Scanner(System.in);
        int c1;
        System.out.println("\n-Your Champions On The Map : ");
        for (Champion c : this.currentChampions) {
            System.out.println(c.PrintChampion());
        }
        System.out.println("Select Champion To Print its Items :");
        c1 = s.nextInt();
        while (SearchInList(c1, this.currentChampions) == null && !this.currentChampions.isEmpty()) {
            System.out.println("-Champion Not Found !\nPlease Enter a Valid Number or press (0) to Skip !\n------->>>");
            c1 = s.nextInt();
            if(c1==0){
                return;
            }
        }
        Champion c = SearchInList(c1, this.currentChampions);
        c.Items.toString();
    }

    @Override
    public void Play(Planning r) {
        Scanner sc = new Scanner(System.in), s = new Scanner(System.in);
        Champion temp, deff;
        Move move = null;
        int choose;

        this.coins += 2;
        List<Champion> RoundStore = GetTemporalList(r.newStore);
        System.out.println("-" + this.Name + " Do You Want To Add A Move ? (Y/N)");
        System.out.print(" --->> : ");
        String ans = sc.nextLine();
        while (!ans.equals("Y") && !ans.equals("y") && !ans.equals("n") && !ans.equals("N")) {
            System.out.println("-Wrong Input ! \n-Do You Want To Add A Move ? (Y/N) \n ");
            ans = sc.nextLine();
        }
        while (ans.equals("Y") || ans.equals("y")) {
            System.out.println("-What Do You Want To Do ? ");
            System.out.println("1-BuyMove\t2-SellMove\t3-SwapMove\t4-PutChampionOnMap");
            System.out.println("5-WalkMove\t6-BasicAttackMove\t7-ActivateAbillity\t8-Print Champions\n9-Print Champion Items\t10-Use Item\t11-Buy Item\t12-Sell Item.\n13-Go Back");
            System.out.print(" --->> : ");
            choose = s.nextInt();
            switch (choose) {
                case 1:
                    move = new BuyMove(this, r.newStore, RoundStore, champID++);
                    for (Champion c : RoundStore) {
                        System.out.print(c.StoreChampion());
                    }
                    move.PerformMove();
                    break;
                case 2:
                    move = new SellMove(this, r.newStore, r.Map);
                    move.PerformMove();
                    break;
                case 3:
                    move = new SwapMove(this, r.Map);
                    move.PerformMove();
                    if (r.NumberOfRound <= 3) {
                        ShowMap(this);
                    } else {
                        ShowMap();
                    }
                    break;
                case 4:
                    move = new PutOnMapMove(this, r.Map);
                    move.PerformMove();
                    if (r.NumberOfRound <= 3) {
                        ShowMap(this);
                    } else {
                        ShowMap();
                    }
                    break;
                case 5:
                    if (r.NumberOfRound <= 3) {
                        System.out.println("-Illegal Move !!");
                        System.out.println("-You Are Still In The First 9 Rounds !!");
                        break;
                    }
                    temp = this.ChooseChampionFromMap();
                    GetMoves(this, temp, r.Map);
                    move = new WalkMove(this, temp, r.Map);
                    for (Move m : temp.Moves) {
                        if (m.equals(move)) {
                            this.PlayerPlans.add(move);
                            break;
                        }
                    }
                    break;
                case 6:
                    if (r.NumberOfRound <= 3) {
                        System.out.println("-Illegal Move !!");
                        System.out.println("-You Are Still In The First 9 Rounds !!");
                        break;
                    }
                    temp = this.ChooseChampionFromMap();
                    GetMoves(this, temp, r.Map);
                    deff = ChooseDeffender(this);
                    move = new BasicAttackMove(temp, deff, r.Map);
                    for (Move m : temp.Moves) {
                        if (m.equals(move)) {
                            this.PlayerPlans.add(move);
                            if (temp.CA.Number == 30 || temp.CA.Number == 31) {
                                if (temp.MimicChampions.size() > 0) {
                                    for (Champion c : temp.MimicChampions) {
                                        GetMoves(this, c, r.Map);
                                        for (Move mo : c.Moves) {
                                            if (mo.MoveID == move.MoveID) {
                                                this.PlayerPlans.add(mo);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 7:
                    if (r.NumberOfRound <= 3) {
                        System.out.println("-Illegal Move !!");
                        System.out.println("-You Are Still In The First 9 Rounds !!");
                        break;
                    }
                    temp = this.ChooseChampionFromMap();
                    GetMoves(this, temp, r.Map);
                    deff = ChooseDeffender(this);
                    int num = temp.CA.Number;
                    switch (num) {
                        case 1:
                            move = (new ActivateAatroxAbilityMove(Map, temp));
                            break;
                        case 6:
                            move = (new ActivateDariusAbilityMove(Map, temp));
                            break;
                        case 12:
                            move = (new ActivateKarthusAbilityMove(Map, this, temp));
                            break;
                        case 16:
                            move = (new ActivateKindredAbilityMove(Map, this, temp));
                            break;
                        case 20:
                            move = (new ActivateLuluAbilityMove(Map, this, temp));
                            break;
                        case 23:
                            move = (new ActivateMorganaAbilityMove(Map, temp));
                            break;
                        case 30:
                            move = (new ActivateZedAbilityMove(temp, Map));
                            break;
                        case 31:
                            move = (new ActivateAzirAbilityMove(temp, Map));
                            break;
                        case 2:
                            move = (new ActivateAkaliAbilityMove(temp, deff));
                            break;
                        case 3:
                            move = (new ActivateAniviaAbilityMove(temp, deff, Map));
                            break;
                        case 4:
                            move = (new ActivateAsheAbilityMove(temp, deff));
                            break;
                        case 5:
                            move = (new ActivateChogathAbilityMove(temp, deff));
                            break;
                        case 7:
                            move = (new ActivateDravenAbilityMove(temp, deff));
                            break;
                        case 8:
                            move = (new ActivateFioraAbilityMove(temp, deff));
                            break;
                        case 9:
                            move = (new ActivateGankplankAbilityMove(deff));
                            break;
                        case 10:
                            move = (new ActivateGarenAbilityMove(temp, deff));
                            break;
                        case 11:
                            move = (new ActivateGravesAbilityMove(temp, deff));
                            break;
                        case 13:
                            move = (new ActivateKassadinAbilityMove(temp, deff));
                            break;
                        case 14:
                            move = (new ActivateKatarinaAbilityMove(deff));
                            break;
                        case 15:
                            move = (new ActivateKennenAbilityMove(temp, deff));
                            break;
                        case 17:
                            move = (new ActivateLeonaAbilityMove(deff));
                            break;
                        case 18:
                            move = (new ActivateLissandraAbilityMove(temp, deff, Map));
                            break;
                        case 19:
                            move = (new ActivateLucianAbilityMove(deff));
                            break;
                        case 21:
                            move = (new ActivateMissFortuneAbilityMove(temp, deff));
                            break;
                        case 22:
                            move = (new ActivateMordekaiserAbilityMove(temp, deff));
                            break;
                        case 24:
                            move = (new ActivateShyvanaAbilityMove(temp, deff));
                            break;
                        case 25:
                            move = (new ActivateTalonAbilityMove(temp, deff));
                            break;
                        case 26:
                            move = (new ActivateVarusAbilityMove(deff));
                            break;
                        case 27:
                            move = (new ActivateVayneAbilityMove(temp, deff));
                            break;
                        case 28:
                            move = (new ActivateVeigerAbilityMove(deff));
                            break;
                        case 29:
                            move = (new ActivateVolibearAbilityMove(Map, this, temp, deff));
                            break;
                        default:
                            break;
                    }
                    for (Move m : temp.Moves) {
                        if (m.CurrentChampion == null) {
                            break;
                        }
                        if (m.equals(move)) {
                            this.PlayerPlans.add(move);
                            break;
                        }
                    }
                    break;
                case 8:
                    this.PrintChampions();
                    break;
                case 9:
                    this.PrintChampionItems();
                    break;
                case 10:
                    if (r.NumberOfRound <= 3) {
                        System.out.println("-Illegal Move !!");
                        System.out.println("-You Cant use item until round 10 !!");
                        break;
                    }
                    move =new ItemMove(this);
                    move.PerformMove();
                    break;
                case 11:
                    move =new BuyItem(r.ItemsStore,this);
                    move.PerformMove();
                    break;
                case 12:
                    move =new SellItem(r.ItemsStore,this);
                    move.PerformMove();
                    break;
                case 13:
                    break;
                default:
                    System.out.println("-Wrong Input !!");
                    break;
            }
            System.out.println("-Do You Want To Add A Move ? (Y/N)");
            System.out.print(" --->> : ");
            ans = sc.nextLine();
            while (!ans.equals("Y") && !ans.equals("y") && !ans.equals("n") && !ans.equals("N")) {
                System.out.println("-Wrong Input !\n Do You Want To Add A Move ? (Y/N)");
                System.out.print(" --->> : ");
                ans = sc.nextLine();
            }
        }
        ShowMap(this);
    }
}
