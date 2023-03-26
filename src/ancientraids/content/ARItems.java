package ancientraids.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

public class ARItems {
    public static Item
    aScrap, iron, steel, aMetal, aGlass, aAlloy, conductor, cube, aAmmo, matter;

    public static final Seq<Item> atramaceItems = new Seq<>();

    public static void load(){
        iron = new Item("iron", Color.valueOf("005243")){{
            hardness = 2;
            cost = 1.25f;
            healthScaling = 1.2f;
        }};
        steel = new Item("steel", Color.valueOf("6c676e")){{
            cost = 1.5f;
            healthScaling = 1.6f;
        }};
        aMetal = new Item("ancient-metal", Color.valueOf("00ffff")){{
            cost = 5f;
            healthScaling = 3f;
        }};
        aGlass = new Item("ancient-glass", Color.white){{
            cost = 1.5f;
        }};
        aAmmo = new Item("ancient-ammo", Color.black);
        aAlloy = new Item("ancient-alloy", Color.yellow){{
            cost = 15;
            healthScaling = 10;
        }};
        conductor = new Item("conductor", Color.sky);
        cube = new Item("cube", Color.red){{
            charge = 2.5f;
        }};
        aScrap = new Item("ancient-scrap", Color.gray);
        matter = new Item("matter", Color.gray);

        atramaceItems.addAll(
                iron, steel, aScrap, aMetal, aGlass, aAlloy, conductor, cube, aAmmo, matter,
                Items.graphite, Items.sand, Items.silicon
        );
    }
}
