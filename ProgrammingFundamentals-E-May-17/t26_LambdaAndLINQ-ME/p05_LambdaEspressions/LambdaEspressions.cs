using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_LambdaEspressions
{
    class LambdaEspressions
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, string>> data = new Dictionary<string, Dictionary<string, string>>();

            string input = Console.ReadLine();
            while (!"lambada".Equals(input))
            {
                if (!"dance".Equals(input))
                {
                    string[] inputTokens = input
                        .Split(new string[] { " => ", "." }, StringSplitOptions.RemoveEmptyEntries);

                    string selector = inputTokens[0];
                    string selectorObject = inputTokens[1];
                    string selectorProperty = inputTokens[2];

                    if (!data.ContainsKey(selector))
                        data.Add(selector, new Dictionary<string, string>());
                    data[selector][selectorObject] = selectorProperty;

                }
                else
                {
                    // From SoftUni video with Preslav Mihailov
                    /* data = data
                        .ToDictionary(selectorData => selectorData.Key,
                        selectorData => selectorData.Value
                                        .ToDictionary(
                                            selectorObjectData => selectorObjectData.Key,
                                            selectorObjectData => selectorObjectData.Key + "." + selectorObjectData.Value));
                    */

                    /*
                    foreach (var selectorData in data)
                    {
                        string selector = selectorData.Key;
                        var selectorsObjectData = selectorData.Value;
                        foreach (var selectorObjectData in selectorsObjectData)
                        {
                            string selectorObject = selectorObjectData.Key;
                            string selectorProperty = selectorObjectData.Value;
                            data[selector][selectorObject] = selectorObject + data[selector][selectorObject];
                        }
                    }
                    */
                    foreach (string outKey in data.Keys)
                    {
                        foreach (string inKey in data[outKey].Keys.ToArray())
                        {
                            data[outKey][inKey] = string.Concat(inKey, ".", data[outKey][inKey]);
                        }
                    }
                }

                input = Console.ReadLine();
            }

            foreach(KeyValuePair<string, Dictionary<string, string>> selectorData in data)
            {
                string selector = selectorData.Key;
                Dictionary<string, string> selectorObjectsData = selectorData.Value;
                foreach (KeyValuePair<string, string> selectorObjectData in selectorObjectsData)
                {
                    string selectorObject = selectorObjectData.Key;
                    string property = selectorObjectData.Value;
                    Console.WriteLine("{0} => {1}.{2}", selector, selectorObject, property);
                }

            }
        }
    }
}
