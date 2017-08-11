using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p05_DictRefAdvanced
{
    class DictRefAdvanced
    {
        static void Main(string[] args)
        {
            Dictionary<string, List<uint>> refDic = new Dictionary<string, List<uint>>();
            string input = Console.ReadLine();
            while(!"end".Equals(input))
            {
                string[] tokens = input.Split(new string[] { " -> ", ", " }, StringSplitOptions.RemoveEmptyEntries);
                string key = tokens[0];
                uint num;
                if(uint.TryParse(tokens[1], out num))
                {
                    if (!refDic.ContainsKey(key)) refDic.Add(key, new List<uint>());
                    for (int i = 1; i < tokens.Length; i++)
                    {
                        refDic[key].Add(uint.Parse(tokens[i]));
                    }
                }
                else if(refDic.ContainsKey(tokens[1]))
                {
                    refDic.Add(key, new List<uint>());
                    foreach(uint u in refDic[tokens[1]])
                    {
                        refDic[key].Add(u);
                    }
                }
                input = Console.ReadLine();
            }

            foreach (string key in refDic.Keys)
            {
                Console.WriteLine("{0} === {1}", key, string.Join(", ", refDic[key]));
            }
        }
    }
}
