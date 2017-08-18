using System;

namespace p14_BoatSimulator
{
    class BoatSimulator
    {
        static void Main(string[] args)
        {
            char firstBoat = Console.ReadLine()[0];
            char secondBoat = Console.ReadLine()[0];
            byte n = byte.Parse(Console.ReadLine());
            int firstTiles = 0;
            int secondTiles = 0;

            for (int i = 1; i <= n; i++)
            {
                string input = Console.ReadLine();
                if (input.Equals("UPGRADE"))
                {
                    firstBoat += (char)3;
                    secondBoat += (char)3;
                }
                else if (i % 2 != 0)
                {
                    firstTiles += input.Length;
                }
                else
                {
                    secondTiles += input.Length;
                }
                if (firstTiles >=50 || secondTiles >= 50)
                {
                    break;
                }

            }
            Console.WriteLine(firstTiles > secondTiles ? firstBoat : secondBoat);

        }
    }
}
