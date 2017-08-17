using System;

namespace p03_StringRepeater
{
    class StringRepeater
    {
        static string RepeatString(string str, int count)
        {
            string repeatedString = string.Empty;
            for (int i = 0; i < count; i++)
            {
                repeatedString += str;
            }
            return repeatedString;
        }
        static void Main(string[] args)
        {
            string str = Console.ReadLine();
            int n = int.Parse(Console.ReadLine());
            Console.WriteLine(RepeatString(str, n));
        }
    }
}
