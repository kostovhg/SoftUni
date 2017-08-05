using System;
using System.Collections.Generic;
using System.Linq;

namespace p07_Pyramidic
{
    class Pyramidic
    {
        static void Main(string[] args)
        {
            int number = int.Parse(Console.ReadLine());
            List<string> lines = new List<string>();
            for (int i = 0; i < number; i++)
            {
                lines.Add(Console.ReadLine());
            }
            int pSize = 1;
            char theChar = '\0';
            for (int l = 0; l < lines.Count() - 1; l++)
            {
                foreach (char ch in lines[l].Distinct())
                {
                    int rows = 1;
                    for (int nextLine = l + 1; nextLine < lines.Count(); nextLine++)
                    {
                        if (!lines[nextLine].Contains(new string(ch, rows * 2 + 1))) break;
                        else rows++;
                    }
                    if (rows > pSize)
                    {
                        pSize = rows;
                        theChar = ch;
                    }
                }
            }

            for (int i = 1; i <= pSize; i++)
            {
                Console.WriteLine(new string(theChar, i*2 - 1));
            }
        }
    }
}
