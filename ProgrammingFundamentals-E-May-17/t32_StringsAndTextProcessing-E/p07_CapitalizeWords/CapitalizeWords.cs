using System;

namespace p07_CapitalizeWords
{
    class CapitalizeWords
    {
        static void Main(string[] args)
        {
            string input;
            // string[] sentDelim = new string[] { "! ", "? ", ". " };
            while (!"end".Equals(input = Console.ReadLine()))
            {
                //string[] sentences = input
                //    .Split(new string[] { "! ", "? ", ". " },
                //    StringSplitOptions.RemoveEmptyEntries);
                string[] words = input.Split(GetPunctuationArray(),
                StringSplitOptions.RemoveEmptyEntries);
                for (int i = 0; i < words.Length; i++)
                {
                    words[i] = UppercaseFirst(words[i]);
                }
                Console.WriteLine(string.Join(", ", words));
            }
        }

        static string UppercaseFirst(string s)
        {
            if (string.IsNullOrEmpty(s))
            {
                return string.Empty;
            }
            char[] a = s.ToCharArray();
            a[0] = char.ToUpper(a[0]);
            for (int i = 1; i < a.Length; i++)
            {
                a[i] = char.ToLower(a[i]);
            }
            return new string(a);
        }

        static char[] GetPunctuationArray()
        {
            return new char[] { ' ', ',', '.', '!', '?', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', ';', ':' };
        }
    }
}
