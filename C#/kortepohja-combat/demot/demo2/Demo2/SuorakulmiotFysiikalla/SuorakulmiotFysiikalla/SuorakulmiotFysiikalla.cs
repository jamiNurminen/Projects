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
        // Kirjoita ohjelmakoodisi tähän
        double r = 50;
        Level.Background.Color = Color.Black;
        

        

        for (int i = 0; i < 30; i++)
        {

            int x = 0;
            int y = 0;
            int koko = 0;
            x = RandomGen.NextInt(-200, 600);
            y = RandomGen.NextInt(20, 600);
            koko = RandomGen.NextInt(5, 30);
            PiirraPallo(this,x,y,koko );
        }

        PhysicsObject pelaaja = PiirraPallo(this, 0, 800, 40);
        pelaaja.Color = Color.White;
        Keyboard.Listen(Key.Up, ButtonState.Pressed, LyoPalloa, pelaaja, new Vector(0, 200 ));
        Keyboard.Listen(Key.Right, ButtonState.Pressed, LyoPalloa, pelaaja, new Vector(200, 0 ));
        Keyboard.Listen(Key.Left, ButtonState.Pressed, LyoPalloa, pelaaja, new Vector(-200, 0));
        Keyboard.Listen(Key.Down, ButtonState.Pressed, LyoPalloa, pelaaja, new Vector(0, -200));

        Level.CreateBorders();
        Gravity = new Vector(0, -200);
        PhoneBackButton.Listen(ConfirmExit, "Lopeta peli");
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, ConfirmExit, "Lopeta peli");
    }

    public static PhysicsObject PiirraPallo(PhysicsGame peli, double x, double y, double r)
    {
        PhysicsObject pallo = new PhysicsObject(r, r, Shape.Circle);
        pallo.X = x; pallo.Y = y;
        pallo.Color = RandomGen.NextColor();
        peli.Add(pallo);
        return pallo;
       
    }

    public static void LyoPalloa (PhysicsObject kolmio, Vector suunta)
    {
        kolmio.Hit(suunta);
    }


    


}

