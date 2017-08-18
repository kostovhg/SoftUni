using System;
using System.Text;

namespace p08_SMSTyping
{
    class SMSTyping
    {
        static void Main(string[] args)
        {
            int charNum = int.Parse(Console.ReadLine());
            int len = 0;
            int num = 0;
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < charNum; i++)
            {
                string input = Console.ReadLine();
                if (input.Equals("0"))
                {
                    output.Append(' ');
                    continue;
                }
                len = input.Length;
                num = input[0] - 48;
                int offset = (num - 2) * 3;
                if (num > 7) offset++;
                char letter = (char)(97 + offset + len - 1);
                output.Append(letter);
            }
            Console.WriteLine(output);
        }
    }
}
