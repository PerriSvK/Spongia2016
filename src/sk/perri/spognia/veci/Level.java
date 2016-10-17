package sk.perri.spognia.veci;

import org.newdawn.slick.*;
import sk.perri.spognia.utils.Camera;
import sk.perri.spognia.utils.Constants;
import sk.perri.spognia.utils.Mapa;
import sk.perri.spognia.utils.Point;

public class Level
{
    private Image mapTexture;
    private Clovek ja;
    private String name;
    private Mapa mapa;
    private Camera camera;
    private Point trans = new Point(0, 0);
    
    public Level(String name)
    {
        this.name = name;
    }
    
    public void init()
    {
        try 
        {
            mapTexture = new Image("/assets/"+name+".png");
        } 
        catch (SlickException e) 
        {
            System.err.println("Map image load falied name: "+name+" error: "+e.toString());
        }
        
        ja = new Clovek(100, 100, false, "/assets/PAN.png");
        ja.setVisible(true);
        mapa = new Mapa(mapTexture);
        mapa.loadMap();
        camera = new Camera();
    }
    
    public void update(GameContainer container, int delta)
    {
        Input inp = container.getInput();
        int smer = (inp.isKeyDown(Constants.CONTROL_UP) ? 1 : 0) + (inp.isKeyDown(Constants.CONTROL_DOWN) ? -1 : 0) +
                (inp.isKeyDown(Constants.CONTROL_RIGHT) ? 3 : 0 ) + (inp.isKeyDown(Constants.CONTROL_LEFT) ? -3 : 0);

        if(smer != 0)
        {
            float spee = delta*Constants.BLOCK_WIDTH*Constants.CLOVEK_SPEED/1000;
            switch(smer)
            {
                /*case 1: ja.move(Clovek.SMER_HORE, delta); break;
                case -1: ja.move(Clovek.SMER_DOLE, delta); break;
                case 3: ja.move(Clovek.SMER_VPRAVO, delta); break;
                case -3: ja.move(Clovek.SMER_VLAVO, delta); break;

                case 4: ja.move(315, delta); break;
                case -4: ja.move(135, delta); break;
                case 2: ja.move(45, delta); break;
                case -2: ja.move(225, delta); break;*/

                case 1: camera.moveCamera(Clovek.SMER_HORE, spee); mapa.load(Clovek.SMER_HORE, camera); break;
                case -1: camera.moveCamera(Clovek.SMER_DOLE, spee); mapa.load(Clovek.SMER_DOLE, camera); break;
                case 3: camera.moveCamera(Clovek.SMER_VPRAVO, spee); mapa.load(Clovek.SMER_VPRAVO, camera); break;
                case -3: camera.moveCamera(Clovek.SMER_VLAVO, spee); mapa.load(Clovek.SMER_VLAVO, camera); break;
            }

            //trans.add(ja.getDelta());
        }
    }
    
    public void render(GameContainer container, Graphics g)
    {
        //mapTexture.draw();
        //g.translate(trans.getX(), trans.getY());
        mapa.render(g, camera.getOffSet());
        ja.render();
    }
        
}
