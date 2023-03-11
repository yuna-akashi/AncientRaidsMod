package ancientraids.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class ARLiquids {
    public static Liquid ancientWater, conductorLiquid, efficiencyLiquid;

    public static void load(){
        ancientWater = new Liquid("ancient-water", Color.valueOf("596ab8")){{
            heatCapacity = 0.4f;
            effect = StatusEffects.wet;
            boilPoint = 0.5f;
            gasColor = Color.grays(0.9f);
            alwaysUnlocked = true;
        }};
        conductorLiquid = new Liquid("conductor-liquid", Color.yellow){{
            heatCapacity = 0.4f;
            boilPoint = 0.5f;
            gasColor = Color.grays(0.9f);
        }};

        efficiencyLiquid = new Liquid("efficiency-liquid"){{
            heatCapacity = 1111f;
            boilPoint = 0.5f;
            gasColor = Color.blue;

            temperature = -100f;
        }};
    }
}
