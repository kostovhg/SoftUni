using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_ArrayHistogram
{
    class ArrayHistogram
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split();
            Dictionary<string, int> count = new Dictionary<string, int>();
            foreach (string word in input)
            {
                if (count.ContainsKey(word))
                {
                    count[word]++;
                }
                else
                {
                    count.Add(word, 1);
                }
            }
            foreach (var pair in count.OrderByDescending(x => x.Value))
            {
                Console.WriteLine("{0} -> {1} times ({2:F2}%)",
                    pair.Key, pair.Value, ((double)pair.Value / input.Length) * 100);
            }
        }
    }
}
