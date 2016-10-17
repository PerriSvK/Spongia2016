package sk.perri.spognia;

import org.newdawn.slick.*;
import sk.perri.spognia.utils.Constants;
import sk.perri.spognia.veci.Level;

public class Main extends BasicGame
{
    private boolean paused = false;
    private long ttime = 0;
    private Level level;

    public Main(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        level = new Level("mapa");
        level.init();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        ttime += delta;
        if(paused) return;

        level.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.setBackground(Color.cyan);
        level.render(container, g);
        g.setColor(Color.white);
        g.drawString(Float.toString(ttime/(float)1000), 90, 10);
    }

    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Main("Spongia"));

        app.setDisplayMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, false);
        app.setVSync(true);
        app.setMaximumLogicUpdateInterval(60);

        app.start();
    }
}
