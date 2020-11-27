package tactical_chaos.arena;

import tactical_chaos.champion.Champion;
import java.util.*;
import static tactical_chaos.arena.Square.LandType.*;
import tactical_chaos.champion.ChampionsEnum.*;

public class Square {

    public List<Champion> square;
    public List<ChampionItems> Items;
    public int SquareNumber, SquareX, SquareY;
    public LandType Type;
    
    public enum LandType {
        None,Standard,Grass,Terrain,Water,
    }
    
    public Square() {
        square = new ArrayList<>();
        Items = new ArrayList<>();
        Type=None;
    }

}
