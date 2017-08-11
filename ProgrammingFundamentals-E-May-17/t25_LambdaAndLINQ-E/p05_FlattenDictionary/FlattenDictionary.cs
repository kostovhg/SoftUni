using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_FlattenDictionary
{
    class FlattenDictionary
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, string>> mainDic = new Dictionary<string, Dictionary<string, string>>();
            string input = Console.ReadLine();

            while (!"end".Equals(input))
            {
                string[] tokens = input.Split();
                if (!"flatten".Equals(tokens[0]))
                {
                    string outKey = tokens[0];
                    string inKey = tokens[1];
                    string value = tokens[2];
                    if (!mainDic.ContainsKey(outKey))
                        mainDic.Add(outKey, new Dictionary<string, string>());
                    if (!mainDic[outKey].ContainsKey(inKey))
                        mainDic[outKey].Add(inKey, value);
                    else
                        mainDic[outKey][inKey] = value;
                }
                else
                {
                    flatten(mainDic, tokens[1]);
                }

                input = Console.ReadLine(); 
            }

            ushort count = 1;
            foreach (KeyValuePair<string, Dictionary<string, string>> kvp in mainDic
                .OrderByDescending(x => x.Key.Length))
            {
                Console.WriteLine("{0}", kvp.Key);
                foreach(KeyValuePair<string, string> inPair in kvp.Value
                    .Where(x => !"".Equals(x.Value))
                    .OrderBy(x => x.Key.Length)
                    )
                {
                    Console.WriteLine("{0}. {1} - {2}", count, inPair.Key, inPair.Value);
                    count++;
                }
                foreach (KeyValuePair<string, string> inPair in kvp.Value
                    .Where(x => "".Equals(x.Value))
                    )
                {
                    Console.WriteLine("{0}. {1}", count, inPair.Key);
                    count++;
                }
                count = 1;
            }
        }

        private static void flatten(Dictionary<string, Dictionary<string, string>> mainDic, string v)
        {
            string oldKey = "";
            string oldValue = "";
            foreach (KeyValuePair<string, string> item in mainDic[v].ToArray())
            {
                oldKey = item.Key;
                oldValue = item.Value;
                mainDic[v].Remove(oldKey);
                mainDic[v].Add(oldKey + oldValue, "");
            }
        }
    }
}
