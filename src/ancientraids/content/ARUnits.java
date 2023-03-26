package ancientraids.content;

import ancientraids.AncientRaids;
import arc.func.Cons;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AssemblerAI;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.CargoAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.RepairFieldAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.part.HoverPart;
import mindustry.gen.EntityMapping;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.weapons.BuildWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;

public class ARUnits {
    public static Weapon
            ancientLargeLaserWeapon,
            pointDefenceWeapon, autoTurret,
            siegeWeapon
    ;

    public static Weapon laserCannon;

    public static Weapon basicBossWeapon;

    //enemy unit. Player can't product these units.
    public static UnitType
            ancientDagger, legT2, legT3, legT4, legT5,
            mechT1, mechT2, mechT3, mechT4, mechT5,
            airT1, airT2, airT3, airT4, airT5
    ;
    //
    //Normal unit. Enemy and player can product these units.
    public static UnitType

            //leg
            carnage,

            //small unit
            spore
    ;
    //
    //Enemy's boss unit, but player can application it while after campaign.
    public static UnitType
            //leg T6 T7
            legT6, ancientFortress,

            //mech T6 T7
            mechT6, mechT7,

            //air T6 T7
            airT6, mechanicalDragon,

            ancientEye
    ;
    //
    //special unit. Enemy and player can use, but can't product at factory.
    public static UnitType
            //greece unit
            delta, epsilon, zehta, eta, theta/*, iota, kappa, lambda, mu, nu, xi, omicron, pi, rho, sigma, tau, upsilon, phi, chi, psi*/, omega,

