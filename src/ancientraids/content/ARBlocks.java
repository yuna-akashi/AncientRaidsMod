package ancientraids.content;

import mindustry.content.Items;
import mindustry.entities.bullet.MassDriverBolt;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.AutoDoor;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;

import static mindustry.type.ItemStack.with;

public class ARBlocks {
    public static Block
            //env
            ancientMetalFloor, ancientMetalFloorHeat, ancientFloor, ancientFloorHeat,

            //ore
            ironOre
    ;
    public static Block
            //crafting
            steelSmelter/*,
            ancientScrapSeparator, ancientMetalSmelter, ancientAlloyArcSmelter*/,

            //wall
            ironWall, steelWall,
            ancientWall, ancientWalLarge, ancientDefenceWall, ancientDefenceWallLarge, ancientDefenceDoor/*,

            //defence
            ancientMendProjector, ancientMendDome, ancientBoostProjector, ancientOverdriveDome, ancientRadar*/,

            //distribution
            ancientConveyor, ancientJunction, ancientItemBridge, ancientSorter, ancientInvertedSorter, ancientRouter, ancientDisruptor, ancientOverflowGate, ancientUnderflowGate,
            ancientDuct, ancientDuctRouter, ancientOverflowDuct, ancientUnderflowDuct, ancientDuctBridge, ancientDuctUnloader, ancientStackConveyor,
            ancientMassDriver, ancientItemTransportCannon, ancientCargoLoader, ancientCargoUnloadPoint,

            //liquid
            ancientPump, ancientReinforcedPump,
            ancientConduit, ancientReinforcedConduit, ancientLiquidJunction, ancientLiquidRouter, ancientBridgeConduit, ancientPhaseConduit,
            ancientLiquidContainer, ancientLiquidTank/*,

            //power

            //drill
            ironDrill, steelDrill,
            ancientDrill, ancientMechanicalDrill, ancientBurstDrill*/,

            //storage
            ancientCore, ancientCoreFortress, ancientCoreStronghold, ancientCoreAncientSEye/*,
            ancientContainer, ancientVault, ancientUnloader,

            //turret
            ancientDuo,

            //unit
            unitGenerator, ancientUnitGenerator,
            regenerationMachine, additiveRegenerationMachine, multipleRegenerationMachine, tetrativeRegenerationMachine,
            ancientRegenerationMachine, ancientRebornMachine,
            t1UpgradeModule, t2UpgradeModule, t3UpgradeModule, t4UpgradeModule, t5UpgradeModule,
            ancientRepairTower,

            //logic
            exaProcessor, petaStorage*/
    ;

    public static void load(){
        int wallHealthMultiplier = 6;
        //region env
        ancientMetalFloor = new Floor("ancient-metal-floor");
        ancientMetalFloorHeat = new Floor("ancient-metal-floor-heat"){{
            lightRadius = 40f;
        }};
        ancientFloor = new Floor("ancient-floor");
        ancientFloorHeat = new Floor("ancient-floor-heat"){{
            lightRadius = 40f;
        }};

        ironOre = new OreBlock("iron-ore", ARItems.iron){{
            oreDefault = true;
            oreThreshold = 0.815f;
            oreScale = 23.7f;
        }};
        //endregion
        //region factory
        steelSmelter = new GenericCrafter("steel-smelter"){{
            size = 2;
            health = 250;

            consumePower(2);
            consumeItems(with(ARItems.iron, 2, Items.graphite, 1));
            outputItem = new ItemStack(ARItems.steel, 1);
            craftTime = 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(ARItems.iron, 120));
        }};
        //endregion
        //region wall
        ironWall = new Wall("iron-wall"){{
            size = 1;
            health = 80 * wallHealthMultiplier;

            requirements(Category.defense, with(ARItems.iron, 8));
        }};

        steelWall = new Wall("steel-wall"){{
            size = 1;
            health = 100 * wallHealthMultiplier;

            requirements(Category.defense, with(ARItems.steel, 8));
        }};
        ancientWall = new Wall("ancient-wall"){{
            size = 1;
            health = 750 * wallHealthMultiplier;
            requirements(Category.defense, with(ARItems.aMetal, 8));
        }};
        ancientWalLarge = new Wall("ancient-wall-large"){{
            size = 2;
            health = 750 * 4 * wallHealthMultiplier;

            requirements(Category.defense, with(ARItems.aMetal, 32));
        }};
        ancientDefenceWall = new Wall("ancient-defence-wall"){{
            size = 1;
            health = 1250 * wallHealthMultiplier;
            armor = 50;

            requirements(Category.defense, with(ARItems.aAlloy, 8));
        }};
        ancientDefenceWallLarge = new Wall("ancient-defence-wall-large"){{
            size = 2;
            health = 1250 * 4 * wallHealthMultiplier;
            armor = 100;

            requirements(Category.defense, with(ARItems.aAlloy, 32));
        }};
        ancientDefenceDoor = new AutoDoor("ancient-defence-door"){{
            size = 2;
            health = 1000 * 4 * wallHealthMultiplier;
            armor = 50;

            requirements(Category.defense, with(ARItems.aAlloy, 32));
        }};
        //endregion
        //defence

