package ancientraids.content;

import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import static mindustry.type.ItemStack.with;

public class ARBlocks {
    public static Block
            ancientMetalWall, ancientMetalWalLarge
    ;

    public static void load(){
        ancientMetalWall = new Wall("ancient-metal-wall"){{
            size = 1;
            health = 250;
            requirements(Category.defense, with(ARItems.ancientMetal, 8));
        }};

        ancientMetalWalLarge = new Wall("ancient-metal-wall-large"){{
            size = 2;
            health = 1000;

            requirements(Category.defense, with(ARItems.ancientMetal, 32));
        }};
    }
}
