using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_BinarySearch
{
    class BinarySearch
    {
        static void Main(string[] args)
        {
            int[] arr = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToArray();
            int element = int.Parse(Console.ReadLine());
            int[] linear = linearSearch(arr, element);
            int binary = binarySearch(arr, element);
            Console.WriteLine((linear[0] > 0 ? "Yes" : "No"));
            Console.WriteLine("Linear search made {0} iterations", linear[1]);
            Console.WriteLine("Binary search made {0} iterations", binary);
        }

        private static int binarySearch(int[] arr, int element)
        {
            int count = 0;
            Array.Sort(arr);
            int low = arr.GetLowerBound(0);
            int high = arr.GetUpperBound(0);
            while (true)
            {
                
                if (high < low)
                {
                    return count;
                }
                count++;
                int mid = low + (high - low) / 2;
                if (arr[mid] < element)
                {
                    low = mid + 1;
                }
                if (arr[mid] > element)
                {
                    high = mid - 1;
                }
                if (arr[mid] == element)
                {
                    return count;
                }
            }
        }

        private static int[] linearSearch(int[] arr, int element)
        {
            int[] output = new int[2];
            for (int i = 0; i < arr.Length; i++)
            {
                output[1]++;
                if (arr[i] == element)
                {
                    output[0] = 1;
                    break;
                }
            }
            return output;
        }
    }
}
