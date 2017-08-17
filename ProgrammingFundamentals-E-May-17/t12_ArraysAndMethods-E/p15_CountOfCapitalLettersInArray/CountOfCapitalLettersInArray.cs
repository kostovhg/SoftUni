using System;

namespace p15_CountOfCapitalLettersInArray
{
    class CountOfCapitalLettersInArray
    {
        static void Main(string[] args)
        {
            string[] arr = Console.ReadLine().Split();
            ushort count = 0;
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i].Length.Equals(1) && char.IsUpper(arr[i][0])) count++;
            }
            Console.WriteLine(count);
        }
    }
}
