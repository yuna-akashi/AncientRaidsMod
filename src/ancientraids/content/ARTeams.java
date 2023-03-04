package ancientraids.content;

import arc.graphics.Color;
import mindustry.game.Team;

public class ARTeams {
    public static Team
            atramace
    ;

    public static class ARTeam extends Team{
        protected ARTeam(int id, String name, Color color, Color pal1, Color pal2, Color pal3) {
            super(id, name, color, pal1, pal2, pal3);
        }
    }

//    public static final Team[] plusTeam = new Team[1];

    public static void load(){
        atramace = new ARTeam(7, "atramace", Color.valueOf("afaf00"), Color.valueOf("afaf00"), Color.valueOf("afaf00"), Color.valueOf("afaf00"));
    }
}
