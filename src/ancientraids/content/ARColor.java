package ancientraids.content;

import arc.graphics.Color;
import arc.graphics.Colors;

public class ARColor {
    public static Color
            ancientBlueLight = Color.valueOf("00ffff"),
            ancientBlue = Color.valueOf("00c2c2"),
            ancientBlueDark = Color.valueOf("00a0a0"),
            ancientYellowLight = Color.valueOf("c8c800"),
            ancientYellow = Color.valueOf("919100"),
            mmnColor3 = Color.valueOf("777777"),
            mmnColor2 = Color.valueOf("939393"),
            mmnColor1 = Color.valueOf("bebebe")
    ;

    static {
        Colors.put("ancient-blue-light", ancientBlueLight);
        Colors.put("ancient-blue", ancientBlue);
        Colors.put("ancient-blue-dark", ancientBlueDark);
        Colors.put("ancient-yellow-light", ancientYellowLight);
        Colors.put("ancient-yellow", ancientYellow);
        Colors.put("momona-color-1", mmnColor1);
        Colors.put("momona-color-2", mmnColor2);
        Colors.put("momona-color-3", mmnColor3);
    }
}