        //endregion
        //distribution
        ancientConveyor = new Conveyor("ancient-conveyor"){{
            size = 1;
            health = 200;

            speed = 0.12f;

            requirements(Category.distribution, with(ARItems.aMetal, 5));
        }};

        ancientJunction = new Junction("ancient-junction"){{
            size = 1;
            health = 200;

            speed = 26;
            capacity = 10;

            requirements(Category.distribution, with(ARItems.aMetal, 2));
        }};

        ancientItemBridge = new BufferedItemBridge("ancient-bridge-conveyor"){{
            size = 1;
            health = 200;

            speed = 74f;
            range = 5;
            arrowSpacing = 6f;
            bufferCapacity = 20;

            requirements(Category.distribution, with(ARItems.aMetal, 12));
        }};

        ancientSorter = new Sorter("ancient-sorter"){{
            size = 1;
            health = 200;

            requirements(Category.distribution, with(ARItems.aMetal, 4));
        }};

        ancientInvertedSorter = new Sorter("ancient-inverted-sorter"){{
            size = 1;
            health = 200;

            invert = true;

            requirements(Category.distribution, with(ARItems.aMetal, 4));
        }};

        ancientRouter = new Router("ancient-router"){{
            size = 1;
            health = 200;

            requirements(Category.distribution, with(ARItems.aMetal, 3));
        }};

        ancientDisruptor = new Router("ancient-disruptor"){{
            size = 2;
            health = 800;

            requirements(Category.distribution, with(ARItems.aMetal, 10));
        }};

        ancientOverflowGate = new OverflowGate("ancient-overflow-gate"){{
            size = 1;
            health = 200;

            requirements(Category.distribution, with(ARItems.aMetal, 6));
        }};

        ancientUnderflowGate = new OverflowGate("ancient-underflow-gate"){{
            size = 1;
            health = 200;

            invert = true;

            requirements(Category.distribution, with(ARItems.aMetal, 6));
        }};

