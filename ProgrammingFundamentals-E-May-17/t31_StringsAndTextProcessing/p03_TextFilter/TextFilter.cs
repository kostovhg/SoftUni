using System;
using System.Collections.Generic;

namespace p03_TextFilter
{
    class TextFilter
    {
        static void Main(string[] args)
        {
            string[] banned = Console.ReadLine()
                .Split(new string[] { ", " }, 
                StringSplitOptions.RemoveEmptyEntries);
            string text = Console.ReadLine();
            foreach (string ban in banned)
            {
                text = text.Replace(ban,
                    new string('*', ban.Length));
            }
            Console.WriteLine(text);
        }
    }
}
