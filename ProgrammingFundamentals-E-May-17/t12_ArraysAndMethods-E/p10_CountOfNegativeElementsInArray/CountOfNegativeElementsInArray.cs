using System;
using System.Linq;

namespace p10_CountOfNegativeElementsInArray
{
    class CountOfNegativeElementsInArray
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                arr[i] = int.Parse(Console.ReadLine());
            }
            Console.WriteLine(arr.Count(i => i < 0));
        }
    }
}
