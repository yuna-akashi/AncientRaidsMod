package ancientraids.content;

import static mindustry.content.TechTree.*;

public class ARTechTree {

    public static void load(){
        ARPlanets.atrantis.techTree = nodeRoot("ancient-raids", ARBlocks.ironWall, () -> {
            node(ARBlocks.steelWall);
        });

        nodeRoot("ancient-raids-atramace", ARBlocks.ancientCore, () -> {
            node(ARBlocks.ancientConveyor, () -> {
                node(ARBlocks.ancientJunction, () -> {
                    node(ARBlocks.ancientRouter, () -> {
                        //node(ARBlocks.);
                        node(ARBlocks.ancientDisruptor);
                        node(ARBlocks.ancientSorter, () -> {
                            node(ARBlocks.ancientInvertedSorter);
                            node(ARBlocks.ancientOverflowGate, () -> {
                                node(ARBlocks.ancientUnderflowGate);
                            });
                        });
                        node(ARBlocks.ancientContainer, () -> {
                            node(ARBlocks.ancientUnloader);
                            node(ARBlocks.ancientVault);
                        });
                        node(ARBlocks.ancientItemBridge, () -> {
                            node(ARBlocks.ancientDuct);
                        });
                    });
                });
            });

            node(ARBlocks.ancientCoreFortress, () -> {
                node(ARBlocks.ancientCoreStronghold);
            });

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

                    node(ARBlocks.ancientAlloyArcSmelter);
                });
            });

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

//            node(Unit factory);

//            node(ARSectorPresets.beginnings, () -> {
//                node(ARSectorPresets.deepAncient);
//            });

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
