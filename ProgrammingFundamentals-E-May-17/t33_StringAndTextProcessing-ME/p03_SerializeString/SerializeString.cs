using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_SerializeString
{
    class SerializeString
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            foreach (char ch in input.Distinct())
            {
                List<int> indexes = new List<int>();
                for (int i = input.IndexOf(ch); i > -1; i = input.IndexOf(ch, i + 1))
                {
                    indexes.Add(i);
                }
                Console.WriteLine("{0}:{1}", ch, string.Join("/", indexes));
            }
        }
    }
}
