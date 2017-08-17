using System;

namespace p05_CharRotation
{
    class CharRotation
    {
        static void Main(string[] args)
        {
            char[] chArr = Console.ReadLine().ToCharArray();
            int[] intArr = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            for (int i = 0; i < chArr.Length; i++)
            {
                if (intArr[i] % 2 == 0)
                {
                    chArr[i] = (char)(chArr[i] - intArr[i]);
                }
                else
                {
                    chArr[i] = (char)(chArr[i] + intArr[i]);
                }
            }
            Console.WriteLine(new string(chArr));
        }
    }
}
