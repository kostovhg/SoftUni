using System;
using System.Collections.Generic;

namespace p01_RemoveElementsAtOddPositions
{
    class RemoveElementsAtOddPositions
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split();
            List<string> output = new List<string>();
            for (int i = 1; i < input.Length; i += 2)
            {
                output.Add(input[i]);
            }
            Console.WriteLine(string.Join("", output));
        }
    }
}
