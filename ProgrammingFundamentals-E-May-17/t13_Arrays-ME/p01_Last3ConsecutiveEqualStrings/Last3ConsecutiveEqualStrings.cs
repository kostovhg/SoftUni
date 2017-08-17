using System;

namespace p01_Last3ConsecutiveEqualStrings
{
    class Last3ConsecutiveEqualStrings
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split();
            string output = string.Empty;
            for (int i = 2; i < input.Length; i++)
            {
                if (input[i].Equals(input[i-1]) && input[i].Equals(input[i-2]))
                {
                    output = input[i];
                }
            }
            Console.WriteLine("{0} {0} {0}", output);
        }
    }
}
