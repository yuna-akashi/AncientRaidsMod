package ancientraids.content;

import mindustry.type.SectorPreset;

public class ARSectorPresets {
    //atramace
    public static SectorPreset
            unknownRuin/*, secondSector, thirdSector, fourthSector, fifthSector,
    sixthSector, seventhSector, eighthSector, ninthSector*/, deepEmpire;


    //atramoon
    public static SectorPreset landingPoint, atramaceEmpire;

    public static void load() {
        //region atramace
        unknownRuin = new SectorPreset("unknown-ruin", ARPlanets.atramace, 0){{
            captureWave = 30;
            difficulty = 3;

            rules = r -> r.winWave = captureWave;
        }};

        deepEmpire = new SectorPreset("deep-empire", ARPlanets.atramace, 200){{
            captureWave = 75;
            difficulty = 18;

            rules = r -> r.winWave = captureWave;
        }};
        //endregion
        //region atramoon
        landingPoint = new SectorPreset("landing-point", ARPlanets.atramoon, 0){{
            captureWave = 25;
            difficulty = 4;

            rules = r -> r.winWave = captureWave;
        }};

        atramaceEmpire = new SectorPreset("core-of-atramace", ARPlanets.atramoon, 180){{
            captureWave = 55;
            difficulty = 22;

            rules = r -> r.winWave = captureWave;
        }};
        //endregion
    }
}
