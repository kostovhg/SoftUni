using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_UnunionList
{
    class UnunionList
    {
        static void Main(string[] args)
        {
            List<int> primalList = readIntegerList();
            int iSecuences = int.Parse(Console.ReadLine());
            for (int s = 0; s < iSecuences; s++)
            {
                List<int> sequence = readIntegerList();
                for (int i = 0; i < sequence.Count; i++)
                {
                    if (!primalList.Contains(sequence[i]))
                    {
                        primalList.Add(sequence[i]);
                        sequence.RemoveAt(i);
                        i--;
                    }
                }
                primalList.RemoveAll(x => sequence.Contains(x));
            }
            primalList.Sort();
            Console.WriteLine(string.Join(" ", primalList));
        }

        private static List<int> readIntegerList()
        {
            List<int> list = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            return list;
        }
    }
}
