using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_DiamondProblem
{
    class DiamondProblem
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            List<string> diamonds = new List<string>();
            int start = input.IndexOf('<');
            while (start >= 0)
            {
                int end = input.IndexOf('>', start);
                if (end > 0) diamonds.Add(input.Substring(start + 1, end - start - 1));
                else break;
                start = input.IndexOf('<', end);
            }
            if (diamonds.Count > 0)
            {
                foreach (string diamond in diamonds)
                {
                    Console.WriteLine("Found {0} carat diamond", CalculateCarats(diamond));
                }
            }
            else
            {
                Console.WriteLine("Better luck next time");
            }

        }

        private static int CalculateCarats(string v)
        {
            return v.Where(x => x > '0' && x <= '9')
                .Select(x => x - 48).Sum();
        }
    }
}
