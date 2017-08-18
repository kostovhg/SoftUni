using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p02_SpyGram
{
    class SpyGram
    {
        static void Main(string[] args)
        {
            int[] key = Console.ReadLine().Select(x => int.Parse(x.ToString())).ToArray();
            string input;
            List<string[]> msgs = new List<string[]>();
            Regex regex = new Regex(@"^TO: (?'recip'[A-Z]+); MESSAGE:.+;$");
            while (!"END".Equals(input = Console.ReadLine()))
            {
                Match m = regex.Match(input);
                if (!m.Success) continue;
                msgs.Add(new string[] { m.Groups["recip"].Value, DecodeMsg(m.Value, key) });
            }
            foreach (string[] msg in msgs.OrderBy(x => x[0]))
            {
                Console.WriteLine(msg[1]);
            }
        }
        

        private static string DecodeMsg(string value, int[] key)
        {
            char[] decoded = new char[value.Length];
            for (int i = 0; i < value.Length; i++)
            {
                decoded[i] = (char)(value[i] + key[i % (key.Length)]);
            }
            return new string(decoded);
        }
    }
}
