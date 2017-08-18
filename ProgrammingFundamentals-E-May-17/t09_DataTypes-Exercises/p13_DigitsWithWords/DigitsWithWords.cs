using System;

namespace p13_DigitsWithWords
{
    class DigitsWithWords
    {
        static void Main(string[] args)
        {
            string digit = Console.ReadLine();
            byte output = 0;
            switch (digit)
            {
                case "one": output = 1; break;
                case "two": output = 2; break;
                case "three": output = 3; break;
                case "four": output = 4; break;
                case "five": output = 5; break;
                case "six": output = 6; break;
                case "seven": output = 7; break;
                case "eight": output = 8; break;
                case "nine": output = 9; break;
                default:
                    break;
            }
            Console.WriteLine(output);
        }
    }
}
