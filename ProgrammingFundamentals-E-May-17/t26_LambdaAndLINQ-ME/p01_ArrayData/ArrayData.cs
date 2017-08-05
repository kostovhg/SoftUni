using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_ArrayData
{
    class ArrayData
    {
        static void Main(string[] args)
        {
            List<int> nums = Console.ReadLine().Split().Select(int.Parse).ToList();
            double average = nums.Average();
            nums = nums.Where(n => n >= average).ToList();
            string filter = Console.ReadLine();
            switch (filter)
            {
                case "Min":
                    Console.WriteLine(nums.Min());
                    break;
                case "Max":
                    Console.WriteLine(nums.Max());
                    break;
                case "All":
                    Console.WriteLine(string.Join(" ", nums.OrderBy(n => n)));
                    break;
                default:
                    break;
            }
        }
    }
}
