package ancientraids.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.Units;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.MassDriverBolt;
import mindustry.entities.bullet.PointBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.game.Team;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.AutoDoor;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.with;

public class ARBlocks {
    public static Block
            //env
            ancientMetalFloor, ancientMetalFloorHeat, ancientFloor, ancientFloorHeat,
            ancientMetalUnit, ancientMetalUnitLarge,

            //ore
            ironOre, ancientScrapOre
    ;
    public static Block
            //crafting
            steelSmelter,
            ancientMetalSmelter, ancientAlloyArcSmelter,
            conductorSmelter, conductorLiquidMixer, cubeGenerator,

            //wall
            ironWall, steelWall,
            ancientWall, ancientWalLarge, ancientDefenceWall, ancientDefenceWallLarge, ancientDefenceDoor,

            //defence
            //ancientMendProjector, ancientMendDome, ancientBoostProjector, ancientOverdriveDome, ancientShieldProjector, ancientShieldDome, ancientRadar,

            //distribution
            ancientConveyor, ancientJunction, ancientItemBridge, ancientSorter, ancientInvertedSorter, ancientRouter, ancientDisruptor, ancientOverflowGate, ancientUnderflowGate,
            ancientDuct, ancientArmorDuct, ancientDuctRouter, ancientOverflowDuct, ancientUnderflowDuct, ancientDuctBridge, ancientPhaseDuct, ancientDuctUnloader, ancientStackConveyor,
            ancientMassDriver, ancientItemTransportCannon, ancientCargoLoader, ancientCargoUnloadPoint,

            //liquid
            ancientPump, ancientReinforcedPump,
            ancientConduit, ancientReinforcedConduit, ancientLiquidJunction, ancientLiquidRouter, ancientBridgeConduit, ancientPhaseConduit,
            ancientLiquidContainer, ancientLiquidTank,

            //power

            //drill
//            ironDrill, steelDrill,
            ancientDrill, ancientBeamDrill, ancientBurstDrill,

            //storage
            ancientCore, ancientCoreFortress, ancientCoreStronghold, ancientCoreAncientSEye,
            ancientContainer, ancientVault, ancientUnloader,

            //turret
            ancientDuo/*, ancientAntiAir, ancientMissileSilo*/, ancientMultiMissileSilo, ancientRaidMissileSilo, ancientRailgun, ancientRailcannon, eyeOfTurret/*,

            //unit
            unitGenerator, ancientUnitGenerator,
            regenerationMachine, additiveRegenerationMachine, multipleRegenerationMachine, tetrativeRegenerationMachine,
            ancientRegenerationMachine, ancientRebornMachine,
            t1UpgradeModule, t2UpgradeModule, t3UpgradeModule, t4UpgradeModule, t5UpgradeModule,
            ancientRepairTower*/
    ;

