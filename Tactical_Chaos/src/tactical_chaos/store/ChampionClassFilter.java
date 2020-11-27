
package tactical_chaos.store;

import tactical_chaos.champion.Champion;
import java.util.*;
import tactical_chaos.champion.ChampionsEnum.ChampionClass;

public class ChampionClassFilter extends StoreFilter {
           @Override
           public List<Champion> GetOnly(List<Champion>store , int ChampionsNumber,List<ChampionClass>classes) {
                     List<Champion> newList = new ArrayList<>(); 
                     //int legalMoves = 0;
                     //boolean ok = true;
                     for(Champion c : store) {
                               for(ChampionClass cc : c.ActiveClasses) {
                                          if(classes.contains(cc)) {
                                                    newList.add(c);
                                                    break;
                                          }
                               }
                     }
                     return newList;
           }
}
