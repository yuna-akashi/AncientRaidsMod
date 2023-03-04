package ancientraids.content;

import ancientraids.expand.maps.ARPlanetGenerator;
import arc.graphics.Color;
import arc.util.Time;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Rules;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.type.Planet;

public class ARPlanets {
    public static Planet atrantis, mace;

    public static void load() {
        atrantis = new Planet("atrantis", Planets.sun, 2, 3) {{
            alwaysUnlocked = true;
            visible = true;

            generator = new ARPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );

            launchCapacityMultiplier = 1f;
            sectorSeed = 2;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            clearSectorOnLose = true;

            defaultCore = Blocks.coreBastion;

            ruleSetter = r -> {
                r.waveTeam = ARTeams.atramace;

                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.waveSpacing = 100 * Time.toSeconds;
                if(r.sector.preset == null)r.winWave = 250;
                r.coreDestroyClear = true;
                r.onlyDepositCore = false;

                Rules.TeamRule teamRule = r.teams.get(r.defaultTeam);
                teamRule.rtsAi = true;
                teamRule.unitBuildSpeedMultiplier = 5f;
                teamRule.blockDamageMultiplier = 2f;
                teamRule.buildSpeedMultiplier = 3f;
                teamRule.blockHealthMultiplier = 2.5f;

                teamRule = r.teams.get(r.waveTeam);
                teamRule.infiniteAmmo = teamRule.infiniteResources = false;
            };

            iconColor = Color.valueOf("afaf00");
            atmosphereColor = Color.valueOf("3c1b8f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            hiddenItems.removeAll(Items.serpuloItems);
            hiddenItems.removeAll(Items.erekirItems);
        }};

        mace = new Planet("mace", atrantis, 0.35f, 2){{
            generator = new ARPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            sectorSeed = 2;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyBuildSpeedMultiplier = 0.75f;
            allowLaunchLoadout = true;

            defaultCore = ARBlocks.ancientCore;

            ruleSetter = r -> {
                r.waveTeam = ARTeams.atramace;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                if(r.sector.preset == null)r.winWave = 500;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;

                Rules.TeamRule teamRule = r.teams.get(r.defaultTeam);
                teamRule.rtsAi = true;

                teamRule = r.teams.get(r.waveTeam);
                teamRule.infiniteAmmo = teamRule.infiniteResources = false;
            };

            iconColor = Color.valueOf("afff00");
            atmosphereColor = Color.valueOf("3c1b8f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);

            unlockedOnLand.add(ARBlocks.ancientCore);
        }};
    }
}
