package ancientraids.content;

import ancientraids.AncientRaids;
import mindustry.gen.EntityMapping;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.Env;

public class MMNUnits {
    //Player's units. Units made by adapting ancient units.
    public static UnitType
            //leg
            pLegT1, pLegT2, pLegT3, pLegT4, pLegT5, pLegT6, pLegBoss,
            //tank
            pTankT1, pTankT2, pTankT3, pTankT4, pTankT5, pTankT6, pTankBoss,
            //mech
            pMechT1, pMechT2, pMechT3, pMechT4, pMechT5, pMechT6, pMechBoss,
            //ship
            pShipT1, pShipT2, pShipT3, pShipT4, pShipT5, pShipT6, pShipBoss,
            //support air
            pAirT1, pAirT2, pAirT3, pAirT4, pAirT5
    ;
    //
    //core units
    public static UnitType momo, mona, momona;
    //
    //special units
    public static UnitType mmnCargoDrone, mmnPayloadCargoDrone, mmnAssemblyDrone;
    static{
        EntityMapping.nameMap.put(AncientRaids.name("momona"), EntityMapping.idMap[3]);
    }

    public static class MMNUnitType extends UnitType {
        public MMNUnitType(String name){
            super(name);
            envDisabled = Env.none;

            ammoType = new ItemAmmoType(ARItems.iron);
        }
    }
    public static void load() {

    }
}
