using System;
using System.Linq;

namespace p12_CountOccurrencesOfLargerNumbersInArray
{
    class CountOccurrencesOfLargerNumbersInArray
    {
        static void Main(string[] args)
        {
            double[] arr = Array.ConvertAll(Console.ReadLine().Split(), double.Parse);
            double p = double.Parse(Console.ReadLine());

            Console.WriteLine(arr.Count(a => a > p));
        }
    }
}
