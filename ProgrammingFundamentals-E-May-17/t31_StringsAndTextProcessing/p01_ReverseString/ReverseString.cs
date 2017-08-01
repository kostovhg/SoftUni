using System;
using System.Linq;

namespace p01_ReverseString
{
    class ReverseString
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            char[] output = new char[input.Length];
            for (int i = 0; i < input.Length; i++)
            {
                output[input.Length - 1 - i] = input[i];
            }
            Console.WriteLine(output);
        }
    }
}
