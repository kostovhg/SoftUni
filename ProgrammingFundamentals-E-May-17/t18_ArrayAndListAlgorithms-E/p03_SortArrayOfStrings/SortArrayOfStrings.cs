using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03_SortArrayOfStrings
{
    class SortArrayOfStrings
    {
        static void Main(string[] args)
        {
            string[] iniArray = Console.ReadLine().Split();
            for (int i = 1; i < iniArray.Length; i++)
            {
                int j = i;
                while (j > 0 && iniArray[j - 1].CompareTo(iniArray[j]) > 0)
                {
                    Swap(iniArray, j, j - 1);
                    j--;
                }
            }
            Console.WriteLine(string.Join(" ", iniArray));
        }

        private static void Swap(string[] iniArray, int j, int v)
        {
            string tmp = iniArray[v];
            iniArray[v] = iniArray[j];
            iniArray[j] = tmp;
            return;
        }
    }
}
