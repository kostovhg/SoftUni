using System;
using System.Text.RegularExpressions;

namespace p03_Regexmon
{
    class Regexmon
    {
        static void Main(string[] args)
        {
            Regex didi = new Regex(@"(?'didi'[^a-zA-Z\-]+)");
            Regex bojo = new Regex(@"(?'bojo'[a-zA-Z]+-[a-zA-Z]+)");
            string input = Console.ReadLine();
            while (didi.Match(input).Success)
            {
                string didiMatch = didi.Match(input).Value;
                Console.WriteLine(didiMatch);
                input = input.Substring(input.IndexOf(didiMatch) + didiMatch.Length);
                if (bojo.Match(input).Success)
                {
                    string bojoMatch = bojo.Match(input).Value;
                    Console.WriteLine(bojoMatch);
                    input = input.Substring(input.IndexOf(bojoMatch) + bojoMatch.Length);
                }
                else break;
            }
        }
    }
}
