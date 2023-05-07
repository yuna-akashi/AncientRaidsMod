package ancientraids.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.PayloadStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.blocks.units.UnitAssemblerModule;
import mindustry.world.blocks.units.UnitFactory;
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
            ironWall, cementWall, mmnCementAlloyWallLarge, steelWallLarge, mmnAlloyWall, mmnAlloyWallLarge,
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
            unitGenerator, tankGenerator, mechGenerator, shipGenerator, airGenerator,
            unitRegenerator, primeRegenerator,
            unitRegenerationMachine, tankRegenerationMachine, mechRegenerationMachine, shipRegenerationMachine,
            unitRebornMachine, tankRebornMachine, mechRebornMachine, shipRebornMachine,
            upgradeModule,
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

        unitGenerator = new UnitFactory("unit-generator"){{
            size = 3;
            health = baseHP * size * size;

            consumePower(2);

            plans = Seq.with(
                    new UnitPlan(MMNUnits.pUnitT1, 24 * 60, with(MMNItems.iron, 10, MMNItems.microProcessor, 40)),
                    new UnitPlan(MMNUnits.pAirMiner, 24 * 60, with(MMNItems.iron, 10, MMNItems.microProcessor, 40))
            );

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 60));
        }};
        unitRegenerator = new Reconstructor("unit-regenerator"){{
            size = 3;
            health = baseHP * size * size;

            consumePower(3);
            consumeItems(with(MMNItems.steel, 20, MMNItems.microProcessor, 30));

            constructTime = 40f * 60f;

            upgrades.add(
                    new UnitType[]{MMNUnits.pAirMiner, MMNUnits.pAirRepair}
            );

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 70));
        }};
        primeRegenerator = new Reconstructor("prime-regenerator"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(3.5f);
            consumeItems(with(MMNItems.steel, 40, MMNItems.microProcessor, 40));

            constructTime = 50f * 60f;

            upgrades.add(
                    new UnitType[]{MMNUnits.pAirRepair, MMNUnits.pAirReBuilder}
            );

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 80));
        }};
        unitRegenerationMachine = new UnitAssembler("unit-regeneration-machine"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(4);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pAirHealer, 70 * 60, PayloadStack.list(MMNUnits.pAirRepair, 5, MMNBlocks.mmnAlloyWall, 15)),
                    new AssemblerUnitPlan(MMNUnits.pAirCarrier, 90 * 60, PayloadStack.list(MMNUnits.pAirReBuilder, 8, MMNBlocks.mmnCementAlloyWallLarge, 40))
            );

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 60));
        }};
        unitRebornMachine = new UnitAssembler("unit-reborn-machine"){{
            size = 9;
            health = baseHP * size * size;

            consumePower(9);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.bommer, 75 * 60, PayloadStack.list(MMNUnits.pUnitT1, 10, MMNBlocks.steelWallLarge, 20)),
                    new AssemblerUnitPlan(MMNUnits.recon, 75 * 60, PayloadStack.list(MMNUnits.pUnitT1, 10, MMNBlocks.mmnAlloyWall, 20)),
                    new AssemblerUnitPlan(MMNUnits.launcher, 75 * 60, PayloadStack.list(MMNUnits.pUnitT1, 10, MMNBlocks.mmnAlloyWallLarge, 40))
            );

            requirements(Category.units, with(MMNItems.mmnAlloy, 180, MMNItems.mmnProcessor, 60));
        }};
        tankGenerator = new UnitFactory("tank-generator"){{
            size = 3;
            health = baseHP * size * size;

            consumePower(3);

            configurable = false;
            plans.add(new UnitPlan(MMNUnits.pTankAlpha, 35 * 60 , with(MMNItems.steel, 30, MMNItems.microProcessor, 40)));

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 60));
        }};
        tankRegenerationMachine = new UnitAssembler("tank-regeneration-machine"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(5);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pTankBeta, 75 * 60, PayloadStack.list(MMNUnits.pTankAlpha, 10, MMNBlocks.steelWallLarge, 20)),
                    new AssemblerUnitPlan(MMNUnits.pTankGamma, 75 * 60, PayloadStack.list(MMNUnits.pTankAlpha, 10, MMNBlocks.mmnAlloyWallLarge, 20))
            );

            requirements(Category.units, with(MMNItems.steel, 360, MMNItems.microProcessor, 180));
        }};
        tankRebornMachine = new UnitAssembler("tank-reborn-machine"){{
            size = 9;
            health = baseHP * size * size;

            consumePower(18);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pTankBoss, 75 * 60, PayloadStack.list(MMNUnits.pTankAlpha, 20, MMNBlocks.mmnCementAlloyWallLarge, 20, MMNBlocks.mmnAlloyWall, 40))
            );

            requirements(Category.units, with(MMNItems.mmnAlloy, 360, MMNItems.mmnProcessor, 180));
        }};
        mechGenerator = new UnitFactory("mech-generator"){{
            size = 3;
            health = baseHP * size * size;

            consumePower(3);

            configurable = false;
            plans.add(new UnitPlan(MMNUnits.pMechAlpha, 35 * 60 , with(MMNItems.steel, 30, MMNItems.microProcessor, 40)));

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 60));
        }};
        mechRegenerationMachine = new UnitAssembler("mech-regeneration-machine"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(5);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pMechBeta, 75 * 60, PayloadStack.list(MMNUnits.pMechAlpha, 10, MMNBlocks.steelWallLarge, 20)),
                    new AssemblerUnitPlan(MMNUnits.pMechGamma, 75 * 60, PayloadStack.list(MMNUnits.pMechAlpha, 10, MMNBlocks.mmnAlloyWallLarge, 20))
            );

            requirements(Category.units, with(MMNItems.steel, 360, MMNItems.microProcessor, 180));
        }};
        mechRebornMachine = new UnitAssembler("mech-reborn-machine"){{
            size = 9;
            health = baseHP * size * size;

            consumePower(18);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pMechBoss, 75 * 60, PayloadStack.list(MMNUnits.pMechAlpha, 20, MMNBlocks.mmnCementAlloyWallLarge, 20, MMNBlocks.mmnAlloyWall, 40))
            );

            requirements(Category.units, with(MMNItems.mmnAlloy, 360, MMNItems.mmnProcessor, 180));
        }};
        shipGenerator = new UnitFactory("ship-generator"){{
            size = 3;
            health = baseHP * size * size;

            consumePower(3);

            configurable = false;
            plans.add(new UnitPlan(MMNUnits.pShipAlpha, 35 * 60 , with(MMNItems.steel, 30, MMNItems.microProcessor, 40)));

            requirements(Category.units, with(MMNItems.steel, 180, MMNItems.microProcessor, 60));
        }};
        shipRegenerationMachine = new UnitAssembler("ship-regeneration-machine"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(5);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pShipBeta, 75 * 60, PayloadStack.list(MMNUnits.pShipAlpha, 10, MMNBlocks.steelWallLarge, 20)),
                    new AssemblerUnitPlan(MMNUnits.pShipGamma, 75 * 60, PayloadStack.list(MMNUnits.pShipAlpha, 10, MMNBlocks.mmnAlloyWallLarge, 20))
            );

            requirements(Category.units, with(MMNItems.steel, 360, MMNItems.microProcessor, 180));
        }};
        shipRebornMachine = new UnitAssembler("ship-reborn-machine"){{
            size = 9;
            health = baseHP * size * size;

            consumePower(18);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 20;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.pShipBoss, 75 * 60, PayloadStack.list(MMNUnits.pShipAlpha, 20, MMNBlocks.mmnCementAlloyWallLarge, 20, MMNBlocks.mmnAlloyWall, 40))
            );

            requirements(Category.units, with(MMNItems.mmnAlloy, 360, MMNItems.mmnProcessor, 180));
        }};
        upgradeModule = new UnitAssemblerModule("upgrade-module"){{
            size = 5;
            health = baseHP * size * size;

            consumePower(5);

            tier = 1;

            requirements(Category.units, with(MMNItems.mmnAlloy, 480, MMNItems.microProcessor, 180));
        }};

        //end
    }
}
