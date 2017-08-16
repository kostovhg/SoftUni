using System;
using System.Linq;

namespace p03_EnduranceRally
{
    class EnduranceRally
    {
        static void Main(string[] args)
        {
            string[] drivers = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
            decimal[] zones = Console.ReadLine().
                Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                .Select(decimal.Parse).ToArray();
            long[] cps = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                .Select(long.Parse).ToArray();

            foreach (string driver in drivers)
            {
                decimal fuel = (decimal)driver[0]; 
                int zone = 0;
                while (fuel > 0 && zone < zones.Length)
                {
                    if (cps.Contains(zone))
                    {
                        fuel = fuel + zones[zone];
                    }
                    else
                    {
                        fuel = fuel - zones[zone];
                    }
                    if (fuel > 0)
                    {
                        zone++;
                    }
                    else
                    {
                        Console.WriteLine($"{driver} - reached {zone}");
                        break;
                    }
                }
                if (fuel > 0)
                {
                    Console.WriteLine($"{driver} - fuel left {fuel:F}");
                }
            }
        }
    }
}
