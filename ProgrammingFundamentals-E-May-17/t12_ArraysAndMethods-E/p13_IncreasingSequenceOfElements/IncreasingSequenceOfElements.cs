using System;

namespace p13_IncreasingSequenceOfElements
{
    class IncreasingSequenceOfElements
    {
        static void Main(string[] args)
        {
            int[] arr = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            bool incr = true;
            for (int i = 0; i < arr.Length - 1; i++)
            {
                if (arr[i] > arr [i +1])
                {
                    incr = false;
                    break;
                }
            }
            Console.WriteLine((incr) ? "Yes" : "No");
        }
    }
}
