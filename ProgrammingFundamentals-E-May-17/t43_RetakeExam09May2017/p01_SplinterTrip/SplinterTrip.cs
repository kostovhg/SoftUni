using System;

namespace p01_SplinterTrip
{
    class SplinterTrip
    {
        static void Main(string[] args)
        {
            double dist = double.Parse(Console.ReadLine());
            double tank = double.Parse(Console.ReadLine());
            double windy = double.Parse(Console.ReadLine());
            double fuel = ((dist - windy) * 25 + (windy) * (25 * 1.5)) * 1.05;
            Console.WriteLine($"Fuel needed: {fuel:F}L");
            if (fuel <= tank)
            {
                Console.WriteLine($"Enough with {(tank - fuel):F}L to spare!");
            }
            else
            {
                Console.WriteLine($"We need {(fuel - tank):F}L more fuel.");
            }

        }
    }
}
