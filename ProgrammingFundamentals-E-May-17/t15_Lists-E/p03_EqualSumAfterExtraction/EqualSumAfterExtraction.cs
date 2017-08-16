using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_EqualSumAfterExtraction
{
    class EqualSumAfterExtraction
    {
        static void Main(string[] args)
        {
            List<int> firstList = Console.ReadLine().Split(' ').Select(int.Parse).ToList();
            List<int> secondList = Console.ReadLine().Split(' ').Select(int.Parse).ToList();

            for (int i = 0; i < secondList.Count; i++)
            {
                if (firstList.Contains(secondList[i]))
                {
                    secondList.RemoveAt(i);
                    i--;
                }
            }
            if(firstList.Sum() == secondList.Sum())
            {
                Console.WriteLine("Yes. Sum: {0}", firstList.Sum());
            }
            else
            {
                Console.WriteLine("No. Diff: {0}", Math.Abs(firstList.Sum() - secondList.Sum()));
            }
        }
    }
}
