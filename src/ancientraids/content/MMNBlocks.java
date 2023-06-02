package ancientraids.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.PayloadStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.blocks.units.UnitAssemblerModule;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawParticles;
import mindustry.world.meta.Attribute;

import static mindustry.type.ItemStack.with;

public class MMNBlocks {
    public static Block
            //environment
            ironOre, limestoneFloor, limestoneUnit, clayUnit,
            //crafting
            steelSmelter, processorFactory, diamondCompressor, mmnAlloySmelter, mmnProcessorFactory, mmnCementAlloyARCMixer, mmnHSource,
            //wall
            ironWall, mmnCementAlloyWallLarge, steelWallLarge, mmnAlloyWall, mmnAlloyWallLarge,
            //defence
            //distribution
            ironDuct, mmnTransporter, mmnRTransporter, mmnBridge, mmnUnloader, mmnISource,
            //liquid
            mmnRConduit, mmnLJunction, mmnCRouter, mmnCBridge, mmnLContainer, mmnTank, mmnLSource,
            //power
            nuclearFusionReactor, mmnPSource,
            //drill
            ironDrill, ironBeamDrill, clayCrusher, limestoneCrusher, steelBeamDrill, mmnDrill, mmnSuperBurstDrill,
            //storage
            coreBase, coreMona, coreMomona, mmnContainer, mmnVault,
            //turret
            //unit->payload
            unitGenerator, tankGenerator, mechGenerator, shipGenerator,
            unitRegenerator, primeRegenerator,
            unitRegenerationMachine, tankRegenerationMachine, mechRegenerationMachine, shipRegenerationMachine,
            unitRebornMachine, tankRebornMachine, mechRebornMachine, shipRebornMachine,
            momonaUnitGenerator,
            upgradeModule,
            ancientRepairTower
    ;

    private static void loadEnv(){
        ironOre = new OreBlock("iron-ore", MMNItems.iron){{
            oreDefault = true;
            oreThreshold = 0.815f;
            oreScale = 23.7f;
        }};

        limestoneFloor = new Floor("limestone-floor"){{
            attributes.set(ARContent.limestone, 0.4f);
        }};

        limestoneUnit = new StaticWall("limestone-unit"){{
            attributes.set(ARContent.limestone, 0.4f);
            variants = 1;
        }};

        clayUnit = new StaticWall("clay-unit"){{
            attributes.set(ARContent.clay, 0.4f);
            variants = 1;
        }};
    }

