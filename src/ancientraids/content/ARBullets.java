package ancientraids.content;

import ancientraids.AncientRaids;
import ancientraids.expand.bullets.AccelSpeedBulletType;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Rand;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.EntityMapping;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;

import static arc.math.Angles.randLenVectors;
import static arc.scene.actions.Actions.color;

public class ARBullets {
    public static String SUPER_STRIKE, CLUSTER_STRIKE, EYE_CLUSTER;

    public static MissileUnitType ancientMissile, ancientRaidMissile, clusterMissile;

    public static BulletType
            railgunBullet1, railcannonBullet1, railcannonBullet2, eye
    ;

    static {
        EntityMapping.nameMap.put(AncientRaids.name("ancient-missile"), EntityMapping.idMap[39]);
        EntityMapping.nameMap.put(AncientRaids.name("ancient-raid-missile"), EntityMapping.idMap[39]);
    }

    public static void load() {
        SUPER_STRIKE = AncientRaids.name("super-strike");
        CLUSTER_STRIKE = AncientRaids.name("cluster-strike");
        EYE_CLUSTER = AncientRaids.name("eye-cluster");

        ancientMissile = new MissileUnitType("ancient-missile"){{
            speed = 6f;
            maxRange = 6f;
            lifetime = 60f * 5.5f;
            outlineColor = Pal.darkOutline;
            engineColor = trailColor = ARColor.ancientYellow;
            engineLayer = Layer.effect;
            engineSize = 3.1f;
            engineOffset = 10f;
            rotateSpeed = 6f;
            trailLength = 18;
            missileAccelTime = 50f;
            lowAltitude = true;
            loopSound = Sounds.missileTrail;
            loopSoundVolume = 0.6f;
            deathSound = Sounds.largeExplosion;
            targetAir = true;

            fogRadius = 6f;

            health = 210;

            weapons.add(new Weapon(){{
                shootCone = 360f;
                mirror = false;
                reload = 1f;
                deathExplosionEffect = Fx.massiveExplosion;
                shootOnDeath = true;
                shake = 10f;
                bullet = new ExplosionBulletType(640f, 65f){{
                    hitColor = ARColor.ancientYellow;
                    shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                        lifetime = 10f;
                        strokeFrom = 4f;
                        sizeTo = 130f;
                    }});

                    collidesAir = true;
                    buildingDamageMultiplier = 0.3f;

                    ammoMultiplier = 1f;
                    fragLifeMin = 0.1f;
                    fragBullets = 7;
                    fragBullet = new ArtilleryBulletType(3.4f, 32){{
                        buildingDamageMultiplier = 0.3f;
                        drag = 0.02f;
                        hitEffect = Fx.massiveExplosion;
                        despawnEffect = Fx.scatheSlash;
                        knockback = 0.8f;
                        lifetime = 23f;
                        width = height = 18f;
                        collidesTiles = false;
                        splashDamageRadius = 40f;
                        splashDamage = 80f;
                        backColor = trailColor = hitColor = ARColor.ancientYellow;
                        frontColor = Color.white;
                        smokeEffect = Fx.shootBigSmoke2;
                        despawnShake = 7f;
                        lightRadius = 30f;
                        lightColor = ARColor.ancientYellow;
                        lightOpacity = 0.5f;

                        trailLength = 20;
                        trailWidth = 3.5f;
                        trailEffect = Fx.none;
                    }};
                }};
            }});

