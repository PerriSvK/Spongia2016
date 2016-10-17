package sk.perri.spognia.veci;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;

public class Backgound
{
    Image texture;

    public Backgound(String path) throws SlickException
    {
        texture = new Image(path);
    }

    public void render()
    {
        texture.draw(0, 0);
    }
}
