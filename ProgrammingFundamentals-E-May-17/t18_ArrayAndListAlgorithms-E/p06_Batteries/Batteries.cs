 using System;
using System.Linq;

namespace p06_Batteries
{
    class Batteries
    {
        static void Main(string[] args)
        {
            double[] bateryCapacities = Console.ReadLine().Split().Select(double.Parse).ToArray();
            double[] usagePerHour = Console.ReadLine().Split().Select(double.Parse).ToArray();
            int hours = int.Parse(Console.ReadLine());
            for (int i = 0; i < usagePerHour.Length; i++)
            {
                double left = bateryCapacities[i] - (usagePerHour[i] * hours);
                if (left > 0)
                {
                    Console.WriteLine("Battery {0}: {1:F2} mAh ({2:F2})%",
                    i + 1, left, left * 100 / bateryCapacities[i]);
                }
                else
                {
                    Console.WriteLine("Battery {0}: dead (lasted {1} hours)",
                        i + 1, Math.Ceiling(bateryCapacities[i] / usagePerHour[i]));
                }
            }
        }
    }
}
