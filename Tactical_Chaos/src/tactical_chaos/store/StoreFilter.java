
package tactical_chaos.store;

import tactical_chaos.champion.Champion;
import tactical_chaos.champion.ChampionsEnum.ChampionClass;
import java.util.*;

public abstract class StoreFilter {
           public abstract List<Champion> GetOnly(List<Champion>ChampionsList , int ChampionsNumber,List<ChampionClass>classes);
}
