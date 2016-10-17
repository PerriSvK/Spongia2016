package sk.perri.spognia.utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class Constants
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final float VIEWPORT_WIDTH = 15.0f; //in hugs
    public static final float VIEWPORT_HEIGHT = 15.0f; //in hugs
    public static final float CLOVEK_SPEED = 40f; // hugs per second
    public static final float SCALE_VEC = 0.5f; // hug
    public static final float SCALE_CLOVEK = 1.7f; // hug

    public static final int BLOCK_WIDTH = 10; //real block width in px, this value = 1 hug
    public static final int BLOCK_HEIGHT = 10; //real block height in px
    public static final int CHUNK_SIZE = 36; //blocks -> must be square number n^2
    public static final int CHUNK_LEN = (int) Math.round(Math.sqrt(CHUNK_SIZE));

    public static final int CONTROL_UP = Input.KEY_W;
    public static final int CONTROL_DOWN = Input.KEY_S;
    public static final int CONTROL_LEFT = Input.KEY_A;
    public static final int CONTROL_RIGHT = Input.KEY_D;
    public static final int CONTROL_USE = Input.KEY_E;

    public static final String ASSETS_PATH = "/assets/";
    public static final String CESTA = "path.jpg";
    public static final String ZEM = "grass1.png";

    public static final Color GROUND_COLOR = Color.white;
    public static final Color PATH_COLOR = Color.black;

    public static void print(Object... o)
    {
        String res = "";
        int i = 0;
        for(Object ob : o)
            res += ob.toString()+(i++ < o.length ? " " : "");

        System.out.println(res);
    }
}
