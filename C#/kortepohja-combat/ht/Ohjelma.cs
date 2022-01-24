using Jypeli;
using Jypeli.Assets;

/// <summary>
/// @author Jami Petteri Nurminen
/// @version 8.12.2020
/// Pelissä kaksi pelaajaa pelaa toisiaan vastaan.
/// Ensimmäisessä vaiheessa pelaajat yrittävät osui omilla kuulilla mahdollisimman moneen sieneen.
/// Toisessa vaiheessa pelaajat yrittävät osua kuulillaan vastustajan pelaajaan.
/// </summary>
public class KortepohjaCombat : PhysicsGame
{
    private IntMeter pisteLaskuri1;
    private IntMeter pisteLaskuri2;
    
    private int pelaajan1Osumat = 0;
    private int pelaajan2Osumat = 0;
    private int sieni = 0;
    private double pisteet1 = 0;
    private double pisteet2 = 0;
    private PhysicsObject pelaaja;
    private PhysicsObject kuula;
    private PhysicsObject pelaaja2;
    private PhysicsObject kuula2;
    private int stage = 1;

    private GameObject[] p1Elama;
    private GameObject[] p2Elama;

    public override void Begin()
    {
        SeuraavaKentta();
        SeuraavaKentta();
    }


    /// <summary>
    /// Aloittaa pelin ensimmäisen kentän
    /// </summary>
    private void Kentta1()
    {
        IsPaused = true;

        LuoPistelaskuri1();
        LuoPistelaskuri2();

        MultiSelectWindow alkuValikko = new MultiSelectWindow("Pelin alkuvalikko",
        "Aloita peli", "Lopeta");
        Add(alkuValikko);

        alkuValikko.AddItemHandler(0, Jatka);
        alkuValikko.AddItemHandler(1, Exit);
        Level.Background.Color = Color.Black;
        MediaPlayer.Play("phase1music.wav");

        int sienienLkm = 160;
        for (int i = 0; i < sienienLkm; i++)
        {
            int x = 0;
            int y = 0;
            int koko = 0;
            x = RandomGen.NextInt(-500, 500);
            y = RandomGen.NextInt(-300, 300);
            koko = RandomGen.NextInt(20, 20);
            PhysicsObject sieni = PiirraKuva(this, x, y, koko, "vihu");
            sieni.Image = LoadImage("SuperMarioSieni.png");

        }

        pelaaja = PiirraPallo(this, 450, 0, 40, "pelaaja");
        pelaaja.Color = Color.Red;
        kuula = PiirraPallo(this, 450, -50, 30, "kuula");
        kuula.Color = Color.Red;

        pelaaja2 = PiirraPallo(this, -450, 0, 40, "pelaaja2");
        pelaaja2.Color = Color.Blue;
        kuula2 = PiirraPallo(this, -450, -50, 30, "kuula2");
        kuula2.Color = Color.Blue;

        AddCollisionHandler(kuula, "vihu", KuulaTormasi);
        AddCollisionHandler(kuula2, "vihu", KuulaTormasi2);

        AsetaOhjaimet(pelaaja, Key.Up, Key.Right, Key.Left, Key.Down);
        AsetaOhjaimet(pelaaja2, Key.W, Key.D, Key.A, Key.S);

        Level.CreateBorders();
    }


    /// <summary>
    /// Aloittaa pelin toisen kentän.
    /// </summary>
    private void Kentta2()
    {
        p1Elama = new GameObject[] { Sydan(this, 400, 300, 30, true),
                                     Sydan(this, 350, 300, 30, true),
                                     Sydan(this, 300, 300, 30, true)};

        p2Elama = new GameObject[] { Sydan(this, -400, 300, 30, false),
                                     Sydan(this, -350, 300, 30, false),
                                     Sydan(this, -300, 300, 30, false)};

        AddCollisionHandler(kuula2, pelaaja, Osui2);
        Level.CreateBorders();

        pelaaja = PiirraPallo(this, 450, 0, 40, "pelaaja");
        pelaaja.Color = Color.Red;
        kuula = PiirraPallo(this, 450, -50, 30+pisteet1, "kuula");
        kuula.Color = Color.Red;

        pelaaja2 = PiirraPallo(this, -450, 0, 40, "pelaaja2");
        pelaaja2.Color = Color.Blue;
        kuula2 = PiirraPallo(this, -450, -50, 30+pisteet2, "kuula2");
        kuula2.Color = Color.Blue;

        AsetaOhjaimet(pelaaja, Key.Up, Key.Right, Key.Left, Key.Down);
        AsetaOhjaimet(pelaaja2, Key.W, Key.D, Key.A, Key.S);

        AddCollisionHandler(kuula, pelaaja2, Osui1);
        AddCollisionHandler(kuula2, pelaaja, Osui2);
    }


