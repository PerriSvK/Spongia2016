package sk.perri.spognia.utils;

public class Point
{
    private float x, y;

    public Point()
    {
        x = 0;
        y = 0;
    }

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void set(Point p) { this.x = p.getX(); this.y = p.getY(); }

    @Override
    public String toString()
    {
        return "x: "+x+" y: "+y;
    }

    public void add(Point p2)
    {
        this.x += p2.getX();
        this.y += p2.getY();
    }

    public void subtract(Point p2)
    {
        this.x -= p2.getX();
        this.y -= p2.getY();
    }

    public static Point plus(Point p1, Point p2)
    {
        return new Point(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    }

    public static Point minus(Point p1, Point p2)
    {
        return new Point(p1.getX()-p2.getX(), p1.getY()-p2.getY());
    }
}
