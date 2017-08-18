using System;

namespace p16_TrickyStrings
{
    class TrickyStrings
    {
        static void Main(string[] args)
        {
            string del = Console.ReadLine();
            int n = int.Parse(Console.ReadLine());
            string[] output = new string[n];
            for (int i = 0; i < n; i++)
            {
                output[i] = Console.ReadLine();
            }
            Console.WriteLine(string.Join(del, output));
        }
    }
}
