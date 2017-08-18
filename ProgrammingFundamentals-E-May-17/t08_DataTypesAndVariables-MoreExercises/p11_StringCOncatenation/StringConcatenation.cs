using System;
using System.Text;

namespace p11_StringCOncatenation
{
    class StringCOncatenation
    {
        static void Main(string[] args)
        {
            char delim = Convert.ToChar(Console.ReadLine());
            bool odd = Console.ReadLine().Equals("odd");
            byte n = byte.Parse(Console.ReadLine());
            StringBuilder output = new StringBuilder();
            for (int i = 1; i <= n; i++)
            {
                if ((i + Convert.ToInt32(odd)) % 2 == 0)
                {
                    output.Append(Console.ReadLine() + delim);
                }
                else
                {
                    Console.ReadLine();
                }
            }
            output.Remove(output.Length - 1, 1);
            Console.WriteLine(output);
        }
    }
}
