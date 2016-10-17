package sk.perri.spognia.utils;

import sk.perri.spognia.veci.Vec;

import java.util.Vector;

public class Posit<P extends Point, O>
{
    Vector<P> pos;
    Vector<O> value;

    public Posit()
    {
        pos = new Vector<>();
        value = new Vector<>();
    }

    public void add(P point, O object)
    {
        pos.add(point);
        value.add(object);
    }

    public void remove(P point)
    {
        int index = pos.indexOf(point);
        pos.remove(index);
        value.remove(index);
    }

    public void remove(O object)
    {
        int index = value.indexOf(object);
        pos.remove(index);
        value.remove(index);
    }

    public void remove(int index)
    {
        if(index > pos.size())
            System.err.println("OUT OF RANGE");
        pos.remove(index);
        value.remove(index);
    }

    public boolean contain(P point)
    {
        for(P p : pos)
        {
            if(p.getX() == point.getX() && p.getY() == point.getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean containPoint(float x, float y)
    {
        for(P p : pos)
        {
            if(p.getX() == x && p.getY() == y)
            {
                return true;
            }
        }
        return false;
    }

    public int size()
    {
        return pos.size();
    }

    public Vector<O> getValues()
    {
        return value;
    }

    public Vector<P> getKeys()
    {
        return pos;
    }

    public O getValue(P p)
    {
        int i = 0;
        for(P ps : pos)
        {
            if(ps.getX() == p.getX() && ps.getY() == p.getY())
            {
                return value.get(i);
            }
            i++;
        }
        return null;
    }

    public O getValue(float x, float y)
    {
        int i = 0;
        for(P ps : pos)
        {
            if(ps.getX() == x && ps.getY() == y)
            {
                return value.get(i);
            }
            i++;
        }
        return null;
    }

    public O getValue(int index)
    {
        if(index <= value.size())
            return value.get(index);

        return null;
    }

    public int getIndex(P p)
    {
        int i = 0;
        for(P ps : pos)
        {
            if(ps.getX() == p.getX() && ps.getY() == p.getY())
            {
                return i;
            }
            i++;
        }
        return -1;
    }
}
