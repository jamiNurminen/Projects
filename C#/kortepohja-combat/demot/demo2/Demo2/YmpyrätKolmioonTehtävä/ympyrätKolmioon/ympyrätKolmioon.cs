using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Widgets;

public class ympyrätKolmioon : PhysicsGame
{

    
    public override void Begin()
    {
        /// <summary>
        /// Aliohjelmassa piirretään ja zoomataan kamera siten että
        /// kenttä näkyy hyvin ruudulla.
        /// </summary>
        

        
        double r = 50;
        Camera.ZoomToLevel();
        Level.Background.Color = Color.Black;
        Piirrapallo(this, r*1, r*1, r);
        Piirrapallo(this, r*2, r*1, r);
        Piirrapallo(this, r*3, r*1 ,r);
        Piirrapallo(this, r*1.5, r*1.9 ,r);
        Piirrapallo(this, r*2.5, r*1.9, r);
        Piirrapallo(this, r*2, r*2.8 ,r);



        PhoneBackButton.Listen(ConfirmExit, "Lopeta peli");
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, ConfirmExit, "Lopeta peli");
    }
    public static void Piirrapallo(PhysicsGame peli, double x, double y, double r)
    {
    
        PhysicsObject p1 = new PhysicsObject(r, r, Shape.Circle);
        p1.X = x; p1.Y = y;
        peli.Add(p1);
    }
}

