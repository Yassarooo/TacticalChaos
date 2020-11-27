package tactical_chaos.store;

import tactical_chaos.champion.Champion;
import java.util.*;
import java.io.*;
import tactical_chaos.champion.ChampionsEnum.ChampionClass;

public class InGameStore implements Filter {
    //this will read all champions from the file

    public List<Champion> ChampionsList;
    public List<ChampionClass> classFilter;
    public boolean HasBeenFiltered;

    public InGameStore() {
        ChampionsList = new ArrayList<>();
        classFilter = new ArrayList<>();
        this.HasBeenFiltered = false;
    }

    public void GetList() throws FileNotFoundException {
        File ChampionsFile = new File("ChampionsList.txt");
        Scanner FileScanner = new Scanner(ChampionsFile);
        String tempLine;
        while (FileScanner.hasNextLine()) {
            Champion tempChampion = new Champion();
            tempLine = FileScanner.nextLine();
            String[] lineSplit = tempLine.split("\\W+");
            tempChampion.CA.Number = Integer.parseInt(lineSplit[0]);
            tempChampion.CA.Name = lineSplit[1];
            for (ChampionClass cc : ChampionClass.values()) {
                if (lineSplit[2].equals(cc.toString())
                        || lineSplit[3].equals(cc.toString())
                        || lineSplit[4].equals(cc.toString())) {
                    tempChampion.ActiveClasses.add(cc);
                }
            }
            tempChampion.CA.Cost = Integer.parseInt(lineSplit[5]);
            tempChampion.CA.Health = Float.parseFloat(lineSplit[6]);
            tempChampion.CA.newHealth = tempChampion.CA.Health;
            tempChampion.CA.Armor = Integer.parseInt(lineSplit[7]);
            tempChampion.CA.MagicResist = Integer.parseInt(lineSplit[8]);
            tempChampion.CA.VisionRange = Integer.parseInt(lineSplit[9]);
            tempChampion.CA.AttackRange = Integer.parseInt(lineSplit[10]);
            tempChampion.CA.AttackDamage = Integer.parseInt(lineSplit[11]);
            tempChampion.CA.Speed = Integer.parseInt(lineSplit[12]);
            tempChampion.CA.CriticalStrikeChance = Integer.parseInt(lineSplit[13]);
            tempChampion.CA.CriticalStrikeDamage = Integer.parseInt(lineSplit[14]);
            tempChampion.CA.ManaStart = Integer.parseInt(lineSplit[15]);
            tempChampion.CA.ManaCost = Integer.parseInt(lineSplit[16]);
            tempChampion.CA.Abillity = lineSplit[17];
            tempChampion.CA.Exist = lineSplit[18];
            tempChampion.CA.Level = Integer.parseInt(lineSplit[19]);
            tempChampion.CA.Copies = Integer.parseInt(lineSplit[20]);
            ChampionsList.add(tempChampion);
        }
    }

    @Override
    public List<Champion> AcceptFilter(StoreFilter... filters) {
        List<ChampionClass> temp = new ArrayList<>();
        for (ChampionClass cc : ChampionClass.values()) {
            temp.add(cc);
        }
        if (!this.HasBeenFiltered) {
            Scanner sca = new Scanner(System.in);
            System.out.println("-Do you want to Filter Champions Classes ? (Y/N) :");
            System.out.print(" ----> : ");
            String ans = sca.nextLine();
            while (!ans.equals("Y") && !ans.equals("y") && !ans.equals("n") && !ans.equals("N")) {
                System.out.println("-Wrong Input ! \n-Do you want to Filter Champions Classes ? (Y/N) : \n ");
                ans = sca.nextLine();
            }
            if (ans.charAt(0) == 'Y' || ans.charAt(0) == 'y') {
                String tempClass;
                Scanner sc = new Scanner(System.in);
                System.out.println("-Choose Classes to include (Type Ex to Exit) : ");
                tempClass = sc.nextLine();
                while (!tempClass.equals("Ex")) {
                    for (ChampionClass cc : temp) {
                        if (cc.toString().equals(tempClass)) {
                            if (!classFilter.contains(cc)) {
                                classFilter.add(cc);
                            }
                            break;
                        }
                    }
                    System.out.println(classFilter);
                    tempClass = sc.nextLine();
                }
            } else {
                classFilter = temp;
            }
            this.HasBeenFiltered = true;
        }
        List<Champion> tempList = new ArrayList<>();
        for (StoreFilter filter : filters) {
            tempList = filter.GetOnly(ChampionsList, 5, classFilter);
        }
        return tempList;
    }

    public InGameStore CopyStore(InGameStore mainStore) {
        this.HasBeenFiltered = mainStore.HasBeenFiltered;
        this.ChampionsList.addAll(mainStore.ChampionsList);
        this.classFilter.addAll(mainStore.classFilter);
        return this;
    }

}
