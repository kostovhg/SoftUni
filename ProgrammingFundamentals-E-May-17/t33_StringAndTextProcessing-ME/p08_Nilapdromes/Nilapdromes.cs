using System;

namespace p08_Nilapdromes
{
    class Nilapdromes
    {
        static void Main(string[] args)
        {
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                string niapdrome = SwitchNiapdorme(input);
                if (niapdrome != "")
                {
                    Console.WriteLine(niapdrome);
                }
            }
        }

        public static string SwitchNiapdorme(string input)
        {
            int borderStart = input.Length / 2 + 1;
            while (borderStart <= input.Length - 1)
            {
                int borderLen = input.Length - borderStart;
                if (!input.Substring(0, borderLen)
                    .Equals(input
                    .Substring(borderStart, borderLen)))
                {
                    borderStart++;
                } 
                else
                {
                    string border = input.Substring(borderStart, borderLen);
                    string core = input.Substring(borderLen , input.Length - 2 * borderLen);
                    return string.Format("{1}{0}{1}", border, core);
                }
            }
            return "";
        }
    }
}
