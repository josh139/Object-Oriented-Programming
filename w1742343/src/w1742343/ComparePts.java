package w1742343;

import java.util.*;

public class ComparePts implements Comparator<FootballClub> 
{
    
    @Override
    public int compare(FootballClub a, FootballClub b) 
    {
        
        if(a.getPoints() > b.getPoints()) // if FootballClub a has more points than FootballClub b
            return -1; // minus 1 position
        else
            if (a.getPoints() < b.getPoints())
                return 1; // plus 1 position
            else 
            {
                if(a.getGoalDifference() > b.getGoalDifference()) // if the FootballClubs have the same points check the goal difference
                    return -1;
                else
                    if(a.getGoalDifference() < b.getGoalDifference())
                        return 1;
                    else return 0; // if none of the conditions apply return 0
            }
        
    }
}