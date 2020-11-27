package tactical_chaos.move;

import tactical_chaos.store.*;
import java.util.*;
import tactical_chaos.champion.*;
import static tactical_chaos.champion.Champion.CopyChampion;
import tactical_chaos.player.*;

public class BuyMove extends Move {

    int ChampNum = 0, c1, c2, NewPrice;
    boolean CanBuy = false;

    public BuyMove(Player player, InGameStore store, List<Champion> tempstore, int ID) {
        this.Store = store;
        this.CurrentPlayer = player;
        this.TempStore = tempstore;
        this.ID = ID;
        this.MoveID = 33;
    }

    @Override
    public void PerformMove() {
        if (this.Store.ChampionsList.isEmpty()) {
            System.out.println("-Store Is Empty!!");
            return;
        }
        if (this.CurrentPlayer.PlayerID == 2) {
            List<Champion> BotChamps = new ArrayList<>();
            Random rand = new Random();
            int r;
            for (Champion c : this.TempStore) {
                if (this.CurrentPlayer.coins >= c.CA.Cost) {
                    BotChamps.add(c);
                }
            }
            if (BotChamps.isEmpty()) {
                return;
            } else {
                if ((CurrentPlayer.Bench.size() + CurrentPlayer.currentChampions.size()) < 9) {
                    r = rand.nextInt(BotChamps.size());
                    this.c1 = BotChamps.get(r).CA.Number;
                } else {
                    return;
                }
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n-Your Coins : << " + this.CurrentPlayer.coins + " >>\n-Enter Champion Number To Buy Or Press (0) To (Skip) : \n--->>");
            this.c1 = sc.nextInt();
            if (c1 == 0) {
                return;
            }

            while (SearchStore(c1, this.TempStore) == null) {
                System.out.println("-Champion Not Found !,Please Enter a Valid Number !\n--->>");
                this.c1 = sc.nextInt();
            }
        }

        this.CurrentChampion = CopyChampion(SearchStore(c1, this.TempStore));
        this.CurrentChampion.CA.ID = ID;

        //Check Player Coins and store copies
        if (this.CurrentChampion != null) {
            if (CurrentPlayer.coins >= this.CurrentChampion.CA.Cost && (CurrentPlayer.Bench.size() + CurrentPlayer.currentChampions.size()) < this.CurrentPlayer.ChampionsLimit) {
                CanBuy = true;
            } else {
                if (this.CurrentPlayer.PlayerID == 1) {
                    System.out.println("-You Can't Buy The Champion..\n-Please Check Your Coins Or Your Champions Number is Reached Limit !!");
                }
            }
        }

        if (CanBuy) {

            //edit player coins
            this.CurrentPlayer.coins -= this.CurrentChampion.CA.Cost;

            //to edit champion copies in the store
            SearchStore(c1, this.Store.ChampionsList).CA.Copies--;
            if (SearchStore(c1, this.Store.ChampionsList).CA.Copies == 0) {
                this.Store.ChampionsList.remove(SearchStore(c1, this.Store.ChampionsList));
            }

            //remove champion in tempstore
            this.RemoveChampionTempStore(c1);

            //add champion to the bench
            CurrentPlayer.Bench.add(this.CurrentChampion);
            CurrentPlayer.ChampionsID.add(ID);

            //check champion level
            this.CheckLevel(this.CurrentChampion);
            if (this.CurrentPlayer.PlayerID == 1) {
                System.out.println("-Champion Buyed Successfully ");
            }

        }
    }

    int CountPlayerChampions(int num, int L) {
        int CountBenchLevel1 = 0, CountCurrentLevel1 = 0, CountBenchLevel2 = 0, CountCurrentLevel2 = 0;

        for (Champion c : this.CurrentPlayer.Bench) {
            int i = 0;
            if (c.CA.Number == num && c.CA.Level == 1) {
                CountBenchLevel1++;
            } else if (c.CA.Number == num && c.CA.Level == 2) {
                CountBenchLevel2++;
            }
        }
        for (Champion c : this.CurrentPlayer.currentChampions) {
            if (c.CA.Number == num && c.CA.Level == 1) {
                CountCurrentLevel1++;
            } else if (c.CA.Number == num && c.CA.Level == 2) {
                CountCurrentLevel2++;
            }
        }
        int CountLevel1 = CountBenchLevel1 + CountCurrentLevel1;
        int CountLevel2 = CountBenchLevel2 + CountCurrentLevel2;

        switch (L) {
            case 1:
                return CountLevel1;
            case 2:
                return CountLevel2;
            default:
                return -1;
        }
    }

    public void CheckLevel(Champion c) {
        if (CountPlayerChampions(c.CA.Number, 1) == 3) {
            this.RemoveAll(c.CA.Number, 1);
            c.CA.Level++;
            c.CA.AttackDamage += c.CA.AttackDamage * 10 / 100;
            c.CA.Armor += c.CA.Armor * 20 / 100;
            c.CA.MagicResist += c.CA.MagicResist * 20 / 100;
            c.CA.newHealth += c.CA.Health * 20 / 100;
            c.CA.Health += c.CA.Health * 20 / 100;
            this.CurrentPlayer.Bench.add(c);
        }

        if (CountPlayerChampions(c.CA.Number, 2) == 3) {
            this.RemoveAll(c.CA.Number, 2);
            c.CA.Level++;
            c.CA.AttackDamage += c.CA.AttackDamage * 15 / 100;
            c.CA.Armor += c.CA.Armor * 25 / 100;
            c.CA.MagicResist += c.CA.MagicResist * 25 / 100;
            c.CA.newHealth += c.CA.Health * 25 / 100;
            c.CA.Health += c.CA.Health * 25 / 100;
            this.CurrentPlayer.Bench.add(c);
        }

    }

    public void RemoveAll(int num, int L) {
        List<Champion> toRemove1 = new ArrayList<>();
        List<Champion> toRemove2 = new ArrayList<>();
        if (L == 1) {
            for (Champion c : this.CurrentPlayer.Bench) {
                if (c.CA.Number == num && c.CA.Level == 1) {
                    toRemove1.add(c);
                }
            }
            this.CurrentPlayer.Bench.removeAll(toRemove1);
        } else if (L == 2) {
            for (Champion c : this.CurrentPlayer.Bench) {
                if (c.CA.Number == num && c.CA.Level == 2) {
                    toRemove2.add(c);
                }
            }
            this.CurrentPlayer.Bench.removeAll(toRemove2);
        }

    }

    public void RemoveChampionTempStore(int num) {
        List<Champion> toReplace = new ArrayList<>();
        boolean checkNum = false;
        for (Champion c : this.TempStore) {
            if (c.CA.Number != num || checkNum) {
                toReplace.add(c);
            } else {
                checkNum = true;
            }
        }
        this.TempStore.clear();
        this.TempStore.addAll(toReplace);
    }

}
