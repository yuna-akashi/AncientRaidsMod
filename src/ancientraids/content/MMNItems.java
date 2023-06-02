package ancientraids.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

public class MMNItems {
    public static Item
            limestone, clay,
            iron, reinforcedGlass, steel, microProcessor, diamond, mmnAlloy, mmnCementAlloy, mmnProcessor
    ;

    public static final Seq<Item> momonaItems = new Seq<>();

    public static void load(){
        limestone = new Item("limestone", Color.white){{
            lowPriority = true;
            buildable = false;
            //needed to show up as requirement
            alwaysUnlocked = true;
        }};

        clay = new Item("clay"){{
            lowPriority = true;
        }};

        iron = new Item("iron", Color.valueOf("005243")){{
            hardness = 2;
            cost = 1.25f;
            healthScaling = 1.2f;
        }};

        reinforcedGlass = new Item("reinforced-glass"){{
            cost = 1.12f;
        }};

        steel = new Item("steel", Color.valueOf("6c676e")){{
            cost = 1.5f;
            healthScaling = 1.6f;
        }};

        microProcessor = new Item("micro-processor", Color.coral);

        diamond = new Item("diamond"){{
            cost =1.35f;
        }};

        mmnAlloy = new Item("mmn-alloy", ARColor.mmnColor2){{
            cost = 4;
            healthScaling = 6f;
        }};

        mmnProcessor = new Item("mmn-processor", Color.green);

        /** This item use for Build Units. */
        mmnCementAlloy = new Item("mmn-cement-alloy", ARColor.mmnColor3);

        momonaItems.addAll(
                limestone, clay,
                iron, steel, microProcessor, diamond, mmnAlloy, mmnCementAlloy, mmnProcessor,
                Items.silicon, Items.sand, Items.graphite
        );
    }
}
