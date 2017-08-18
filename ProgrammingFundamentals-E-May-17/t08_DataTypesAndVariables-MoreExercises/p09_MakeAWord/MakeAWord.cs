using System;
using System.IO;
using System.Text;

namespace p09_MakeAWord
{
    class MakeAWord
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < n; i++)
            {
                word.Append(Console.ReadLine());
            }
            Console.WriteLine($"The word is: {word}");
        }
    }
}