        //ducts
        ancientDuct = new Duct("ancient-duct"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            requirements(Category.distribution, with(ARItems.aAlloy, 5));
        }};

        ancientDuctRouter = new DuctRouter("ancient-duct-router"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            regionRotated1 = 1;
            solid = false;

            requirements(Category.distribution, with(ARItems.aAlloy, 10));
        }};

        ancientOverflowDuct = new OverflowDuct("ancient-overflow-duct"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            solid = false;

            requirements(Category.distribution, with(Items.graphite, 8, ARItems.aAlloy, 8));
        }};

        ancientUnderflowDuct = new OverflowDuct("ancient-underflow-duct"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            invert = true;
            solid = false;

            requirements(Category.distribution, with(Items.graphite, 8, ARItems.aAlloy, 8));
        }};
        ancientDuctBridge = new DuctBridge("ancient-duct-bridge"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            requirements(Category.distribution, with(ARItems.aAlloy, 20));
        }};

        ancientDuctUnloader = new DirectionalUnloader("ancient-duct-unloader"){{
            size = 1;
            health = 500;

            speed = 5.5f;

            solid = false;
            underBullets = true;
            regionRotated1 = 1;

            requirements(Category.distribution, with(Items.graphite, 20, ARItems.aAlloy, 10));
        }};

        //special

        ancientStackConveyor = new StackConveyor("ancient-stack-conveyor"){{
            size = 1;
            health = 500;

            speed = 0.1f;

            itemCapacity = 20;

            requirements(Category.distribution, with(ARItems.aMetal, 1, ARItems.aAlloy, 1));
        }};

        ancientMassDriver = new MassDriver("ancient-mass-driver"){{
            size = 3;
            health = 4500;

            hasPower = false;

            itemCapacity = 180;
            reload = 300f;
            range = 520;

            requirements(Category.distribution, with(ARItems.aMetal, 275));
        }};

        ancientItemTransportCannon = new MassDriver("ancient-item-transport-cannon"){{
            size = 5;
            health = 13200;

            hasPower = false;

            consumeLiquid(ARLiquids.conductorLiquid, 10f / 60f);

            itemCapacity = 500;
            reload = 450f;
            range = 2400;

            bullet = new MassDriverBolt(){{
                lifetime =30f;
                speed = 80f;
                hittable = false;
            }};

            requirements(Category.distribution, with(ARItems.aAlloy, 715, ARItems.conductor, 135, ARItems.cube, 50));
        }};

        ancientCargoLoader = new UnitCargoLoader("ancient-cargo-loader"){{
            size = 3;
            buildTime = 60f * 8f;

            hasPower = false;

            unitType = ARUnits.ancientCargoDrone;

            consumeLiquid(ARLiquids.conductorLiquid, 10f / 60f);

            itemCapacity = 400;

            requirements(Category.distribution, with(ARItems.aAlloy, 200, ARItems.conductor, 75, ARItems.cube, 25));
        }};

        ancientCargoUnloadPoint = new UnitCargoUnloadPoint("ancient-cargo-unload-point"){{
            size = 2;

            itemCapacity = 200;

            requirements(Category.distribution, with(ARItems.aAlloy, 225, ARItems.conductor, 75));
        }};

        //endregion
        //liquid

        ancientPump = new Pump("ancient-pump"){{
            size = 1;
            health = 200;

            pumpAmount = 0.25f;

            requirements(Category.liquid, with(ARItems.aMetal, 15, ARItems.aGlass, 10));
        }};

        ancientReinforcedPump = new Pump("ancient-reinforced-pump"){{
            size = 2;
            health = 2000;

            consumeLiquid(ARLiquids.conductorLiquid, 10f / 60f);

            pumpAmount = 0.5f;

            requirements(Category.liquid, with(ARItems.aAlloy, 40, ARItems.aGlass, 25));
        }};

        ancientConduit = new Conduit("ancient-conduit"){{
            size = 1;
            health = 200;

            liquidCapacity = 16f;
            liquidPressure = 1.025f;

            underBullets = true;

            requirements(Category.liquid, with(ARItems.aMetal, 2, ARItems.aGlass, 1));
        }};

        ancientReinforcedConduit = new ArmoredConduit("ancient-reinforced-conduit"){{
            size = 1;
            health = 500;

            liquidCapacity = 20f;
            liquidPressure = 2f;

            underBullets = true;
            botColor = Pal.darkestMetal;

            requirements(Category.liquid, with(ARItems.aAlloy, 2, ARItems.aGlass, 1));
        }};

        ancientLiquidJunction = new LiquidJunction("ancient-liquid-junction"){{
            size = 1;
            health = 200;
            ((Conduit)ancientConduit).junctionReplacement = this;

            solid = false;
            underBullets = true;

            requirements(Category.liquid, with(ARItems.aMetal, 4, ARItems.aGlass, 8));
        }};

        ancientLiquidRouter = new LiquidRouter("ancient-liquid-router"){{
            size = 1;
            health = 200;

            liquidCapacity = 20f;

            solid = false;
            underBullets = true;

            requirements(Category.liquid, with(ARItems.aMetal, 4, ARItems.aGlass, 2));
        }};

        ancientBridgeConduit = new LiquidBridge("ancient-bridge-conduit"){{
            size = 1;
            health = 200;

            arrowSpacing = 6f;
            range = 6;
            hasPower = false;

            underBullets = true;

            ((Conduit)ancientConduit).rotBridgeReplacement = this;

            requirements(Category.liquid, with(ARItems.aMetal, 4, ARItems.aGlass, 8));
        }};

        ancientPhaseConduit = new LiquidBridge("ancient-phase-conduit"){{
            size = 1;
            health = 500;

            range = 30;
            hasPower = false;

            underBullets = true;

            ((Conduit)ancientReinforcedConduit).rotBridgeReplacement = this;

            requirements(Category.liquid, with(ARItems.aAlloy, 10, ARItems.aGlass, 20));
        }};

        ancientLiquidContainer = new LiquidRouter("ancient-liquid-container"){{
            size = 2;
            health = 800;

            solid = true;

            liquidCapacity = 3000f;
            liquidPadding = 1.8f;

            underBullets = true;

            requirements(Category.liquid, with(ARItems.aMetal, 30, ARItems.aGlass, 15));
        }};

        ancientLiquidTank = new LiquidRouter("ancient-liquid-tank"){{
            size = 3;
            health = 4500;

            solid = true;

            liquidCapacity = 12000;
            liquidPadding = 2.5f;

            requirements(Category.liquid, with(ARItems.aAlloy , 50, ARItems.aGlass, 40));
        }};

        //endregion
        //power

        //endregion
        //drill

        //endregion
        //storage

        ancientCore = new CoreBlock("ancient-core"){{
            size = 4;
            health = 15200;
            armor = 20;

            unitType = ARUnits.delta;
            unitCapModifier = 20;

            itemCapacity = 10000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aMetal, 2000));
        }};

        ancientCoreFortress = new CoreBlock("ancient-core-fortress"){{
            size = 5;
            health = 30400;
            armor = 40;

            unitType = ARUnits.epsilon;
            unitCapModifier = 27;

            itemCapacity = 15000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aMetal, 4000));
        }};

        ancientCoreStronghold = new CoreBlock("ancient-core-stronghold"){{
            size = 6;
            health = 60800;
            armor = 60;

            unitType = ARUnits.zehta;
            unitCapModifier = 35;

            itemCapacity = 20000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aMetal, 8000));
        }};

        ancientCoreAncientSEye = new CoreBlock("ancient-core-ae"){{
            size = 7;
            health = 56230;
            armor = 500;

            unitType = ARUnits.eta;
            unitCapModifier = 50;

             itemCapacity = 25000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aAlloy, 3920));
        }};

        //endregion
        //turrets

        //endregion
        //units

        //endregion
    }
}
