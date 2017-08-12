using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_Odd_Occurrences
{
    class Odd_Occurrences
    {
        static void Main(string[] args)
        {
            Dictionary<string, byte> dic = TurnToDictionary(Console.ReadLine().ToLower().Split());
            Console.WriteLine(string.Join(", ",
                dic.Where(x => x.Value % 2 == 1)
                .Select(x => x.Key)
                .ToArray()
                ));
        }

        private static Dictionary<string, byte> TurnToDictionary(string[] v)
        {
            Dictionary<string, byte> dic = new Dictionary<string, byte>();
            foreach (string str in v)
            {
                if (!dic.ContainsKey(str.ToLower())) dic[str] = 0;
                dic[str]++;
            }
            return dic;
        }
    }
}