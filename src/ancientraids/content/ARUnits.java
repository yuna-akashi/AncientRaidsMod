package ancientraids.content;

import ancientraids.AncientRaids;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.gen.EntityMapping;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class ARUnits {
    public static Weapon
            ancientLargeLaserWeapon
    ;
    public static UnitType
            ancientDagger
    ;

    static{
        EntityMapping.nameMap.put(AncientRaids.name("ancient-dagger"), EntityMapping.idMap[4]);
    }

    private static void loadWeapon(){
        ancientLargeLaserWeapon = new Weapon(AncientRaids.name("ancient-large-laser-weapon")){{
            reload = 13f;
            x = 4f;
            y = 2f;
            top = false;
            ejectEffect = Fx.casing1;

            bullet = new LaserBulletType(){{
                damage = 10;
                width = 7f;
                lifetime = 30f;
                maxRange = 150f;
                length = maxRange;

                status = StatusEffects.shocked;
                statusDuration = 0.5f * 60f;
            }};

        }};
    }

    public static void load(){
        loadWeapon();

        ancientDagger = new UnitType("ancient-dagger"){{
            speed = 0.5f;
            hitSize = 8f;
            health = 150;
            weapons.add(
                    ancientLargeLaserWeapon
            );
        }};
    }
}
