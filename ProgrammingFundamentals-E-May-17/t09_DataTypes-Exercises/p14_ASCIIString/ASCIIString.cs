using System;
using System.Text;

namespace p14_ASCIIString
{
    class ASCIIString
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            char[] output = new char[n];
            // StringBuilder output = new StringBuilder();
            for (int i = 0; i < n; i++)
            {
                // output.Append((char)int.Parse(Console.ReadLine()));
                output[i] = (char)int.Parse(Console.ReadLine());
            }

            Console.WriteLine(new string(output));
        }
    }
}