    public static void load(){
        int baseHP = 180;
        int mmnHP = 400;
        int wallHPMultiplier = 4;

        loadEnv();

        //crafting

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

            requirements(Category.crafting, with(MMNItems.clay, 260, MMNItems.iron, 120));
        }};

        processorFactory = new GenericCrafter("processor-factory"){{
            size = 2;
            health = baseHP * size * size;

            consumeItems(with(Items.graphite, 3, Items.sand, 2, MMNItems.steel, 2));
            outputItem = new ItemStack(MMNItems.microProcessor, 1);
            craftTime = 1.2f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(MMNItems.clay, 280, MMNItems.steel, 140));
        }};

        diamondCompressor = new GenericCrafter("diamond-compressor"){{
            size = 2;
            health = baseHP * size * size;

            consumeItems(with(Items.graphite, 4));
            outputItem = new ItemStack(MMNItems.diamond, 1);
            craftTime = 3f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawParticles()
            );

            requirements(Category.crafting, with(MMNItems.steel, 200, MMNItems.microProcessor, 60));
        }};

        mmnAlloySmelter = new GenericCrafter("mmn-alloy-smelter"){{
            size = 3;
            health = baseHP * size * size;

            consumeItems(with(MMNItems.steel, 2, MMNItems.diamond, 2, MMNItems.microProcessor, 2));
            outputItem = new ItemStack(MMNItems.mmnAlloy, 1);
            craftTime = 2f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault()
            );

            requirements(Category.crafting, with(MMNItems. clay, 300, MMNItems.steel, 150, MMNItems.microProcessor, 100));
        }};

        mmnProcessorFactory = new GenericCrafter("mmn-processor-factory"){{
            size = 3;
            health = mmnHP * size * size;

            consumeItems(with(MMNItems.microProcessor, 2, MMNItems.mmnAlloy, 1));
            outputItem = new ItemStack(MMNItems.mmnProcessor, 1);
            craftTime = 1.75f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(MMNItems.mmnAlloy, 120, MMNItems.microProcessor, 80));
        }};

        mmnCementAlloyARCMixer = new AttributeCrafter("mmn-cement-alloy-arc-mixer"){{
            size = 3;
            health = mmnHP * size * size;

            attribute = Attribute.steam;


            baseEfficiency = 0;
            minEfficiency = 9f - 0.0001f;
            displayEfficiency = false;

            consumeItems(with(MMNItems.limestone, 2, MMNItems.clay, 2, MMNItems.mmnAlloy, 1));
            outputItem = new ItemStack(MMNItems.mmnCementAlloy, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault()
            );

            requirements(Category.crafting, with(MMNItems.mmnAlloy, 240, MMNItems.mmnProcessor, 100));
        }};

        //end
        //wall

        ironWall = new Wall("iron-wall"){{
            size = 1;
            health = 80 * wallHPMultiplier;

            requirements(Category.defense, with(MMNItems.iron, 8));
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
        mmnCementAlloyWallLarge = new Wall("mmn-cement-alloy-wall-large"){{
            size = 2;
            health = 500;

            requirements(Category.defense, with(MMNItems.mmnCementAlloy, 32));
        }};

        //end
        //defence

        //end
        //distribution

        mmnTransporter = new Duct("mmn-transporter"){{
            size = 1;
            health = mmnHP;

            speed = 3f;

            researchCost = with(MMNItems.mmnAlloy, 5);

            requirements(Category.distribution, with(MMNItems.mmnAlloy, 1));
        }};

        mmnRTransporter = new Duct("mmn-reinforced-transporter"){{
            size = 1;
            health = mmnHP + mmnHP/2;

            speed = 3f;

            armored = true;

            researchCost = with(MMNItems.mmnAlloy, 5);

            requirements(Category.distribution, with(MMNItems.mmnAlloy, 1));
        }};

        mmnBridge = new ItemBridge("mmn-bridge"){{
            size = 1;
            health = mmnHP;

            range = 20;
            arrowPeriod = 0.9f;
            arrowTimeScl = 2.75f;
            hasPower = false;
            pulse = true;


            requirements(Category.distribution, with(MMNItems.mmnAlloy, 4, MMNItems.mmnProcessor, 2));
        }};

        //end
        //liquid

        //end
        //power

        //end
        //drill

        ironDrill = new Drill("iron-drill"){{
            size = 2;
            health = baseHP * size * size;

            tier = 3;
            drillTime = 500;

            consumeLiquid(Liquids.water, 0.055f).boost();

            requirements(Category.production, with(MMNItems.iron, 12));
        }};

        ironBeamDrill = new BeamDrill("iron-beam-drill"){{
            size = 2;
            health = baseHP * size * size;

            drillTime = 160f;
            tier = 3;
            range = 5;
            fogRadius = 3;
            consumeLiquid(Liquids.water, 0.6f).boost();

            requirements(Category.production, with(MMNItems.iron, 40));
        }};

        limestoneCrusher = new WallCrafter("limestone-crusher"){{
            size = 2;
            health = baseHP * size * size;

            drillTime = 110f;
            attribute = ARContent.limestone;
            output = MMNItems.limestone;
            fogRadius = 2;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;

            requirements(Category.production, with(MMNItems.steel, 40));
        }};

        clayCrusher = new WallCrafter("clay-crusher"){{
            drillTime = 110f;
            size = 2;
            attribute = ARContent.clay;
            output = MMNItems.clay;
            fogRadius = 2;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;

            requirements(Category.production, with(MMNItems.steel, 80));
        }};

        //end
        //storage

        coreBase = new CoreBlock("core-momo"){{
            size = 3;
            health = baseHP * size * size;

            unitCapModifier = 15;
            itemCapacity = 5000;

            unitType = MMNUnits.momo;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(MMNItems.iron, 800));
        }};

        coreMona = new CoreBlock("core-cluster"){{
            size = 4;
            health = baseHP * size * size;

            unitCapModifier = 25;
            itemCapacity = 10000;

            unitType = MMNUnits.mona;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(MMNItems.steel, 1500));
        }};

        coreMomona = new CoreBlock("core-momona"){{
            size = 5;
            health = baseHP * size * size;

            unitCapModifier = 40;
            itemCapacity = 20000;

            unitType = MMNUnits.momona;

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
        momonaUnitGenerator = new UnitAssembler("mmn-unit-generator"){{
            size = 11;
            health = mmnHP * size * size;

            consumePower(36);
            droneType = MMNUnits.mmnAssemblyDrone;

            areaSize = 40;

            plans.add(
                    new AssemblerUnitPlan(MMNUnits.mmnUnit, 75 * 60, PayloadStack.list(MMNUnits.pUnitT1, 200, MMNBlocks.mmnCementAlloyWallLarge, 600, MMNBlocks.mmnAlloyWall, 300))
            );

            requirements(Category.units, with(MMNItems.mmnAlloy, 1220, MMNItems.mmnProcessor, 360));
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
