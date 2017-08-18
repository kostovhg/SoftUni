using System;


namespace p09_TrangleFormations
{
    class TrangleFormations
    {
        static void Main(string[] args)
        {
            int sideA = int.Parse(Console.ReadLine());
            int sideB = int.Parse(Console.ReadLine());
            int sideC = int.Parse(Console.ReadLine());

            if (sideA + sideB > sideC && sideB + sideC > sideA && sideC + sideA > sideB)
            {
                Console.WriteLine("Triangle is valid.");
            }
            else
            {
                Console.WriteLine("Invalid Triangle.");
                return;
            }

            if (sideA * sideA + sideB * sideB == sideC * sideC)
            {
                Console.WriteLine("Triangle has a right angle between sides a and b");
            }
            else if (sideB * sideB + sideC * sideC == sideA * sideA)
            {
                Console.WriteLine("Triangle has a right angle between sides b and c");
            }
            else if (sideC * sideC + sideA * sideA == sideB * sideB)
            {
                Console.WriteLine("Triangle has a right angle between sides a and c");
            }
            else
            {
                Console.WriteLine("Triangle has no right angles");
            }

        }
    }
}
