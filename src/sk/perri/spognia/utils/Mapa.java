package sk.perri.spognia.utils;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.Vector;

public class Mapa
{
    private Image dataTexture;
    private Vector<Vector<Chunk>> chunks = new Vector<Vector<Chunk>>();
    private Posit<Point, Chunk> posit = new Posit<>();
    private Point offSet = new Point(0, 0);
    private int widthNo = 0;
    private int heightNo = 0;

    private Point LU = new Point(0, 0);
    private Point RD = new Point(0, 0);
    private Point LLU = new Point(0, 0);
    private Point LRD = new Point(0, 0);

    public Mapa(Image mapTexture)
    {
        dataTexture = mapTexture;
    }

    public void loadMap()
    {
        for(long j = 0; j < (600 / (Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN)) + 1; j++) // Math.sqrt(Constants.CHUNK_SIZE))
        {
            heightNo++;
            for(long i = 0; i < (800 / (Constants.BLOCK_WIDTH*Constants.CHUNK_LEN)) + 1; i++) //= Math.sqrt(Constants.CHUNK_SIZE))
            {
                Chunk ch = new Chunk(new Point(i*Constants.CHUNK_LEN, j*Constants.CHUNK_LEN), dataTexture);
                ch.load();
                posit.add(new Point(i, j), ch);

                widthNo++;
            }
        }

        widthNo /= heightNo;

        RD.set(widthNo, heightNo);
        LRD.set(widthNo, heightNo);

        Constants.print("No of chunks:", posit.size(), widthNo, heightNo);
    }

    public void load(float dir, Camera camera)
    {
        int rPosX = (int) camera.getOffSetX()+Constants.WINDOW_WIDTH;
        int rPosY = (int) camera.getOffSetY()+Constants.WINDOW_HEIGHT;

        Constants.print("MAP LU", LU.toString(), "RD", RD.toString(), "LChunkNo:",
                rPosX / (Constants.BLOCK_WIDTH* Constants.CHUNK_LEN)+1, "DChunkNo:",
                rPosY / (Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN)+1);
        Constants.print("REAL POS X:", rPosX, "Y:", rPosY);
        if(dir == 0)
        {
            if(rPosX / (Constants.BLOCK_WIDTH* Constants.CHUNK_LEN) + 1 < RD.getX())
            {
                return;
            }

            if(RD.getX()*Constants.CHUNK_LEN + 1 > dataTexture.getWidth())
            {
                Constants.print("Nerobim lebo OUT a pod:", RD.getX() + 1, ">", dataTexture.getWidth());
                return;
            }

            for(int i = (int) LU.getY() - 2; i < RD.getY() + 2; i++)
            {
                if(i >= 0 && i < dataTexture.getHeight())
                {
                    Constants.print("CREATING I:", i, "x:", RD.getX(), "y:", i);
                    loadChunk((int) RD.getX(), i);
                }
            }
            if(RD.getY() - LU.getY() <= Constants.WINDOW_HEIGHT / (Constants.CHUNK_LEN*Constants.BLOCK_HEIGHT) + 1)
                LU.setX(LU.getX()+1);
            RD.setX(RD.getX()+1);
            LRD.setX(LRD.getX()+1);

            Constants.print("MAP, LOAD LEFT LRDX:", LRD.getX());
        }

        if(dir == 90)
        {
            if(rPosY / (Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN) + 1 < RD.getY())
                return;

            if(RD.getY()*Constants.CHUNK_LEN + 1 > dataTexture.getHeight())
                return;

            for(int i = (int) LU.getX()-2; i < RD.getX()+2; i++)
            {
                if(i >= 0 && i - 1 < dataTexture.getWidth())
                loadChunk(i, (int) RD.getY());
            }
            if(RD.getX() - LU.getX() <= Constants.WINDOW_WIDTH / (Constants.CHUNK_LEN*Constants.BLOCK_WIDTH) + 1)
                LU.setY(LU.getY()+1);
            RD.setY(RD.getY()+1);
            LRD.setY(LRD.getY()+1);
        }

        if(dir == 180)
        {
            if (camera.getOffSetX() / (Constants.BLOCK_WIDTH * Constants.CHUNK_LEN) - 1 > LU.getX())
            {
                Constants.print("CAM pod:", camera.getOffSetX() / (Constants.BLOCK_WIDTH * Constants.CHUNK_LEN) - 1,
                        ">", LU.getX());
                return;
            }

            if (LU.getX() - 1 < 0)
            {
                Constants.print("OUT:", LU.getX() - 1);
                return;
            }

            Constants.print("LEFT", LU.getX(), LU.getY());

            for(int i = (int) LU.getY()-2; i < RD.getY()+2; i++)
            {
                if(i >= 0 && i < dataTexture.getHeight())
                {
                    Constants.print("ROBIM I:", i);
                    loadChunk((int) LU.getX()-1, i);
                }
            }

            LU.setX(LU.getX()-1);
            LLU.setX(LLU.getX()-1);
            RD.setX(RD.getX()-1);
        }

        if(dir == 270)
        {
            if(camera.getOffSetY() / (Constants.BLOCK_HEIGHT*Constants.CHUNK_LEN) - 1 >= LU.getY())
                return;

            if(RD.getY() - 1 <= 0)
                return;

            for(int i = (int) LU.getX() - 2; i < RD.getX() + 2; i++)
            {
                if(i >= 0 && i < dataTexture.getWidth())
                    loadChunk(i, (int) LU.getY());
            }

            LU.setY(LU.getY()-1);
            LLU.setY(LLU.getY()-1);
            RD.setY(RD.getY()-1);
        }
    }

    public boolean canMoveMap(float dir)
    {
        if(dir == -1)
            return false;

        boolean vys1 = LU.getX() + Math.cos(Math.toRadians(dir)) > 0 &&
                RD.getX() + Math.cos(Math.toRadians(dir)) < dataTexture.getWidth();
        boolean vys2 = LU.getY() + Math.sin(Math.toRadians(dir)) > 0 &&
                RD.getY() + Math.sin(Math.toRadians(dir)) < dataTexture.getHeight();
        return vys1 && vys2;
    }

    public void loadChunk(int x, int y)
    {
        if(posit.containPoint(x, y))
        {
            Constants.print("Existujem x:", x, "y:", y, "LUC:", posit.getValue(x, y).getLUC().toString());
            return;
        }

            Point p = new Point(x*Constants.CHUNK_LEN, y*Constants.CHUNK_LEN);
            Chunk ch = new Chunk(p, dataTexture);
            ch.load();
            posit.add(p, ch);
            Constants.print("MAP, CREATING CHUNK AT", p.toString());

    }

    public void render(Graphics g, Point offSet)
    {
        int u = 0;
        int p = 0;

        this.offSet.set(offSet);

        for(Chunk ch : posit.getValues())
        {
            ch.render((int) offSet.getX(), (int) offSet.getY(), g);

            u++;

            if (u > LRD.getX())
            {

                u = 0;
                p++;
            }
        }
    }


}
