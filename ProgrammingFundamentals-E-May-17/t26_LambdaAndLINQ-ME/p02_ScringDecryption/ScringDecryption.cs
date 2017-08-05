using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_ScringDecryption
{
    class ScringDecryption
    {
        static void Main(string[] args)
        {
            int[] skipTake = Console.ReadLine().Split().Select(int.Parse).ToArray();
            List<char> input = Console.ReadLine()
                .Split()
                .Select(int.Parse)
                .Where(x => x >= 65 && x <= 90)
                .Skip(skipTake[0])
                .Take(skipTake[1])
                .Select(x => (char)x)
                .ToList();
            Console.WriteLine(string.Join("", input));
            
        }
    }
}
