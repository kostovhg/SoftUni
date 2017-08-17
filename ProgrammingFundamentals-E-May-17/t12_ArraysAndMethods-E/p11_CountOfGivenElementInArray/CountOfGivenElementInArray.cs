using System;
using System.Linq;

namespace p11_CountOfGivenElementInArray
{
    class CountOfGivenElementInArray
    {
        static void Main(string[] args)
        {
            int[] arr = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            int match = int.Parse(Console.ReadLine());
            
            Console.WriteLine(arr.Count(a => a == match));
        }
    }
}
