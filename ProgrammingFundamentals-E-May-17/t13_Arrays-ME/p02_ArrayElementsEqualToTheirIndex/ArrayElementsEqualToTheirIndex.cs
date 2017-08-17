using System;

namespace p02_ArrayElementsEqualToTheirIndex
{
    class ArrayElementsEqualToTheirIndex
    {
        static void Main(string[] args)
        {
            int[] input = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            for (int i = 0; i < input.Length; i++)
            {
                if (i == input[i])
                {
                    Console.Write(i);
                    Console.Write((i == input.Length - 1) ? "" : " ");
                }
            }
            Console.WriteLine();
        }
    }
}
