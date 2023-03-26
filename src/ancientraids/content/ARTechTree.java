package ancientraids.content;

import static mindustry.content.TechTree.*;

public class ARTechTree {

    public static void load(){
        ARPlanets.atramoon.techTree = nodeRoot("ancient-raids-atramace", ARBlocks.ancientCore, () -> {

            //transport
            node(ARBlocks.ancientDuct, () -> {
                node(ARBlocks.ancientDuctRouter, () -> {
                    node(ARBlocks.ancientDuctBridge, () -> {
                        node(ARBlocks.ancientArmorDuct, () -> {
                            node(ARBlocks.ancientPhaseDuct);
                            node(ARBlocks.ancientStackConveyor);
                        });
                        node(ARBlocks.ancientCargoLoader, () -> {
                            node(ARBlocks.ancientCargoUnloadPoint);
                        });
                    });
                    node(ARBlocks.ancientOverflowDuct, () -> {
                        node(ARBlocks.ancientUnderflowDuct);
                        node(ARBlocks.ancientContainer, () -> {
                            node(ARBlocks.ancientDuctUnloader);
                            node(ARBlocks.ancientVault);
                        });
                    });
                });
            });
            //end

            //production
            node(ARBlocks.ancientDrill, () -> {

                node(ARBlocks.ancientPump, () -> {
                    node(ARBlocks.ancientConduit, () -> {
                        node(ARBlocks.ancientLiquidJunction, () -> {
                            node(ARBlocks.ancientLiquidRouter, () -> {
                                node(ARBlocks.ancientLiquidContainer, () -> {
                                    node(ARBlocks.ancientLiquidTank);
                                });

                                node(ARBlocks.ancientBridgeConduit);

                                node(ARBlocks.ancientReinforcedConduit, () -> {
                                    node(ARBlocks.ancientPhaseConduit);

                                    node(ARBlocks.ancientReinforcedPump);
                                });
                            });
                        });
                    });
                });

                node(ARBlocks.ancientMetalSmelter, () -> {
                    node(ARBlocks.ancientBeamDrill, () -> {
                        node(ARBlocks.ancientBurstDrill);
                    });

                    node(ARBlocks.ancientAlloySmelter);
                });
            });
            //end

            //turret+wall
            node(ARBlocks.ancientDuo, () -> {
                node(ARBlocks.ancientWall, () -> {
                    node(ARBlocks.ancientWalLarge, () -> {
                        node(ARBlocks.ancientDefenceWall, () -> {
                            node(ARBlocks.ancientDefenceWallLarge);
                            node(ARBlocks.ancientDefenceDoor);
                        });
                    });
                });

                node(ARBlocks.ancientRailgun, () -> {
                    node(ARBlocks.ancientRailcannon);
                });

            });
            //end

            //core
            node(ARBlocks.ancientCoreFortress, () -> {
                node(ARBlocks.ancientCoreStronghold);
            });
            //end

            //units
//            node(Unit factory);
            //end

            //sector
//            node(ARSectorPresets.unknownRuin, () -> {
//                node(ARSectorPresets.deepEmpire);
//            });
            //end

            nodeProduce(ARItems.aScrap, () -> {
                nodeProduce(ARLiquids.ancientWater, () -> {
                    nodeProduce(ARLiquids.conductorLiquid, () -> {});
                    nodeProduce(ARLiquids.efficiencyLiquid, () -> {});
                });

                nodeProduce(ARItems.aGlass, () -> {});
                nodeProduce(ARItems.aMetal, () -> {
                    nodeProduce(ARItems.conductor, () -> {
                        nodeProduce(ARItems.cube, () -> {});
                    });
                    nodeProduce(ARItems.aAlloy, () -> {});
                });

            });
        });
    }
}
