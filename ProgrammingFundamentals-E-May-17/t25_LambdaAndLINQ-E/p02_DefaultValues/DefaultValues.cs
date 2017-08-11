using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_DefaultValues
{
    class DefaultValues
    {
        static void Main(string[] args)
        {
            Dictionary<string, string> pairs = new Dictionary<string, string>();
            string input = Console.ReadLine();
            while (!"end".Equals(input))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string sKey = tokens[0];
                string sValue = tokens[1];
                if (!pairs.ContainsKey(sKey))
                    pairs.Add(sKey, sValue);
                else
                    pairs[sKey] = sValue;

                input = Console.ReadLine();
            }

            input = Console.ReadLine();

            Dictionary<string, string> defVals = new Dictionary<string, string>();
            foreach (KeyValuePair<string, string> pair in pairs
                .Where(x => "null".Equals(x.Value))
                )
            {
                defVals.Add(pair.Key, input);
            }

            foreach (KeyValuePair<string, string> pair in pairs
                .OrderByDescending(x => x.Value.Length)
                .Where(x => !("null".Equals(x.Value)))
                )
            {
                Console.WriteLine("{0} <-> {1}", pair.Key, pair.Value);
            }

            foreach (KeyValuePair<string, string> pair in defVals)
            {
                Console.WriteLine("{0} <-> {1}", pair.Key, pair.Value);
            }
        }
    }
}
