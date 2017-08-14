using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_DistinctList
{
    class DistinctList
    {
        static void Main(string[] args)
        {
            List<int> input = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            Console.WriteLine(string.Join(" ", input.Distinct()));
        }
    }
}
