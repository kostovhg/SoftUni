using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_DecodeRadioFrequencies
{
    class DecodeRadioFrequencies
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                .ToArray();
            List<char> output = new List<char>();
            for (int i = 0; i < input.Length; i++)
            {
                if (int.Parse(input[i].ToString().Split('.')[0]) > 0)
                {
                    output.Insert(i, (char)int.Parse(input[i].ToString().Split('.')[0]));
                }
            }
            for (int i = input.Length - 1; i >= 0; i--)
            {
                if (int.Parse(input[i].ToString().Split('.')[1]) > 0)
                {
                    output.Add((char)((char)int.Parse(input[i].ToString().Split('.')[1])));
                }
            }
            Console.WriteLine(string.Join("", output));
        }
    }
}
