package ancientraids;

import ancientraids.content.*;
import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AncientRaids extends Mod{
//    public static final String MOD_RELEASES = "https://github.com/Yunatexya/AncientRaidsMod/releases";
//    public static final String MOD_REPO = "Yunatexya/AncientRaidMod";
//    public static final String MOD_GITHUB_URL = "https://github.com/Yunatexya/AncientRaidMod.git";
    public static final String MOD_NAME = "ancient-raids";

    //public static Mods.LoadedMod MOD;
    @Contract(pure = true)
    public static @NotNull String name(String name){
        return MOD_NAME + "-" + name;
    }

    public AncientRaids(){
        Log.info("Loaded AncientRaids constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Attention");
                dialog.cont.add("Ancient raids Mod loaded.").row();
                dialog.cont.image(Core.atlas.find("ancient-raids-icon-2")).pad(20f).row();
                dialog.cont.button("Close", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading content.");

        {
            ARFx.load();
            ARTeams.load();
            ARItems.load();
            ARLiquids.load();
            ARBullets.load();
            ARUnits.load();
            ARBlocks.load();
            //ARSectorPresets.load();
            ARPlanets.load();
            ARTechTree.load();
        }
    }

}
