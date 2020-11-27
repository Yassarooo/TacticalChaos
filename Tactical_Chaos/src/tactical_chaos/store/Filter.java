
package tactical_chaos.store;

import tactical_chaos.champion.Champion;
import java.util.*;

public interface Filter {
           List<Champion> AcceptFilter(StoreFilter...filters);
}
