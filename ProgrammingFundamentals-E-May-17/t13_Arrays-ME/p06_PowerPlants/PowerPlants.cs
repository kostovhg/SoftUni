using System;
using System.Linq;

namespace p06_PowerPlants
{
    class PowerPlants
    {
        static void Main(string[] args)
        {
            uint[] plants = Array.ConvertAll(Console.ReadLine().Split(), uint.Parse);
            int arrLen = plants.Length;
            int days = 0;
            // int seasons = 0;
            while(plants.Any(plant => plant > 0))
            {
                days++;
                int index = (days - 1) % arrLen;
                for (int i = 0;  i < arrLen; i++)
                {
                    if (plants[i] > 0 && i != index)
                    {
                        plants[i]--;
                    }
                }
                if(index == arrLen - 1)
                {
                    //plants.Where(p => p > 0).Select(p => p++);
                    for (int p = 0; p < arrLen; p++)
                    {
                        if (plants[p] > 0) plants[p]++;
                    }
                }

            }

            Console.WriteLine("survived {0} days ({1} seasons)", days, (days - 1) / arrLen );
        }
    }
}
