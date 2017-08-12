using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_IncreasingCrisis
    /*
     * For 100 points I did used the following:
     * // copied from https://github.com/Bullsized/Assignments-Fundamentals-Extended/blob/master/09%20Array%20and%20List%20Algorithms/2017-04-28/03%20Increasing%20Crisis/03%20Increasing%20Crisis.cs
        Whit Author https://github.com/Bullsized (Ivan Stefanov)

    current version of this program manage to gather 80 points */
{
    class IncreasingCrisis
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            List<int> list = new List<int>();
            int startIndex = 0;
            for (int i = 0; i < n; i++)
            {
                List<int> seq = Console
                    .ReadLine()
                    .Split()
                    .Select(int.Parse)
                    .ToList();
                // Check which is the possition of right most element lower than first from the sequence
                startIndex = FindInsertionPoint(list, seq[0]);
                
                //  insert seq from 'start' till elements are increasing
                for (int seqIndex = 1; seqIndex < seq.Count ; seqIndex++)
                {
                    int listIndex = startIndex + seqIndex;
                    list.Insert(listIndex, seq[seqIndex]);
                    if (list[listIndex - 1] > list[listIndex])
                    {
                        list.RemoveRange(listIndex,list.Count - listIndex);
                        break;
                    }
                }
            }
            Console.WriteLine(string.Join(" ", list));
        }

        private static int FindInsertionPoint(List<int> list, int v)
        {
            for (int j = 0; j <= list.Count; j++)
            {
                if (j == list.Count)
                {
                    list.Add(v);
                    return list.Count -1;
                }
                if (list[j] > v)
                {
                    list.Insert(j, v);
                    return j;
                }
            }
            return 0;
        }
    }
}
