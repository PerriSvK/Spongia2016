package sk.perri.spognia.veci;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import sk.perri.spognia.utils.Constants;

public class Zem
{
    public static final String CESTA = Constants.ASSETS_PATH+Constants.CESTA;
    public static final String ZEM = Constants.ASSETS_PATH+Constants.ZEM;

    private Image texture;
    private String type;

    public Zem(String type)
    {
        try
        {
            texture = new Image(type);
        }
        catch (SlickException e)
        {
            System.err.println("Could not load map texture on path: "+type);
        }

        this.type = type;
    }

    public void render(int x, int y)
    {
        texture.draw(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
    }
}
