package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.arena.Square;
import tactical_chaos.arena.Square.LandType;
import tactical_chaos.player.Player;

public class WalkMove extends Move {

    public WalkMove(Player player, Champion champ, Arena arena) {
        this.CurrentPlayer = player;
        this.CurrentChampion = champ;
        this.Map = arena;
        this.MoveID = 37;
    }

    @Override
    public void PerformMove() {
        int Ans, index;
        if (this.CurrentPlayer.PlayerID == 2) {
            Random rand = new Random();
            Ans = rand.nextInt(3);
            Ans++;
            if (this.Map.SearchForSquare(CurrentChampion) == -1) {
                return;
            }
            index = this.Map.SearchForSquare(CurrentChampion);
        } else {
            Scanner sc = new Scanner(System.in);
            index = this.Map.SearchForSquare(CurrentChampion);
            System.out.println("-Which way would you like to go ?");
            System.out.println("1-UP\t2-RIGHT\t3-DOWN\t4-LEFT");
            System.out.print(" ---> : ");
            Ans = sc.nextInt();
            while (Ans < 1 || Ans > 4) {
                System.out.println("-Wrong Input!!");
                System.out.println("-Which way would you like to go ?");
                System.out.println("1-UP\t2-RIGHT\t3-DOWN\t4-LEFT");
                System.out.print(" ---> : ");
                Ans = sc.nextInt();
            }
        }
        boolean IsTerrain = false;
        int WaterCounter = 0, mod = 1;
        switch (Ans) {
            case 1:
                //move up
                if (this.Map.Squares.get(index).SquareX - CurrentChampion.CA.Speed >= 0) {
                    if (this.Map.Squares.get(index - 100 * CurrentChampion.CA.Speed).Type.equals(LandType.Terrain)) {
                        System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                        break;
                    } else if (this.Map.Squares.get(index - 100 * CurrentChampion.CA.Speed).Type.equals(LandType.Water)) {
                        WaterCounter++;
                    }
                    int z = index;
                    while (z != index - 100 * CurrentChampion.CA.Speed) {
                        if (this.Map.Squares.get(z).Type.equals(LandType.Terrain)) {
                            IsTerrain = true;
                            System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                            break;
                        } else if (this.Map.Squares.get(z).Type.equals(LandType.Water)) {
                            WaterCounter++;
                        }
                        z -= 100;
                    }
                    if (IsTerrain) {
                        break;
                    } else {
                        if (WaterCounter > 0) {
                            mod = WaterCounter * 2;
                        }
                        this.Map.Squares.get(index).square.remove(CurrentChampion);
                        this.Map.Squares.get(index - 100 * (CurrentChampion.CA.Speed / mod)).square.add(CurrentChampion);
                    }
                } else {
                    System.out.println("Illegal Move !!");
                }
                break;
            case 2:
                //move right
                if (this.Map.Squares.get(index).SquareY + CurrentChampion.CA.Speed <= 99) {
                    if (this.Map.Squares.get(index + CurrentChampion.CA.Speed).Type.equals(LandType.Terrain)) {
                        System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                        break;
                    } else if (this.Map.Squares.get(index + CurrentChampion.CA.Speed).Type.equals(LandType.Water)) {
                        WaterCounter++;
                    }
                    int z = index;
                    while (z != index + CurrentChampion.CA.Speed) {
                        if (this.Map.Squares.get(z).Type.equals(LandType.Terrain)) {
                            IsTerrain = true;
                            System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                            break;
                        } else if (this.Map.Squares.get(z).Type.equals(LandType.Water)) {
                            WaterCounter++;
                        }
                        z += 1;
                    }
                    if (IsTerrain) {
                        break;
                    } else {
                        if (WaterCounter > 0) {
                            mod = WaterCounter * 2;
                        }
                        this.Map.Squares.get(index).square.remove(CurrentChampion);
                        this.Map.Squares.get(index + (CurrentChampion.CA.Speed / mod)).square.add(CurrentChampion);
                    }
                } else {
                    System.out.println("-Illegal Move !!");
                }
                break;
            case 3:
                //move down
                if (this.Map.Squares.get(index).SquareX + CurrentChampion.CA.Speed <= 99) {
                    if (this.Map.Squares.get(index + CurrentChampion.CA.Speed).Type.equals(LandType.Terrain)) {
                        System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                        break;
                    } else if (this.Map.Squares.get(index + 100 * CurrentChampion.CA.Speed).Type.equals(LandType.Water)) {
                        WaterCounter++;
                    }
                    int z = index;
                    while (z != index + 100 * CurrentChampion.CA.Speed) {
                        if (this.Map.Squares.get(z).Type.equals(LandType.Terrain)) {
                            IsTerrain = true;
                            System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                            break;
                        } else if (this.Map.Squares.get(z).Type.equals(LandType.Water)) {
                            WaterCounter++;
                        }
                        z += 100;
                    }
                    if (IsTerrain) {
                        break;
                    } else {
                        if (WaterCounter > 0) {
                            mod = WaterCounter * 2;
                        }
                        this.Map.Squares.get(index).square.remove(CurrentChampion);
                        this.Map.Squares.get(index + 100 * (CurrentChampion.CA.Speed / mod)).square.add(CurrentChampion);
                    }
                } else {
                    System.out.println("-Illegal Move !!");
                }
                break;
            case 4:
                //move left
                if (this.Map.Squares.get(index).SquareY - CurrentChampion.CA.Speed >= 0) {
                    if (this.Map.Squares.get(index - CurrentChampion.CA.Speed).Type.equals(LandType.Terrain)) {
                        System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                        break;
                    } else if (this.Map.Squares.get(index - CurrentChampion.CA.Speed).Type.equals(LandType.Water)) {
                        WaterCounter++;
                    }
                    int z = index;
                    while (z != index - CurrentChampion.CA.Speed) {
                        if (this.Map.Squares.get(z).Type.equals(LandType.Terrain)) {
                            IsTerrain = true;
                            System.out.println("Can't Move !!\nThere's Terrain Land Ahead");
                            break;
                        } else if (this.Map.Squares.get(z).Type.equals(LandType.Water)) {
                            WaterCounter++;
                        }
                        z -= 1;
                    }
                    if (IsTerrain) {
                        break;
                    } else {
                        if (WaterCounter > 0) {
                            mod = WaterCounter * 2;
                        }
                        this.Map.Squares.get(index).square.remove(CurrentChampion);
                        this.Map.Squares.get(index - (CurrentChampion.CA.Speed / mod)).square.add(CurrentChampion);
                    }
                } else {
                    System.out.println("-Illegal Move !!");
                }
                break;
            default:
                //wrong input
                System.out.println("-Wrong Input!!");
                break;
        }
    }
}