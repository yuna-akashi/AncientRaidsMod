package ancientraids.content;

import ancientraids.AncientRaids;
import ancientraids.content.ARUnits.ARUnitType;
import ancientraids.expand.bullets.AccelSpeedBulletType;
import ancientraids.expand.bullets.RailcannonBulletType;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Rand;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.PointBulletType;
import mindustry.graphics.Drawf;

import static arc.math.Angles.randLenVectors;
import static arc.scene.actions.Actions.color;

public class ARBullets {
    public static String SUPER_STRIKE, CLUSTER_STRIKE, EYE_CLUSTER;

    public static ARUnitType ancientRaidMissile, clusterMissile;

    public static BulletType
            railgunBullet1, railcannonBullet1, railcannonBullet2
    ;

    public static void load() {
        SUPER_STRIKE = AncientRaids.name("super-strike");
        CLUSTER_STRIKE = AncientRaids.name("cluster-strike");
        EYE_CLUSTER = AncientRaids.name("eye-cluster");

        railgunBullet1 = new PointBulletType(){{
            damage = 1000;
            pierceArmor = true;
            lifetime = 180f;
            buildingDamageMultiplier = 5f;

            chargeEffect = ARFx.railChargeShoot(Color.valueOf("919100"), 800f, 18, 240f, 25);
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

            backColor = frontColor = lightColor = Color.valueOf("919100");

            chargeEffect = ARFx.railChargeShoot(Color.valueOf("919100"), 800f, 18, 130f, 30f);
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

            backColor = frontColor = lightColor = Color.valueOf("919100");

            chargeEffect = ARFx.railChargeShoot(Color.valueOf("919100"), 800f, 18, 130f, 30f);
            hitEffect = ARFx.instHit;
            trailEffect = ARFx.instTrail;
            despawnEffect = ARFx.instBomb;
        }};
    }
}
