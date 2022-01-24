using System;
using System.Text;
using System.Linq;
using System.Collections.Generic;

/// @author Omanimi
/// @version Päivämäärä
/// <summary>
/// 
/// </summary>
public class Demo4
{
    /// <summary>
    /// 
    /// </summary>
    public static void Main()
    {
        Console.Write("Anna 1. luku ");
        int luku1 = int.Parse(Console.ReadLine());
        Console.Write("Anna 2. luku ");
        int luku2 = int.Parse(Console.ReadLine());
        Console.Write("Anna 3. luku ");
        int luku3 = int.Parse(Console.ReadLine());
        int isoin = Suurin(luku1, luku2, luku3);
        int pienin = Pienin(luku1, luku2, luku3);
        Console.WriteLine($"Pienin luku on: {pienin} ");
        Console.WriteLine($"Suurin luku on: {isoin} " );

    }

    public static int Suurin(int eka, int toka, int kolmas)
    {
        int suurin = eka;
        if (toka > suurin) suurin = toka;
        if (kolmas > suurin) suurin = kolmas;
        return suurin;
    }

    public static int Pienin(int eka, int toka, int kolmas)
    {
        int pienin = eka;
        if (toka < pienin) pienin = toka;
        if (kolmas < pienin) pienin = kolmas;
        return pienin;
    }

}
