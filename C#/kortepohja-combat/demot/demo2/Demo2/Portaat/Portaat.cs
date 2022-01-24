using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Widgets;

public class Portaat : PhysicsGame
{
    /// <summary>
    /// Ohjelma piirtää viisi neliötä (sivun pituus 80) siten, että
    /// ensimmäisen neliön keskipiste on origossa, ja viimeisen
    /// neliön keskipiste pisteeessä (320, 320).
    /// </summary>


    public override void Begin()
    {
        Level.Background.Color = Color.Black;
        Camera.ZoomToLevel(0);     // Seuraavia voi kutsua myös
        PiirraNelio(this, 0, 0);  // PiirraNelio(peli:this,x:0,y:0);
        PiirraNelio(this, 80, 80);// PiirraNelio(peli:this,x:80,y:80);
        PiirraNelio(this, 160, 160);
        PiirraNelio(this, 240, 240);
        PiirraNelio(this, 320, 320);
       
    }

    


    /// <summary>
    /// Aliohjelma piirtää ruutuun yhden neliön, jonka
    /// sivun pituus on 80, ja keskipiste on (x, y).
    /// </summary>
    /// <param name="peli">Peli, johon neliö piirretään</param>
    /// <param name="x">Neliön keskipisteen x-koordinaatti.</param>
    /// <param name="y">Neliön keskipisteen y-koordinaatti.</param>
    public static void PiirraNelio(PhysicsGame peli,
                                   double x, double y)
    {
        GameObject nelio = new GameObject(80, 80, Shape.Rectangle);
        nelio.X = x; nelio.Y = y;
        peli.Add(nelio);
    }
}

