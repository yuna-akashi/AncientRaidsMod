package ancientraids.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class ARItems {
    public static Item
    ancientMetal;

    public static void load(){
        ancientMetal = new Item("ancient-metal", Color.yellow);
    }
}
