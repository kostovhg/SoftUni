using System;
using System.Linq;

namespace p03_Wormhole
{
    class Wormhole
    {
        static void Main(string[] args)
        {
            int[] field = Console.ReadLine().Split().Select(int.Parse).ToArray();
            int steps = 0;
            int i = 0;
            while (i != field.Length)
            {
                if (field[i] == 0)
                {
                    i++;
                    steps++;
                }
                else
                {
                    int tmp = field[i];
                    field[i] = 0;
                    i = tmp;
                }
            }
            Console.WriteLine(steps);
        }
    }
}
