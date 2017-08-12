using System;
using System.Collections.Generic;

namespace p01_LetterRepetition
{
    class LetterRepetition
    {
        static void Main(string[] args)
        {
            string inputString = Console.ReadLine();
            Dictionary<char, uint> output = new Dictionary<char, uint>();
            foreach (char ch in inputString)
            {
                if (!output.ContainsKey(ch))
                {
                    output.Add(ch, 0);
                }
                output[ch]++;
            }
            foreach (char ch in output.Keys)
            {
                Console.WriteLine("{0} -> {1}", ch, output[ch]);
            }
        }
    }
}
