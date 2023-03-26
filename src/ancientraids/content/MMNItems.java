package ancientraids.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

public class MMNItems {
    public static Item
            reinforcedAlloy, mmnAlloy, basicProcessor, mmnProcessor
    ;

    public static final Seq<Item> momonaItems = new Seq<>();

    public static void load(){
        reinforcedAlloy = new Item("reinforced-alloy", Color.white){{
            cost = 1.75f;
            healthScaling = 2f;
        }};
        mmnAlloy = new Item("mmn-alloy", ARColor.mmnColor2){{
            cost = 4;
            healthScaling = 6f;
        }};

        momonaItems.addAll(
                reinforcedAlloy, mmnAlloy,
                Items.silicon, Items.sand, ARItems.iron, ARItems.steel
        );
    }
}
