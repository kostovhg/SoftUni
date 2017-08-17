using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace p04_CubicMessages
{
    class Program
    {
        static void Main(string[] args)
        {
            Regex valid = new Regex(@"^(?'start'[0-9]+)(?'code'[a-zA-Z]+)(?'end'[^a-zA-Z]+)$");
            string input;
            while (!"Over!".Equals(input = Console.ReadLine()))
            {
                Match m = valid.Match(input);
                if (!m.Success)
                {
                    Console.ReadLine();
                    continue;
                }
                string code = m.Groups["code"].Value;
                if (code.Length != int.Parse(Console.ReadLine())) continue;
                List<char> verif = new List<char>();
                Regex digits = new Regex(@"[0-9]");
                foreach (string index in digits.Matches(input)
                    .Cast<Match>()
                    .Select(mm => mm.Value).ToList())
                {
                    int i = int.Parse(index);
                    if (i < code.Length)
                    {
                        verif.Add(code[i]);
                    }
                    else
                    {
                        verif.Add(' ');
                    }
                }

                Console.WriteLine($"{code} == {string.Concat(verif)}");
            }
        }
    }
}
