package ancientraids.content;

import static mindustry.content.TechTree.*;

public class ARTTechTree {

    public static void load(){
        nodeRoot("ancient-raids", ARBlocks.ancientMetalWall, () -> {
            node(ARBlocks.ancientMetalWalLarge);
        });
    }
}
