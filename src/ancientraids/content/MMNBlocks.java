package ancientraids.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.type.ItemStack.with;

public class MMNBlocks {
    public static Block
            //crafting
            //wall
            reinforcedAlloyWall, reinforcedAlloyWallLarge,
            //defence
            //storage
            coreBase, coreCluster, coreMomona
            //turret
            //unit->payload
    ;

    public static void load(){
        int baseHP = 150;
        int wallHPMMultiplier = 4;

        //crafting

        //end
        //wall

        reinforcedAlloyWall = new Wall("reinforced-alloy-wall"){{
            size = 1;
            health = baseHP * wallHPMMultiplier;

            requirements(Category.defense, with(MMNItems.reinforcedAlloy, 8));
        }};

        reinforcedAlloyWallLarge = new Wall("reinforced-alloy-wall-large"){{
            size = 2;
            health = baseHP * wallHPMMultiplier * 4;

            requirements(Category.defense, with(MMNItems.reinforcedAlloy, 32));
        }};

        //end
        //defence

        //end
        //distribution

        //end
        //liquid

        //end
        //power

        //end
        //drill

        //end
        //storage

        coreBase = new CoreBlock("core-base"){{
            size = 3;
            health = baseHP * 9;

            requirements(Category.effect, with(MMNItems.reinforcedAlloy, 800));
        }};

        coreCluster = new CoreBlock("core-cluster"){{
            size = 4;
            health = baseHP * 16;

            requirements(Category.effect, with(MMNItems.reinforcedAlloy, 1500));
        }};

        coreMomona = new CoreBlock("core-momona"){{
            size = 5;
            health = baseHP * 25;

            requirements(Category.effect, with(MMNItems.reinforcedAlloy, 2500));
        }};

        //end
        //turret

        //end
        //unit->payload

        //end
    }
}
