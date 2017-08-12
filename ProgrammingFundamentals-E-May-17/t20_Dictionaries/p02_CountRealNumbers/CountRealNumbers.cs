using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_CountRealNumbers
{
    class CountRealNumbers
    {
        static void Main(string[] args)
        {
            SortedDictionary<double, byte> counts = TurnToDictionary(
                Console.ReadLine().Split()
                .Select(x => double.Parse(x))
                .ToArray());
            foreach (KeyValuePair<double, byte> num in counts)
            {
                Console.WriteLine("{0} -> {1}", num.Key, num.Value);
            } 
        }
        private static SortedDictionary<double, byte> TurnToDictionary(double[] v)
        {
            SortedDictionary<double, byte> dic = new SortedDictionary<double, byte>();
            foreach (double num in v)
            {
                if (!dic.ContainsKey(num)) dic[num] = 0;
                dic[num]++;
            }
            return dic;
        }
    }
}
