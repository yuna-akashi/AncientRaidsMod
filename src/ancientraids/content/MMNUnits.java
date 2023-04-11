package ancientraids.content;

import ancientraids.AncientRaids;
import arc.math.geom.Rect;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AssemblerAI;
import mindustry.gen.EntityMapping;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.unit.TankUnitType;
import mindustry.world.meta.Env;

public class MMNUnits {
    //Player's units. Units made by adapting ancient units.
    public static UnitType
            //generic
            pUnitT1,
            //tank
            pTankTAlpha, pTankTBeta, pTankTGamma, pTankBoss,
            //mech
            pMechTAlpha, pMechTBeta, pMechTGamma, pMechBoss,
            //ship
            pShipTAlpha, pShipTBeta, pShipTGamma, pShipBoss,
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

        //tank
        pTankBoss = new MMNTankUnitType("pTankBoss"){{
            health = 50000;
            armor = 1500;

            hitSize = 20f;
            treadRects = new Rect[]{new Rect(16 - 60, 48 - 70f, 30, 75), new Rect()};
        }};

        //mech
        pMechTAlpha = new MMNUnitType("alpha-mech"){{
            health = 500;
            armor = 4;
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

        //core
        momo = new MMNUnitType("momo"){{
            health = 2500;
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
