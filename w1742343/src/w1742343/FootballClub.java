package w1742343;


public class FootballClub extends SportsClub 
{
    int wins;
    int draws;
    int losses;
    int points;
    int matches_played;
    int goals_for;
    int goals_against;
    int goal_difference;
    
    
    public void setWins(int i) // setter which sets the FootballClub wins
    {            
        wins = i;
    }
    
    public int getWins() // getter which gets the FootballClub wins
    {                   
        return wins;
    }

    public void setDraws(int i)
    {            
        draws = i;
    }
    
    public int getDraws()
    {            
        return draws;
    }

    public void setLosses(int i)
    {            
        losses = i;
    }
    
    public int getLosses()
    {            
        return losses;
    }

    public void setPoints(int i)
    {
        points = i;         
    }
    
    public int getPoints()
    {
        return points;         
    }

    public void setMatchesPlayed(int i)
    {
        matches_played = i;
    }
    
    public int getMatchesPlayed()
    {
        return matches_played;
    }

    public void setGoalsFor(int i)
    {            
        goals_for = i;
    }
    
    public int getGoalsFor()
    {            
        return goals_for;
    }

    public void setGoalsAgainst(int i)
    {            
        goals_against = i;
    }
    
    public int getGoalsAgainst()
    {            
        return goals_against;
    }
    
        public void setGoalDifference(int i)
    {            
        goal_difference = i;
    }
    
        public int getGoalDifference()
    {            
        return goal_difference;
    }
}
