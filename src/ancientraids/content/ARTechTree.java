package ancientraids.content;

import static ancientraids.content.MMNBlocks.*;
import static ancientraids.content.MMNItems.*;
import static mindustry.content.TechTree.*;

public class ARTechTree {

    private static void loadMMN(){
        ARPlanets.atramace.techTree = nodeRoot("momona-tech", coreBase, () -> {

            node(coreCluster, () -> {
                node(coreMomona);
            });

            node(steelSmelter);

            node(ironWall, () -> {
                node(steelWallLarge);
            });

            nodeProduce(iron, () -> {
                nodeProduce(steel, () -> {});
            });
        });
    }

    public static void load(){
        ARPlanets.atramoon.techTree = nodeRoot("ancient-raids-atramace", AMBlocks.ancientCore, () -> {

            //transport
            node(AMBlocks.ancientDuct, () -> {
                node(AMBlocks.ancientDuctRouter, () -> {
                    node(AMBlocks.ancientDuctBridge, () -> {
                        node(AMBlocks.ancientArmorDuct, () -> {
                            node(AMBlocks.ancientPhaseDuct);
                            node(AMBlocks.ancientStackConveyor);
                        });
                        node(AMBlocks.ancientCargoLoader, () -> {
                            node(AMBlocks.ancientCargoUnloadPoint);
                        });
                    });
                    node(AMBlocks.ancientOverflowDuct, () -> {
                        node(AMBlocks.ancientUnderflowDuct);
                        node(AMBlocks.ancientContainer, () -> {
                            node(AMBlocks.ancientDuctUnloader);
                            node(AMBlocks.ancientVault);
                        });
                    });
                });
            });
            //end

            //production
            node(AMBlocks.ancientDrill, () -> {

                node(AMBlocks.ancientPump, () -> {
                    node(AMBlocks.ancientConduit, () -> {
                        node(AMBlocks.ancientLiquidJunction, () -> {
                            node(AMBlocks.ancientLiquidRouter, () -> {
                                node(AMBlocks.ancientLiquidContainer, () -> {
                                    node(AMBlocks.ancientLiquidTank);
                                });

                                node(AMBlocks.ancientBridgeConduit);

                                node(AMBlocks.ancientReinforcedConduit, () -> {
                                    node(AMBlocks.ancientPhaseConduit);

                                    node(AMBlocks.ancientReinforcedPump);
                                });
                            });
                        });
                    });
                });

                node(AMBlocks.ancientMetalSmelter, () -> {
                    node(AMBlocks.ancientBeamDrill, () -> {
                        node(AMBlocks.ancientBurstDrill);
                    });

                    node(AMBlocks.ancientAlloySmelter);
                });
            });
            //end

            //turret+wall
            node(AMBlocks.ancientDuo, () -> {
                node(AMBlocks.ancientWall, () -> {
                    node(AMBlocks.ancientWalLarge, () -> {
                        node(AMBlocks.ancientDefenceWall, () -> {
                            node(AMBlocks.ancientDefenceWallLarge);
                            node(AMBlocks.ancientDefenceDoor);
                        });
                    });
                });

                node(AMBlocks.ancientRailgun, () -> {
                    node(AMBlocks.ancientRailcannon);
                });

            });
            //end

            //core
            node(AMBlocks.ancientCoreFortress, () -> {
                node(AMBlocks.ancientCoreStronghold);
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

            nodeProduce(AMItems.aScrap, () -> {
                nodeProduce(ARLiquids.ancientWater, () -> {
                    nodeProduce(ARLiquids.conductorLiquid, () -> {});
                    nodeProduce(ARLiquids.efficiencyLiquid, () -> {});
                });

                nodeProduce(AMItems.aGlass, () -> {});
                nodeProduce(AMItems.aMetal, () -> {
                    nodeProduce(AMItems.conductor, () -> {
                        nodeProduce(AMItems.cube, () -> {});
                    });
                    nodeProduce(AMItems.aCementAlloy, () -> {});
                });

            });
        });

        loadMMN();
    }
}
