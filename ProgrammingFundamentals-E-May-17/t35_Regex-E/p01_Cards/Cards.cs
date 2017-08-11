using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace p01_Cards
{
    class Cards
    {
        static void Main(string[] args)
        {
            string pattern = @"(?<cards>(?<=^|[SHDC])(?<power>[2-9]|10|[JQKA])(?<suit>[SHDC])+)";
            MatchCollection matches = Regex.Matches(Console.ReadLine(), pattern);

            Console.WriteLine(string.Join(", ", matches
                .Cast<Match>()
                .Select(m => m.Value)
                .ToArray()));
        }
    }
}
