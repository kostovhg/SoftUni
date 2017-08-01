using System;

namespace p02_Placeholders
{
    class Placeholders
    {
        static void Main(string[] args)
        {
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                string[] line = input
                    .Split(new string[] { " -> " },
                    StringSplitOptions.RemoveEmptyEntries);
                string[] tokens = line[1].Split(new string[] { ", " },
                    StringSplitOptions.RemoveEmptyEntries);
                for (int i = 0; i < tokens.Length; ++i)
                {
                    string par = tokens[i];
                    string pos = "{" + i + "}";

                    line[0] = line[0].Replace(pos, par);
                }
                Console.WriteLine(line[0]);
            }
        }
    }
}
