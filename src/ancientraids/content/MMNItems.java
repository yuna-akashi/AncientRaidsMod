package ancientraids.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

public class MMNItems {
    public static Item
            iron, cement, mmnCementAlloy, steel, mmnAlloy, microProcessor, mmnProcessor
    ;

    public static final Seq<Item> momonaItems = new Seq<>();

    public static void load(){
        iron = new Item("iron", Color.valueOf("005243")){{
            hardness = 2;
            cost = 1.25f;
            healthScaling = 1.2f;
        }};

        cement = new Item("cement", ARColor.mmnColor2){{
            cost = 0.71f;
        }};

        mmnCementAlloy = new Item("reinforced-cement", ARColor.mmnColor3);
        steel = new Item("steel", Color.valueOf("6c676e")){{
            cost = 1.5f;
            healthScaling = 1.6f;
        }};
        mmnAlloy = new Item("mmn-alloy", ARColor.mmnColor2){{
            cost = 4;
            healthScaling = 6f;
        }};

        microProcessor = new Item("micro-processor", Color.coral);
        mmnProcessor = new Item("mmn-processor", Color.green);

        momonaItems.addAll(
                iron, cement, mmnCementAlloy, steel, mmnAlloy, microProcessor, mmnProcessor,
                Items.silicon, Items.sand
        );
    }
}