            //special
            ancientAssemblyDrone, ancientCargoDrone, ancientPayloadCargoDrone
    ;
    //
    static{
        EntityMapping.nameMap.put(AncientRaids.name("ancient-dagger"), EntityMapping.idMap[4]);

        EntityMapping.nameMap.put(AncientRaids.name("carnage"), EntityMapping.idMap[4]);

        EntityMapping.nameMap.put(AncientRaids.name("spore"), EntityMapping.idMap[3]);

        EntityMapping.nameMap.put(AncientRaids.name("ancient-fortress"), EntityMapping.idMap[4]);
        EntityMapping.nameMap.put(AncientRaids.name("mechanical-dragon"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("eye"), EntityMapping.idMap[5]);

        //greece
        EntityMapping.nameMap.put(AncientRaids.name("delta"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("epsilon"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("zehta"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("eta"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("theta"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(AncientRaids.name("omega"), EntityMapping.idMap[5]);

        //special
        EntityMapping.nameMap.put(AncientRaids.name("ancient-assembly-drone"), EntityMapping.idMap[36]);
        EntityMapping.nameMap.put(AncientRaids.name("ancient-cargo-drone"), EntityMapping.idMap[36]);
        EntityMapping.nameMap.put(AncientRaids.name("ancient-payload-cargo-drone"), EntityMapping.idMap[5]);
    }

    public static class ARUnitType extends UnitType{
        public ARUnitType(String name){
            super(name);
            envDisabled = Env.space;

            ammoType = new ItemAmmoType(ARItems.aMetal);
        }
    }

    public static Weapon copyAnd(Weapon weapon, Cons<Weapon> modifier){
        Weapon n = weapon.copy();
        modifier.get(n);
        return n;
    }

    public static Weapon copyAndMove(Weapon weapon, float x, float y){
        Weapon n = weapon.copy();
        n.x = x;
        n.y = y;
        return n;
    }

    public static Weapon copyAndMoveAnd(Weapon weapon, float x, float y, Cons<Weapon> modifier){
        Weapon n = weapon.copy();
        n.x = x;
        n.y = y;
        modifier.get(n);
        return n;
    }

    private static void loadWeapon(){
        ancientLargeLaserWeapon = new Weapon(AncientRaids.name("ancient-large-laser-weapon")){{
            reload = 13f;
            x = 4f;
            y = 2f;
            top = false;
            ejectEffect = Fx.casing1;
            shootSound = Sounds.pew;

            bullet = new LaserBulletType(){{
                damage = 10;
                width = 7f;
                lifetime = 30f;
                maxRange = 150f;
                length = maxRange;

                status = StatusEffects.shocked;
                statusDuration = 0.3f * 60f;
            }};

        }};

        laserCannon = new Weapon("laser-cannon"){{
            bullet = new LaserBulletType(){{
                damage = 200;
            }};
        }};

        //boss weapon
        loadBossWeapon();
    }

    private static void loadBossWeapon(){
        basicBossWeapon = new Weapon("basic-boss-weapon"){{
            shootY = 10f;

            reload = 450;
            recoil = 5f;
            inaccuracy = 0f;

            bullet = new BasicBulletType(30f, 5000){{
                width = 10f;
                height = 14f;
                lifetime = 300f;
                pierceArmor = true;
            }};
        }};
    }

    public static void load(){
        var coreFleeRange = 600;

        loadWeapon();

        //region leg
        ancientDagger = new UnitType("ancient-dagger"){{
            outlineColor = Pal.darkOutline;
            speed = 0.5f;
            hitSize = 8f;
            health = 150;
            weapons.add(
                    ancientLargeLaserWeapon
            );
        }};

        //leg
        carnage = new ARUnitType("carnage"){{
            health = 2500;
        }};

        //small unit
        spore = new ARUnitType("spore"){{
            health = 500;
            armor = 5;

            outlineColor = Pal.darkOutline;

            flying = true;

            speed = 10f;
            drag = 0.01f;
            accel = 0.05f;

            hitSize = 1f;
            engineSize = engineOffset = 0;

            weapons.addAll(
                    new Weapon(AncientRaids.name("spore-seed")){{
                        reload = 100f;
                        recoil = 1f;

                        bullet = new BasicBulletType(80, 10){{
                            lifetime = 20f;
                        }};
                    }}
            );
        }};

        ancientFortress = new ARUnitType("ancient-fortress"){{
            health = 100000;
            armor = 70f;
            hitSize = 15f;

            speed = 0.1f;
            accel = 0.5f;
            drag = 0.2f;

            abilities.addAll(
                    new RepairFieldAbility(10, 0.5f * 60f, 120)
            );
        }};

        //endregion
        //region mech

        //endregion
        //region air

        //endregion
        //region boss

        mechanicalDragon = new ARUnitType("mechanical-dragon"){{
            health = 125000;

            outlineColor = Pal.darkOutline;
        }};

        ancientEye = new ARUnitType("eye"){{
            health = 15248100;

            outlineColor = Pal.darkOutline;

            weapons.addAll(
                    basicBossWeapon,
                    basicBossWeapon
            );
        }};

        //endregion
        //region greece unit


        ///core unit

        delta = new ARUnitType("delta"){{
            localizedName = "DELTA";

            defaultCommand = UnitCommand.moveCommand;

            isEnemy = false;
            envDisabled = 0;
            outlineColor = Pal.darkOutline;

            mineWalls = true;
            mineItems = Seq.with(ARItems.aScrap);
            mineHardnessScaling = false;
            mineSpeed = 7.25f;
            mineTier = 10;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 120;
            health = 1000f;
            armor = 20f;
            hitSize = 12f;

            fogRadius = 0f;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );
        }};
        epsilon = new ARUnitType("epsilon"){{
            localizedName = "EPSILON";

            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            outlineColor = Pal.darkOutline;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 9;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 120;
            health = 1000f;
            armor = 20f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f/4f;
                y = 19f/4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};

        zehta = new ARUnitType("zehta"){{
            localizedName = "ZEHTA";

            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            outlineColor = Pal.darkOutline;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 9;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 150;
            health = 1500f;
            armor = 25f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f/4f;
                y = 19f/4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};

        eta = new ARUnitType("eta"){{
            localizedName = "ETA";

            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            outlineColor = Pal.darkOutline;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 9;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 180;
            health = 2000f;
            armor = 35f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f/4f;
                y = 19f/4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};

        theta = new ARUnitType("theta"){{
            localizedName = "THETA";

            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            outlineColor = Pal.darkOutline;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 9;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 210;
            health = 2500f;
            armor = 50f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0;
                y = 8f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};

        ///default
        omega = new ARUnitType("omega"){{
            localizedName = "OMEGA";

            coreUnitDock = true;
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            outlineColor = Pal.darkOutline;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 9f;
            mineTier = 9;
            buildSpeed = 1.5f;
            drag = 0.08f;
            speed = 7.5f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 110;
            health = 2500f;
            armor = 50f;
            hitSize = 12f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(35 / 4f, -13 / 4f, 2.7f, 315f),
                    new UnitEngine(28 / 4f, -35 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f/4f;
                y = 19f/4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 3.6f / 2f;
                fractionRepairSpeed = 0.03f;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 65f;
                }};
            }});
        }};

        //endregion
        //region special

        ancientAssemblyDrone = new ARUnitType("ancient-assembly-drone"){{
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

        ancientCargoDrone = new ARUnitType("ancient-cargo-drone"){{
            controller = u -> new CargoAI();
            isEnemy = false;
            allowedInPayloads = false;
            logicControllable = false;
            playerControllable = false;
            envDisabled = 0;
            payloadCapacity = 0f;

            outlineColor = Pal.darkOutline;

            lowAltitude = false;
            flying = true;
            drag = 0.06f;
            speed = 7f;
            rotateSpeed = 9f;
            accel = 0.1f;
            itemCapacity = 150;
            health = 750f;
            hitSize = 11f;
            engineSize = 2.3f;
            engineOffset = 6.5f;
            hidden = true;

            setEnginesMirror(
                    new UnitEngine(24 / 4f, -24 / 4f, 2.3f, 315f)
            );
        }};

        ancientPayloadCargoDrone = new ARUnitType("ancient-payload-cargo-drone"){{
            controller = u -> new BuilderAI(true, coreFleeRange);
            isEnemy = false;
            envDisabled = 0;

            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 12f;
            mineTier = 9;
            buildSpeed = 2.5f;
            drag = 0.08f;
            speed = 40f;
            rotateSpeed = 8f;
            accel = 0.005f;
            itemCapacity = 200;
            health = 5000f;
            armor = 120f;
            hitSize = 15f;
            payloadCapacity = 7 * 7 * tilesize * tilesize;
            pickupUnits = true;
            vulnerableWithPayloads = true;

            outlineColor = Pal.darkOutline;

            targetable = false;
            hittable = false;

            engineOffset = 7.2f;
            engineSize = 3.1f;

            forceMultiTarget = true;

            abilities.add(new MoveEffectAbility(0f, -7f, Pal.sapBulletBack, Fx.missileTrailShort, 4f){{
                teamColor = true;
            }});

            parts.add(
                    new HoverPart(){{
                        x = 24f;
                        y = 24f;
                        mirror = true;
                        radius = 12f;
                        phase = 90f;
                        stroke = 4f;
                        layerOffset = -0.001f;
                        color = Color.valueOf("84f491");
                    }},

                    new HoverPart(){{
                        x = 24f;
                        y = -24f;
                        mirror = true;
                        radius = 12f;
                        phase = 90f;
                        stroke = 4f;
                        layerOffset = -0.001f;
                        color = Color.valueOf("84f491");
                    }}
            );

            weapons.add(
                    new RepairBeamWeapon(){{
                        widthSinMag = 0.11f;
                        reload = 20f;
                        x = 0f;
                        y = 10f;
                        rotate = false;
                        shootY = 0f;
                        beamWidth = 0.7f;
                        aimDst = 0f;
                        shootCone = 15f;
                        mirror = false;

                        repairSpeed = 9f;
                        fractionRepairSpeed = 0.4f;

                        targetUnits = false;
                        targetBuildings = true;
                        autoTarget = false;
                        controllable = true;
                        laserColor = Pal.accent;
                        healColor = Pal.accent;

                        bullet = new BulletType(){{
                            maxRange = 80f;
                        }};
                    }},
                    new RepairBeamWeapon(){{
                        widthSinMag = 0.11f;
                        reload = 20f;
                        x = -6f;
                        y = -7.5f;
                        rotate = false;
                        shootY = 0f;
                        beamWidth = 0.7f;
                        aimDst = 0f;
                        shootCone = 15f;
                        mirror = false;

                        repairSpeed = 4.5f;
                        fractionRepairSpeed = 0.25f;

                        targetUnits = true;
                        targetBuildings = false;
                        autoTarget = true;
                        controllable = false;
                        laserColor = Pal.accent;
                        healColor = Pal.accent;

                        bullet = new BulletType(){{
                            maxRange = 80f;
                        }};
                    }},
                    new RepairBeamWeapon(){{
                        widthSinMag = 0.11f;
                        reload = 20f;
                        x = 6f;
                        y = -7.5f;
                        rotate = false;
                        shootY = 0f;
                        beamWidth = 0.7f;
                        aimDst = 0f;
                        shootCone = 15f;
                        mirror = false;

                        repairSpeed = 4.5f;
                        fractionRepairSpeed = 0.25f;

                        targetUnits = true;
                        targetBuildings = false;
                        autoTarget = true;
                        controllable = false;
                        laserColor = Pal.accent;
                        healColor = Pal.accent;

                        bullet = new BulletType(){{
                            maxRange = 100f;
                        }};
                    }}
            );

            drawBuildBeam = false;

            weapons.add(new BuildWeapon("build-weapon"){{
                rotate = true;
                rotateSpeed = 7f;
                x = 14/4f;
                y = 15/4f;
                layerOffset = -0.001f;
                shootY = 6f;
            }});
        }};

        //endregion
    }
}
