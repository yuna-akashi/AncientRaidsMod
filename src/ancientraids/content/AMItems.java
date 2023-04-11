package ancientraids.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

public class AMItems {
    public static Item
    aScrap, aMetal, aGlass, aAlloy, conductor, cube, aAmmo, matter;

    public static final Seq<Item> atramaceItems = new Seq<>();

    public static void load(){
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
                aScrap, aMetal, aGlass, aAlloy, conductor, cube, aAmmo, matter,
                Items.graphite, Items.sand, Items.silicon
        );
    }
}
