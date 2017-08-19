using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace p02_TrophonTheGrumpyCat
{
    class Program
    {
        static void Main(string[] args)
        {
            // Create integer array
            int[] items = Console.ReadLine().Split().Select(int.Parse).ToArray();
            // take entry point 1 or len -2
            int entryPoint = int.Parse(Console.ReadLine());
            // type of items (cheap, expensive)
            string type = Console.ReadLine();
            // type of price ratings (positive, negative, all)
            string ratings = Console.ReadLine();
            BigInteger leftSum = 0;
            BigInteger rightSum = 0;
            for (int i = 0; i < items.Length; i++)
            {
                if (i < entryPoint)
                {
                    leftSum += ToBeBreak(items[i], items[entryPoint], type, ratings);
                }
                else if (i > entryPoint)
                {
                    rightSum += ToBeBreak(items[i], items[entryPoint], type, ratings);
                }
            }

            // sum of prices rating {Left/Right} - {sum of price ratings}
            if (leftSum < rightSum)
            {
                Console.WriteLine("Right - {0}", rightSum);
            }
            else
            {
                Console.WriteLine("Left - {0}", leftSum);
            }
        }

        private static int ToBeBreak(int item, int value, string type, string ratings)
        {
            if (ratings.Equals("all"))
            {
                switch (type)
                {
                    case "cheap":
                        if (item < value) return item;
                        else return 0;
                    case "expensive":
                        if (item >= value) return item;
                        else return 0;
                }
            }
            else if (ratings.Equals("negative"))
            {
                switch (type)
                {
                    case "cheap":
                        if (item < value && item < 0) return item;
                        else return 0;
                    case "expensive":
                        if (item >= value && item < 0) return item;
                        else return 0;
                }
            }
            else
            {
                switch (type)
                {
                    case "cheap":
                        if (item < value && item >= 0) return item;
                        else return 0;
                    case "expensive":
                        if (item >= value && item >= 0) return item;
                        else return 0;
                }
            }
            return 0;
        }
    }
}
