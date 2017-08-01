using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_DeserializeString
{
    class DeserializeString
    {
        static void Main(string[] args)
        {
            string input;
            Dictionary<char, int[]> output = new Dictionary<char, int[]>();
            while (!"end".Equals(input = Console.ReadLine()))
            {
                char ch = input[0];
                int[] indexes = input.Substring(2, input.Length - 2)
                    .Split(new char[] { '/' }, StringSplitOptions.RemoveEmptyEntries)
                    .Select(int.Parse).ToArray();
                output.Add(ch, indexes);
            }
            char[] result = new char[(output.Max(x => x.Value.Max()) + 1)];
            foreach (char ch in output.Keys)
            {
                foreach (int index in output[ch])
                {
                    result[index] = ch;
                }
            }
            Console.WriteLine(new string(result));
        }
    }
}
