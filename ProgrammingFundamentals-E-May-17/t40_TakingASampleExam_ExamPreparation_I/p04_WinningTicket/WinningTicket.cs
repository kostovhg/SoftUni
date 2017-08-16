using System;
using System.Text.RegularExpressions;

namespace p04_WinningTicket
{
    class WinningTicket
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine()
                .Split(new char[] { ' ',',' }, StringSplitOptions.RemoveEmptyEntries);
            Regex winning = new Regex(@"([#$@^])\1{5,9}");
            foreach (string ticket in input)
            {
                if (ticket.Length !=20)
                {
                    Console.WriteLine("invalid ticket");
                    continue;
                }
                
                string left = ticket.Substring(0,10);
                string right = ticket.Substring(10, 10);
                byte count = Math.Min(CountRepeatingCharacters(left),
                    CountRepeatingCharacters(right));
                char ch = (left[5] == right[4]) ? left[5] : '\0';
                if ((ch != '@' && ch !='#' && ch != '$' && ch != '^') || count < 6)
                {
                    Console.WriteLine("ticket \"{0}\" - no match", ticket);
                }
                else if (count < 10)
                {
                    Console.WriteLine("ticket \"{0}\" - {1}{2}",
                        ticket,
                        count,
                        ch);
                }
                else
                {
                    Console.WriteLine("ticket \"{0}\" - {1}{2} Jackpot!",
                        ticket,
                        count,
                        ch);
                }
            }
        }

        private static byte CountRepeatingCharacters(string str)
        {
            byte i = 4;
            byte count = 0;
            while (i < 9 && str[i] == str [i + 1])
            {
                count++;
                i++;
            }
            i = 5;
            while (i > 0 && str[i] == str[i - 1])
            {
                count++;
                i--;
            }
            return count;

        }
    }
}
