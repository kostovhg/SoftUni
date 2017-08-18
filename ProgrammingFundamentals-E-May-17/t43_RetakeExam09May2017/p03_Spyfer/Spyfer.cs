using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_Spyfer
{
    class Spyfer
    {
        static void Main(string[] args)
        {
            List<int> nums = Console.ReadLine()
                .Split().Select(int.Parse)
                .ToList();
            for (int i = 0; i < nums.Count; )
            {
                int borders = ((i == 0) ? 0 : nums[i - 1]) + ((i == nums.Count - 1) ? 0 : nums[i + 1]);
                if (nums[i] == borders)
                {
                    if (i < nums.Count - 1) nums.RemoveAt(i + 1);
                    if (i > 0 ) nums.RemoveAt(i - 1);
                    i = 0;
                }
                else i++;
            }
            Console.WriteLine(string.Join(" ", nums));
        }
    }
}
