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
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.blocks.units.UnitCargoUnloadPoint;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.with;

public class AMBlocks {
    public static Block
            //env
            ancientFloor, ancientFloorHeat,
            ancientMetalUnit, ancientVent,

            //ore
            ancientScrapOre
    ;
    public static Block
            //crafting
            ancientCementMixer, ancientCementAlloyARCMixer,
            ancientMetalSmelter, ancientKiln, conductorSmelter, ancientAlloySmelter,
            conductorLiquidMixer, ammoMaker, cubeGenerator, ancientMetalARCSmelter, ancientAlloyARCSmelter,
            //wall
            ancientWall, ancientWalLarge, ancientDefenceWall, ancientDefenceWallLarge, ancientDefenceDoor,
            //defence
            //ancientMendProjector, ancientMendDome, ancientBoostProjector, ancientOverdriveDome, ancientShieldProjector, ancientShieldDome, ancientRadar,
            //distribution
            ancientDuct, ancientArmorDuct, ancientDuctRouter, ancientDuctDisruptor, ancientOverflowDuct, ancientUnderflowDuct, ancientDuctBridge, ancientPhaseDuct, ancientDuctUnloader,
            ancientStackConveyor, ancientStackRouter,
            ancientMassDriver, ancientItemTransportCannon, ancientCargoLoader, ancientCargoUnloadPoint,
            //liquid
            ancientPump, ancientReinforcedPump,
            ancientConduit, ancientReinforcedConduit, ancientLiquidJunction, ancientLiquidRouter, ancientBridgeConduit, ancientPhaseConduit,
            ancientLiquidContainer, ancientLiquidTank,
            //power
            //drill
            ancientDrill, ancientScrapCracker, ancientBeamDrill, ancientBurstDrill,
            //storage
            ancientCore, ancientCoreFortress, ancientCoreStronghold, ancientCoreAncientSEye,
            ancientContainer, ancientVault,
            //turret
            ancientDuo, ancientSalvo/*, ancientAntiAir, ancientMissileSilo*/, ancientMultiMissileSilo, ancientRaidMissileSilo, ancientRailgun, ancientRailcannon, eyeOfTurret//,
            //unit
            //leg
            //legGenerator, legRegenerationMachine, LegRegenerationAssembler,
            //mech
            //mechGenerator, mechRegenerationMachine, mechRegenerationAssembler,
            //air
            //airGenerator, airRegenerationMachine, airRegenerationAssembler,
            //upGrader
            //primeRegenerationMachine, upGradeModule
    ;

    public static void load(){
        int wallHPMultiplier = 6;
        //region env
        ///floor
        ancientFloor = new Floor("ancient-tile", 4){{
            attributes.set(Attribute.water, -0.75f);
        }};
        ancientFloorHeat = new Floor("ancient-tile-heat", 3){{
            attributes.set(Attribute.water, -0.75f);
            lightRadius = 40f;
        }};

        ///wall
        ancientMetalUnit = new StaticWall("ancient-metal-unit"){{
            attributes.set(ARContent.aScrap, 0.4f);
            variants = 1;
        }};

        ancientVent = new SteamVent("ancient-vent"){{
            variants = 2;
            attributes.set(Attribute.steam, 1f);

            parent = blendGroup = AMBlocks.ancientFloor;
        }};

        ///ore

        ancientScrapOre = new OreBlock("ancient-scrap-ore", AMItems.aScrap){{
            wallOre = true;
            oreThreshold = 1f;
            oreScale = 28f;
        }};
        //endregion
        //region factory

        ancientMetalSmelter = new GenericCrafter("ancient-metal-smelter"){{
            size = 3;

            consumeItems(with(AMItems.aScrap, 4));
            outputItem = new ItemStack(AMItems.aMetal, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(AMItems.aScrap, 120));
        }};

        ancientKiln = new GenericCrafter("ancient-kiln"){{
            size = 2;

            consumeItems(with(AMItems.aScrap, 4));
            outputItem = new ItemStack(AMItems.aGlass, 1);
            craftTime = 0.5f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(Items.graphite, 30, AMItems.aScrap, 60, AMItems.aMetal, 30));
        }};

