package sk.perri.spognia.veci;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import sk.perri.spognia.utils.Constants;
import sk.perri.spognia.utils.Point;

public abstract class Entit
{
    public static int JA = 0;
    public static int VEC = 1;
    public static int CLOVEK = 2;
    public static int STAVBA = 3;

    private Point pos;
    private int typ;
    private boolean pickedAble;
    private boolean pickedUp;
    private boolean visible;
    private Image texture;

    public void render()//Point offSet)
    {
        if(!pickedUp && visible)
        {
            texture.draw(pos.getX(), pos.getY(),
                    (float) Constants.BLOCK_HEIGHT / texture.getHeight() *
                            (typ == VEC ? Constants.SCALE_VEC : Constants.SCALE_CLOVEK));
        }
    }

    public Entit(int typ) { this.typ = typ; }

    public Entit(int typ, float x, float y)
    {
        this.typ = typ;
        pos = new Point(x, y);
    }

    public Entit(int typ, float x, float y, String path)
    {
        this.typ = typ;
        pos = new Point(x, y);
        try
        {
            texture = new Image(path);
        }
        catch (SlickException e)
        {
            System.err.println("Could not load Entit type: "+typ+" texture: "+path+" error: "+e.toString());
        }
    }

    public Entit(int typ, float x, float y, boolean pickedAble, String path)
    {
        this.typ = typ;
        pos = new Point(x, y);
        this.pickedAble = pickedAble;
        try
        {
            texture = new Image(path);
        }
        catch (SlickException e)
        {
            System.err.println("Could not load Entit type: "+typ+" texture: "+path+" error: "+e.toString());
        }
    }

    public void setPickedAble(boolean newPickedAble) { this.pickedAble = newPickedAble; }
    public void setTexture(String path) throws SlickException { texture = null; texture = new Image(path); }
    public void setPosition(float x, float y) { pos.setX(x); pos.setY(y); }
    public void setVisible(boolean visible) { this.visible = visible; }

    public void pickUp() { if(pickedAble && !pickedUp) pickedUp = true; }
    public void putDown() { if(pickedUp) pickedUp = false; }

    public boolean isPickedAble() { return pickedAble; }
    public float getX() { return pos.getX(); }
    public float getY() { return pos.getY(); }
    public boolean isVisible() { return visible; }
    public boolean isPickedUp() { return pickedUp; }
}
