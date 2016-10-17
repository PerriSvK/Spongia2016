package sk.perri.spognia.utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import sk.perri.spognia.veci.Zem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chunk
{
    private Map<Point, Zem> zem = new HashMap<>();
    private Image mapTexture;
    private Point LUC;
    private Rectangle dbgRect;
    private Rectangle shRect;

    public Chunk(Point LUC, Image mapTexture) // LUC = LeftUpCorner
    {
        this.LUC = LUC;
        this.mapTexture = mapTexture;
        dbgRect = new Rectangle(LUC.getX()*Constants.BLOCK_WIDTH,
                LUC.getY()*Constants.BLOCK_HEIGHT, Constants.BLOCK_WIDTH*Constants.CHUNK_LEN-2,
                Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN-2);
        shRect = new Rectangle(LUC.getX()*Constants.BLOCK_WIDTH,
                LUC.getY()*Constants.BLOCK_HEIGHT, Constants.BLOCK_WIDTH*Constants.CHUNK_LEN-2,
                Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN-2);
        /*Constants.print("New dbgRect at x:", LUC.getX(), "y:", LUC.getY(), "w:",
                Constants.BLOCK_WIDTH*Constants.CHUNK_LEN-2, "h:", Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN-2);*/
    }

    public void load()
    {
        for(int j = Math.round(LUC.getY()); j <  Math.round(LUC.getY()) + Constants.CHUNK_LEN; j++)
        {
            for(int i = Math.round(LUC.getX()); i <  Math.round(LUC.getX()) + Constants.CHUNK_LEN; i++)
            {
                String typ = getType(mapTexture, i, j);
                zem.put(new Point(i, j), new Zem(typ));
            }
        }
    }

    public boolean exist(Point checkLP)
    {
        return (LUC.getX() == checkLP.getX() && LUC.getY() == checkLP.getY());
    }

    public void render(int x, int y, Graphics g)
    {
        for (Map.Entry<Point, Zem> c : zem.entrySet())
        {
            c.getValue().render(Math.round(c.getKey().getX()*Constants.BLOCK_WIDTH)-x,
                    Math.round(c.getKey().getY()*Constants.BLOCK_HEIGHT)-y);
        }

        shRect.setX(dbgRect.getX()-x);
        shRect.setY(dbgRect.getY()-y);
        g.setColor(Color.blue);
        g.setLineWidth(2);
        g.draw(shRect);
    }

    public Point getLUC()
    {
        return LUC;
    }

    public static String getType(Image t, int x, int y)
    {
        if(t.getWidth() < x && t.getHeight() < y)
            return Zem.ZEM;

        Color pixCol = t.getColor(x, y);
        return (pixCol.equals(Constants.PATH_COLOR) ? Zem.CESTA : Zem.ZEM);
    }
}