        conductorSmelter = new GenericCrafter("conductor-smelter"){{
            size = 2;
            health = 800;

            consumeItems(with(AMItems.aMetal, 1, Items.silicon, 1));
            outputItem = new ItemStack(AMItems.conductor, 1);
            craftTime = 1.2f * 60f;
            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(AMItems.aMetal, 20));
        }};

        conductorLiquidMixer = new GenericCrafter("conductor-liquid-mixer"){{
            size = 2;
            health = 800;

            consumeLiquid(Liquids.water, 1.2f);
            consumeItems(with(AMItems.conductor, 1));
            outputLiquid = new LiquidStack(ARLiquids.conductorLiquid, 1f);

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(ARLiquids.conductorLiquid){{drawLiquidLight = true;}}, new DrawDefault()
            );

            requirements(Category.crafting, with(AMItems.aMetal, 32, AMItems.aGlass , 20));
        }};

        ammoMaker = new GenericCrafter("ammo-maker"){{
            size = 2;
            health = 800;

            consumeItems(with(AMItems.aMetal, 4));
            outputItem = new ItemStack(AMItems.aAmmo, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault()
            );

            requirements(Category.crafting, with(AMItems.aMetal, 80));
        }};

        ancientAlloySmelter = new GenericCrafter("ancient-alloy-smelter"){{
            size = 3;

            consumeLiquid(ARLiquids.conductorLiquid, 1.2f);
            consumeItems(with(AMItems.aScrap, 4));
            outputItem = new ItemStack(AMItems.aAlloy, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawDefault(), new DrawFlame()
            );

            requirements(Category.crafting, with(AMItems.aMetal, 840, AMItems.conductor, 50));
        }};

        cubeGenerator = new GenericCrafter("cube-generator"){{
            size = 3;
            health = 1800;

            consumeLiquid(ARLiquids.conductorLiquid, 2f);
            consumeItems(with(AMItems. aAlloy, 1, AMItems.conductor, 2));
            outputItem = new ItemStack(AMItems.cube, 1);
            craftTime = 2f * 60f;
            drawer = new DrawMulti(
                    new DrawDefault()
            );

            requirements(Category.crafting, with(AMItems.aAlloy, 200));
        }};

        ancientMetalARCSmelter = new GenericCrafter("ancient-metal-arc-smelter"){{
            size = 5;
            health = 4500;

            outputItem = new ItemStack(AMItems.aMetal, 1);
            craftTime = 1.33f * 60f;
        }};

        ancientAlloyARCSmelter = new GenericCrafter("ancient-alloy-arc-smelter"){{
            size = 5;
            health = 4500;

            consumeLiquid(ARLiquids.conductorLiquid, 1.2f);
            consumeItems(with(AMItems.aScrap, 4));
            outputItem = new ItemStack(AMItems.aAlloy, 1);
            craftTime = 1.33f * 60f;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawLiquidTile(), new DrawArcSmelt(), new DrawDefault()
            );
        }};

        //endregion
        //region wall

        ancientWall = new Wall("ancient-wall"){{
            size = 1;
            health = 750 * wallHPMultiplier;
            requirements(Category.defense, with(AMItems.aMetal, 8));
        }};
        ancientWalLarge = new Wall("ancient-wall-large"){{
            size = 2;
            health = 750 * 4 * wallHPMultiplier;

            requirements(Category.defense, with(AMItems.aMetal, 32));
        }};
        ancientDefenceWall = new Wall("ancient-defence-wall"){{
            size = 1;
            health = 1250 * wallHPMultiplier;
            armor = 50;

            requirements(Category.defense, with(AMItems.aAlloy, 8));
        }};
        ancientDefenceWallLarge = new Wall("ancient-defence-wall-large"){{
            size = 2;
            health = 1250 * 4 * wallHPMultiplier;
            armor = 100;

            requirements(Category.defense, with(AMItems.aAlloy, 32));
        }};
        ancientDefenceDoor = new AutoDoor("ancient-defence-door"){{
            size = 2;
            health = 1000 * 4 * wallHPMultiplier;
            armor = 50;

            requirements(Category.defense, with(AMItems.aAlloy, 32));
        }};
        //endregion
        //defence

        //endregion
        //distribution


        //ducts
        ancientDuct = new Duct("ancient-duct"){{
            size = 1;
            health = 200;

            speed = 3f;

            researchCost = with(AMItems.aMetal, 5);

            requirements(Category.distribution, with(AMItems.aMetal, 1));
        }};

        ancientArmorDuct = new Duct("ancient-armor-duct"){{
            size = 1;
            health = 500;

            armored = true;

            speed = 2.5f;

            researchCost = with(AMItems.aMetal, 300, AMItems.aAlloy, 100);

            requirements(Category.distribution, with(AMItems.aMetal, 2, AMItems.aAlloy, 1));
        }};

        ancientDuctRouter = new DuctRouter("ancient-duct-router"){{
            size = 1;
            health = 500;

            speed = 3f;

            regionRotated1 = 1;
            solid = false;

            researchCost = with(AMItems.aMetal, 30);

            requirements(Category.distribution, with(AMItems.aMetal, 10));
        }};

        ancientDuctDisruptor = new Router("ancient-duct-disruptor"){{
            size = 2;
            health = 2000;

            researchCost = with(AMItems.aAlloy, 60);

            requirements(Category.distribution, with(AMItems.aAlloy, 20));
        }};

        ancientOverflowDuct = new OverflowDuct("ancient-overflow-duct"){{
            size = 1;
            health = 500;

            speed = 3f;

            solid = false;

            researchCostMultiplier = 1.5f;

            requirements(Category.distribution, with(Items.graphite, 8, AMItems.aAlloy, 8));
        }};

        ancientUnderflowDuct = new OverflowDuct("ancient-underflow-duct"){{
            size = 1;
            health = 500;

            speed = 3f;

            invert = true;
            solid = false;

            researchCostMultiplier = 1.5f;

            requirements(Category.distribution, with(Items.graphite, 8, AMItems.aAlloy, 8));
        }};
        ancientDuctBridge = new DuctBridge("ancient-duct-bridge"){{
            size = 1;
            health = 200;

            speed = 3f;

            buildCostMultiplier = 2f;
            researchCostMultiplier = 0.3f;

            requirements(Category.distribution, with(AMItems.aMetal, 20));
        }};

        ancientPhaseDuct = new ItemBridge("ancient-phase-duct"){{
            size = 1;
            health = 500;

            hasPower = false;
            range = 40;

            requirements(Category.distribution, with(AMItems.aAlloy, 20));
        }};

        ancientDuctUnloader = new DirectionalUnloader("ancient-duct-unloader"){{
            size = 1;
            health = 500;

            speed = 3f;

            solid = false;
            underBullets = true;
            regionRotated1 = 1;

            requirements(Category.distribution, with(Items.graphite, 20, AMItems.aAlloy, 10));
        }};

        //special

        ancientStackConveyor = new StackConveyor("ancient-stack-conveyor"){{
            size = 1;
            health = 500;

            speed = 0.1f;

            itemCapacity = 20;

            outputRouter = false;
            underBullets = true;
            baseEfficiency = 1f;

            researchCost = with(AMItems.aMetal, 30, AMItems.aAlloy, 80);

            requirements(Category.distribution, with(AMItems.conductor, 30, AMItems.aAlloy, 80));
        }};

        ancientStackRouter = new StackRouter("ancient-stack-router"){{
            size = 1;
            health = 500;

            speed = 6f;

            baseEfficiency = 1f;
            underBullets = true;
            solid = false;

            requirements(Category.distribution, with(AMItems.conductor, 5, AMItems.aAlloy, 1));
        }};

        ancientMassDriver = new MassDriver("ancient-mass-driver"){{
            size = 3;
            health = 4500;

            hasPower = false;

            itemCapacity = 180;
            reload = 300f;
            range = 520;

            requirements(Category.distribution, with(AMItems.aMetal, 275));
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

            requirements(Category.distribution, with(AMItems.aAlloy, 715, AMItems.conductor, 135, AMItems.cube, 50));
        }};

        ancientCargoLoader = new UnitCargoLoader("ancient-cargo-loader"){{
            size = 3;
            buildTime = 60f * 8f;

            hasPower = false;

            unitType = AMUnits.ancientCargoDrone;

            consumeLiquid(ARLiquids.conductorLiquid, 10f / 60f);

            itemCapacity = 400;

            requirements(Category.distribution, with(AMItems.aAlloy, 200, AMItems.conductor, 75, AMItems.cube, 25));
        }};

        ancientCargoUnloadPoint = new UnitCargoUnloadPoint("ancient-cargo-unload-point"){{
            size = 2;

            itemCapacity = 200;

            requirements(Category.distribution, with(AMItems.aAlloy, 225, AMItems.conductor, 75));
        }};

        //endregion
        //liquid

        ancientPump = new Pump("ancient-pump"){{
            size = 1;
            health = 200;

            pumpAmount = 0.25f;

            requirements(Category.liquid, with(AMItems.aMetal, 15, AMItems.aGlass, 10));
        }};

        ancientReinforcedPump = new Pump("ancient-reinforced-pump"){{
            size = 2;
            health = 2000;

            consumeLiquid(ARLiquids.conductorLiquid, 10f / 60f);

            pumpAmount = 0.5f;

            requirements(Category.liquid, with(AMItems.aAlloy, 40, AMItems.aGlass, 25));
        }};

        ancientConduit = new Conduit("ancient-conduit"){{
            size = 1;
            health = 200;

            liquidCapacity = 16f;
            liquidPressure = 1.025f;

            underBullets = true;

            requirements(Category.liquid, with(AMItems.aMetal, 2, AMItems.aGlass, 1));
        }};

        ancientReinforcedConduit = new ArmoredConduit("ancient-reinforced-conduit"){{
            size = 1;
            health = 500;

            liquidCapacity = 20f;
            liquidPressure = 2f;

            underBullets = true;
            botColor = Pal.darkestMetal;

            requirements(Category.liquid, with(AMItems.aAlloy, 2, AMItems.aGlass, 1));
        }};

        ancientLiquidJunction = new LiquidJunction("ancient-liquid-junction"){{
            size = 1;
            health = 200;
            ((Conduit)ancientConduit).junctionReplacement = this;

            solid = false;
            underBullets = true;

            requirements(Category.liquid, with(AMItems.aMetal, 4, AMItems.aGlass, 8));
        }};

        ancientLiquidRouter = new LiquidRouter("ancient-liquid-router"){{
            size = 1;
            health = 200;

            liquidCapacity = 20f;

            solid = false;
            underBullets = true;

            requirements(Category.liquid, with(AMItems.aMetal, 4, AMItems.aGlass, 2));
        }};

        ancientBridgeConduit = new LiquidBridge("ancient-bridge-conduit"){{
            size = 1;
            health = 200;

            arrowSpacing = 6f;
            range = 6;
            hasPower = false;

            underBullets = true;

            ((Conduit)ancientConduit).rotBridgeReplacement = this;

            requirements(Category.liquid, with(AMItems.aMetal, 4, AMItems.aGlass, 8));
        }};

        ancientPhaseConduit = new LiquidBridge("ancient-phase-conduit"){{
            size = 1;
            health = 500;

            range = 30;
            hasPower = false;

            underBullets = true;

            ((Conduit)ancientReinforcedConduit).rotBridgeReplacement = this;

            requirements(Category.liquid, with(AMItems.aAlloy, 10, AMItems.aGlass, 20));
        }};

        ancientLiquidContainer = new LiquidRouter("ancient-liquid-container"){{
            size = 2;
            health = 800;

            solid = true;

            liquidCapacity = 3000f;
            liquidPadding = 1.8f;

            underBullets = true;

            requirements(Category.liquid, with(AMItems.aMetal, 30, AMItems.aGlass, 15));
        }};

        ancientLiquidTank = new LiquidRouter("ancient-liquid-tank"){{
            size = 3;
            health = 4500;

            solid = true;

            liquidCapacity = 12000;
            liquidPadding = 2.5f;

            requirements(Category.liquid, with(AMItems.aAlloy , 50, AMItems.aGlass, 40));
        }};

        //endregion
        //power

        //endregion
        //drill

        ancientDrill = new Drill("ancient-drill"){{
            size = 2;
            health = 800;

            tier = 7;

            researchCost = with(AMItems.aMetal, 10);

            requirements(Category.production, with(AMItems.aMetal, 40));
        }};
        ancientScrapCracker = new WallCrafter("ancient-scrap-cracker"){{
            size = 2;
            health = 800;

            attribute = ARContent.aScrap;
            output = AMItems.aScrap;

            drillTime = 110f;
            fogRadius = 2;

            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;

            requirements(Category.production, with(AMItems.aScrap, 32));
        }};
        ancientBeamDrill = new BeamDrill("ancient-beam-drill"){{
            size = 2;
            health = 800;

            drillTime = 160f;
            tier = 7;

            range = 5;
            fogRadius = 5;
            researchCost = with(AMItems.aMetal, 10);

            consumeLiquid(ARLiquids.conductorLiquid, 0.25f / 60f).boost();

            requirements(Category.production, with(AMItems.aMetal, 40));
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

            requirements(Category.production, with(AMItems.aAlloy, 200, AMItems.conductor, 200, AMItems.cube, 140));
        }};

        //endregion
        //storage

        ancientCore = new CoreBlock("ancient-core-base"){{
            size = 4;
            health = 15200;
            armor = 20;

            unitType = AMUnits.epsilon;
            unitCapModifier = 20;

            itemCapacity = 10000;

            incinerateNonBuildable = true;
            alwaysUnlocked = true;

            requirements(Category.effect, with(AMItems.aMetal, 2000));
        }};

        ancientCoreFortress = new CoreBlock("ancient-core-fortress"){{
            size = 5;
            health = 30400;
            armor = 40;

            unitType = AMUnits.zehta;
            unitCapModifier = 27;

            itemCapacity = 15000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(AMItems.aMetal, 4000));
        }};

        ancientCoreStronghold = new CoreBlock("ancient-core-stronghold"){{
            size = 6;
            health = 60800;
            armor = 60;

            unitType = AMUnits.eta;
            unitCapModifier = 35;

            itemCapacity = 20000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(AMItems.aMetal, 8000));
        }};

        ancientCoreAncientSEye = new CoreBlock("ancient-core-ae"){{
            size = 7;
            health = 56230;
            armor = 500;

            unitType = AMUnits.theta;
            unitCapModifier = 50;

            itemCapacity = 25000;

            incinerateNonBuildable = true;

            requirements(Category.effect, with(AMItems.aAlloy, 3920));
        }};

        ancientContainer = new StorageBlock("ancient-container"){{
            size = 2;
            health = 800;

            itemCapacity = 120;

            requirements(Category.effect, with(AMItems.aMetal, 100));
        }};

        ancientVault = new StorageBlock("ancient-vault"){{
            size = 3;
            health = 4500;

            itemCapacity = 1200;

            requirements(Category.effect, with(AMItems.aAlloy, 300));
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
                    AMItems.aScrap,  new BasicBulletType(2.5f, 9){{
                        width = 7f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = 2;
                    }},
                    AMItems.conductor, new BasicBulletType(3.5f, 18){{
                        width = 9f;
                        height = 12f;
                        reloadMultiplier = 1.5f;
                        homingPower = 0.1f;
                        ammoMultiplier = 4;
                        lifetime = 60f;
                    }}
            );

            requirements(Category.turret, with(AMItems.aScrap, 15));
        }};

        ancientSalvo = new ItemTurret("ancient-salvo"){{
            size = 2;
            health = 800;

            range = 190f;
            reload = 31f;
            ammoEjectBack = 3f;
            recoil = 3f;
            shake = 1f;
            shoot.shots = 4;
            shoot.shotDelay = 3f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;

            limitRange();

            ammo(
                    AMItems.aMetal, new BasicBulletType(2.5f, 10){{
                        width = 7f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = 2;
                    }},
                    AMItems.conductor, new BasicBulletType(2.5f, 18, "bullet"){{
                        width = 7f;
                        height = 9f;
                        homingPower = 0.1f;
                        reloadMultiplier = 1.5f;
                        ammoMultiplier = 5;
                        lifetime = 60f;
                    }}
            );

            requirements(Category.turret, with(AMItems.aScrap, 100, Items.graphite, 80, AMItems.aMetal, 50));
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
                    AMItems.conductor, new BasicBulletType(0f, 1){{
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

            requirements(Category.turret, with(AMItems.aMetal, 350, AMItems.conductor, 50));
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
                    AMItems.aAmmo, new PointBulletType(){{
                        damage = 1000;
                        pierceArmor = true;
                        lifetime = 180f;
                        buildingDamageMultiplier = 5f;

                        hitEffect = Fx.instHit;
                        trailEffect = Fx.instTrail;
                        despawnEffect = Fx.instBomb;
                        trailSpacing = 20f;
                    }},
                    AMItems.conductor, new PointBulletType(){{
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

            requirements(Category.turret, with(AMItems.aAlloy, 200, AMItems.conductor, 32, AMItems.cube, 16));
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
                    AMItems.aAlloy, ARBullets.railcannonBullet1,
                    AMItems.cube, ARBullets.railcannonBullet2
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

            requirements(Category.turret, with(AMItems.aAlloy, 392, AMItems.conductor, 128, AMItems.cube, 64));
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
                    AMItems.conductor, new BulletType(){{
                        spawnUnit = ARBullets.ancientRaidMissile;
                        shoot = new ShootPattern(){{
                            shots = 5;
                            shotDelay = 10f;
                        }};
                    }},
                    AMItems.cube, ARBullets.eye
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

            requirements(Category.turret, with(AMItems.aAlloy, 8000, AMItems.conductor, 600, AMItems.cube, 400));
        }};

        //endregion
        //units

        //endregion
    }
}
