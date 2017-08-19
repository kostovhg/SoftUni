using System;


namespace p14_MagicLetter
{
    class MagicLetter
    {
        static void Main(string[] args)
        {
            char start = Console.ReadLine()[0];
            char end = Console.ReadLine()[0];
            char not = Console.ReadLine()[0];

            for (char i = start; i <= end; i++)
            {
                for (char j = start; j <= end; j++)
                {
                    for (char k = start; k <= end; k++)
                    {
                        if ((i != not && j != not) && k != not)
                        {
                            Console.Write($"{i}{j}{k} ");
                        }
                    }
                }
            }
        }
    }
}
