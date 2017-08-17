using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace p02_WormIpsum
{
    class WormIpsum
    {
        static void Main(string[] args)
        {
            string input;
            Regex regex = new Regex(@"^(?'sent'[A-Z][^.]*)\.{1,3}$");
            while(!"Worm Ipsum".Equals(input = Console.ReadLine()))
            {
                if (!regex.Match(input).Success) continue;
                string[] sentence = Regex.Matches(input, @"(?'words'[a-zA-Z]+)")
                    .Cast<Match>().Select(m => m.Groups["words"].Value).ToArray();
                foreach (string word in sentence)
                {
                    string changed = CheckWord(word);
                    if (changed != "") input = input.Replace(word, changed);
                }
                Console.WriteLine(input);
            }
        }
        
        private static string CheckWord(string word)
        {
            char ch = '.';
            ushort max = 0;
            for (int i = 0; i < word.Length; i++)
            {
                char cch = word[i];
                if (cch == ch) continue;
                ushort count = 1;
                for (int j = i + 1; j < word.Length; j++)
                {
                    if (cch == word[j])
                    {
                        count++;
                    }
                }
                if (count > max)
                {
                    max = count;
                    ch = cch;
                }
            }
            if (max < 2)
            {
                return "";
            }
            else
            {
                return new string(ch, word.Length);
            }
            
        }
    }
}
