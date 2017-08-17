using System;

namespace p16_ArraySymmetry
{
    class ArraySymmetry
    {
        static void Main(string[] args)
        {
            string[] arr = Console.ReadLine().Split();
            bool symmetryc = true;
            for (int i = 0; i <= arr.Length /2; i++)
            {
                if (!arr[i].Equals(arr[arr.Length - 1 - i]))
                {
                    symmetryc = false;
                    break;
                }
            }
            Console.WriteLine(symmetryc ? "Yes" : "No");
        }
    }
}
