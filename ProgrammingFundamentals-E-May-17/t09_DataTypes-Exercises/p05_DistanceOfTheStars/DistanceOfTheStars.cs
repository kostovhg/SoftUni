using System;

namespace p05_DistanceOfTheStars
{
    class DistanceOfTheStars
    {
        static void Main(string[] args)
        {
            // Distances in light years
            double dProximaCentauri = 4.22;
            short shCenterOfGalaxy = 26000;
            int iGalaxyDiameter = 100000;
            long lToTheEdge = 46500000000;
            decimal ratio = 9450000000000;
            Console.WriteLine($"{ ratio * (decimal)dProximaCentauri:e2}");
            Console.WriteLine($"{ ratio * (decimal)shCenterOfGalaxy:e2}");
            Console.WriteLine($"{ ratio * (decimal)iGalaxyDiameter:e2}");
            Console.WriteLine($"{ ratio * (decimal)lToTheEdge:e2}");

        }
    }
}
