package ancientraids.content;

import static ancientraids.content.MMNBlocks.*;
import static mindustry.content.TechTree.*;

public class MOMONATechTree {
    public static void load(){
        nodeRoot("momona-tech", coreBase, () -> {

            node(coreCluster, () -> {
                node(coreMomona);
            });

            //node();
        });
    }
}
