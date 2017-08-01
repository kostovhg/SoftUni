using System;

namespace p06_SentenceSplit
{
    class SentenceSplit
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            string delim = Console.ReadLine();
            Console.WriteLine("[" + 
                string.Join(", ", 
                input.Split(new string[] { delim }, StringSplitOptions.None)) + "]");

        }
    }
}
