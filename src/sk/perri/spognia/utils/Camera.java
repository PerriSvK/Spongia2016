package sk.perri.spognia.utils;

public class Camera
{
    //private int offSetX = 0;
    //private int offSetY = 0;
    private Point offSet = new Point(0, 0);

    public Camera() { }

    public Camera(int offSetX, int offSetY)
    {
        offSet.set(offSetX, offSetY);
    }

    public Camera(Point offset)
    {
        offSet = offset;
    }

    public void moveCamera(float degAngle, float speed)
    {
        double posunX = Math.cos(Math.toRadians(degAngle))*speed;
        double posunY = Math.sin(Math.toRadians(degAngle))*speed;

        offSet.setX(Math.max(0,(int) Math.round(offSet.getX()+posunX)));
        offSet.setY(Math.max(0,(int) Math.round(offSet.getY()+posunY)));
    }

    public float getOffSetX() { return offSet.getX(); }

    public float getOffSetY() { return offSet.getY(); }

    public void setOffSetX(int offSetX) { offSet.setX(offSetX); }

    public void setOffSetY(int offSetY) { offSet.setY(offSetY); }

    public Point getOffSet() { return offSet; }
}
