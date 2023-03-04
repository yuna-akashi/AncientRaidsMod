package ancientraids.content;

import static mindustry.content.TechTree.*;

public class ARTechTree {

    public static void load(){
        nodeRoot("ancient-raids", ARBlocks.ancientWall, () -> {
            node(ARBlocks.ancientWalLarge);
        });
        nodeRoot("ancient-raids-atramace", ARBlocks.ancientCore, () -> {
            node(ARBlocks.ancientCoreFortress, () -> {
                node(ARBlocks.ancientCoreStronghold);
            });
        });
    }
}
