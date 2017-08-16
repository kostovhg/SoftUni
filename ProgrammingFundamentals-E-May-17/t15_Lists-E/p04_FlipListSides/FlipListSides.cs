using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_FlipListSides
{
    class FlipListSides
    {
        static void Main(string[] args)
        {
            List<int> list = Console.ReadLine().Split().Select(int.Parse).ToList();
            for (int i = 1; i < list.Count / 2; i++)
            {
                int temp = list[i];
                list[i] = list[list.Count - i - 1];
                list[list.Count - i - 1] = temp;
            }
            Console.WriteLine(string.Join(" ", list));
        }
    }
}
