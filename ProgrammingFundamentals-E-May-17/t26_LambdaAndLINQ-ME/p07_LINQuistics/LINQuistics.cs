using System;
using System.Collections.Generic;
using System.Linq;

namespace p07_LINQuistics
{
    class LINQuistics
    {
        static void Main(string[] args)
        {
            Dictionary<string, List<string>> data = new Dictionary<string, List<string>>();

            string input;
            while (!"exit".Equals(input = Console.ReadLine()))
            {
                string[] inputTokens = input.Split(new char[] { '.'}, 2);
                if (inputTokens.Length == 1)
                {
                    int num;
                    bool isNum = int.TryParse(input, out num);
                    if (isNum)
                    {
                        if (data.Count > 0)
                        {
                            printOnlyFirst(data.Values
                                .OrderByDescending(x => x.Count())
                                .Take(1)
                                .ToList()[0]
                                , num);
                        }
                    }
                    else if (data.ContainsKey(input))
                    {
                        printTheMethods(data.Values
                            .OrderByDescending(x => x.Count())
                            .Take(1)
                            .ToList()[0]
                            , input);
                    }
                }
                else
                {
                    string collectionName = inputTokens[0];
                    List<string> methods = inputTokens[1].Split(new string[] { ".", "()"}, StringSplitOptions.RemoveEmptyEntries).ToList();
                    if (!data.ContainsKey(collectionName))
                    {
                        data.Add(collectionName, new List<string>());
                    }
                    data[collectionName].AddRange(methods);
                    data[collectionName] = data[collectionName].Distinct().ToList();
                }
            }

            input = Console.ReadLine();

            string[] tokens = input.Split(new char[] { ' '}, 2);
            string method = tokens[0];
            string selection = tokens[1];

            foreach (KeyValuePair<string, List<string>> coll in data
                .Where(x => x.Value.Contains(method))
                .OrderByDescending(x => x.Value.Count)
                .ThenByDescending(x => x.Value.Min(l => l.Length)))
            {
                Console.WriteLine("{0}", coll.Key);
                if ("all".Equals(selection))
                {
                    foreach (string met in coll.Value
                        .OrderByDescending(l => l.Length))
                    {
                        Console.WriteLine("* {0}", met);
                    }
                }
            }
        }

        private static void printOnlyFirst(List<string> list, int v)
        {
            foreach (string method in list.OrderBy(x => x.Length).Take(v))
            {
                Console.WriteLine("* {0}", method);
            }
        }

        private static void printTheMethods(List<string> data, string input)
        {
            foreach (string method in data
                .OrderByDescending(x => x.Length)
                .ThenByDescending(x => x.ToCharArray().Distinct().Count()))
            {
                Console.WriteLine("* {0}", method);
            }
        }
    }
}
