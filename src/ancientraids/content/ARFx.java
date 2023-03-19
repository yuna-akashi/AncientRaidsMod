package ancientraids.content;

import ancientraids.AncientRaids;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;
import static mindustry.Vars.tilesize;

public class ARFx {
    public static final Effect
            railCharge = new Effect(80f, 100f, e -> {
                color(Color.valueOf("919100"));
                stroke(e.fin() * 2f);
                Lines.circle(e.x, e.y, 4f + e.fout() * 100f);

                Fill.circle(e.x, e.y, e.fin() * 20);

                randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
                    Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Color.valueOf("919100"), 0.7f);
                });

                color();

                Fill.circle(e.x, e.y, e.fin() * 10);
                Drawf.light(e.x, e.y, e.fin() * 20f, Color.valueOf("919100"), 0.7f);
            }).followParent(true).rotWithParent(true),

            railChargeSmall = new Effect(40f, 100f, e -> {
                color(Color.valueOf("919100"));
                stroke(e.fin() * 2f);
                Lines.circle(e.x, e.y, e.fout() * 50f);
            }).followParent(true).rotWithParent(true),

            instShoot = new Effect(24f, e -> {
                e.scaled(10f, b -> {
                    color(Color.white, Color.valueOf("919100"), b.fin());
                    stroke(b.fout() * 3f + 0.2f);
                    Lines.circle(b.x, b.y, b.fin() * 50f);
                });

                color(Color.valueOf("919100"));

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 85f, e.rotation + 90f * i);
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 50f, e.rotation + 20f * i);
                }

                Drawf.light(e.x, e.y, 180f, Color.valueOf("919100"), 0.9f * e.fout());
            }),

            instTrail = new Effect(30, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? Color.valueOf("919100") : Color.yellow);

                    float m = i == 0 ? 1f : 0.5f;

                    float rot = e.rotation + 180f;
                    float w = 15f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, Color.valueOf("919100"), 0.6f * e.fout());
            }),

            instHit = new Effect(20f, 200f, e -> {
                color(Color.valueOf("919100"));

                for(int i = 0; i < 2; i++){
                    color(i == 0 ? Color.valueOf("919100") : Color.yellow);

                    float m = i == 0 ? 1f : 0.5f;

                    for(int j = 0; j < 5; j++){
                        float rot = e.rotation + Mathf.randomSeedRange(e.id + j, 50f);
                        float w = 23f * e.fout() * m;
                        Drawf.tri(e.x, e.y, w, (80f + Mathf.randomSeedRange(e.id + j, 40f)) * m, rot);
                        Drawf.tri(e.x, e.y, w, 20f * m, rot + 180f);
                    }
                }

                e.scaled(10f, c -> {
                    color(Color.yellow);
                    stroke(c.fout() * 2f + 0.2f);
                    Lines.circle(e.x, e.y, c.fin() * 30f);
                });

                e.scaled(12f, c -> {
                    color(Color.valueOf("919100"));
                    randLenVectors(e.id, 25, 5f + e.fin() * 80f, e.rotation, 60f, (x, y) -> {
                        Fill.square(e.x + x, e.y + y, c.fout() * 3f, 45f);
                    });
                });
            }),

            instBomb = new Effect(15f, 100f, e -> {
                color(Color.valueOf("919100"));
                stroke(e.fout() * 4f);
                Lines.circle(e.x, e.y, 4f + e.finpow() * 20f);

                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 6f, 80f * e.fout(), i*90 + 45);
                }

                color();
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i*90 + 45);
                }

                Drawf.light(e.x, e.y, 150f, Color.valueOf("919100"), 0.9f * e.fout());
            })
    ;

    public static Effect railChargeShoot(Color color, float length, float width, float lifetime, float spacing){
        return new Effect(lifetime, length * 2f, e -> {
            TextureRegion arrowRegion = Core.atlas.find(AncientRaids.name("ancient-railcannon-arrow"));

            Draw.color(color);

            float railF = Mathf.curve(e.fin(Interp.pow2Out), 0f, 0.25f) * Mathf.curve(e.fout(Interp.pow4Out), 0f, 0.3f) * e.fin();

            for(int i = 0; i <= length / spacing; i++){
                Tmp.v1.trns(e.rotation, i * spacing);
                float f = Interp.pow3Out.apply(Mathf.clamp((e.fin() * length - i * spacing) / spacing)) * (0.6f + railF * 0.4f);
                Draw.rect(arrowRegion, e.x + Tmp.v1.x, e.y + Tmp.v1.y, arrowRegion.width * Draw.scl * f, arrowRegion.height * Draw.scl * f, e.rotation - 90);
            }

            Tmp.v1.trns(e.rotation, 0f, (2 - railF) * tilesize * 1.4f);

            Lines.stroke(railF * 2f);
            for(int i : Mathf.signs){
                lineAngle(e.x + Tmp.v1.x * i, e.y + Tmp.v1.y * i, e.rotation, length * (0.75f + railF / 4f) * Mathf.curve(e.fout(Interp.pow5Out), 0f, 0.1f));
            }
        }).followParent(true);
    }

    public static Effect hitSparkLarge, eyeTrail;

    public static void load(){
        hitSparkLarge = new Effect(40, e -> {
            color(e.color, Color.white, e.fout() * 0.3f);
            stroke(e.fout() * 1.6f);

            rand.setSeed(e.id);
            randLenVectors(e.id, 18, e.finpow() * 27f, (x, y) -> {
                float ang = Mathf.angle(x, y);
                lineAngle(e.x + x, e.y + y, ang, e.fout() * rand.random(4, 8) + 2f);
            });
        });

        eyeTrail = new Effect(50.0F, e -> {
            Draw.color(e.color, Color.white, e.fout() * 0.35f);
            randLenVectors(e.id, 2, tilesize * e.fin(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.rotation * e.fout()));
        });
    }
}
