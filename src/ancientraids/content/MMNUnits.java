package ancientraids.content;

import ancientraids.AncientRaids;
import ancientraids.expand.bullets.AccelSpeedBulletType;
import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AssemblerAI;
import mindustry.ai.types.MinerAI;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.bullet.BombBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.EntityMapping;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.unit.TankUnitType;
import mindustry.type.weapons.BuildWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;

public class MMNUnits {
    public static Weapon momona_weapon;

    //Player's units. Units made by adapting ancient units.
    public static UnitType
            //generic
            pUnitT1,
            //tank
            pTankAlpha, pTankBeta, pTankGamma, pTankBoss,
            //mech
            pMechAlpha, pMechBeta, pMechGamma, pMechBoss,
            //ship
            pShipAlpha, pShipBeta, pShipGamma, pShipBoss,
            //fighter
            recon, launcher, bommer,
            //support air
            pAirMiner, pAirRepair, pAirReBuilder, pAirHealer, pAirCarrier
    ;
    //
    //core units
    public static UnitType momo, mona, momona;
    //
    //special units
    public static UnitType mmnCargoDrone, mmnPayloadCargoDrone, mmnAssemblyDrone;
    //

    static{
        EntityMapping.nameMap.put(AncientRaids.name("pUnitT1"), EntityMapping.idMap[3]);

        EntityMapping.nameMap.put(AncientRaids.name("pTankAlpha"), EntityMapping.idMap[43]);
        EntityMapping.nameMap.put(AncientRaids.name("pTankBeta"), EntityMapping.idMap[43]);
        EntityMapping.nameMap.put(AncientRaids.name("pTankGamma"), EntityMapping.idMap[43]);
        EntityMapping.nameMap.put(AncientRaids.name("pTankBoss"), EntityMapping.idMap[43]);

        EntityMapping.nameMap.put(AncientRaids.name("pMechAlpha"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(AncientRaids.name("pMechBeta"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(AncientRaids.name("pMechGamma"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(AncientRaids.name("pMechBoss"), EntityMapping.idMap[24]);

        EntityMapping.nameMap.put(AncientRaids.name("pShipAlpha"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pShipBeta"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pShipGamma"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pShipBoss"), EntityMapping.idMap[3]);

        EntityMapping.nameMap.put(AncientRaids.name("pAirMiner"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pAirRepair"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pAirReBuilder"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pAirHealer"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("pAirCarrier"), EntityMapping.idMap[5]);

        EntityMapping.nameMap.put(AncientRaids.name("recon"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("launcher"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("bommer"), EntityMapping.idMap[3]);

        EntityMapping.nameMap.put(AncientRaids.name("momo"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("mona"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(AncientRaids.name("momona"), EntityMapping.idMap[5]);

        EntityMapping.nameMap.put(AncientRaids.name("item-drone"), EntityMapping.idMap[36]);
        EntityMapping.nameMap.put(AncientRaids.name("payload-drone"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("mmn-assembly-drone"), EntityMapping.idMap[36]);
    }

    public static class MMNUnitType extends UnitType {
        public MMNUnitType(String name){
            super(name);
            envDisabled = Env.none;

            ammoType = new ItemAmmoType(MMNItems.iron);
        }
    }

    public static class MMNTankUnitType extends TankUnitType {
        public MMNTankUnitType(String name){
            super(name);
            envDisabled = Env.none;

            ammoType = new ItemAmmoType(MMNItems.iron);
        }
    }

    private static void loadWeapons(){
    }

    public static void load() {
        loadWeapons();

        pUnitT1 = new MMNUnitType("pUnitT1"){{
            health = 180;

            flying = true;
            isEnemy = false;
        }};

        //tank
        pTankAlpha = new MMNTankUnitType("pTankAlpha"){{
            health = 500;
            armor = 5;
        }};
        pTankBeta = new MMNTankUnitType("pTankBeta"){{
            health = 2500;
            armor = 75;
        }};
        pTankGamma = new MMNTankUnitType("pTankGamma"){{
            health = 5000;
            armor = 150;
        }};
        pTankBoss = new MMNTankUnitType("pTankBoss"){{
            health = 50000;
            armor = 1500;

            speed = 0.5f;
            rotateSpeed = 1f;

            hitSize = 80f;

            treadRects = new Rect[]{new Rect(16 - 60, 48 - 70f, 30, 75), new Rect()};

            weapons.add(
                    new Weapon(AncientRaids.name("pTankBoss-weapon")){{
                        mirror = false;
                        rotate = true;

                        x = y = 0;
                        shootY = 30f;
                        reload = 60f * 5f;
                        cooldownTime = 2f *60f;
                        recoil = 2f;
                        rotateSpeed = 0.4f;
                        shoot = new ShootAlternate(25f){{
                            shots = 3;
                        }};

                        bullet = new AccelSpeedBulletType(40,5000){{
                            velocityBegin = 1f;
                            velocityIncrease = 20f;
                            accelInterp = a -> 2 * (0.9f * a + 0.31f) + 1f / (5f * (a + 0.1f)) - 1.6f;
                            accelerateBegin = 0.045f;
                            accelerateEnd = 0.675f;

                            pierceArmor = true;
                        }};
                    }}
            );
        }};

        //mech
        pMechAlpha = new MMNUnitType("pMechAlpha"){{
            health = 400;
            armor = 3;
        }};
        pMechBeta = new MMNUnitType("pMechBeta"){{
            health = 1600;
            armor = 50;
        }};
        pMechGamma = new MMNUnitType("pMechGamma"){{
            health = 4000;
            armor = 100;
        }};
        pMechBoss = new MMNUnitType("pMechBoss"){{
            health = 40000;
            armor = 1000;

            hitSize = 80f;
        }};

        //ship
        pShipAlpha = new MMNUnitType("pShipAlpha"){{
            health = 450;
            armor = 2;
        }};
        pShipBeta = new MMNUnitType("pShipBeta"){{
            health = 1700;
            armor = 30;
        }};
        pShipGamma = new MMNUnitType("pShipGamma"){{
            health = 4500;
            armor = 60;
        }};
        pShipBoss = new MMNUnitType("pShipBoss"){{
            health = 45000;
            armor = 600;

            hitSize = 80f;
        }};

        //air
        pAirMiner = new MMNUnitType("pAirMiner"){{
            controller = u -> new MinerAI();
            defaultCommand = UnitCommand.mineCommand;

            isEnemy = false;

            flying = true;
            health = 240;
            hitSize = 7f;

            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;
            engineSize = 1.8f;
            engineOffset = 5.7f;
            range = 50f;

            ammoType = new PowerAmmoType(500);

            mineTier = 4;
            mineSpeed = 6f;
        }};
        pAirRepair = new MMNUnitType("pAirRepair"){{
            health = 480;

            hitSize = 20f;

            weapons.add(
                    new RepairBeamWeapon(){{
                        repairSpeed = 8f;
                    }}
            );
        }};
        pAirReBuilder = new MMNUnitType("pAirReBuilder"){{
            defaultCommand = UnitCommand.rebuildCommand;

            health = 960;

            hitSize = 30f;

            weapons.add(
                    new BuildWeapon(){{
                        buildSpeed = 3f;
                    }}
            );
        }};
        pAirHealer = new MMNUnitType("pAirHealer"){{
            health = 1820;

            hitSize = 40f;

            weapons.add(
                    new RepairBeamWeapon(){{
                        targetBuildings = false;
                        targetUnits = true;

                        repairSpeed = 4.5f;
                    }}
            );
        }};
        pAirCarrier = new MMNUnitType("pAirCarrier"){{
            health = 3640;

            hitSize = 60f;

            payloadCapacity = 10 * 10 * tilesize * tilesize;

            abilities.add(
                    new ForceFieldAbility(120, 25 * 60, 5000, 5 * 60)
            );
        }};

        //pemu
        recon = new MMNUnitType("recon"){{
            defaultCommand = UnitCommand.assistCommand;

            buildSpeed = 3.5f;
            mineTier = 9;
            mineSpeed = 23f;

            isEnemy = false;

            health = 20000;
            armor = 120;
            targetable = false;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;
            itemCapacity = 65;

            fogRadius = 20f;
        }};
        launcher = new MMNUnitType("launcher"){{
            isEnemy = false;

            health = 20000;
            armor = 120;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;
            itemCapacity = 65;
            circleTarget = true;

            targetFlags = new BlockFlag[]{BlockFlag.launchPad, BlockFlag.storage, BlockFlag.battery, BlockFlag.generator, BlockFlag.core, null};

            weapons.add(
                    new Weapon(){{
                        x = y = 0;
                        mirror = false;
                        reload = 60f * 30f;
                        cooldownTime = 60f * 10f;
                        maxRange = 160f;

                        bullet = new BulletType(){{
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootSmokeTitan;
                            hitColor = ARColor.mmnColor2;
                            shake = 10f;
                            speed = 0f;
                            keepVelocity = false;
                            spawnUnit = ARBullets.mmnMissile;
                        }};

                        parts.add(
                                new RegionPart(AncientRaids.name("mmn-missile")){{
                                    progress = PartProgress.reload.curve(Interp.pow2In);

                                    colorTo = new Color(1f, 1f, 1f, 0f);
                                    color = Color.white;
                                    mixColorTo = Pal.accent;
                                    mixColor = new Color(1f, 1f, 1f, 0f);
                                    outline = false;
                                    under = true;

                                    layerOffset = -0.01f;
                                }}
                        );
                    }}
            );
        }};
        bommer = new MMNUnitType("bommer"){{
            isEnemy = false;

            health = 20000;
            armor = 120;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;
            itemCapacity = 65;
            circleTarget = true;

            targetFlags = new BlockFlag[]{BlockFlag.factory, BlockFlag.reactor, null};
            ammoType = new ItemAmmoType(Items.graphite);

            weapons.add(
                    new Weapon(){{
                        x = y = 0;
                        reload = 1.5f * 60f;
                        cooldownTime = 0.5f * 60f;
                        bullet = new BombBulletType(4500, 30){{
                            width = 30f;
                            height = 30f;
                            hittable = false;
                            targetable = false;

                            hitEffect = Fx.flakExplosion;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;

                            status = StatusEffects.blasted;
                            statusDuration = 60f * 6f;
                        }};
                    }}
            );
        }};

        //core
        momo = new MMNUnitType("momo"){{
            lowAltitude = true;
            isEnemy = false;

            health = 2500;
            hitSize = 10f;
            drag = 0.05f;
            speed = 3f;
            rotateSpeed = 15f;
            accel = 0.1f;

            itemCapacity = 60;

            flying = true;

            mineTier = 4;
            mineSpeed = 6f;
            buildSpeed = 6f;
            buildBeamOffset = 6f;

            alwaysUnlocked = true;

            weapons.add(
                    new Weapon(){{
                        mirror = false;
                        x = 0;
                        y = 6f;
                        reload = 17f;

                        bullet = new LaserBulletType(18){{
                            pierce = false;
                            width = 2f;
                            length = 80f;
                            lifetime = 60f * 0.2f;
                            buildingDamageMultiplier = 0.2f;
                        }};
                    }}
            );
        }};

        mona = new MMNUnitType("mona"){{
            lowAltitude = true;
            isEnemy = false;

            health = 3000;
            hitSize = 14f;
            drag = 0.05f;
            speed = 3f;
            rotateSpeed = 15f;
            accel = 0.1f;

            itemCapacity = 100;

            flying = true;

            mineTier = 6;
            mineSpeed = 18f;
            mineWalls = true;
            buildSpeed = 18f;
            buildBeamOffset = 10f;

            weapons.add(
                    new Weapon(){{
                        reload = 17f;
                        mirror = false;
                        x = 0;
                        y = 10f;

                        bullet = new LaserBulletType(24){{
                            pierce = false;
                            width = 4f;
                            length = 100f;
                            lifetime = 60f * 0.2f;
                            buildingDamageMultiplier = 0.4f;
                        }};
                    }}
            );
        }};

        momona = new MMNUnitType("momona"){{
            lowAltitude = true;
            isEnemy = false;

            health = 5000;
            hitSize = 20f;
            drag = 0.05f;
            speed = 3f;
            rotateSpeed = 15f;
            accel = 0.1f;

            itemCapacity = 180;

            flying = true;

            mineTier = 10;
            mineSpeed = 30f;
            buildSpeed = 30f;
            buildBeamOffset = 12f;

            weapons.add(
                    new Weapon(){{
                        mirror = false;
                        x = 0;
                        y = 12f;
                        reload = 17f;

                        bullet = new LaserBulletType(32){{
                            width = 6f;
                            length = 200f;
                            lifetime = 60f * 0.2f;
                            buildingDamageMultiplier = 0.8f;
                        }};
                    }}
            );
        }};

        //special
        mmnAssemblyDrone = new MMNUnitType("mmn-assembly-drone"){{
            controller = u -> new AssemblerAI();

            flying = true;
            drag = 0.06f;
            accel = 0.11f;
            speed = 1.3f;
            health = 1000;
            armor = 15000;
            engineSize = 2f;
            engineOffset = 6.5f;
            payloadCapacity = 0f;
            targetable = false;
            bounded = false;

            outlineColor = Pal.darkOutline;
            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            envEnabled = Env.any;
            envDisabled = Env.none;
        }};
    }
}
