using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p03_WordEncounter
{
    class WordEncounter
    {
        static void Main(string[] args)
        {
            char[] cond = Console.ReadLine().ToCharArray();
            char theChar = cond[0];
            byte count = (byte)(cond[1] - 48);
            string sentPattern = @"^[A-Z].*[.?!]$";
            string wordPattern = @"\w+";
            List<string> output = new List<string>();
            string input = "";
            while (!"end".Equals(input = Console.ReadLine()))
            {
                Regex sentRegEx = new Regex(sentPattern);
                if (sentRegEx.IsMatch(input)) // if we have only one sentence per line
                {
                    //MatchCollection words = Regex.Matches(input, wordPattern);
                    
                    foreach (string word in Regex.Matches(input, wordPattern)
                        .Cast<Match>()
                        .Select(m => m.Value)
                        .Where(x => x.Count(c => c == theChar) >= count))
                    {
                            output.Add(word);
                    }
                }
            }
            Console.WriteLine(string.Join(", ", output));
        }
    }
}
