package sk.perri.spognia.utils;

public class Camera
{
    //private int offSetX = 0;
    //private int offSetY = 0;
    private Point offSet = new Point(0, 0);
    private Point mapSize = new Point(0, 0);

    public Camera() { }

    public Camera(int offSetX, int offSetY, int mapWidth, int mapHeight)
    {
        offSet.set(offSetX, offSetY);
        mapSize.set(mapWidth, mapHeight);
    }

    public Camera(Point offset, Point mapSize)
    {
        offSet = offset;
        this.mapSize = mapSize;
    }

    public void moveCamera(float degAngle, float speed)
    {
        double posunX = Math.cos(Math.toRadians(degAngle))*speed;
        double posunY = Math.sin(Math.toRadians(degAngle))*speed;

        offSet.setX(Math.min(mapSize.getX()*Constants.BLOCK_WIDTH-Constants.WINDOW_WIDTH, Math.max(0,(int) Math.round(offSet.getX()+posunX))));
        offSet.setY(Math.min(mapSize.getY()*Constants.BLOCK_HEIGHT-Constants.WINDOW_HEIGHT, Math.max(0,(int) Math.round(offSet.getY()+posunY))));
    }

    public float getOffSetX() { return offSet.getX(); }

    public float getOffSetY() { return offSet.getY(); }

    public float getMapWidth() { return mapSize.getX(); }

    public float getMapHeight() { return mapSize.getY(); }

    public void setOffSetX(int offSetX) { offSet.setX(offSetX); }

    public void setOffSetY(int offSetY) { offSet.setY(offSetY); }

    public Point getOffSet() { return offSet; }
}
