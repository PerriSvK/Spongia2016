package sk.perri.spognia.veci;

import org.newdawn.slick.SlickException;

public class Vec extends Entit
{
    public Vec(float x, float y, boolean pickedAble, String path) throws SlickException
    {
        super(Entit.VEC, x, y, pickedAble, path);
    }
}
