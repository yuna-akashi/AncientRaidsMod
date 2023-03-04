package ancientraids.util.gen;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.Tmp;
import arc.util.noise.Simplex;
import mindustry.graphics.Shaders;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexMesher;
import mindustry.type.Planet;

public class ARHexMesh extends HexMesh {
    public static float waterOffset = 0.05f;

    public ARHexMesh(Planet planet, int divisions, double octaves, double persistence, double scl, double pow, double mag, float colorScale, Color... colors){
        super(planet, new HexMesher(){
            @Override
            public float getHeight(Vec3 position){
                position = Tmp.v33.set(position).scl(4f);
                float height = (Mathf.pow(Simplex.noise3d(123, 7, 0.5f, 1f/3f, position.x, position.y, position.z), 2.3f) + waterOffset) / (1f + waterOffset);
                return Math.max(height, 0.05f);
            }

            @Override
            public Color getColor(Vec3 position){
                double height = Math.pow(Simplex.noise3d(1, octaves, persistence, scl, position.x, position.y, position.z), pow) * mag;
                return Tmp.c1.set(colors[Mathf.clamp((int)(height * colors.length), 0, colors.length - 1)]).mul(colorScale);
            }

        }, divisions, Shaders.unlit);
    }
}
