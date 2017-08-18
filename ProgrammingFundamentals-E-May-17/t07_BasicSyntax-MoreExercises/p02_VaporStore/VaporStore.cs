using System;

namespace p02_VaporStore
{
    class VaporStore
    {
        static void Main(string[] args)
        {
            double balance = double.Parse(Console.ReadLine());
            string game = Console.ReadLine();
            double price = 0.0;
            double spent = 0.0;

            while (!game.Equals("Game Time"))
            {
                switch (game)
                {
                    case "OutFall 4":
                    case "RoverWatch Origins Edition": price = 39.99; break;
                    case "CS: OG": price = 15.99; break;
                    case "Zplinter Zell": price = 19.99; break;
                    case "Honored 2": price = 59.99; break;
                    case "RoverWatch": price = 29.99; break;
                    default: price = 0;
                        break;
                }

                if (price == 0)
                {
                    Console.WriteLine("Not Found");
                    game = Console.ReadLine();
                    continue;
                }
                else if (balance < price )
                {
                    Console.WriteLine("Too Expensive");
                    game = Console.ReadLine();
                    continue;
                }
                else
                {
                    Console.WriteLine($"Bought {game}");
                    balance -= price;
                    spent += price;
                    game = Console.ReadLine();
                }
                if (balance == 0.0)
                {
                    Console.WriteLine("Out of money!");
                    return;
                }
            }
            Console.WriteLine($"Total spent: {spent:C}. Remaining: {balance:C}");
        }
    }
}