    /// <summary>
    /// Kentän 2 funktio. Poistaa pelaaja2 elämän ja luo räjähdyksen.
    /// </summary>
    /// <param name="kuula">Mikä kuula osui</param>
    /// <param name="Pelaaja">Mihin pelaajaan osui</param>
    private void Osui1(PhysicsObject kuula, PhysicsObject Pelaaja )
    {

        Remove(p2Elama[pelaajan2Osumat]);
        Explosion rajahdys = new Explosion(30);
        rajahdys.Position = Pelaaja.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        pelaajan2Osumat++;

        if (pelaajan2Osumat == 3) p1Voitto();
    }


    /// <summary>
    /// Kentän 2 funktio. Poistaa pelaaja1 yhden elämän ja luo räjähdyksen.
    /// </summary>
    /// <param name="kuula"> Mikä kuula osui </param>   
    /// <param name="pelaaja"> Mihin pelaajaan osui </param>
    private void Osui2(PhysicsObject kuula, PhysicsObject pelaaja)
    {
        Remove(p1Elama[pelaajan1Osumat]);
        Explosion rajahdys = new Explosion(30);
        rajahdys.Position = pelaaja.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        pelaajan1Osumat++;

        if (pelaajan1Osumat == 3) p2Voitto();
    }


    /// <summary>
    /// Aliohjelma vaihtaa kentän.
    /// </summary>
    private void SeuraavaKentta()
    {
        ClearAll();
        if (stage == 1) Kentta1();
        else Kentta2();
    }


