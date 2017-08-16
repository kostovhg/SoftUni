using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_TearListInHalf
{
    class TearListInHalf
    {
        static void Main(string[] args)
        {
            List<int> inList = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            List<int> outList = new List<int>();

            for (int i = 0; i < inList.Count / 2; i++)
            {
                outList.Add(inList[i + inList.Count /2]/ 10);
                outList.Add(inList[i]);
                outList.Add(inList[i + inList.Count / 2] % 10);
            }

            Console.WriteLine(string.Join(" ", outList));
        }
    }
}
