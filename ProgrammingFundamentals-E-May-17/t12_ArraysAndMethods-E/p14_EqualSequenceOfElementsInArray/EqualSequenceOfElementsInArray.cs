using System;

namespace p14_EqualSequenceOfElementsInArray
{
    class EqualSequenceOfElementsInArray
    {
        static void Main(string[] args)
        {
            int[] arr = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            bool eq = true;
            for (int i = 0; i < arr.Length - 1; i++)
            {
                if (!arr[i].Equals(arr[i + 1]))
                {
                    eq = false;
                    break;
                }
            }
            Console.WriteLine((eq) ? "Yes" : "No");
        }
    }
}
