package ancientraids.content;

import ancientraids.AncientRaids;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.gen.EntityMapping;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class ARUnits {
    public static Weapon
            ancientLaser
    ;
    public static UnitType
            ancientDagger
    ;

    static{
        EntityMapping.nameMap.put(AncientRaids.name("ancient-dagger"), EntityMapping.idMap[4]);
    }

    private static void loadWeapon(){
        ancientLaser = new Weapon(AncientRaids.name("ancient-laser")){{
            mirror = true;
            shootY = 3f;

            recoil = 0f;
            reload = 20f;

            bullet = new LaserBulletType(){{
                damage = 10;
                maxRange = 144f;
                width = 6f;
                length = maxRange;
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
                    ancientLaser
            );
        }};
    }
}
