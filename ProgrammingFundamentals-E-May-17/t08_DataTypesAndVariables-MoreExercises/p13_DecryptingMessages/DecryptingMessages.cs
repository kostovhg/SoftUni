using System;
using System.Text;

namespace p13_DecryptingMessages
{
    class DecryptingMessages
    {
        static void Main(string[] args)
        {
            byte key = byte.Parse(Console.ReadLine());
            byte n = byte.Parse(Console.ReadLine());
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < n; i++)
            {
                output.Append((char)(Convert.ToChar(Console.ReadLine()) + key));
            }
            Console.WriteLine(output);
        }
    }
}
