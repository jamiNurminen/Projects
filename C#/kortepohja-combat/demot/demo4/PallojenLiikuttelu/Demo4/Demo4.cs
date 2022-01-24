using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Widgets;

public class SuorakulmiotFysiikalla : PhysicsGame
{



    public override void Begin()
    {
        
        Level.Background.Color = Color.DarkJungleGreen;

        BoundingRectangle ylaosa = new BoundingRectangle(Level.BoundingRect.TopLeft, new Vector(Level.Right, 0));
        
        
        for (int i = 0; i < 30; i++)
        {

            int x = 0;
            int y = 0;
            int koko = 0;
            x = RandomGen.NextInt(-200, 600);
            y = RandomGen.NextInt(-600, 600);
            koko = RandomGen.NextInt(15, 35);
            PiirraPallo(this, x, y, koko, "vihu");
        }

        PhysicsObject pelaaja = PiirraPallo(this, 0, 0, 40, "pelaaja");
        pelaaja.Color = Color.White;
        PhysicsObject kuula = PiirraPallo(this, 50, 0, 50, "kuula");
        kuula.Color = Color.Black;
        AddCollisionHandler(kuula, "vihu", KuulaTormasi);
        AddCollisionHandler(pelaaja, "vihu", PelaajaTormasi);

        Keyboard.Listen(Key.Escape, ButtonState.Pressed, Exit, "Poistu");
        Keyboard.Listen(Key.F1, ButtonState.Pressed,
        ShowControlHelp, "Näytä näppäinohjeet");
        Keyboard.Listen(Key.Up, ButtonState.Down, LyoPalloa, "Lyö keskipalloa ylöspäin", pelaaja, new Vector(0, 20));
        Keyboard.Listen(Key.Right, ButtonState.Down, LyoPalloa, "Lyö keskipalloa oikealle", pelaaja, new Vector(20, 0));
        Keyboard.Listen(Key.Left, ButtonState.Down, LyoPalloa, "Lyö keskipalloa vasemmalle", pelaaja, new Vector(-20, 0));
        Keyboard.Listen(Key.Down, ButtonState.Down, LyoPalloa, "Lyö keskipalloa alas", pelaaja, new Vector(0, -20));

        Level.CreateBorders();
        Gravity = new Vector(0, 0);
        PhoneBackButton.Listen(ConfirmExit, "Lopeta peli");
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, ConfirmExit, "Lopeta peli");
    }

    public static PhysicsObject PiirraPallo(PhysicsGame peli, double x, double y, double r, string tunniste)
    {
        PhysicsObject pallo = new PhysicsObject(r, r, Shape.Circle);
        pallo.X = x; pallo.Y = y;
        pallo.Color = Color.Red;
        peli.Add(pallo);
        pallo.Tag = tunniste;
        int suuntaX = RandomGen.NextInt(-40, 40);
        int suuntaY = RandomGen.NextInt(-40, 40);
        pallo.Hit(new Vector(suuntaX, suuntaY));
        
        return pallo;
        

    }

    public static void LyoPalloa(PhysicsObject kolmio, Vector suunta)
    {
        kolmio.Hit(suunta);
    }

    private void KuulaTormasi(PhysicsObject kuula, PhysicsObject vihu)
    {
        
        double vihuPaikka = vihu.Y;


        //if (vihuPaikka > 0.0)
        //{
            Explosion rajahdys = new Explosion(vihu.Width * 2);
            rajahdys.Position = vihu.Position;
            rajahdys.UseShockWave = false;
            this.Add(rajahdys);
            Remove(vihu);
        //}
    }

    private void PelaajaTormasi(PhysicsObject pelaaja, PhysicsObject vihu)
    {
        Explosion rajahdys = new Explosion(pelaaja.Width * 2);
        rajahdys.Position = pelaaja.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        Remove(pelaaja);
        Timer t = new Timer();
        t.Interval = 1.0;
        t.Timeout += PelistaUlos;
        t.Start();

    }

    public static void PelistaUlos()
    {
        
    }





}
