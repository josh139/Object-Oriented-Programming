package w1742343;


public abstract class SportsClub 
{
        String club_name;
        String club_location;
        int founded;
    
        public void setName(String i) 
        {
            club_name = i;
        }
        
        public String getName() 
        {
            return club_name;
        }
        
        public void setLocation(String i) 
        {
            club_location = i;
        }
        
        public String getLocation() 
        {
            return club_location;
        }
        
        public void setFounded(int i) 
        { 
            founded = i;
        }
        
        public int getFounded() 
        { 
            return founded;
        }
}
