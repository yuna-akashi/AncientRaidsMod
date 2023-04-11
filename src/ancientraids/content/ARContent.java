package ancientraids.content;

import mindustry.ctype.Content;
import mindustry.ctype.ContentType;
import mindustry.world.meta.Attribute;

public class ARContent extends Content {

    public static Attribute aScrap;

    @Override
    public ContentType getContentType() {
        return ContentType.error;
    }

    public static void loadBeforeContentLOad() {
        aScrap = Attribute.add("ancient-scrap");
    }

    public void load(){

    }
}
