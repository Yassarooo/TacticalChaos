package tactical_chaos.round;

import java.io.*;
import tactical_chaos.player.*;
import tactical_chaos.champion.*;
import tactical_chaos.arena.*;

import java.util.*;
import tactical_chaos.champion.ChampionsEnum.*;
import static tactical_chaos.champion.ChampionsEnum.ChampionItems.*;
import static tactical_chaos.damage.DamageCalculatorFactory.*;
import static tactical_chaos.move.Move.SearchInList;
import tactical_chaos.store.InGameStore;

public class Game {

    public static List<Player> CurrentPlayers;
    public static Arena Map;
    public RoundManager RM;
    public InGameStore newStore;
    public List<ChampionItems> ItemsStore;
    public static int champID = 1;
    public static int PlayerNumber = 1;
    public static List<List<Player>> Teams;
    public boolean Alliance = false;

    public Game() throws FileNotFoundException {
        newStore = new InGameStore();
        newStore.GetList();
        CurrentPlayers = new ArrayList<>();
        Teams = new ArrayList<>();
        ItemsStore = new ArrayList<>();
        Map = new Arena();
        RM = new RoundManager();

    }

    public void StartANewGame() throws FileNotFoundException {

        System.out.println("-Enter The Number Of Players : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while (num < 0 || num > 8) {
            System.out.println("-Illegal Input ! Please Enter A Valid Number \n --->>");
            num = sc.nextInt();
        }
        Player tempPlayer;
        for (int i = 0; i < num; i++) {
            tempPlayer = new ConsolePlayer();
            CurrentPlayers.add(tempPlayer);
        }
        System.out.println("-Enter The Number Of Bots : ");
        int number = sc.nextInt();
        while (number < 0 || number > 8 - num) {
            System.out.println("-Illegal Input ! Please Enter A Valid Number Betweeen(0->" + (8 - num) + ") \n --->> : ");
            number = sc.nextInt();
        }
        for (int i = 0; i < number; i++) {
            tempPlayer = new AutoPlayer();
            CurrentPlayers.add(tempPlayer);
        }
        System.out.println("-Do You Want To Use Alliance mode ? (Y/N)");
        System.out.print(" --->> : ");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if (choice.equals("Y") || choice.equals("y")) {
            Alliance = true;
        }
        int TeamCount = 1;
        List<Player> tempPlayersList = new ArrayList<>();
        List<String> PlayersNames = new ArrayList<>();
        for (int i = 0; i < CurrentPlayers.size(); i++) {
            String name = (i + 1) + "- " + CurrentPlayers.get(i).Name;
            PlayersNames.add(name);
        }
        if (Alliance) {
            while (PlayersNames.size() > 0) {
                System.out.println("-Select Players for Team " + TeamCount + " :");
                for (int i = 0; i < PlayersNames.size(); i++) {
                    System.out.println(PlayersNames.get(i));
                }
                System.out.print(" --->> : ");
                int ans = sc.nextInt();
                while (ans < 1 || ans > CurrentPlayers.size()) {
                    System.out.println("-Please Choose Wisely !");
                    System.out.print(" --->> : ");
                    ans = sc.nextInt();
                }
                for (String s : PlayersNames) {
                    if (s.charAt(0) - '0' == ans) {
                        
                        tempPlayersList.add(CurrentPlayers.get(ans - 1));
                        System.out.println(tempPlayersList);
                        PlayersNames.remove(s);
                        break;
                    }
                }

                if (PlayersNames.isEmpty()) {
                    Teams.add(tempPlayersList);
                    tempPlayersList = new ArrayList<>();
                    break;
                }
                System.out.println("-If You Want To Add More Players To This Team Type (M)");
                System.out.println("-Or (S) To Skip This Team");
                System.out.print(" --->> : ");
                String an = scan.nextLine();
                if (an.equals("M") || an.equals("m")) {
                    if (PlayersNames.size() == 1 && TeamCount == 1) {
                        System.out.println("Sorry You Can't Add More !");
                        TeamCount++;
                    }
                } else if (an.equals("S") || an.equals("s")) {
                    Teams.add(tempPlayersList);
                    tempPlayersList = new ArrayList<>();
                    TeamCount++;
                }
            }
        }
        System.out.println("-Enter Maximum Champions Limit : ");
        System.out.print(" --->> : ");
        int maxSize = sc.nextInt();
        while (maxSize < 4 || maxSize > 13) {
            System.out.println("-Wrong Input !!");
            System.out.println("-Enter Number Between (4 --> 13) : ");
            System.out.print(" --->> : ");
            maxSize = sc.nextInt();
        }
        for (Player p : CurrentPlayers) {
            p.ChampionsLimit = maxSize;
        }
        while (!RM.rounds.isEmpty()) {
            RM.RunTurn();
            if (Alliance) {
                if (Teams.size() == 1) {
                    System.out.println("Team :");
                    for (Player p : Teams.get(0)) {
                        System.out.println(p.Name);
                    }
                    System.out.println("-Win ^__^");
                    break;
                }
            } else {
                if (CurrentPlayers.size() == 1) {
                    System.out.println(CurrentPlayers.get(0).Name + " Win (O.O)");
                    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ScoreBoard.txt", true)))) {
                        out.println("-Player " + CurrentPlayers.get(0).Name + " Won");
                    } catch (IOException e) {
                    }
                    break;
                }
            }
        }
        if (Alliance) {
            if (Teams.size() > 1) {
                System.out.println("-No One Wins (#__#)");
            }
        } else {
            if (CurrentPlayers.size() > 1) {
                System.out.println("-No One Wins (#__#)");
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ScoreBoard.txt", true)))) {
                    out.println("-No One Won this Game");
                } catch (IOException e) {
                }
            }
        }
        System.out.println("Thanks For Playing (^__^)\n");
    }

    public class RoundManager {

        List<Round> rounds;

        public RoundManager() throws FileNotFoundException {
            rounds = new ArrayList<>();
            Round r;
            int num = 1;
            for (int i = 0; i < 9; i++) {
                r = new Planning(num++, Map, newStore, ItemsStore);
                rounds.add(r);
                r = new ExecuteMove();
                rounds.add(r);
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("-Enter The Number Of Rounds : ");
            int roundNum = sc.nextInt();
            while (num < 1) {
                System.out.println("Illegal Input ! Please Enter A Valid Number \n --->>");
                num = sc.nextInt();
            }
            Round tempRound;
            for (int i = 0; i < roundNum; i++) {
                tempRound = new Planning(num++, Map, newStore, ItemsStore);
                rounds.add(tempRound);
                tempRound = new ExecuteMove();
                rounds.add(tempRound);
            }
        }

        public void RunTurn() {
            List<List<Champion>> Reset = new ArrayList<>();

            for (Player p : CurrentPlayers) {
                p.Play((Planning) this.rounds.get(0));
                Reset.add(CalculateClasses(p));
            }

            this.rounds.get(1).Execute();
            ResetAllBuffs(Reset);
            if (this.rounds.get(0).NumberOfRound > 9) {
                for (int i = 0; i < CurrentPlayers.size(); i++) {
                    if (CurrentPlayers.get(i).currentChampions.isEmpty()) {
                        System.out.println(CurrentPlayers.get(i).Name + " Lost !!");
                        CurrentPlayers.remove(i);
                        i--;
                    }
                }
            }

            if (!this.rounds.isEmpty()) {
                if (this.rounds.get(0).NumberOfRound > 9) {
                    ShowMap();
                }
            }
            this.rounds.remove(1);
            this.rounds.remove(0);
        }
    }

    public static void ShowMap() {
        System.out.println("__Battle Arena__\n");
        Map.PrintMap();
    }

    public static void ShowMap(Player p) {
        System.out.println("__Player Battle Arena__\n");
        Map.PlayerMap(p);
    }

    public static void ShowAttackMap(Player p) {
        System.out.println("__Champions To Attack__\n");
        Map.AttackMap(p);
    }

    public static Champion ChooseDeffender(Player Attacker) {
        Scanner sc = new Scanner(System.in);
        ShowAttackMap(Attacker);
        System.out.println("-Enter Champion's Number To Attack :");
        System.out.print(" --->> : ");
        int id = sc.nextInt();
        while (SearchInList(id, Map.AttackMap(Attacker)) == null) {
            System.out.println("Champion Not Found ! Please Insert A Valid Champion ID\n--->>");
            id = sc.nextInt();
        }
        for (Player p : CurrentPlayers) {
            if (p.ChampionsID.contains(id)) {
                return SearchInList(id, p.currentChampions);
            }
        }
        return null;
    }

    public static int GetID() {
        return champID++;
    }

    public static void CheckStunnedChampions() {
        for (Square s : Map.Squares) {
            for (Champion c : s.square) {
                if (c.CA.IsStunned) {
                    c.CA.NextRound--;
                    if (c.CA.NextRound == 0) {
                        c.CA.IsStunned = false;
                    }
                }
            }
        }
    }

    public static void CheckTalonAbility() {
        for (Square s : Map.Squares) {
            for (Champion c : s.square) {
                if (c.CA.Number == 25) {
                    if (c.CA.TalonAbility) {
                        c.CA.TalonNextRound--;
                        if (c.CA.TalonNextRound == 0) {
                            c.CA.TalonAbility = false;
                            c.CA.CriticalStrikeChance = 25;
                        }
                    }
                }
            }
        }
    }

    public static void CheckItems() {
        for (Square s : Map.Squares) {
            if (!s.square.isEmpty() && !s.Items.isEmpty()) {
                for (Champion c : s.square) {
                    for (ChampionItems i : s.Items) {
                        if (c.Items.size() < 3) {
                            if (IsSorcerer(c) && i.equals(Magic_Hat)) {
                                c.Items.add(i);
                            }
                            if (IsBladeMaster(c) && i.equals(Warrior_Gloves)) {
                                c.Items.add(i);
                            }
                            if (IsKnight(c) && i.equals(Knight_Armor)) {
                                c.Items.add(i);
                            }
                            if (IsYordle(c) && i.equals(Angry_Cloak)) {
                                c.Items.add(i);
                            }
                            if (IsAssassin(c) && i.equals(Night_Shift)) {
                                c.Items.add(i);
                            }
                            if (IsVoid(c) && i.equals(Void_Hit)) {
                                c.Items.add(i);
                            }
                            if (IsElementalist(c) && i.equals(Universe_Core)) {
                                c.Items.add(i);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void ResetAllBuffs(List<List<Champion>> ResetList) {

        for (int i = 0; i < CurrentPlayers.size(); i++) {

            for (int j = 0; j < CurrentPlayers.get(i).currentChampions.size(); j++) {
                if (CurrentPlayers.get(i).currentChampions.get(j).CA.IsDead) {
                    continue;
                }
                if (CurrentPlayers.get(i).currentChampions.get(j).CA.newHealth <= 0) {
                    continue;
                }
                ResetList.get(i).get(j).CA.newHealth = CurrentPlayers.get(i).currentChampions.get(j).CA.newHealth;
                ResetList.get(i).get(j).CA.ManaStart = CurrentPlayers.get(i).currentChampions.get(j).CA.ManaStart;
                ResetList.get(i).get(j).CA.Health = CurrentPlayers.get(i).currentChampions.get(j).CA.Health;
                ResetList.get(i).get(j).CA.BrawlerExtra = CurrentPlayers.get(i).currentChampions.get(j).CA.BrawlerExtra;
                ResetList.get(i).get(j).CA.BrawlerBuff = CurrentPlayers.get(i).currentChampions.get(j).CA.BrawlerBuff;
                for (Champion c : CurrentPlayers.get(i).currentChampions.get(j).MimicChampions) {
                    if (c.CA.newHealth <= 0) {
                        ResetList.get(i).get(j).MimicChampions.add(c);
                    }
                }
                if (!CurrentPlayers.get(i).currentChampions.get(j).CA.ImperialBuff) {
                    ResetList.get(i).get(j).CA.ImperialBuff = CurrentPlayers.get(i).currentChampions.get(j).CA.ImperialBuff;
                }
                CurrentPlayers.get(i).currentChampions.get(j).CA.CopyCA(ResetList.get(i).get(j).CA);
                if (CurrentPlayers.get(i).currentChampions.get(j).CA.Number == 30 || CurrentPlayers.get(i).currentChampions.get(j).CA.Number == 31) {
                    CurrentPlayers.get(i).currentChampions.get(j).MimicChampions = ResetList.get(i).get(j).MimicChampions;
                }

            }
        }
    }

    public static void RemoveDeadChampions() {
        for (Player p : CurrentPlayers) {
            for (int i = 0; i < p.currentChampions.size(); i++) {
                Champion temp = p.currentChampions.get(i);
                if (temp.CA.Number == 30 || temp.CA.Number == 31) {
                    for (int j = 0; j < temp.MimicChampions.size(); j++) {
                        if (temp.MimicChampions.get(j).CA.IsDead || temp.MimicChampions.get(j).CA.newHealth <= 0) {
                            int index = Map.SearchForSquare(temp.MimicChampions.get(j));
                            if (index != -1) {
                                Map.Squares.get(index).square.remove(temp.MimicChampions.get(j));
                            }
                            temp.MimicChampions.remove(temp.MimicChampions.get(j));
                            j--;
                        }
                    }
                }
                if (temp.CA.newHealth <= 0 || temp.CA.IsDead) {
                    int idx = Map.SearchForSquare(temp);
                    if (idx != -1) {
                        Map.Squares.get(idx).square.remove(temp);
                    }
                    p.currentChampions.remove(temp);
                    i--;
                }
            }
            for (int i = 0; i < p.Bench.size(); i++) {
                Champion temp = p.Bench.get(i);
                if (temp.CA.Number == 30 || temp.CA.Number == 31) {
                    for (int j = 0; j < temp.MimicChampions.size(); j++) {
                        if (temp.MimicChampions.get(j).CA.IsDead || temp.MimicChampions.get(j).CA.newHealth <= 0) {
                            int index = Map.SearchForSquare(temp.MimicChampions.get(j));
                            if (index != -1) {
                                Map.Squares.get(index).square.remove(temp.MimicChampions.get(j));
                            }
                            temp.MimicChampions.remove(temp.MimicChampions.get(j));
                            j--;
                        }
                    }
                }
                if (temp.CA.newHealth <= 0 || temp.CA.IsDead) {
                    p.Bench.remove(temp);
                    i--;
                }
            }
        }
        for (Square s : Map.Squares) {
            for (int i = 0; i < s.square.size(); i++) {
                if (s.square.get(i).CA.newHealth <= 0) {
                    s.square.remove(i);
                    i--;
                }
            }
        }
    }
}