            abilities.add(new MoveEffectAbility(){{
                effect = Fx.missileTrailSmoke;
                rotation = 180f;
                y = -9f;
                color = Color.grays(0.6f).lerp(ARColor.ancientYellow, 0.5f).a(0.4f);
                interval = 7f;
            }});
        }};

        railgunBullet1 = new PointBulletType(){{
            damage = 1000;
            pierceArmor = true;
            lifetime = 180f;
            buildingDamageMultiplier = 5f;

            chargeEffect = ARFx.railChargeShoot(ARColor.ancientYellow, 800f, 18, 240f, 25);
            hitEffect = ARFx.instHit;
            trailEffect = ARFx.instTrail;
            despawnEffect = Fx.instBomb;
            smokeEffect = new Effect(180f, 300f, b -> {
                float intensity = 2f;

                Rand rand = Fx.rand;

                color(b.color, 0.7f);
                for(int i = 0; i < 4; i++){
                    rand.setSeed(b.id*2 + i);
                    float lenScl = rand.random(0.5f, 1f);
                    int fi = i;
                    b.scaled(b.lifetime * lenScl, e -> {
                        randLenVectors(e.id + fi - 1, e.fin(Interp.pow4Out), (int)(2 * intensity), 35f * intensity, e.rotation, 20, (x, y, in, out) -> {
                            float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                            float rad = fout * ((2f + intensity) * 1.75f);

                            Fill.circle(e.x + x, e.y + y, rad);
                            Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);
                        });
                    });
                }
            });
        }};

        railcannonBullet1 = new AccelSpeedBulletType(60f, 1450, SUPER_STRIKE){{
            width = 12f;
            height = 20f;
            damage = 1500;
            pierceArmor = pierce = pierceBuilding = true;
            lifetime = 13f;
            buildingDamageMultiplier = 5f;

            backColor = frontColor = lightColor = ARColor.ancientYellow;

            chargeEffect = ARFx.railChargeShoot(ARColor.ancientYellow, 800f, 18, 130f, 30f);
            hitEffect = ARFx.instHit;
            trailEffect = ARFx.instTrail;
            despawnEffect = ARFx.instBomb;
        }};

        railcannonBullet2 = new BasicBulletType(60f, 1450, SUPER_STRIKE){{
            width = 12f;
            height = 20f;
            pierceArmor = pierce = pierceBuilding = true;
            splashDamage = 400;
            splashDamageRadius = 20f;
            lifetime = 13f;
            buildingDamageMultiplier = 5f;

            backColor = frontColor = lightColor = ARColor.ancientYellow;

            chargeEffect = ARFx.railChargeShoot(ARColor.ancientYellow, 800f, 18, 130f, 30f);
            hitEffect = ARFx.instHit;
            trailEffect = ARFx.instTrail;
            despawnEffect = ARFx.instBomb;
        }};

        eye = new AccelSpeedBulletType(2f, 129000, SUPER_STRIKE){{
            velocityBegin = 1f;
            velocityIncrease = 20f;
            accelInterp = a -> 2 * (0.9f * a + 0.31f) + 1f / (5f * (a + 0.1f)) - 1.6f;
            accelerateBegin = 0.045f;
            accelerateEnd = 0.675f;

            pierceCap = 3;
            splashDamage = damage / 4;
            splashDamageRadius = 160f;

            trailLength = 30;
            trailWidth = 3f;

            lifetime = 160f;

            trailEffect = ARFx.eyeTrail;

            pierceArmor = true;
            trailRotation = false;
            trailChance = 0.35f;
            trailParam = 4f;

            homingRange = 640F;
            homingPower = 0.075f;
            homingDelay = 5;

            lightning = 3;
            lightningLengthRand = 10;
            lightningLength = 5;
            lightningDamage = damage / 4;

            shootEffect = smokeEffect = Fx.none;
            hitEffect = despawnEffect = ARFx.hitSparkLarge;

            despawnHit = false;
        }};

        ancientRaidMissile = new MissileUnitType("ancient-raid-missile"){{
            health = 1500;
            hittable = targetable = false;

            speed = 20f;
            maxRange = 6f;
            lifetime = 60f * 5f;
            outlineColor = Pal.darkOutline;
            engineColor = trailColor = ARColor.ancientYellow;
            engineLayer = Layer.effect;
            engineSize = 3.1f;
            engineOffset = 11f;
            rotateSpeed = 15f;
            trailLength = 18;
            missileAccelTime = 45f;
            lowAltitude = true;
            loopSound = Sounds.missileTrail;
            loopSoundVolume = 0.6f;
            deathSound = Sounds.largeExplosion;
            targetAir = true;

            fogRadius = 6f;

            clipSize = 620;

            parts.add(
                    //circle
                    new ShapePart(){{
                        layer = Layer.effect;
                        circle = true;
                        y = -0.25f;
                        radius = 1.5f;
                        color = ARColor.ancientYellow;
                        colorTo = Color.white;
                        progress = PartProgress.life.curve(Interp.pow5In);
                    }}

                    //shape
            );

            weapons.add(new Weapon(){{
                shootCone = 360f;
                mirror = false;
                reload = 1f;
                deathExplosionEffect = Fx.massiveExplosion;
                shootOnDeath = true;
                shake = 10f;
                bullet = new ExplosionBulletType(125000f, 160f){{
                    hitColor = ARColor.ancientYellow;
                    shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                        lifetime = 10f;
                        strokeFrom = 4f;
                        sizeTo = 130f;
                    }});

                    collidesAir = true;
                    buildingDamageMultiplier = 1f;

                    ammoMultiplier = 1f;
                    fragLifeMin = 0.1f;
                    fragBullets = 7;
                    fragBullet = new ArtilleryBulletType(3.4f, 32){{
                        buildingDamageMultiplier = 0.3f;
                        drag = 0.02f;
                        hitEffect = Fx.massiveExplosion;
                        despawnEffect = Fx.scatheSlash;
                        knockback = 0.8f;
                        lifetime = 23f;
                        width = height = 18f;
                        collidesTiles = false;
                        splashDamageRadius = 40f;
                        splashDamage = 80f;
                        backColor = trailColor = hitColor = ARColor.ancientYellow;
                        frontColor = Color.white;
                        smokeEffect = Fx.shootBigSmoke2;
                        despawnShake = 7f;
                        lightRadius = 30f;
                        lightColor = ARColor.ancientYellow;
                        lightOpacity = 0.5f;

                        trailLength = 20;
                        trailWidth = 3.5f;
                        trailEffect = Fx.none;
                    }};
                }};
            }});
            abilities.add(new MoveEffectAbility(){{
                effect = Fx.missileTrailSmoke;
                rotation = 180f;
                y = -15f;
                color = Color.grays(0.6f).lerp(ARColor.ancientYellow, 0.5f).a(0.4f);
                interval = 7f;
            }});
        }};
    }
}
