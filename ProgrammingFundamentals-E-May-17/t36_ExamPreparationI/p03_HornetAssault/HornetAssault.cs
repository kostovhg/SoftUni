using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_HornetAssault
{
    class HornetAssault
    {
        static void Main(string[] args)
        {
            List<ulong> beehives = Console.ReadLine()
                .Split().Select(x => ulong.Parse(x)).ToList();
            List<ulong> hornets = Console.ReadLine()
                .Split().Select(x => ulong.Parse(x)).ToList();
            for (int i = 0; i < beehives.Count; i++)
            {
                ulong power = hornets.Aggregate((a, b) => a + b);
                if (beehives[i] < power) beehives[i] = 0;
                else
                {
                    beehives[i] -= power;
                    hornets.RemoveAt(0);
                }
                if (hornets.Count() == 0) break;
            }
            if (!beehives.Any(x => x > 0)) Console.WriteLine(string.Join(" ", hornets));
            else Console.WriteLine(string.Join(" ", beehives.Where(x => x > 0)));
        }
    }
}
