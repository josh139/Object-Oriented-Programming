package w1742343;

import java.util.*;

public class CompareGF implements Comparator<FootballClub> 
{
    
    @Override
    public int compare(FootballClub a, FootballClub b) 
    {
        
        if(a.getGoalsFor() > b.getGoalsFor()) 
            return -1;
        else
            if (a.getGoalsFor() < b.getGoalsFor())
                return 1;
                    else return 0;
        
    }
}
