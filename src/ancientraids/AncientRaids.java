package ancientraids;

import ancientraids.content.*;
import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class AncientRaids extends Mod{
    public static final String MOD_RELEASES = "https://github.com/Yunatexya/AncientRaidsMod/releases";
    public static final String MOD_REPO = "Yunatexya/AncientRaidMod";
    public static final String MOD_GITHUB_URL = "https://github.com/Yunatexya/AncientRaidMod.git";
    public static final String MOD_NAME = "ancient-raids";

    public static Mods.LoadedMod MOD;
    public static String name(String name){
        return MOD_NAME + "-" + name;
    }

    public AncientRaids(){
        Log.info("Loaded AncientRaids constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading content.");

        {
            ARItems.load();
            ARLiquids.load();
            ARBlocks.load();
            ARUnits.load();
            ARTTechTree.load();
        }
    }

}