    /// <summary>
    /// Aliohjelma luo ohjaimet pelaajalle
    /// </summary>
    /// <param name="pelaaja"> Kelle pelaajalle</param>
    /// <param name="ylos"> ylös</param>
    /// <param name="oikealle"> oikealle</param>
    /// <param name="vasemmalle"> vasemmalle</param>
    /// <param name="alas"> alas</param>
    private void AsetaOhjaimet(PhysicsObject pelaaja, Key ylos, Key oikealle, Key vasemmalle, Key alas)
    {
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, Exit, "Poistu");
        Keyboard.Listen(Key.F1, ButtonState.Pressed,
        ShowControlHelp, "Näytä näppäinohjeet");
        Keyboard.Listen(ylos, ButtonState.Down, LyoPalloa, "Lyö Pelaaja1 ylöspäin", pelaaja, new Vector(0, 10));
        Keyboard.Listen(oikealle, ButtonState.Down, LyoPalloa, "Lyö Pelaaja1 oikealle", pelaaja, new Vector(10, 0));
        Keyboard.Listen(vasemmalle, ButtonState.Down, LyoPalloa, "Lyö Pelaaja1 vasemmalle", pelaaja, new Vector(-10, 0));
        Keyboard.Listen(alas, ButtonState.Down, LyoPalloa, "Lyö Pelaaja1 alas", pelaaja, new Vector(0, -10));
        
    }


    /// <summary>
    /// Aliohjelma luo pelaaja 1 pistelaskurin.
    /// </summary>
    private void LuoPistelaskuri1()
    {
        pisteLaskuri1 = new IntMeter(0);

        Label pisteNaytto = new Label();
        pisteNaytto.X = Screen.Left + 100;
        pisteNaytto.Y = Screen.Top - 100;
        pisteNaytto.TextColor = Color.Black;
        pisteNaytto.Color = Color.Blue;

        pisteNaytto.BindTo(pisteLaskuri1);
        Add(pisteNaytto);
    }


    /// <summary>
    /// Tapahtumasarja joka käynnistyy pelaaja1 voittaessa pelin.
    /// </summary>
    private void p1Voitto()
    {
        pisteet1 = 0;
        pisteet2 = 0;
        sieni = 0;
        stage = 1;
        pelaajan1Osumat = 0;
        pelaajan2Osumat = 0;
        Explosion rajahdys = new Explosion(30);
        rajahdys.Position = pelaaja.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        Explosion rajahdys2 = new Explosion(30);
        rajahdys.Position = kuula.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        Remove(pelaaja2);
        Remove(kuula2);

        Timer.SingleShot(2.0, Lopetus2);
    }


    /// <summary>
    /// Tapahtumasarja joka käynnistyy pelaaja2 voittaessa pelin.
    /// </summary>
    private void p2Voitto()
    {
        pisteet1 = 0;
        pisteet2 = 0;
        sieni = 0;
        stage = 1;
        pelaajan1Osumat = 0;
        pelaajan2Osumat = 0;
        Explosion rajahdys = new Explosion(30);
        rajahdys.Position = pelaaja2.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        Explosion rajahdys2 = new Explosion(30);
        rajahdys.Position = kuula2.Position;
        rajahdys.UseShockWave = false;
        this.Add(rajahdys);
        Remove(pelaaja);
        Remove(kuula);

        Timer.SingleShot(2.0, Lopetus);
    }


    /// <summary>
    /// Aliohjelma lopettaa pelin ja julistaa pelaaja 1 voittajaksi.
    /// </summary>
    private void Lopetus()
    {
        ClearAll();
        MultiSelectWindow alkuValikko = new MultiSelectWindow("Pelaaja 1 voitti!",
        "Uusi peli", "Lopeta");
        Add(alkuValikko);

        alkuValikko.AddItemHandler(0, SeuraavaKentta);
        alkuValikko.AddItemHandler(1, Exit);
        Level.Background.Color = Color.Black;
    }


    /// <summary>
    /// Aliohjelma lopettaa pelin ja julistaa pelaaja 2 voittajaksi. 
    /// </summary>
    private void Lopetus2()
    {
        ClearAll();
        MultiSelectWindow alkuValikko = new MultiSelectWindow("Pelaaja 2 voitti!",
        "Uusi peli", "Lopeta");
        Add(alkuValikko);

        alkuValikko.AddItemHandler(0, SeuraavaKentta);
        alkuValikko.AddItemHandler(1, Exit);
        Level.Background.Color = Color.Black;
    }


    /// <summary>
    /// Aliohjelma luo pelaaja2 pistelaskurin
    /// </summary>
    private void LuoPistelaskuri2()
    {
        pisteLaskuri2 = new IntMeter(0);

        Label pisteNaytto = new Label();
        pisteNaytto.X = Screen.Right - 100;
        pisteNaytto.Y = Screen.Top - 100;
        pisteNaytto.TextColor = Color.Black;
        pisteNaytto.Color = Color.Red;

        pisteNaytto.BindTo(pisteLaskuri2);
        Add(pisteNaytto);
    }


    /// <summary>
    /// Ohjelma piirtää uuden pallon peliin
    /// </summary>
    /// <param name="peli">Peli johon pallo luodaan</param>
    /// <param name="x"> Pallon x koordinaatti</param>
    /// <param name="y">Pallon y koordinaatti</param>
    /// <param name="r"> palon koko</param>
    /// <param name="tunniste">Tunniste mikä pallolle annetaan</param>
    /// <returns>Palauttaa pallon</returns>
    private static PhysicsObject PiirraPallo(PhysicsGame peli, double x, double y, double r, string tunniste)
    {
        PhysicsObject pallo = new PhysicsObject(r, r, Shape.Circle);
        pallo.X = x; pallo.Y = y;
        pallo.Color = Color.Red;
        peli.Add(pallo);
        pallo.Tag = tunniste;
        int suuntaX = RandomGen.NextInt(-80, 80);
        int suuntaY = RandomGen.NextInt(-80, 80);
        pallo.Hit(new Vector(suuntaX, suuntaY));

        return pallo;
    }


    /// <summary>
    /// Liikutetaan palloja
    /// </summary>
    /// <param name="pallo"> Määrittelee mitä palloa liikutetaan</param>
    /// <param name="suunta"> Määrittelee mihin suuntaan liikutetaan.</param>
    private static void LyoPalloa(PhysicsObject pallo, Vector suunta)
    {
        pallo.Hit(suunta);
    }


    /// <summary>
    /// Kun kuula osuu vihollispalloihin, vihollispallot räjähtävät
    /// </summary>
    /// <param name="kuula"> Pelin kuula</param>
    /// <param name="vihu"> Vihollispallot</param>
    private void KuulaTormasi(PhysicsObject kuula, PhysicsObject vihu)
    {
        sieni++;
        Remove(vihu);
        kuula.Size = kuula.Size + (vihu.Size / 8);
        MediaPlayer.Play("Pelaaja1");
        pisteLaskuri2.Value += 1;
        pisteet1 = pisteet1 + 2.5;

        if (sieni == 160) Taso1Lopetus();
    }


    /// <summary>
    /// Aliohjelma luo peliin halutun kuvan.
    /// </summary>
    /// <param name="peli">Mihin peliin luodaan.</param>
    /// <param name="x"> Kuvan x-koordinaatti </param>
    /// <param name="y">Kuvan Y-koordinaatti</param>
    /// <param name="koko">Kuvan koko</param>
    /// <param name="tunniste">Kuvan tunniste</param>
    /// <returns>Palauttaa halutun kuvan peliin.</returns>
    private static PhysicsObject PiirraKuva(PhysicsGame peli, double x, double y, double koko, string tunniste)
    {
        PhysicsObject pallo = new PhysicsObject(koko, koko, Shape.Circle);
        pallo.X = x; pallo.Y = y;
        pallo.Color = Color.Red;
        peli.Add(pallo);
        pallo.Tag = tunniste;

        return pallo;
    }


    /// <summary>
    /// Luo peliin sydämen kuvastamaan elämien määrää kentässä 2.
    /// </summary>
    /// <param name="peli">Peli mihin luodaan.</param>
    /// <param name="x">Sydamen x-koordinaatti</param>
    /// <param name="y">Sydamen y-koordinaatti</param>
    /// <param name="koko">Sydämen koko</param>
    /// <param name="kumpi">Sydämen väri riippuen onko kyseessä pelaaja 1 vai pelaaja 2</param>
    /// <returns>Punaisen tai sinisen sydamen</returns>
    private static GameObject Sydan(PhysicsGame peli, double x, double y, double koko, bool kumpi)
    {
        GameObject Sydan = new GameObject(koko, koko, Shape.Circle);
        Sydan.X = x; Sydan.Y = y;
        Sydan.Color = Color.Red;
        peli.Add(Sydan);
        if (kumpi) Sydan.Image = LoadImage("PunainenSydan.png");
        else Sydan.Image = LoadImage("SininenSydan.png");
        return Sydan;
    }


    /// <summary>
    /// Poistaa sienen pelistä ja suurentaa pelaaja 2 kuulaa.
    /// </summary>
    /// <param name="kuula">Mikä kuula osui</param>
    /// <param name="vihu">Mihin sieneen osui</param>
    private void KuulaTormasi2(PhysicsObject kuula, PhysicsObject vihu)
    {
        sieni++;
        Remove(vihu);
        kuula.Size = kuula.Size + (vihu.Size / 8);
        MediaPlayer.Play("Pelaaja2.wav");
        pisteLaskuri1.Value += 1;
        pisteet2 = pisteet2 + 2.5;

        if (sieni == 160) Taso1Lopetus();
    }


    /// <summary>
    /// Kontrolloi valikon taustamusiikkia
    /// </summary>
    private void Jatka()
    {
        IsPaused = false;
    }


    /// <summary>
    /// Lopettaa ensimmäisen tason.
    /// </summary>
    private void Taso1Lopetus()
    {
        ClearAll();
        Label vaihde = new Label("Stage 2");
        Add(vaihde);
        vaihde.TextColor = Color.Wheat;
        Font fontti = new Font(70, true);
        vaihde.Font = fontti;
        
        stage++;
        Timer.SingleShot(3.0, SeuraavaKentta);
    }
}