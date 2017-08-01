using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_Palindromes
{
    class Palindromes
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine()
                .Split(new char[] { ' ', ',', '.', '?', '!'},
                StringSplitOptions.RemoveEmptyEntries);
            List<string> output = new List<string>();
            foreach (string w in input)
            {
                if(IsPalindrome(w))
                {
                    output.Add(w);
                }
            }
            Console.WriteLine("{0}", string.Join(", ", 
                output.OrderBy(x => x).Distinct().ToList()));
        }

        private static bool IsPalindrome(string w)
        {
            for (int i = 0, j = w.Length - 1; i < w.Length / 2; i++ , j--)
            {
                if (w[j] != w[i])
                {
                    return false;
                }
            }
            return true;
        }
    }
}
