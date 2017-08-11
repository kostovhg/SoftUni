using System;
using System.Collections.Generic;

namespace p01_Wardrobe
{
    class Wardrobe
    {
        static void Main(string[] args)
        {
            uint n = uint.Parse(Console.ReadLine());
            Dictionary<string, Dictionary<string, uint>> clothes = new Dictionary<string, Dictionary<string, uint>>();
            for (uint i = 0; i < n; i++)
            {
                string[] inputLine = Console.ReadLine().Split(new string[] { " -> " }, StringSplitOptions.None);
                string inputColor = inputLine[0];
                string[] input = inputLine[1].Split(new char[] { ',' });

                if (!clothes.ContainsKey(inputColor))
                {
                    clothes.Add(inputColor, new Dictionary<string, uint>());
                }
                for (uint j = 0; j < input.Length; j++)
                {
                    if (!clothes[inputColor].ContainsKey(input[j]))
                    {
                        clothes[inputColor].Add(input[j], 1);
                    }
                    else
                    {
                        clothes[inputColor][input[j]]++;
                    }
                }
            }
            string[] toFind = Console.ReadLine().Split();
            foreach (string color in clothes.Keys)
            {
                Console.WriteLine("{0} clothes:", color);
                foreach (string cloth in clothes[color].Keys)
                {
                    Console.Write("* {0} - {1}", cloth, clothes[color][cloth]);
                    if (color.Equals(toFind[0]) && cloth.Equals(toFind[1]))
                    {
                        Console.WriteLine(" (found!)");
                    }
                    else
                    {
                        Console.WriteLine();
                    }
                }
            }
        }
    }
}
