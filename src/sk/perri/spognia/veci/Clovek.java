package sk.perri.spognia.veci;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import sk.perri.spognia.utils.Camera;
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

    public boolean isCenterX(Camera cam)
    {
        float deltaOffSet = getX() - cam.getOffSetX();
        return Math.abs(deltaOffSet - Constants.WINDOW_WIDTH / 2) < 5;
    }

    public boolean isCenterY(Camera cam)
    {
        float deltaOffSet = getY() - cam.getOffSetY();
        return Math.abs(deltaOffSet - Constants.WINDOW_HEIGHT / 2) < 5;
    }

    public boolean isNearEdge(float dir, Camera cam)
    {
        float deltaOffSetX = getX() - cam.getOffSetX();
        float deltaOffSetY = getY() - cam.getOffSetY();

        if(dir == Clovek.SMER_HORE)
        {
            if(getY() < Constants.WINDOW_HEIGHT/2 && cam.getOffSetY() > 1)
            {
                Constants.print("Clovek, som nad polovic! deltaY:", getY());
                return false;
            }

            if(cam.getOffSetY() < 1 || (cam.getOffSetY() + getY() > cam.getMapHeight()*Constants.BLOCK_HEIGHT - Constants.WINDOW_HEIGHT/2))
                return true;
        }

        if(dir == Clovek.SMER_DOLE)
        {
            if(getY() > Constants.WINDOW_HEIGHT/2 &&
                    Math.abs(cam.getOffSetY() + Constants.WINDOW_HEIGHT) < cam.getMapHeight()* Constants.BLOCK_HEIGHT - 5)
                return false;

            if(cam.getOffSetY() < 1 || Math.abs(cam.getOffSetY() + Constants.WINDOW_HEIGHT) > cam.getMapHeight()* Constants.BLOCK_HEIGHT - 5)
                return true;
        }

        if(dir == Clovek.SMER_VPRAVO)
        {
            if(getX() > Constants.WINDOW_WIDTH/2 && cam.getOffSetX() + Constants.WINDOW_WIDTH < cam.getMapWidth()*Constants.BLOCK_WIDTH - 5)
                return false;

            if(cam.getOffSetX() +  Constants.WINDOW_WIDTH >= cam.getMapWidth()*Constants.BLOCK_WIDTH - 5 || (cam.getOffSetX() < 1 && getX() < cam.getMapWidth()*Constants.BLOCK_HEIGHT - Constants.WINDOW_WIDTH / 2))
                return true;
        }

        if(dir == Clovek.SMER_VLAVO)
        {
            if(getX() < Constants.WINDOW_WIDTH/2 && cam.getOffSetX() > 1)
                return false;

            if(cam.getOffSetX() < 1 || cam.getOffSetX()+getX() > cam.getMapWidth()*Constants.BLOCK_WIDTH - Constants.WINDOW_WIDTH / 2 )
                return true;
        }

        return false;
    }

    public Point getDelta()
    {
        return delta;
    }
}
