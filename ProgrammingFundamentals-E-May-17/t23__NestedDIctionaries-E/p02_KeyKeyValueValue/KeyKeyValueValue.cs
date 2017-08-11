using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_KeyKeyValueValue
{
    class KeyKeyValueValue
    {
        static void Main(string[] args)
        {
            string theKey = Console.ReadLine();
            string theValue = Console.ReadLine();
            uint n = uint.Parse(Console.ReadLine());
            Dictionary<string, List<string>> dict = new Dictionary<string, List<string>>();

            for (uint i = 0; i < n; i++)
            {
                string[] input = Console.ReadLine().Split(new string[] { " => " }, StringSplitOptions.RemoveEmptyEntries);
                string inKey = input[0];
                string[] inVal = input[1].Split(new char[] { ';' }, StringSplitOptions.RemoveEmptyEntries);
                if (inKey.Contains(theKey))
                {
                    if (!dict.ContainsKey(inKey))
                    {
                        dict.Add(inKey, new List<string>());
                    }
                    foreach (string v in inVal)
                    {
                        if (v.Contains(theValue))
                        {
                            dict[inKey].Add(v);
                        }
                    }
                }
            }
            foreach (string k in dict.Keys)
            {
                Console.WriteLine("{0}:", k);
                foreach (string v in dict[k])
                {
                    Console.WriteLine("-{0}", v);
                }
            }
        }
    }
}
