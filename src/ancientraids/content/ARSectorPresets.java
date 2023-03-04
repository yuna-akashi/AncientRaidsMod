package ancientraids.content;

import mindustry.type.SectorPreset;

public class ARSectorPresets {
    public static SectorPreset
    beginnings/*, secondSector, thirdSector, fourthSector, fifthSector,
    sixthSector, seventhSector, eighthSector, ninthSector*/, deepAncient;

    public static SectorPreset landingPoint, coreOfAtramace;

    public static void load() {
        //region atrantis
        beginnings = new SectorPreset("beginnings", ARPlanets.atrantis, 0){{
            captureWave = 30;
            difficulty = 3;

            rules = r -> r.winWave = captureWave;
        }};

        deepAncient = new SectorPreset("deep-ancient", ARPlanets.atrantis, 200){{
            captureWave = 75;
            difficulty = 18;

            rules = r -> r.winWave = captureWave;
        }};
        //endregion
        //region mace
        landingPoint = new SectorPreset("landing-point", ARPlanets.mace, 0){{
            captureWave = 25;
            difficulty = 4;

            rules = r -> r.winWave = captureWave;
        }};

        coreOfAtramace = new SectorPreset("core-of-atramace", ARPlanets.mace, 180){{
            captureWave = 55;
            difficulty = 22;

            rules = r -> r.winWave = captureWave;
        }};
        //endregion
    }
}