    public static void load(){
        int wallHealthMultiplier = 6;
        //region env
        ///floor
        ancientMetalFloor = new Floor("ancient-metal-floor");
        ancientMetalFloorHeat = new Floor("ancient-metal-floor-heat"){{
            lightRadius = 40f;
        }};
        ancientFloor = new Floor("ancient-floor");
        ancientFloorHeat = new Floor("ancient-floor-heat"){{
            lightRadius = 40f;
        }};

        ///wall
        ancientMetalUnit = new StaticWall("ancient-metal-unit");
        ancientMetalUnitLarge = new StaticWall("ancient-metal-unit-large");

        ///ore
        ironOre = new OreBlock("iron-ore", ARItems.iron){{
            oreDefault = true;
            oreThreshold = 0.815f;
            oreScale = 23.7f;
        }};

        ancientScrapOre = new OreBlock("ancient-scrap-ore", ARItems.aScrap){{
            wallOre = true;
            oreThreshold = 1f;
            oreScale = 28f;
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

        ancientMetalSmelter = new GenericCrafter("ancient-metal-smelter"){{
            size = 3;

            consumeItems(with(ARItems.aScrap, 4));
            outputItem = new ItemStack(ARItems.aMetal, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(ARItems.aScrap, 120));
        }};

        ancientAlloyArcSmelter = new GenericCrafter("ancient-alloy-arc-smelter"){{
            size = 5;

            consumeLiquid(ARLiquids.conductorLiquid, 1.2f);
            consumeItems(with(ARItems.aScrap, 4));
            outputItem = new ItemStack(ARItems.aAlloy, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawArcSmelt(), new DrawDefault()
            );

            requirements(Category.crafting, with(ARItems.aMetal, 840, ARItems.conductor, 50));
        }};

        conductorSmelter = new GenericCrafter("conductor-smelter"){{
            size = 2;
            health = 800;

            consumeItems(with(ARItems.aMetal, 1));
            outputItem = new ItemStack(ARItems.conductor, 1);
            craftTime = 1.2f * 60f;
            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(ARItems.aMetal, 20));
        }};

        conductorLiquidMixer = new GenericCrafter("conductor-liquid-mixer"){{
            size = 2;
            health = 800;

            consumeLiquid(Liquids.water, 1.2f);
            consumeItems(with(ARItems.conductor, 1));
            outputLiquid = new LiquidStack(ARLiquids.conductorLiquid, 1f);

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(ARLiquids.conductorLiquid){{drawLiquidLight = true;}}, new DrawDefault()
            );

            requirements(Category.crafting, with(ARItems.aMetal, 32, ARItems.aGlass , 20));
        }};

        cubeGenerator = new GenericCrafter("cube-generator"){{
            size = 3;
            health = 1800;

            consumeItems(with(ARItems.aMetal, 2));
            outputItem = new ItemStack(ARItems.cube, 1);
            craftTime = 1.2f * 60f;
            drawer = new DrawMulti(
                    new DrawDefault()
            );

            requirements(Category.crafting, with(ARItems.aMetal, 20));
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

            ((Conveyor)ancientConveyor).junctionReplacement = this;

            requirements(Category.distribution, with(ARItems.aMetal, 2));
        }};

        ancientItemBridge = new BufferedItemBridge("ancient-bridge-conveyor"){{
            size = 1;
            health = 200;

            speed = 74f;
            range = 5;
            arrowSpacing = 6f;
            bufferCapacity = 20;

            ((Conveyor)ancientConveyor).bridgeReplacement = this;

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
            health = 200;

            speed = 3f;

            requirements(Category.distribution, with(ARItems.aMetal, 5));
        }};

        ancientArmorDuct = new Duct("ancient-armor-duct"){{
            size = 1;
            health = 500;

            speed = 2.5f;

            requirements(Category.distribution, with(ARItems.aAlloy, 10));
        }};

        ancientDuctRouter = new DuctRouter("ancient-duct-router"){{
            size = 1;
            health = 500;

            speed = 3f;

            regionRotated1 = 1;
            solid = false;

            requirements(Category.distribution, with(ARItems.aMetal, 10));
        }};

        ancientOverflowDuct = new OverflowDuct("ancient-overflow-duct"){{
            size = 1;
            health = 500;

            speed = 3f;

            solid = false;

            requirements(Category.distribution, with(Items.graphite, 8, ARItems.aAlloy, 8));
        }};

        ancientUnderflowDuct = new OverflowDuct("ancient-underflow-duct"){{
            size = 1;
            health = 500;

            speed = 3f;

            invert = true;
            solid = false;

            requirements(Category.distribution, with(Items.graphite, 8, ARItems.aAlloy, 8));
        }};
        ancientDuctBridge = new DuctBridge("ancient-duct-bridge"){{
            size = 1;
            health = 200;

            speed = 3f;

            requirements(Category.distribution, with(ARItems.aMetal, 20));
        }};

        ancientPhaseDuct = new ItemBridge("ancient-phase-duct"){{
            size = 1;
            health = 500;

            hasPower = false;
            range = 40;

            requirements(Category.distribution, with(ARItems.aAlloy, 20));
        }};

        ancientDuctUnloader = new DirectionalUnloader("ancient-duct-unloader"){{
            size = 1;
            health = 500;

            speed = 3f;

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

        ancientDrill = new Drill("ancient-drill"){{
            size = 2;
            health = 800;

            tier = 7;

            researchCost = with(ARItems.aMetal, 10);

            requirements(Category.production, with(ARItems.aMetal, 40));
        }};

        ancientBeamDrill = new BeamDrill("ancient-beam-drill"){{
            size = 2;
            health = 800;

            drillTime = 160f;
            tier = 7;

            range = 5;
            fogRadius = 5;
            researchCost = with(ARItems.aMetal, 10);

            consumeLiquid(ARLiquids.conductorLiquid, 0.25f / 60f).boost();

            requirements(Category.production, with(ARItems.aMetal, 40));
        }};

        ancientBurstDrill = new BurstDrill("ancient-burst-drill"){{
            size = 5;
            health = 5000;

            drillTime = 60f * 5f;
            hasPower = false;
            tier = 15;
            drillEffect = new MultiEffect(
                    Fx.mineImpact,
                    Fx.drillSteam,
                    Fx.dynamicSpikes.wrap(ARLiquids.conductorLiquid.color, 30f),
                    Fx.mineImpactWave.wrap(ARLiquids.conductorLiquid.color, 45f)
            );
            shake = 4f;
            itemCapacity = 80;
            arrowOffset = 2f;
            arrowSpacing = 5f;
            arrows = 2;
            glowColor.a = 0.6f;
            fogRadius = 10;

            consumeLiquids(LiquidStack.with(ARLiquids.conductorLiquid, 4f / 60f));

            requirements(Category.production, with(ARItems.aAlloy, 200, ARItems.conductor, 200, ARItems.cube, 140));
        }};

        //endregion
        //storage

        ancientCore = new CoreBlock("ancient-core-base"){{
            size = 4;
            health = 15200;
            armor = 20;

            unitType = ARUnits.epsilon;
            unitCapModifier = 20;

            itemCapacity = 10000;

            incinerateNonBuildable = true;
            alwaysUnlocked = true;

            requirements(Category.effect, with(ARItems.aMetal, 2000));
        }};

        ancientCoreFortress = new CoreBlock("ancient-core-fortress"){{
            size = 5;
            health = 30400;
            armor = 40;

            unitType = ARUnits.zehta;
            unitCapModifier = 27;

            itemCapacity = 15000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aMetal, 4000));
        }};

        ancientCoreStronghold = new CoreBlock("ancient-core-stronghold"){{
            size = 6;
            health = 60800;
            armor = 60;

            unitType = ARUnits.eta;
            unitCapModifier = 35;

            itemCapacity = 20000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aMetal, 8000));
        }};

        ancientCoreAncientSEye = new CoreBlock("ancient-core-ae"){{
            size = 7;
            health = 56230;
            armor = 500;

            unitType = ARUnits.theta;
            unitCapModifier = 50;

             itemCapacity = 25000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(ARItems.aAlloy, 3920));
        }};

        ancientContainer = new StorageBlock("ancient-container"){{
            size = 2;
            health = 800;

            itemCapacity = 120;

            requirements(Category.effect, with(ARItems.aMetal, 100));
        }};

        ancientVault = new StorageBlock("ancient-vault"){{
            size = 3;
            health = 4500;

            itemCapacity = 1200;

            requirements(Category.effect, with(ARItems.aAlloy, 300));
        }};

        ancientUnloader = new Unloader("ancient-unloader"){{
            size = 1;
            health = 200;

            speed = 60f / 11f;

            group = BlockGroup.transportation;

            requirements(Category.effect, with(ARItems.aMetal, 25, ARItems.conductor, 30));
        }};

        //endregion
        //turrets

        ancientDuo = new ItemTurret("ancient-duo"){{
            size = 1;

            shoot = new ShootAlternate(3.5f);

            shootY = 3f;
            reload = 20f;
            range = 110;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 250;
            inaccuracy = 2f;
            rotateSpeed = 10f;
            coolant = consumeCoolant(0.1f);
            researchCostMultiplier = 0.05f;

            limitRange();

            ammo(
                    ARItems.aAmmo,  new BasicBulletType(2.5f, 9){{
                        width = 7f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = 2;
                    }},
                    ARItems.conductor, new BasicBulletType(3.5f, 18){{
                        width = 9f;
                        height = 12f;
                        reloadMultiplier = 0.6f;
                        ammoMultiplier = 4;
                        lifetime = 60f;
                    }},
                    ARItems.cube, new BasicBulletType(3f, 12){{
                        width = 7f;
                        height = 9f;
                        homingPower = 0.1f;
                        reloadMultiplier = 1.5f;
                        ammoMultiplier = 5;
                        lifetime = 60f;
                    }}
            );

            requirements(Category.turret, with(ARItems.aMetal, 15));
        }};

        ancientMultiMissileSilo = new ItemTurret("ancient-multi-missile-silo"){{
            size = 7;
            health = 100;
            armor = 150;

            minRange = 80f;

            recoil = 0.5f;

            fogRadiusMultiuplier = 0.4f;
            coolantMultiplier = 6f;
            shootSound = Sounds.missileLaunch;

            minWarmup = 0.94f;
            shootWarmupSpeed = 0.03f;
            targetAir = false;
            targetUnderBlocks = false;

            shake = 6f;
            ammoPerShot = 20;
            maxAmmo = 40;
            shootY = -1;
            outlineColor = Pal.darkOutline;
            envEnabled |= Env.space;
            reload = 600f;
            range = 1350;
            shootCone = 1f;
            rotateSpeed = 0.9f;
            limitRange();

            reload = 60f * 3.25f;
            cooldownTime = reload;
            rotateSpeed = 1.75f;
            shootEffect = Fx.shootSmokeMissile;
            smokeEffect = Fx.shootSmokeMissile;

            ammo(
                    ARItems.conductor, new BasicBulletType(0f, 1){{
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootSmokeMissile;
                        ammoMultiplier = 1f;

                        spawnUnit = ARBullets.ancientMissile;
                    }}
            );

            shoot = new ShootAlternate(){{
                shotDelay = 6f;
                shots = 4;
                barrels = 4;
                spread = 10f;
            }};

            requirements(Category.turret, with(ARItems.aMetal, 350, ARItems.conductor, 50));
        }};

        ancientRailgun = new ItemTurret("ancient-railgun"){{
            size = 5;
            health = 12500;
            armor = 150;

            range = 400F;
            minRange = 80f;

            reload = 120f;
            inaccuracy = 0f;
            cooldownTime = reload;
            rotateSpeed = 1.5f;

            shootEffect = Fx.instShoot;
            smokeEffect = Fx.smokeCloud;
            shootSound = Sounds.railgun;
            ammoUseEffect = Fx.casing3Double;

            ammo(
                    ARItems.aAmmo, new PointBulletType(){{
                        damage = 1000;
                        pierceArmor = true;
                        lifetime = 180f;
                        buildingDamageMultiplier = 5f;

                        hitEffect = Fx.instHit;
                        trailEffect = Fx.instTrail;
                        despawnEffect = Fx.instBomb;
                        trailSpacing = 20f;
                    }},
                    ARItems.conductor, new PointBulletType(){{
                        damage = 1100;
                        pierceArmor = true;
                        splashDamage = 100;
                        splashDamageRadius = 8f;
                        lifetime = 180f;
                        buildingDamageMultiplier = 5f;

                        hitEffect = Fx.instHit;
                        trailEffect = Fx.instTrail;
                        despawnEffect = Fx.instBomb;
                        trailSpacing = 20f;
                    }}
            );

            drawer = new DrawTurret("reinforced-"){{
                parts.addAll(
                        new RegionPart("-shooter"){{
                            moveY = -3f;
                            progress = PartProgress.recoil;
                        }},
                        new RegionPart("-end"),
                        new RegionPart("-mid"){{
                            moveY = -3f;
                            progress = PartProgress.recoil;
                        }}
                );
            }};

            buildType = () -> new ItemTurretBuild(){
                @Override
                public void drawSelect(){
                    super.drawSelect();
                    Drawf.dashCircle(x, y, minRange, Pal.redderDust);
                }

                @Override
                protected boolean validateTarget(){
                    return (!Units.invalidateTarget(target, canHeal() ? Team.derelict : team, x, y) && !within(target, minRange)) || isControlled() || logicControlled();
                }
            };

            requirements(Category.turret, with(ARItems.aAlloy, 200, ARItems.conductor, 32, ARItems.cube, 16));
        }};

        ancientRailcannon = new ItemTurret("ancient-railcannon"){{
            size = 7;
            health = 24500;
            armor = 200;

            range = 800F;
            minRange = 160f;

            shootY = 17.5f;

            reload = 350f;
            recoil = 5f;
            inaccuracy = 0f;
            minWarmup = 0.96f;
            shootWarmupSpeed = 0.03f;
            cooldownTime = reload;
            rotateSpeed = 0.5f;
            moveWhileCharging = false;
            ammoPerShot = 1;

            consumeLiquid(ARLiquids.conductorLiquid, 1.5f);

            shootEffect = ARFx.instShoot;
            smokeEffect = Fx.smokeCloud;

            shootSound = Sounds.blaster;
            chargeSound = Sounds.lasercharge2;
            ammoUseEffect = Fx.none;

            shoot = new ShootPattern(){{
                firstShotDelay = 120f;
            }};

            ammo(
                    ARItems.aAlloy, ARBullets.railcannonBullet1,
                    ARItems.cube, ARBullets.railcannonBullet2
            );

            var haloProgress = DrawPart.PartProgress.warmup;
            Color haloColor = ARColor.ancientYellow;
            float haloY = -40f, haloRotSpeed = 4f;

            drawer = new DrawTurret(){{
                parts.addAll(
                        //actual turret
                        new RegionPart("-shooter"){{
                            mirror = true;
                            x = 0;
                            y = 0;
                            moveX = -3f;
                            moveY = 27.5f;
                            progress = PartProgress.warmup;
                        }},
                        new RegionPart("-wing"),
                        new RegionPart("-engine"){{
                            outline = true;
                            moveY = -8f;
                            progress = PartProgress.warmup;
                        }},
                        new RegionPart("-top"){{
                            moveY = -8f;
                            progress = PartProgress.warmup;
                        }},
                        new ShapePart(){{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = haloColor;
                            circle = true;
                            hollow = true;
                            stroke = 0f;
                            strokeTo = 2f;
                            radius = 10f;
                            layer = Layer.effect;
                            y = haloY;
                            rotateSpeed = haloRotSpeed;
                        }},
                        new ShapePart(){{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = haloColor;
                            circle = true;
                            hollow = true;
                            stroke = 0f;
                            strokeTo = 1.6f;
                            radius = 4f;
                            layer = Layer.effect;
                            y = haloY;
                            rotateSpeed = haloRotSpeed;
                        }},
                        new HaloPart(){{
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            y = haloY;

                            haloRotation = 90f;
                            shapes = 2;
                            triLength = 0f;
                            triLengthTo = 20f;
                            haloRadius = 16f;
                            tri = true;
                            radius = 4f;
                        }},
                        new HaloPart(){{
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            y = haloY;

                            haloRotation = 90f;
                            shapes = 2;
                            triLength = 0f;
                            triLengthTo = 5f;
                            haloRadius = 16f;
                            tri = true;
                            radius = 4f;
                            shapeRotation = 180f;
                        }},
                        new HaloPart(){{
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            y = haloY;
                            haloRotateSpeed = -haloRotSpeed;

                            shapes = 4;
                            triLength = 0f;
                            triLengthTo = 5f;
                            haloRotation = 45f;
                            haloRadius = 16f;
                            tri = true;
                            radius = 8f;
                        }},
                        new HaloPart(){{
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            y = haloY;
                            haloRotateSpeed = -haloRotSpeed;

                            shapes = 4;
                            shapeRotation = 180f;
                            triLength = 0f;
                            triLengthTo = 2f;
                            haloRotation = 45f;
                            haloRadius = 16f;
                            tri = true;
                            radius = 8f;
                        }},
                        new HaloPart(){{
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            y = haloY;
                            haloRotateSpeed = haloRotSpeed;

                            shapes = 4;
                            triLength = 0f;
                            triLengthTo = 3f;
                            haloRotation = 45f;
                            haloRadius = 10f;
                            tri = true;
                            radius = 6f;
                        }}
                );
            }};

            buildType = () -> new ItemTurretBuild(){
                @Override
                public void drawSelect(){
                    super.drawSelect();
                    Drawf.dashCircle(x, y, minRange, Pal.redderDust);
                }

                @Override
                protected boolean validateTarget(){
                    return (!Units.invalidateTarget(target, canHeal() ? Team.derelict : team, x, y) && !within(target, minRange)) || isControlled() || logicControlled();
                }
            };

            requirements(Category.turret, with(ARItems.aAlloy, 392, ARItems.conductor, 128, ARItems.cube, 64));
        }};

        ancientRaidMissileSilo = new ItemTurret("ancient-raid-missile-silo"){{
            size = 16;
            health = 128000;
            armor = 15000;
        }};

        eyeOfTurret = new ItemTurret("eyeOfTurret"){{
            size = 16;
            health = 128000;
            armor = 15000;

            itemCapacity = 150;

            shootCone = 20f;
            range = 1600F;
            shootY = 15f;
            reload = 1200f;
            recoil = 5f;
            inaccuracy = 15f;
            minWarmup = 0.96f;
            shootWarmupSpeed = 0.03f;
            cooldownTime = reload;
            rotateSpeed = 0.04f;
            moveWhileCharging = false;
            ammoPerShot = 15;

            outlineRadius = 7;

            consumeLiquid(ARLiquids.conductorLiquid, 4f);

            shootEffect = ARFx.instShoot;
            smokeEffect = Fx.smokeCloud;

            shootSound = Sounds.laserblast;
            chargeSound = Sounds.lasercharge2;
            ammoUseEffect = Fx.none;

            shoot = new ShootPattern(){{
                firstShotDelay = 120f;
                shots = 20;
                shotDelay = 5f;
            }};

            ammo(
                    ARItems.conductor, new BulletType(){{
                        spawnUnit = ARBullets.ancientRaidMissile;
                        shoot = new ShootPattern(){{
                            shots = 5;
                            shotDelay = 10f;
                        }};
                    }},
                    ARItems.cube, ARBullets.eye
            );

            var haloProgress = DrawPart.PartProgress.warmup.delay(0.05f);
            Color haloColor = ARColor.ancientYellow;
            float haloY = -14.75f, haloX = 38f, haloRotSpeed = 4.5f;

            drawer = new DrawTurret(){{
                parts.addAll(
                        //charger LR
                        new ShapePart(){{
                            mirror = true;
                            progress = PartProgress.warmup.delay(0.05f);
                            color = haloColor;
                            circle = true;
                            hollow = true;
                            stroke = 0f;
                            strokeTo = 2f;
                            radius = 6f;
                            layer = Layer.effect;
                            x = haloX;
                            y = haloY;
                            rotateSpeed = haloRotSpeed;
                        }},
                        new ShapePart(){{
                            mirror = true;
                            progress = PartProgress.warmup.delay(0.05f);
                            color = haloColor;
                            circle = true;
                            hollow = true;
                            stroke = 0f;
                            strokeTo = 1.6f;
                            radius = 2f;
                            layer = Layer.effect;
                            x = haloX;
                            y = haloY;
                            rotateSpeed = haloRotSpeed;
                        }},
                        new HaloPart(){{
                            mirror = true;
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            x = haloX;
                            y = haloY;
                            haloRotateSpeed = -haloRotSpeed;

                            shapes = 4;
                            triLength = 0f;
                            triLengthTo = 5f;
                            haloRotation = 45f;
                            haloRadius = 10f;
                            tri = true;
                            radius = 7f;
                        }},
                        new HaloPart(){{
                            mirror = true;
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            x = haloX;
                            y = haloY;
                            haloRotateSpeed = -haloRotSpeed;

                            shapes = 4;
                            shapeRotation = 180f;
                            triLength = 0f;
                            triLengthTo = 2f;
                            haloRotation = 45f;
                            haloRadius = 10f;
                            tri = true;
                            radius = 7f;
                        }},
                        new HaloPart(){{
                            mirror = true;
                            progress = haloProgress;
                            color = haloColor;
                            layer = Layer.effect;
                            x = haloX;
                            y = haloY;
                            haloRotateSpeed = haloRotSpeed;

                            shapes = 4;
                            triLength = 0f;
                            triLengthTo = 3f;
                            haloRotation = 45f;
                            haloRadius = 2.5f;
                            tri = true;
                            radius = 4f;
                        }},
                        new RegionPart("-joint"),
                        new RegionPart("-hatchBack"){{
                            outline = false;
                            moveY = -12f;
                            progress = PartProgress.warmup;
                        }},
                        new RegionPart("-hatchFront"){{
                            outline = false;
                            moveY = 12f;
                            progress = PartProgress.warmup;
                        }},
                        new RegionPart("-side"){{
                            mirror = true;
                        }},
                        new RegionPart("-shooter"),
                        new RegionPart("-mid")
                );
            }};

            requirements(Category.turret, with(ARItems.aAlloy, 8000, ARItems.conductor, 600, ARItems.cube, 400));
        }};

        //endregion
        //units

        //endregion
    }
}
