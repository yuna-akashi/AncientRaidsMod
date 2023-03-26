package ancientraids.content;

import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.type.ItemStack.with;

public class MMNBlocks {
    public static Block
            //crafting
            reinforcedAlloySmelter, basicProcessorFactory, advancedProcessorFactory, mmnProcessorFactory, mmnAlloySmelter,
            //wall
            reinforcedAlloyWall, reinforcedAlloyWallLarge, mmnAlloyWall, mmnAlloyWallLarge,
            //defence
            //storage
            coreBase, coreCluster, coreMomona,
            //turret
            //unit->payload
            unitGenerator, ancientUnitGenerator,
            regenerationMachine, additiveRegenerationMachine, multipleRegenerationMachine, tetrativeRegenerationMachine,
            ancientRegenerationMachine, ancientRebornMachine,
            t1UpgradeModule, t2UpgradeModule, t3UpgradeModule, t4UpgradeModule, t5UpgradeModule,
            ancientRepairTower
    ;

    public static void load(){
        int baseHP = 180;
        int mmnHP = 400;
        int wallHPMultiplier = 4;

        //crafting

        //end
        //wall

        reinforcedAlloyWall = new Wall("reinforced-alloy-wall"){{
            size = 1;
            health = baseHP * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.reinforcedAlloy, 8));
        }};

        reinforcedAlloyWallLarge = new Wall("reinforced-alloy-wall-large"){{
            size = 2;
            health = baseHP * wallHPMultiplier * size * size;

            requirements(Category.defense, with(MMNItems.reinforcedAlloy, 32));
        }};

        mmnAlloyWall = new Wall("mmn-alloy-wall"){{
            size = 1;
            health = mmnHP * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.mmnAlloy, 8));
        }};

        mmnAlloyWallLarge = new Wall("mmn-alloy-wall-large"){{
            size = 2;
            health = mmnHP * wallHPMultiplier * size * size;

            requirements(Category.defense,with(MMNItems.mmnAlloy, 32));
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

        coreBase = new CoreBlock("core-momo"){{
            size = 3;
            health = baseHP * size * size;

            unitCapModifier = 15;
            itemCapacity = 5000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.iron, 800));
        }};

        coreCluster = new CoreBlock("core-cluster"){{
            size = 4;
            health = baseHP * size * size;

            unitCapModifier = 25;
            itemCapacity = 10000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(MMNItems.reinforcedAlloy, 1500));
        }};

        coreMomona = new CoreBlock("core-momona"){{
            size = 5;
            health = baseHP * size * size;

            unitCapModifier = 40;
            itemCapacity = 20000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(MMNItems.mmnAlloy, 2500));
        }};

        //end
        //turret

        //end
        //unit->payload

        //end
    }
}
