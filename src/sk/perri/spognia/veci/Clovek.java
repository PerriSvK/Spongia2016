package sk.perri.spognia.veci;

import org.newdawn.slick.SlickException;
import sk.perri.spognia.utils.Constants;
import sk.perri.spognia.utils.Point;

import static java.lang.Math.*;

public class Clovek extends Entit
{
    public static final float SMER_HORE = 270;
    public static final float SMER_VLAVO = 180;
    public static final float SMER_DOLE = 90;
    public static final float SMER_VPRAVO = 0;

    private Point delta = new Point(0, 0);


    public Clovek(float x, float y, boolean pickedAble, String path)
    {
        super(Entit.CLOVEK, x, y, pickedAble, path);
    }

    public void move(float dir, float deltaTime)
    {
        float x = super.getX() + (float)cos(Math.toRadians(dir))*deltaTime*Constants.BLOCK_WIDTH*Constants.CLOVEK_SPEED/1000;
        float y = super.getY() + (float)sin(Math.toRadians(dir))*deltaTime*Constants.BLOCK_HEIGHT*Constants.CLOVEK_SPEED/1000;
        delta.set(x-super.getX(), y-super.getY());
        super.setPosition(x, y);
    }

    public Point getDelta()
    {
        return delta;
    }
}
