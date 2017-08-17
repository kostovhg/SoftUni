using System;
using System.Linq;

namespace p09_LargestElementInArray
{
    class LargestElementInArray
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                arr[i] = int.Parse(Console.ReadLine());
            }
            Console.WriteLine(arr.Max());
        }
    }
}
