package ancientraids.content;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;

import static mindustry.type.ItemStack.with;

public class MMNBlocks {
    public static Block
            //environment
            ironOre,
            //crafting
            cementMixer, mmnCementAlloyARCMixer, steelSmelter, processorFactory, mmnProcessorFactory, mmnAlloySmelter,
            //wall
            ironWall, cementWall, mmnCementAlloyWallLarge, steelWallLarge, reinforcedAlloyWall, reinforcedAlloyWallLarge, mmnAlloyWall, mmnAlloyWallLarge,
            //defence
            //distribution
            ironDuct,
            //liquid
            //power
            //drill
            ironDrill, ironBeamDrill, steelDrill, steelBeamDrill, mmnDrill, mmnSuperBurstDrill,
            //storage
            coreBase, coreCluster, coreMomona,
            //turret
            //unit->payload
            unitGenerator,
            unitRegenerationMachine,
            unitRebornMachine,
            t1UpgradeModule, t2UpgradeModule, t3UpgradeModule, t4UpgradeModule, t5UpgradeModule,
            ancientRepairTower
    ;

    private static void loadEnv(){
        ironOre = new OreBlock("iron-ore", MMNItems.iron){{
            oreDefault = true;
            oreThreshold = 0.815f;
            oreScale = 23.7f;
        }};
    }

    public static void load(){
        int baseHP = 180;
        int mmnHP = 400;
        int wallHPMultiplier = 4;

        loadEnv();

        //crafting

        cementMixer = new AttributeCrafter("cement-mixer"){{
            size = 3;
            health = 80 * size;

            baseEfficiency = 0;
            minEfficiency = 1f;

            displayEfficiency = false;

            hasPower = false;

            consumeItems(with(Items.sand, 3));
            outputItem = new ItemStack(MMNItems.cement, 1);
            craftTime = 60f;

            drawer = new DrawMulti(
                    new DrawDefault()
            );

            //requirements(Category.crafting, with(MMNItems.iron, 180));
        }};
        mmnCementAlloyARCMixer = new AttributeCrafter("mmn-cement-alloy-arc-mixer"){{
            size = 3;
            health = mmnHP * size;

            baseEfficiency = 0;
            minEfficiency = 1f;

            displayEfficiency = false;

            consumeItems(with(Items.sand, 3, MMNItems.mmnAlloy, 1));
            outputItem = new ItemStack(MMNItems.mmnCementAlloy, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault()
            );

            //requirements(Category.crafting, with(MMNItems.mmnAlloy, 20));
        }};
        steelSmelter = new GenericCrafter("steel-smelter"){{
            size = 2;
            health = 80 * size;

            consumePower(2);
            consumeItems(with(MMNItems.iron, 2, Items.graphite, 1));
            outputItem = new ItemStack(MMNItems.steel, 1);
            craftTime = 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(MMNItems.iron, 120));
        }};

        //end
        //wall

        ironWall = new Wall("iron-wall"){{
            size = 1;
            health = 80 * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.iron, 8));
        }};
        cementWall = new Wall("cement-wall"){{
            size = 1;
            health = baseHP * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.cement, 8));
        }};
        mmnCementAlloyWallLarge = new Wall("mmn-cement-alloy-wall-large"){{
            size = 2;
            health = mmnHP * size * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.mmnCementAlloy, 32));
        }};
        steelWallLarge = new Wall("steel-wall-large"){{
            size = 2;
            health = 100 * size * size * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.steel, 32));
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

            requirements(Category.effect, with(MMNItems.iron, 800));
        }};

        coreCluster = new CoreBlock("core-cluster"){{
            size = 4;
            health = baseHP * size * size;

            unitCapModifier = 25;
            itemCapacity = 10000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(MMNItems.steel, 1500));
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
